package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordHasher {
    private static final int SALT_LENGTH = 32; // length of salt in bytes
    private static final int HASH_LENGTH = 256; // length of hash in bytes
    private static final String HASH_ALGORITHM = "SHA-256";

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String generateSaltedHash(String password, String salt) {
        byte[] saltBytes = Base64.getDecoder().decode(salt); // FIX: dekodo salt-in nga base64
        byte[] hash = hashWithSalt(password, saltBytes);

        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }
        for (byte b : hash) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean compareSaltedHash(String password, String salt, String saltedHash) {
        String generatedPasswordHash = generateSaltedHash(password, salt);
        return generatedPasswordHash.equals(saltedHash);
    }

    public static boolean verifyPassword(String password, String storedHash, String storedSalt) {
        return compareSaltedHash(password, storedSalt, storedHash);
    }

    private static byte[] hashWithSalt(String password, byte[] saltBytes) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            digest.reset();
            digest.update(saltBytes);
            byte[] hash = digest.digest(password.getBytes());

            // Optional: Stretching
            for (int i = 0; i < 1000; i++) {
                digest.reset();
                hash = digest.digest(hash);
            }

            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to hash password: " + e.getMessage(), e);
        }
    }
}
