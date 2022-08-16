package FIleSystemTools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import Utilities.ChooseFiles;
import Utilities.FindFiles;
import Utilities.ZipOrUnzip;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class UnzipAllZipFilesInFolder {
	
	SourceFolder sourceFolder = new SourceFolder();
	ZipOrUnzip zipOrUnzip = new ZipOrUnzip();
	ChooseFiles chooseFiles = new ChooseFiles();
	FindFiles findFiles = new FindFiles();
	
	public void choose() throws IOException {
		String srcFolder = sourceFolder.getSourceFolder();
		if(!srcFolder.equals("exit")) {
			String zipChoose = zipOrUnzip.zipUnzip();
			if(zipChoose.equals("zip")) {
				String[] files = chooseFiles.getFiles(srcFolder, zipChoose);
				if(files.length == 1) {
			    	int i = files[0].lastIndexOf('\\');
			    	String[] folderName = {files[0].substring(0, i), files[0].substring(i)};
					String zipPath = files[0] + "\\" + folderName[1] + ".zip";
					pack(files[0], zipPath);
				}else {
					zipChosenFiles(files, srcFolder);
				}

			} else if(zipChoose.equals("exit")) {
			} else {
				String[] files = chooseFiles.getFiles(srcFolder, zipChoose);
				unzipAllFiles(files, srcFolder);
			}
		}
	}
	
	public void zipChosenFiles(String[] files, String sourceFolder) throws IOException {
        List<String> srcFiles = Arrays.asList(files);
        FileOutputStream fos = new FileOutputStream(sourceFolder + "\\result.zip");
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        for (String srcFile : srcFiles) {
        	try {
                File fileToZip = new File(srcFile);
                FileInputStream fis = new FileInputStream(fileToZip);
                ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                try {
                    zipOut.putNextEntry(zipEntry);               	
                }catch(ZipException e) {
                	
                }


                byte[] bytes = new byte[1024];
                int length;
                while((length = fis.read(bytes)) >= 0) {
                    zipOut.write(bytes, 0, length);
                }
                fis.close();
        	}catch(FileNotFoundException e) {
        		
        	}

        }
        zipOut.close();
        fos.close();
	}
	
	@SuppressWarnings("resource")
	public void unzipAllFiles(String[] files, String srcFolder) throws IOException {
		List<String> zipFiles = new ArrayList<String>();
		for(String file : files) {
			if (file.endsWith("zip")) {
				zipFiles.add(file);
			}
		}
		for(String zipFile : zipFiles) {
	        File destDir = new File(srcFolder);
	        byte[] buffer = new byte[1024];
	        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
	        ZipEntry zipEntry = zis.getNextEntry();
	        while (zipEntry != null) {
	            File newFile = newFile(destDir, zipEntry);
	            if (zipEntry.isDirectory()) {
	                if (!newFile.isDirectory() && !newFile.mkdirs()) {
	                    throw new IOException("Failed to create directory " + newFile);
	                }
	            } else {
	                File parent = newFile.getParentFile();
	                if (!parent.isDirectory() && !parent.mkdirs()) {
	                    throw new IOException("Failed to create directory " + parent);
	                }
	                FileOutputStream fos = new FileOutputStream(newFile);
	                int len;
	                while ((len = zis.read(buffer)) > 0) {
	                    fos.write(buffer, 0, len);
	                }
	                fos.close();
	            }
	        zipEntry = zis.getNextEntry();
	        }
	        zis.closeEntry();
	        zis.close();

		}
	}
	
	public static void pack(String sourceDirPath, String zipFilePath) throws IOException {
	    Path p = Files.createFile(Paths.get(zipFilePath));
	    Path pp = Paths.get(sourceDirPath);
	    try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p));
	        Stream<Path> paths = Files.walk(pp)) {
	        paths
	          .filter(path -> !Files.isDirectory(path))
	          .forEach(path -> {
	              ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
	              try {
	                  zs.putNextEntry(zipEntry);
	                  Files.copy(path, zs);
	                  zs.closeEntry();
	            } catch (IOException e) {
	                System.err.println(e);
	            }
	          });
	    }
	}
	
	public static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
	    File destFile = new File(destinationDir, zipEntry.getName());

	    String destDirPath = destinationDir.getCanonicalPath();
	    String destFilePath = destFile.getCanonicalPath();

	    if (!destFilePath.startsWith(destDirPath + File.separator)) {
	        throw new IOException("Entry is outside of the target folder: " + zipEntry.getName());
	    }

	    return destFile;
	}
	
}
