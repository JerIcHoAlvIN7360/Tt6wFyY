// 代码生成时间: 2025-10-07 18:52:48
package com.example.quarkus.demo.service;

import io.quarkus.runtime.Quarkus;
import java.util.Arrays;
import java.util.Comparator;

/**
 * A service class that provides sorting functionality.
 * This class includes a method to sort an array of integers.
 */
public class SortingService {

    /**
     * Sorts an array of integers in ascending order.
     *
     * @param numbers The array of integers to sort.
     * @return A sorted array of integers.
     */
    public int[] sortNumbersAscending(int[] numbers) {
        // Check if the input array is null or empty, throw an IllegalArgumentException if true
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }

        // Use Arrays.sort with a custom Comparator for ascending order
        Arrays.sort(numbers, Comparator.naturalOrder());
        return numbers;
    }

    /**
     * Sorts an array of integers in descending order.
     *
     * @param numbers The array of integers to sort.
     * @return A sorted array of integers.
     */
    public int[] sortNumbersDescending(int[] numbers) {
        // Check if the input array is null or empty, throw an IllegalArgumentException if true
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("Input array cannot be null or empty.");
        }

        // Use Arrays.sort with a custom Comparator for descending order
        Arrays.sort(numbers, Comparator.reverseOrder());
        return numbers;
    }

    public static void main(String[] args) {
        // Create an instance of SortingService
        SortingService service = new SortingService();

        // Example usage of sortNumbersAscending method
        int[] unsortedNumbers = {5, 1, 4, 2, 8};
        int[] sortedNumbersAscending = service.sortNumbersAscending(unsortedNumbers);
        System.out.println("Sorted Numbers (Ascending): " + Arrays.toString(sortedNumbersAscending));

        // Example usage of sortNumbersDescending method
        int[] sortedNumbersDescending = service.sortNumbersDescending(unsortedNumbers);
        System.out.println("Sorted Numbers (Descending): " + Arrays.toString(sortedNumbersDescending));
    }
}
