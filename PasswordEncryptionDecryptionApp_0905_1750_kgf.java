// 代码生成时间: 2025-09-05 17:50:11
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * A utility class providing methods for password encryption and decryption.
 */
public class PasswordEncryptionDecryptionApp {

    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    /**
     * Encrypts the password using AES algorithm.
     *
 * @param password the password to be encrypted
 * @return the encrypted password in Base64 encoded string
 * @throws Exception if encryption fails
 */
    public String encryptPassword(String password) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance(ALGORITHM);
        keyGen.init(KEY_SIZE, SecureRandom.getInstanceStrong());
        SecretKey secretKey = keyGen.generateKey();

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    /**
     * Decrypts the password using AES algorithm.
     *
 * @param encryptedPassword the Base64 encoded encrypted password to be decrypted
 * @return the decrypted password
 * @throws Exception if decryption fails
 */
    public String decryptPassword(String encryptedPassword) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKey originalKey = generateKeyFromBase64(encryptedPassword);
        cipher.init(Cipher.DECRYPT_MODE, originalKey);

        byte[] decodedValue = Base64.getDecoder().decode(encryptedPassword);
        byte[] decryptedBytes = cipher.doFinal(decodedValue);
        return new String(decryptedBytes);
    }

    /**
     * Generates a SecretKey from a Base64 encoded string, assuming the string is the key itself.
     *
 * @param base64Key the Base64 encoded key
 * @return the generated SecretKey
 * @throws Exception if key generation fails
 */
    private SecretKey generateKeyFromBase64(String base64Key) throws Exception {
        byte[] decodedKey = Base64.getDecoder().decode(base64Key);
        return new SecretKeySpec(decodedKey, ALGORITHM);
    }

    // Example usage
    public static void main(String[] args) {
        PasswordEncryptionDecryptionApp app = new PasswordEncryptionDecryptionApp();
        try {
            String password = "mySecretPassword";
            String encrypted = app.encryptPassword(password);
            String decrypted = app.decryptPassword(encrypted);

            System.out.println("Original Password: " + password);
            System.out.println("Encrypted Password: " + encrypted);
            System.out.println("Decrypted Password: " + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
