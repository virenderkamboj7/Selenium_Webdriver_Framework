package com.practice.utilities;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Encrypts and decrypts string tokens using Secrete key. Same secret key will be used to decrypt the strings.
 * The secret string is hashed with SHA-256
 * to produce the AES key; each encryption uses a random IV prepended to the ciphertext.
 * Secret keys are not stored in code base anyware. Secret key are stored in System environment variables for security purpose.
 */
public final class TokenCryptoUtils {

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/GCM/NoPadding";
	private static final int GCM_IV_LENGTH = 12;
	private static final int GCM_TAG_LENGTH_BITS = 128;

	private TokenCryptoUtils() {
	}

	public static String encrypt(String plainText, String secret) throws GeneralSecurityException {
		if (plainText == null || secret == null) {
			throw new IllegalArgumentException("plainText and secret must not be null");
		}
		SecretKey key = deriveKey();
		byte[] iv = new byte[GCM_IV_LENGTH];
		new SecureRandom().nextBytes(iv);

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
		byte[] cipherBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

		ByteBuffer buffer = ByteBuffer.allocate(iv.length + cipherBytes.length);
		buffer.put(iv);
		buffer.put(cipherBytes);
		return Base64.getEncoder().encodeToString(buffer.array());
	}

	public static String decrypt(String encryptedBase64, String secret) throws GeneralSecurityException {
		if (encryptedBase64 == null || secret == null) {
			throw new IllegalArgumentException("encryptedBase64 and secret must not be null");
		}
		SecretKey key = deriveKey();
		byte[] decoded = Base64.getDecoder().decode(encryptedBase64);
		if (decoded.length <= GCM_IV_LENGTH) {
			throw new GeneralSecurityException("Invalid ciphertext length");
		}
		ByteBuffer buffer = ByteBuffer.wrap(decoded);
		byte[] iv = new byte[GCM_IV_LENGTH];
		buffer.get(iv);
		byte[] cipherBytes = new byte[buffer.remaining()];
		buffer.get(cipherBytes);

		Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH_BITS, iv));
		byte[] plainBytes = cipher.doFinal(cipherBytes);
		return new String(plainBytes, StandardCharsets.UTF_8);
	}

	private static SecretKey deriveKey() throws GeneralSecurityException {
		// 1. Fetch the secret from the environment variable
		String secret = System.getenv("FRAMEWORK_ENCRYPTION_KEY");
	
		// 2. Fail fast if the environment variable is not set
		if (secret == null || secret.trim().isEmpty()) {
			throw new IllegalStateException("Environment variable FRAMEWORK_ENCRYPTION_KEY is not set. Cannot decrypt passwords.");
		}
	
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] keyBytes = digest.digest(secret.getBytes(StandardCharsets.UTF_8));
			return new SecretKeySpec(keyBytes, ALGORITHM);
		} catch (java.security.NoSuchAlgorithmException e) {
			throw new GeneralSecurityException("SHA-256 not available", e);
		}
	}

	public static void main(String[] args) throws GeneralSecurityException {

		System.out.println(System.getenv("FRAMEWORK_ENCRYPTION_KEY"));
		String encryptedString = encrypt("Test@123", System.getenv("FRAMEWORK_ENCRYPTION_KEY"));
		System.out.println("encryptedString: "+encryptedString);

		String decryptedString = decrypt(encryptedString, "FRAMEWORK_ENCRYPTION_KEY");
		System.out.println("decryptedString: "+decryptedString);
	}
}
