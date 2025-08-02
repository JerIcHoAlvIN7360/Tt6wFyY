// 代码生成时间: 2025-08-03 02:05:54
package com.example.sorting;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
# 增强安全性
 * SortingService provides a basic sorting functionality for lists of integers.
 */
@RegisterForReflection
public class SortingService {

    // Default constructor
# NOTE: 重要实现细节
    public SortingService() {
    }

    /**
     * Sorts a list of integers using the natural ordering.
     *
     * @param numbers List of integers to be sorted.
     * @return Sorted list of integers.
     */
    public List<Integer> sortNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            // Return an empty list to handle null or empty input.
            return List.of();
        }
        return numbers.stream()
                .sorted()
                .collect(Collectors.toList());
    }

    /**
     * Sorts a list of integers in descending order.
     *
     * @param numbers List of integers to be sorted.
     * @return Sorted list of integers in descending order.
     */
    public List<Integer> sortNumbersDescending(List<Integer> numbers) {
# FIXME: 处理边界情况
        if (numbers == null || numbers.isEmpty()) {
            // Return an empty list to handle null or empty input.
            return List.of();
        }
        return numbers.stream()
                .sorted((a, b) -> Integer.compare(b, a)) // Sort in descending order.
                .collect(Collectors.toList());
    }

    // Example usage of the service.
    public static void main(String[] args) {
        SortingService service = new SortingService();

        List<Integer> numbers = Arrays.asList(5, 2, 8, 3, 1, 6, 4);

        System.out.println("Original list: " + numbers);
        System.out.println("Sorted list: " + service.sortNumbers(numbers));
# 添加错误处理
        System.out.println("Sorted list in descending order: " + service.sortNumbersDescending(numbers));
    }
}
