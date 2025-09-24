// 代码生成时间: 2025-09-24 12:00:40
package com.example.hashvaluecalculator;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * A command line tool that calculates the hash value of the provided input.
 * This tool supports different hash algorithms like SHA-256 and MD5.
 */
@QuarkusMain
public class HashValueCalculator implements QuarkusApplication {

    @Override
    public int run(String... args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter the text to hash: ");
            String inputText = scanner.nextLine();

            System.out.println("Enter the hash algorithm (e.g., SHA-256, MD5): ");
            String algorithm = scanner.nextLine();

            String hashValue = calculateHash(inputText, algorithm);
            System.out.println("The hash value is: " + hashValue);

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Hash algorithm not supported: " + e.getMessage());
            return 1;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return 1;
        } finally {
            scanner.close();
        }
        return 0;
    }

    /**
     * Calculates the hash value of the given input text using the specified algorithm.
     *
     * @param inputText the text to be hashed
     * @param algorithm the hash algorithm to use (e.g., SHA-256, MD5)
     * @return the hash value as a hexadecimal string
     * @throws NoSuchAlgorithmException if the specified algorithm is not supported
     */
    public static String calculateHash(String inputText, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = digest.digest(inputText.getBytes(StandardCharsets.UTF_8));
        StringBuilder hashValue = new StringBuilder();
        for (byte b : hashBytes) {
            hashValue.append(String.format("%02x", b));
        }
        return hashValue.toString();
    }

    /**
     * Main method for running the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Quarkus.run(HashValueCalculator.class, args);
    }
}
