package Cryptography.SHAActions;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;

import Titles.ToolTitles.CryptographyTitles.ActionTitles.SHAHashStringTitle;
import Utilities.UserInput.ChooseSHAAlgorithm;
import Utilities.UserInput.ChooseSalt;
import Utilities.UserInput.ChooseStringInput;

public class SHAHashString {

	ChooseStringInput chooseStringInput = new ChooseStringInput();
	SHAHashStringTitle shaHashStringTitle = new SHAHashStringTitle();
	ChooseSHAAlgorithm chooseSHAAlgorithm = new ChooseSHAAlgorithm();
	ChooseSalt chooseSalt = new ChooseSalt();

    private static final Charset UTF_8 = StandardCharsets.UTF_8;

	Scanner scanner = new Scanner(System.in);

	public void hashString() {

		String actionChoice;
		String hashString = "";
		String hashingAlgorithm = "SHA-256";
		int salt = 0;

		boolean run = true;

		while(run) {
			shaHashStringTitle.printTitle(hashString, hashingAlgorithm, salt);
			actionChoice = scanner.nextLine();
			if(actionChoice.toLowerCase().trim().equals("1")) {
				hashString = chooseStringInput.getStringInput();
			} else if(actionChoice.toLowerCase().trim().equals("2")) {
				hashingAlgorithm = chooseSHAAlgorithm.chooseAlgorithm();
			} else if(actionChoice.toLowerCase().trim().equals("3")) {
				salt = chooseSalt.getSalt();
			} else if(actionChoice.toLowerCase().trim().equals("4") && hashString != "" && hashString != "exit" && hashingAlgorithm != "") {
				hash(hashString, hashingAlgorithm, salt);
				run = false;
			} else if(actionChoice.toLowerCase().trim().equals("5")) {
				run = false;
			} else {
				System.out.println("Command was not recognized! Please type a valid command number");
			}
			if(hashString.equals("exit")) {
				run = false;
			}
		}
	}

    public static byte[] digest(byte[] input, String algorithm) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
        byte[] result = md.digest(input);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

	private void hash(String hashString, String hashingAlgorithm, int salt) {
		if(salt == 0) {
	        System.out.println("Input string: " + hashString);
	        System.out.println("Input length:" + hashString.length());
	        System.out.println("");

	        byte[] shaInBytes = digest(hashString.getBytes(UTF_8), hashingAlgorithm);
	        System.out.println(hashingAlgorithm + " (hex): " + bytesToHex(shaInBytes));
	        System.out.println(hashingAlgorithm + " (length): " + shaInBytes.length);
		} else {
			byte[] saltByte = getRandomNonce(16);
			byte[] hashStringByte = hashString.getBytes(StandardCharsets.UTF_8);

			byte[] input = ByteBuffer.allocate(saltByte.length + hashStringByte.length)
			          .put(saltByte)
			          .put(hashStringByte)
			          .array();

			String saltString = new String(saltByte);
			String inputString = new String(input);

	        System.out.println("Input text: " + hashString);
	        System.out.println("Input length:" + hashString.length());
	        System.out.println("");
	        System.out.println("Salt: " + saltString);
	        System.out.println("Salt length: " + saltString.length());
	        System.out.println("");
	        System.out.println("Full hashed text: " + inputString);
	        System.out.println("Full hashed text length: " + inputString.length());
	        System.out.println("");
	        System.out.println(hashingAlgorithm + " (hex): " + bytesToHex(digest(input, hashingAlgorithm)));
	        System.out.println(hashingAlgorithm + " (length): " + digest(input, hashingAlgorithm).length);
		}

	}
}
