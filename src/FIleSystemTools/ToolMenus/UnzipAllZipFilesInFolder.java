package FIleSystemTools.ToolMenus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.IOUtils;

import Titles.ToolTitles.FileSystemTitles.UnzipAllZipFilesInFolderTitle;
import Utilities.UserInput.ChooseFiles;
import Utilities.UserInput.Name;
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
	Name name = new Name();

	public void choose() throws IOException {

		String actionChoice;
		String sourceString = "";
		String destinationFolder = "";
		String zipFileName = "zipFile";

		Scanner scanner = new Scanner(System.in);

		boolean run = true;

		while(run) {
			unzipAllZipFilesInFolderTitle.printTitle(sourceString, destinationFolder, zipFileName);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				sourceString = sourceFolder.getSourceFileOrFolder();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				destinationFolder = sourceFolder.getSourceFolder();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				zipFileName = name.getName();
			} else if(actionChoice.toLowerCase().trim().equals("4") && sourceString != "" && sourceString != "exit" && destinationFolder != "" && destinationFolder != "exit") {
				if(sourceString.contains(".zip")) {
					extractZip(sourceString, destinationFolder);
					run = false;
				}else {
					String zipDestination = destinationFolder + "\\" + zipFileName + ".zip";
					createZipFile(sourceString, zipDestination);
					run = false;
				}
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(sourceString == "exit") {
				run = false;
			}
		}
	}

    public void createZipFile(String fileOrDirectoryToZip, String zipFileName) {
        BufferedOutputStream bufferedOutputStream = null;
        ZipArchiveOutputStream zipArchiveOutputStream = null;
        OutputStream outputStream = null;
        try {
            Path zipFilePath = Paths.get(zipFileName);
            outputStream = Files.newOutputStream(zipFilePath);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            zipArchiveOutputStream = new ZipArchiveOutputStream(bufferedOutputStream);
            File fileToZip = new File(fileOrDirectoryToZip);

            addFileToZipStream(zipArchiveOutputStream, fileToZip, "");

            zipArchiveOutputStream.close();
            bufferedOutputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addFileToZipStream(ZipArchiveOutputStream zipArchiveOutputStream, File fileToZip, String base) throws IOException {
        String entryName = base + fileToZip.getName();
        ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(fileToZip, entryName);
        zipArchiveOutputStream.putArchiveEntry(zipArchiveEntry);
        if(fileToZip.isFile()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(fileToZip);
                IOUtils.copy(fileInputStream, zipArchiveOutputStream);
                zipArchiveOutputStream.closeArchiveEntry();
            } finally {
                IOUtils.closeQuietly(fileInputStream);
            }
        } else {
            zipArchiveOutputStream.closeArchiveEntry();
            File[] files = fileToZip.listFiles();
            if(files != null) {
                for (File file: files) {
                    addFileToZipStream(zipArchiveOutputStream, file, entryName + "/");
                }
            }
        }
    }

    public void extractZip(String zipFilePath, String extractDirectory) {
        InputStream inputStream = null;
        try {
            Path filePath = Paths.get(zipFilePath);
            inputStream = Files.newInputStream(filePath);
            ArchiveStreamFactory archiveStreamFactory = new ArchiveStreamFactory();
            ArchiveInputStream archiveInputStream = archiveStreamFactory.createArchiveInputStream(ArchiveStreamFactory.ZIP, inputStream);
            ArchiveEntry archiveEntry = null;
            while((archiveEntry = archiveInputStream.getNextEntry()) != null) {
                Path path = Paths.get(extractDirectory, archiveEntry.getName());
                File file = path.toFile();
                if(archiveEntry.isDirectory()) {
                    if(!file.isDirectory()) {
                        file.mkdirs();
                    }
                } else {
                    File parent = file.getParentFile();
                    if(!parent.isDirectory()) {
                        parent.mkdirs();
                    }
                    try (OutputStream outputStream = Files.newOutputStream(path)) {
                        IOUtils.copy(archiveInputStream, outputStream);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArchiveException e) {
            e.printStackTrace();
        }
    }

}
