package Cryptography.PGP.PGPActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import Cryptography.PGP.PgpHelper;

public class PGPCreateSignature {

	public void signAction(String signatureFolder, String plainTextFile, String privKeyFile, String pubKeyFile, String passwd) throws Exception{
		String signatureFile = signatureFolder + "\\signature.txt";
		FileInputStream privKeyIn = new FileInputStream(privKeyFile);
		FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
		FileInputStream plainTextInput = new FileInputStream(plainTextFile);
		FileOutputStream signatureOut = new FileOutputStream(signatureFile);

		byte[] bIn = PgpHelper.getInstance().inputStreamToByteArray(plainTextInput);
		byte[] sig = PgpHelper.getInstance().createSignature(plainTextFile, privKeyIn, signatureOut, passwd.toCharArray(), true);
		File outputFile = new File(signatureFile);
		try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
		    outputStream.write(sig);
		}
	}

}
