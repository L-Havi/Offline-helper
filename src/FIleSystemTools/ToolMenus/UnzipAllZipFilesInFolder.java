package FIleSystemTools.ToolMenus;

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
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import Titles.ToolTitles.FileSystemTitles.UnzipAllZipFilesInFolderTitle;
import Utilities.UserInput.ChooseFiles;
import Utilities.UserInput.ZipOrUnzip;
import Utilities.UserOutput.FindFiles;
import WindowsResources.IncludeSubfolders;
import WindowsResources.SourceFolder;

public class UnzipAllZipFilesInFolder {
	
	SourceFolder sourceFolder = new SourceFolder();
	ZipOrUnzip zipOrUnzip = new ZipOrUnzip();
	ChooseFiles chooseFiles = new ChooseFiles();
	FindFiles findFiles = new FindFiles();
	IncludeSubfolders includeSubfolders = new IncludeSubfolders();
	int subfolders;
	UnzipAllZipFilesInFolderTitle unzipAllZipFilesInFolderTitle = new UnzipAllZipFilesInFolderTitle();
	
	public void choose() throws IOException {
		
		String actionChoice;
		String[] files = {"*"};
		String sourceString = "";
		String zipChoose = "";
		List<String> realFiles = new ArrayList<String>();
		
		Scanner scanner = new Scanner(System.in);
		
		boolean run = true;
		
		while(run) {
			unzipAllZipFilesInFolderTitle.printTitle(sourceString, zipChoose, files);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				sourceString = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				zipChoose = zipOrUnzip.zipUnzip();
			} else if(actionChoice.toLowerCase().trim().equals("3") && sourceString != "" && sourceString != "exit" && zipChoose != "" && zipChoose != "exit") {
				files = chooseFiles.getFiles(sourceString, zipChoose);
			} else if(actionChoice.toLowerCase().trim().equals("4") && sourceString != "" && sourceString != "exit" && zipChoose != "" && zipChoose != "exit") {
				if(files.length == 1 && files[0].trim().equals("*")) {

					if(zipChoose.equals("unzip")) {
						subfolders = includeSubfolders.includeSubfolders();
						String [] extensions = {"zip"};
						try {
							realFiles = findFiles.findFiles(Paths.get(sourceString), extensions, subfolders);
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else if(zipChoose.equals("zip")) {
						realFiles.add(sourceString);
					}
			        if (realFiles.size() > 0) {
			        	files = new String[realFiles.size()];
			        	realFiles.toArray(files);
			        }
				}
				if(zipChoose.toLowerCase().trim().equals("zip")) {
					if(files.length == 1) {
				    	int i = files[0].lastIndexOf('\\');
				    	String[] folderName = {files[0].substring(0, i), files[0].substring(i)};
						String zipPath = files[0] + "\\" + folderName[1] + ".zip";
						pack(files[0], zipPath);
					}else {
						zipChosenFiles(files, sourceString);
					}
				} else if (zipChoose.toLowerCase().trim().equals("unzip")){
					unzipAllFiles(files, sourceString);
				}
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if((files.length == 1 && files[0].equals("exit")) || sourceString == "exit" || zipChoose == "exit") {
				run = false;
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
