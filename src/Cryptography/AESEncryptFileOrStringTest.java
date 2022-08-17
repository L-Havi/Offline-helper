package Cryptography;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class AESEncryptFileOrStringTest {

	AESEncryptionAndDecryption aes = new AESEncryptionAndDecryption();
	

    @Test
    void givenString_whenEncrypt_thenSuccess()
        throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException,
        BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        String input = "baeldung";
		SecretKey key = aes.generateKey(128);
        IvParameterSpec ivParameterSpec = aes.generateIv();
        String algorithm = "AES/CBC/PKCS5Padding";

        // when
        String cipherText = aes.encrypt(algorithm, input, key, ivParameterSpec);
        String plainText = aes.decrypt(algorithm, cipherText, key, ivParameterSpec);

        // then
        Assertions.assertEquals(input, plainText);
    }


    @Test
    void givenPassword_whenEncrypt_thenSuccess()
        throws InvalidKeySpecException, NoSuchAlgorithmException, IllegalBlockSizeException,
        InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        // given
        String plainText = "www.baeldung.com";
        String password = "baeldung";
        String salt = "12345678";
        IvParameterSpec ivParameterSpec = aes.generateIv();
        SecretKey key = aes.getKeyFromPassword(password, salt);

        // when
        String cipherText = aes.encryptPasswordBased(plainText, key, ivParameterSpec);
        String decryptedCipherText = aes.decryptPasswordBased(cipherText, key, ivParameterSpec);

        // then
        Assertions.assertEquals(plainText, decryptedCipherText);
    }
}
