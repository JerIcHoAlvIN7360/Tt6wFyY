// 代码生成时间: 2025-08-10 02:46:48
package com.example.analytics;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A service for data analysis.
 */
@ApplicationScoped
public class DataAnalysisService {

    // Method to calculate the sum of a list of numbers.
    public double sum(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List of numbers cannot be null or empty");
        }
        return numbers.stream().mapToDouble(Double::doubleValue).sum();
    }

    // Method to calculate the average of a list of numbers.
    public double average(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List of numbers cannot be null or empty");
        }
        return sum(numbers) / numbers.size();
    }

    // Method to calculate the maximum value in a list of numbers.
    public Double max(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List of numbers cannot be null or empty");
        }
        return numbers.stream().max(Double::compareTo).orElseThrow(
                () -> new IllegalArgumentException("Unable to find the maximum value"));
    }

    // Method to calculate the minimum value in a list of numbers.
    public Double min(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List of numbers cannot be null or empty");
        }
        return numbers.stream().min(Double::compareTo).orElseThrow(
                () -> new IllegalArgumentException("Unable to find the minimum value"));
    }

    // Method to calculate the standard deviation of a list of numbers.
    public double standardDeviation(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List of numbers cannot be null or empty");
        }
        double mean = average(numbers);
        double sqDiffSum = numbers.stream()
            .mapToDouble(n -> Math.pow(n - mean, 2))
            .sum();
        return Math.sqrt(sqDiffSum / (numbers.size() - 1));
    }

    // Method to calculate the median of a list of numbers.
    public double median(List<Double> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("List of numbers cannot be null or empty");
        }
        return numbers.stream().sorted().collect(Collectors.toMap(
            n -> n,
            n -> 1,
            (existing, replacement) -> existing
        ))
        .entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .skip((numbers.size() + 1) / 2 - 1)
        .findFirst()
        .map(Map.Entry::getKey)
        .orElseThrow(
            () -> new IllegalArgumentException("Unable to find the median value"));
    }
}
