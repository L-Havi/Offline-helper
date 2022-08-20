package Cryptography.PGP.PGPActions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchProviderException;

import org.bouncycastle.openpgp.PGPException;

import Cryptography.PGP.PgpHelper;

public class PGPEncrypt {

	private boolean isArmored = false;
	private boolean integrityCheck = true;
	
	public void encrypt(String publicKeyLocation, String encryptedFileLocation, String plainTextFile) throws NoSuchProviderException, IOException, PGPException{
		String encryptedFile = encryptedFileLocation + "\\encrypted.dat";
		FileInputStream pubKeyIs = new FileInputStream(publicKeyLocation);
		FileOutputStream cipheredFileIs = new FileOutputStream(encryptedFile);
		PgpHelper.getInstance().encryptFile(cipheredFileIs, plainTextFile, PgpHelper.getInstance().readPublicKey(pubKeyIs), isArmored, integrityCheck);
		cipheredFileIs.close();
		pubKeyIs.close();
	}
	
}
