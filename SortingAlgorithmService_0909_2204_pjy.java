// 代码生成时间: 2025-09-09 22:04:22
package com.example.quarkus.demo.services;

import io.quarkus.runtime.annotations.RegisterForReflection;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

// Service class that provides sorting functionality
@RegisterForReflection
public class SortingAlgorithmService {

    // Sorts a list of integers using the Bubble Sort algorithm
    public List<Integer> bubbleSort(List<Integer> unsortedList) {
        if (unsortedList == null || unsortedList.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty.");
        }

        for (int i = 0; i < unsortedList.size() - 1; i++) {
            for (int j = 0; j < unsortedList.size() - i - 1; j++) {
                if (unsortedList.get(j) > unsortedList.get(j + 1)) {
                    // Swap the elements
                    int temp = unsortedList.get(j);
                    unsortedList.set(j, unsortedList.get(j + 1));
                    unsortedList.set(j + 1, temp);
                }
            }
        }
        return unsortedList;
    }

    // Sorts a list of integers using the Quick Sort algorithm
    public List<Integer> quickSort(List<Integer> unsortedList) {
        if (unsortedList == null || unsortedList.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty.");
        }
        return quickSort(unsortedList, 0, unsortedList.size() - 1);
    }

    private List<Integer> quickSort(List<Integer> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
        return list;
    }

    private int partition(List<Integer> list, int low, int high) {
        int pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j) < pivot) {
                i++;
                int temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        int temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    // Sorts a list of integers using the Java Collections sort method
    public List<Integer> collectionSort(List<Integer> unsortedList) {
        if (unsortedList == null || unsortedList.isEmpty()) {
            throw new IllegalArgumentException("List must not be null or empty.");
        }

        List<Integer> sortedList = new ArrayList<>(unsortedList);
        sortedList.sort(Comparator.naturalOrder());
        return sortedList;
    }
}
