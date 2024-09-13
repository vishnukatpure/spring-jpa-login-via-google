package com.dev.core.utils;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

	private static final String ALGORITHM = "AES";
	// private static final byte[] ALGO_KEY = new byte[] { 'g', 'r', 'e', 'x', 'e',
	// 'n', 'c', 'd', 'e', 'c', 'k', 'e', 'y',
	// 'v', 'e', 'r' };

	public String encrypt(String valueToEnc, String algoKey) throws NoSuchAlgorithmException, NoSuchPaddingException,
			IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
		final Key key = generateKey(algoKey);
		final Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		final byte[] encValue = c.doFinal(valueToEnc.getBytes());
		return Base64.getEncoder().encodeToString(encValue);
	}

	public String decrypt(String encryptedValue, String algoKey) throws NoSuchAlgorithmException,
			NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		final Key key = generateKey(algoKey);
		final Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		final byte[] decordedValue = Base64.getDecoder().decode(encryptedValue);
		final byte[] decValue = c.doFinal(decordedValue);
		return new String(decValue);
	}

	private static Key generateKey(String algoKey) {
		algoKey = String.format("%-16s", algoKey);
		return new SecretKeySpec(algoKey.getBytes(), ALGORITHM);
	}
}
