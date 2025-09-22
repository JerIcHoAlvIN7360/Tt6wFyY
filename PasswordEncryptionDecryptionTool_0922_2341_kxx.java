// 代码生成时间: 2025-09-22 23:41:48
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * A tool for encrypting and decrypting passwords using AES encryption.
 */
public class PasswordEncryptionDecryptionTool {

    // AES加密使用的密钥长度
    private static final int AES_KEY_SIZE = 128;

    // 定义加密算法
    private static final String ALGORITHM = "AES";

    /**
     * Encrypts a password using AES encryption.
     *
     * @param password The password to encrypt.
     * @return The encrypted password in base64 encoded string format.
     * @throws Exception If an error occurs during encryption.
     */
    public String encryptPassword(String password) throws Exception {
        SecretKeySpec keySpec = generateAESKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts a password using AES encryption.
     *
     * @param encryptedPassword The encrypted password in base64 encoded string format.
     * @return The decrypted password.
     * @throws Exception If an error occurs during decryption.
     */
    public String decryptPassword(String encryptedPassword) throws Exception {
        SecretKeySpec keySpec = generateAESKey();
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
        return new String(decryptedBytes);
    }

    /**
     * Generates a new AES key.
     *
     * @return A SecretKeySpec instance representing the generated key.
     * @throws Exception If an error occurs during key generation.
     */
    private SecretKeySpec generateAESKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        keyGenerator.init(AES_KEY_SIZE, SecureRandom.getInstanceStrong());
        SecretKey secretKey = keyGenerator.generateKey();
        return new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
    }

    // Main method for testing the tool
    public static void main(String[] args) {
        try {
            PasswordEncryptionDecryptionTool tool = new PasswordEncryptionDecryptionTool();

            String originalPassword = "MySecretPassword";
            String encryptedPassword = tool.encryptPassword(originalPassword);
            String decryptedPassword = tool.decryptPassword(encryptedPassword);

            System.out.println("Original Password: " + originalPassword);
            System.out.println("Encrypted Password: " + encryptedPassword);
            System.out.println("Decrypted Password: " + decryptedPassword);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
