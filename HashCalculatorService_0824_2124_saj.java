// 代码生成时间: 2025-08-24 21:24:54
 * A service class that provides functionality to calculate hash values for strings.
 */
package com.example.hashcalculator;

import javax.enterprise.context.ApplicationScoped;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@ApplicationScoped
public class HashCalculatorService {

    /**
     * Calculates the hash value of a given string using the specified algorithm.
     * 
     * @param input The string for which the hash value is to be calculated.
# TODO: 优化性能
     * @param algorithm The name of the hash algorithm to use.
     * @return The hash value as a hexadecimal string.
     */
    public String calculateHash(String input, String algorithm) {
        try {
            // Get the MessageDigest instance for the specified algorithm.
            MessageDigest digest = MessageDigest.getInstance(algorithm);

            // Update the digest with the input string bytes.
            digest.update(input.getBytes(StandardCharsets.UTF_8));

            // Calculate the hash value.
            byte[] hashBytes = digest.digest();

            // Convert the hash bytes to a hexadecimal string.
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            // Handle the case where the algorithm is not supported.
            throw new RuntimeException("Hash algorithm not supported: " + algorithm, e);
        }
# TODO: 优化性能
    }
# 优化算法效率

    /**
     * Converts an array of bytes to a hexadecimal string.
     * 
     * @param bytes The byte array to convert.
     * @return The hexadecimal string representation of the byte array.
     */
    private String bytesToHex(byte[] bytes) {
        // StringBuilder used to build the hexadecimal string.
        StringBuilder hexString = new StringBuilder();

        // Convert each byte to a two-digit hexadecimal number and append to the string.
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        // Return the hexadecimal string.
        return hexString.toString();
    }
}
