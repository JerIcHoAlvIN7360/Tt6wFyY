// 代码生成时间: 2025-09-06 20:04:27
package com.example.quarkus.data;

import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@QuarkusMain
public class DataAnalyzer implements QuarkusApplication {

    // Calculates the mean of a list of numbers
    private static double calculateMean(List<Double> numbers) {
        return numbers.stream().mapToDouble(Double::doubleValue).average().orElse(Double.NaN);
    }

    // Calculates the median of a list of numbers
    private static double calculateMedian(List<Double> numbers) {
        return numbers.stream()
            .sorted()
            .mapToDouble(Double::doubleValue)
            .collect(Collectors.averagingInt(i -> 1)) / 2.0;
    }

    // Calculates the mode of a list of numbers
    private static Optional<Double> calculateMode(List<Double> numbers) {
        return numbers.stream()
            .collect(Collectors.groupingBy(Double::doubleValue, Collectors.counting()))
            .entrySet().stream()
            .max((e1, e2) -> Long.compare(e1.getValue(), e2.getValue()))
            .map(Map.Entry::getKey);
    }

    @Override
    public int run(String... args) throws Exception {
        System.out.println("Data Analyzer Application Starting...");

        // Example data set for demonstration
        List<Double> dataSet = Arrays.asList(1.0, 2.0, 2.0, 3.0, 4.0, 4.0, 4.0);

        try {
            double mean = calculateMean(dataSet);
            double median = calculateMedian(dataSet);
            Optional<Double> mode = calculateMode(dataSet);

            System.out.println("Mean: " + mean);
            System.out.println("Median: " + median);
            mode.ifPresent(m -> System.out.println("Mode: " + m));

            // Wait for the application to be gracefully shut down
            long shutdownTimeout = Duration.ofMinutes(5).toMillis();
            while (shutdownTimeout > 0) {
                Thread.sleep(100);
                shutdownTimeout -= 100;
            }
        } catch (Exception e) {
            System.err.println("Error occurred: " + e.getMessage());
            return 1;
        }

        return 0;
    }

    public static void main(String[] args) {
        DataAnalyzer analyzer = new DataAnalyzer();
        analyzer.run(args);
    }
}
