package Utilities.UserOutput;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Utilities.FileExtension;
import Utilities.MapSubFolders;

public class FindFiles {

	MapSubFolders mapSubFolders = new MapSubFolders();
	FileExtension fileExtensions = new FileExtension();

	public String[][] findFilesByExtension(Path path, String fileExtension) throws IOException{
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException("Path must be a directory!");
        }

        List<String> result;

        try (Stream<Path> walk = Files.walk(path)) {
            result = walk
                    .filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase())
                    .filter(f -> f.endsWith(fileExtension))
                    .collect(Collectors.toList());
        }

        String[] filesInOriginalFolder = new String[result.size()];
        filesInOriginalFolder = result.toArray(filesInOriginalFolder);

        String[][] allPaths = new String[filesInOriginalFolder.length][2];

        List<String> results = new ArrayList<>();

        for(String file : result) {
        	int i = file.lastIndexOf("\\");
        	String[] a =  {file.substring(0, i), file.substring(i)};
        	String newPath = a[0] + "\\" +  fileExtension.toUpperCase() + "\\" + a[1];
        	if(!results.contains(newPath)) {
        		results.add(newPath);
        	}
        }

        String[] filesInNewFolder = new String[results.size()];
        filesInNewFolder = results.toArray(filesInNewFolder);

        for(int j = 0; j<filesInOriginalFolder.length; j++) {
        	allPaths[j][0] = filesInOriginalFolder[j];
        	allPaths[j][1] = filesInNewFolder[j];
        }

        return allPaths;
	}

	public List<String> findFiles(Path path, String[] extensions, int includeSubfolder)
	        throws IOException {

			List<String> results = new ArrayList<>();

	        if (!Files.isDirectory(path)) {
	            throw new IllegalArgumentException("Path must be a directory!");
	        }

	        if(extensions.length == 1 && extensions[0].equals("*")) {
	        	String srcString = path.toString();
	        	List<String> extensionList = fileExtensions.getUniqueFileExtensions(srcString);
	        	extensions = extensionList.toArray(extensions);
	        }

	        if(includeSubfolder == 1) {
	        	String pathString = path.toString();
	        	File tempFile = new File(pathString);
	        	List<File> subFolders = mapSubFolders.findAllSubdirs(tempFile);
	        	if(subFolders.size() > 0) {
	        		for (File subfolder : subFolders) {
	        			String[] pathnames = subfolder.list();
	        			for(String pathname : pathnames) {
	        				String tempname = subfolder.getCanonicalPath() + "\\" + pathname;
	        				File fi = new File(tempname);
	        				if(fi.isFile()) {
	        					results.add(tempname);
	        				}
	        			}
	        		}
	        	}
	        }
		    for(String extension : extensions){
		        List<String> result = new ArrayList<>();
				try (Stream<Path> walk = Files.walk(path)) {
					result = walk
							.filter(p -> !Files.isDirectory(p))
				            .map(p -> p.toString().toLowerCase())
				            .filter(f -> f.endsWith(extension))
				            .collect(Collectors.toList());
				}
				for(String row : result) {
					System.out.println(row);
					results.add(row);
				}
		    }

	        return results;
	    }

}
