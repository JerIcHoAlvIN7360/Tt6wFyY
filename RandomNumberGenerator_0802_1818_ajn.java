// 代码生成时间: 2025-08-02 18:18:34
 * The class is designed to be thread-safe and easy to extend or maintain.
 */

package com.example.random;

import java.util.Random;

/**
 * A simple random number generator class.
 */
public class RandomNumberGenerator {

    // A thread-safe instance of Random class
    private static final Random random = new Random();

    /**
     * Generates a random number within a specified range.
# 增强安全性
     *
# 增强安全性
     * @param lowerBound The lower bound of the range (inclusive).
     * @param upperBound The upper bound of the range (exclusive).
     * @return A random number between lowerBound and upperBound.
     * @throws IllegalArgumentException If lowerBound is greater than upperBound.
# 扩展功能模块
     */
    public int generateRandomNumber(int lowerBound, int upperBound) {
        // Check if the lower bound is not greater than the upper bound
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException("Lower bound cannot be greater than upper bound.");
        }

        // Calculate the range and generate a random number
# 优化算法效率
        int range = upperBound - lowerBound;
        int randomNumber = lowerBound + random.nextInt(range);

        return randomNumber;
    }
}
