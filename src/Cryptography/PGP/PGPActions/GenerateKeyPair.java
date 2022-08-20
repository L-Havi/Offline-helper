package Cryptography.PGP.PGPActions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.SignatureException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openpgp.PGPException;

import Cryptography.PGP.RSAKeyPairGenerator;

public class GenerateKeyPair {
	
	private boolean isArmored = false;

	public void genKeyPair(String id, String passwd, String sourceFolder) throws InvalidKeyException, NoSuchProviderException, SignatureException, IOException, PGPException, NoSuchAlgorithmException {

		String pubKeyFile = sourceFolder + "\\public_key.dat";
		String privKeyFile = sourceFolder + "\\secret_key.dat";
		
		RSAKeyPairGenerator rkpg = new RSAKeyPairGenerator();

		Security.addProvider(new BouncyCastleProvider());

		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA", "BC");

		kpg.initialize(1024);

		KeyPair kp = kpg.generateKeyPair();

		FileOutputStream out1 = new FileOutputStream(privKeyFile);
		FileOutputStream out2 = new FileOutputStream(pubKeyFile);

		rkpg.exportKeyPair(out1, out2, kp.getPublic(), kp.getPrivate(), id, passwd.toCharArray(), isArmored);
		
	}
}
