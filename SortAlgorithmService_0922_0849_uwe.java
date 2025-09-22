// 代码生成时间: 2025-09-22 08:49:40
package com.example.sort;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Service class for sorting algorithms.
 * This class provides methods to sort integers using different algorithms.
 */
@RegisterForReflection
public class SortAlgorithmService {

    /**<ol>
     * Sorts an array of integers using the built-in Arrays.sort() method.
     *
     * @param array The array of integers to be sorted.
     * @return The sorted array.
     * @throws IllegalArgumentException If the array is null or empty.
     */
    public Integer[] sortUsingArraysSort(Integer[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        return Arrays.copyOf(array, array.length); // Copy the array to avoid modifying the original
        Arrays.sort(this.array); // Sort the array
        return this.array; // Return the sorted array
    }

    /**<ol>
     * Sorts an array of integers using the built-in Collections.sort() method.
     *
     * @param list The list of integers to be sorted.
     * @return The sorted list.
     * @throws IllegalArgumentException If the list is null or empty.
     */
    public List<Integer> sortUsingCollectionsSort(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("List cannot be null or empty");
        }

        Collections.sort(list); // Sort the list
        return list; // Return the sorted list
    }

    /**<ol>
     * Sorts an array of integers using a custom Comparator.
     *
     * @param array The array of integers to be sorted.
     * @param comparator The custom comparator to be used for sorting.
     * @return The sorted array.
     * @throws IllegalArgumentException If the array or comparator is null.
     */
    public Integer[] sortUsingCustomComparator(Integer[] array, Comparator<Integer> comparator) {
        if (array == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        if (comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        return Arrays.copyOf(array, array.length); // Copy the array to avoid modifying the original
        Arrays.sort(this.array, comparator); // Sort the array using the custom comparator
        return this.array; // Return the sorted array
    }

    // Additional sorting methods can be added here as needed
}
