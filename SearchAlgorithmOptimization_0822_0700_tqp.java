// 代码生成时间: 2025-08-22 07:00:37
package com.example.search;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.annotations.QuarkusMain;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.inject.Inject;
import java.util.concurrent.Callable;
import java.util.List;
import java.util.ArrayList;

// Command line interface class for search algorithm optimization
@Command(name = "search-algorithm-optimization", mixinStandardHelpOptions = true, version = "Search Algorithm Optimization 1.0")
@QuarkusMain
public class SearchAlgorithmOptimization implements Callable<Integer> {

    // Injecting SearchService for algorithm implementation details
    @Inject
    SearchService searchService;

    @Option(names = { "-i", "--input" }, description = "Input data for search algorithm")
    private String inputData;

    @Option(names = { "-a", "--algorithm" }, description = "Search algorithm to use (binary, linear)")
    private String algorithm;

    // Entry point for the application
    public static void main(String... args) {
        int exitCode = new CommandLine(new SearchAlgorithmOptimization()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        try {
            // Validate input data
            if (inputData == null || inputData.isEmpty()) {
                throw new IllegalArgumentException("Input data is required for search algorithm.");
            }

            // Validate the search algorithm
            if (algorithm == null || (!algorithm.equals("binary") && !algorithm.equals("linear"))) {
                throw new IllegalArgumentException("Invalid search algorithm. Use 'binary' or 'linear'.");
            }

            // Perform the search operation based on the algorithm
            List<Integer> results = new ArrayList<>();
            switch (algorithm.toLowerCase()) {
                case "binary":
                    results = searchService.binarySearch(inputData);
                    break;
                case "linear":
                    results = searchService.linearSearch(inputData);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported search algorithm.");
            }

            // Print the results
            results.forEach(System.out::println);

            // Return a successful exit code
            return 0;
        } catch (Exception e) {
            // Handle any exceptions and print the error message
            System.err.println("Error: " + e.getMessage());
            return 1;
        }
    }
}

// Service class for search algorithm implementation
class SearchService {
    // Binary search implementation
    public List<Integer> binarySearch(String input) {
        // Convert input to a list of integers
        List<Integer> numbers = new ArrayList<>();
        for (String numberStr : input.split(",")) {
            try {
                numbers.add(Integer.parseInt(numberStr));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in input data.");
            }
        }

        // Perform binary search
        return performBinarySearch(numbers, 0, numbers.size() - 1);
    }

    // Helper method for binary search
    private List<Integer> performBinarySearch(List<Integer> numbers, int left, int right) {
        List<Integer> results = new ArrayList<>();
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers.get(mid) == searchValue) {
                results.add(numbers.get(mid));
                break;
            } else if (numbers.get(mid) < searchValue) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return results;
    }

    // Linear search implementation
    public List<Integer> linearSearch(String input) {
        // Convert input to a list of integers
        List<Integer> numbers = new ArrayList<>();
        for (String numberStr : input.split(",")) {
            try {
                numbers.add(Integer.parseInt(numberStr));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format in input data.");
            }
        }

        // Perform linear search
        return performLinearSearch(numbers);
    }

    // Helper method for linear search
    private List<Integer> performLinearSearch(List<Integer> numbers) {
        List<Integer> results = new ArrayList<>();
        for (Integer number : numbers) {
            if (number == searchValue) {
                results.add(number);
            }
        }
        return results;
    }

    // Search value to be found in the list
    private Integer searchValue;

    // Set the search value
    public void setSearchValue(Integer value) {
        this.searchValue = value;
    }
}
