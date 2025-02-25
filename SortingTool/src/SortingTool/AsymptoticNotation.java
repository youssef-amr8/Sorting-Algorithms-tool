package SortingTool;

import java.util.Arrays;

public class AsymptoticNotation {

    public static double calculateAsymptoticEfficiency(int choice, int n, int choosecase, int K, int[] arr) {
        int maxValue = Arrays.stream(arr).max().orElse(0); // Find the maximum value in the array
        switch (choice) {
            case 1: // Bubble Sort
                return choosecase == 2 ? n : Math.pow(n, 2);
            case 2: // Counting Sort
                return n + K;
            case 3: // Quick Sort
                return choosecase == 3 ? Math.pow(n, 2) : n * (Math.log(n) / Math.log(2));
            case 4: // Selection Sort
                return Math.pow(n, 2);
            case 5: // Heap Sort
                return n * (Math.log(n) / Math.log(2));
            case 6: // Radix Sort
                int d = (int) Math.ceil(Math.log10(maxValue) / Math.log10(K)); // Calculate number of digits
                return choosecase == 2 ? (n + 10) : d * (n + 10); // Corrected formula for Radix Sort
            case 7: // Merge Sort
                return n * (Math.log(n) / Math.log(2));
            case 8: // Insertion Sort
                return choosecase == 2 ? n : Math.pow(n, 2);
            default:
                return 0;
        }
    }
}
