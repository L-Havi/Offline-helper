package Cryptography.PGP.PGPActions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import Cryptography.PGP.PgpHelper;

public class PGPVerifySignature {

	public void verifyAction(String pubKeyFile, String plainTextFile, String signatureFile) throws Exception{
		File signature = new File(signatureFile);
		byte[] sig = method(signature);
		FileInputStream pubKeyIs = new FileInputStream(pubKeyFile);
		FileInputStream plainTextInput = new FileInputStream(plainTextFile);

		byte[] bIn = PgpHelper.getInstance().inputStreamToByteArray(plainTextInput);
		PgpHelper.getInstance().verifySignature(plainTextFile, sig, pubKeyIs);
	}

    public static byte[] method(File file)
            throws IOException
        {
            FileInputStream fl = new FileInputStream(file);
            byte[] arr = new byte[(int)file.length()];
            fl.read(arr);
            fl.close();
            return arr;
        }

}
