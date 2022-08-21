package Cryptography.PGP.PGPActions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;

import org.bouncycastle.openpgp.PGPException;

import Cryptography.PGP.PgpHelper;

public class PGPDecrypt {

	private boolean isArmored = false;
	private boolean integrityCheck = true;
	
	public void decrypt(String privateKeyLocation, String encryptedFileLocation, String plainTextFile, String password) throws Exception{
		String decPlainTextFile = plainTextFile + "\\decrypted.dat";
		FileInputStream cipheredFileIs = new FileInputStream(encryptedFileLocation);
		FileInputStream privKeyIn = new FileInputStream(privateKeyLocation);
		FileOutputStream plainTextFileIs = new FileOutputStream(decPlainTextFile);
		PgpHelper.getInstance().decryptFile(cipheredFileIs, plainTextFileIs, privKeyIn, password.toCharArray());
		cipheredFileIs.close();
		plainTextFileIs.close();
		privKeyIn.close();
	}
	
}
