package WindowsResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RemoveAllDuplicateFiles {

	Scanner scanner = new Scanner(System.in);

    private static MessageDigest messageDigest;
    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("cannot initialize SHA-512 hash function", e);
        }
    }

	public static void removeAllDuplicateFiles(Map<String, List<String>> filesList, File directory) {
        for (File dirChild : directory.listFiles()) {
            // Iterate all file sub directories recursively
            if (dirChild.isDirectory()) {
                removeAllDuplicateFiles(filesList, dirChild);
            } else {
                try {
                	// Read file as bytes
                    FileInputStream fileInput = new FileInputStream(dirChild);
                    byte fileData[] = new byte[(int) dirChild.length()];
                    fileInput.read(fileData);
                    fileInput.close();
                    // Create unique hash for current file
                    String uniqueFileHash = new BigInteger(1, messageDigest.digest(fileData)).toString(16);
                    List<String> identicalList = filesList.get(uniqueFileHash);
                    if (identicalList == null) {
                        identicalList = new LinkedList<>();
                    }
                    // Add path to list
                    identicalList.add(dirChild.getAbsolutePath());
                    // push updated list to Hash table
                    filesList.put(uniqueFileHash, identicalList);
                } catch (IOException e) {
                    throw new RuntimeException("cannot read file " + dirChild.getAbsolutePath(), e);
                }
            }
        }
	}
}
