// 代码生成时间: 2025-08-21 23:18:02
package com.yourcompany.testdata;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.util.Random;

/**
 * Main class to generate test data.
 */
@QuarkusMain
public class TestDataGenerator {

    // Generates a random integer between min and max inclusive.
    private int generateRandomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }

    // Generates a random string of fixed length.
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    // Main method to generate and print test data.
    public static void main(String[] args) {
        try {
            TestDataGenerator generator = new TestDataGenerator();
            // Generate and print 10 random integers.
            for (int i = 0; i < 10; i++) {
                int randomInt = generator.generateRandomInt(1, 100);
                System.out.println("Random Int: " + randomInt);
            }

            // Generate and print 5 random strings of length 10.
            for (int i = 0; i < 5; i++) {
                String randomString = generator.generateRandomString(10);
                System.out.println("Random String: " + randomString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
