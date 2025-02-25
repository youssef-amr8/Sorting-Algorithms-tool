package SortingTool;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Algorithms {

    public static void bubbleSort(int[] arr, StepCounter stepCounter, int[] Sortedarr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                stepCounter.increment(); // Count comparison
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    stepCounter.increment(); // Count swap
                }
            }
            if (!swapped) {
                break;
            }
        }        System.arraycopy(arr, 0, Sortedarr, 0, Sortedarr.length);
    }

    public static void countingSort(int[] arr, StepCounter stepCounter, int[] Sortedarr) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int num : arr) {
            stepCounter.increment(); // Count comparison
            if (num > max) max = num;
            if (num < min) min = num;
        }
        int range = max - min + 1;
        int[] count = new int[range];
        int[] output = new int[arr.length];
        for (int num : arr) {
            stepCounter.increment(); // Count insertion into count array
            count[num - min]++;
        }
        for (int i = 1; i < count.length; i++) {
            stepCounter.increment(); // Count cumulative sum calculation
            count[i] += count[i - 1];
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            stepCounter.increment(); // Count placement into output array
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }
        System.arraycopy(output, 0, arr, 0, arr.length);
        System.arraycopy(output, 0, Sortedarr, 0, Sortedarr.length);
    }

    private static int partition(int[] arr, int low, int high, StepCounter stepCounter) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            stepCounter.increment(); // Count comparison
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                stepCounter.increment(); // Count swap
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        stepCounter.increment(); // Count pivot swap
        return i + 1;
    }

    public static void quickSort(int[] arr, int low, int high, StepCounter stepCounter, int[] Sortedarr) {
        if (low < high) {
            int p = partition(arr, low, high, stepCounter);
            quickSort(arr, low, p - 1, stepCounter, Sortedarr);
            quickSort(arr, p + 1, high, stepCounter, Sortedarr);
        }
    }

    public static void selectionSort(int[] arr, StepCounter stepCounter, int[] Sortedarr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                stepCounter.increment(); // Count comparison
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
            stepCounter.increment(); // Count swap
        }
        System.arraycopy(arr, 0, Sortedarr, 0, Sortedarr.length);
    }

    public static void heapSort(int[] arr, StepCounter stepCounter, int[] Sortedarr) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int value : arr) {
            maxHeap.offer(value);
            stepCounter.increment(); // Count heap insertion
        }
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.poll();
            stepCounter.increment(); // Count heap extraction
        }
        System.arraycopy(arr, 0, Sortedarr, 0, Sortedarr.length);
    }

    public static void radixSort(int[] arr, StepCounter stepCounter, int[] Sortedarr) {
        int n = arr.length;
        int max = getMax(arr, n);
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortForRadix(arr, n, exp, stepCounter);
        }
        System.arraycopy(arr, 0, Sortedarr, 0, Sortedarr.length);
    }

    private static int getMax(int[] arr, int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > mx)
                mx = arr[i];
        }
        return mx;
    }

    private static void countingSortForRadix(int[] arr, int n, int exp, StepCounter stepCounter) {
        int[] output = new int[n];
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
            stepCounter.increment(); // Count digit extraction
        }
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
            stepCounter.increment(); // Count cumulative sum
        }
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            stepCounter.increment(); // Count placement in output
        }
        System.arraycopy(output, 0, arr, 0, n);
    }

    private static void merge(int[] A, int p, int q, int r, StepCounter stepCounter) {
        int n1 = q - p + 1;
        int n2 = r - q;
        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        System.arraycopy(A, p, L, 0, n1);
        System.arraycopy(A, q + 1, R, 0, n2);

        L[n1] = Integer.MAX_VALUE;
        R[n2] = Integer.MAX_VALUE;

        int i = 0, j = 0;
        for (int k = p; k <= r; k++) {
            stepCounter.increment(); // Count comparison
            if (L[i] <= R[j]) {
                A[k] = L[i];
                i++;
            } else {
                A[k] = R[j];
                j++;
            }
            stepCounter.increment(); // Count merge step
        }
    }

    public static void mergeSort(int[] arr, int left, int right, StepCounter stepCounter, int[] Sortedarr) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid, stepCounter, Sortedarr);
            mergeSort(arr, mid + 1, right, stepCounter,Sortedarr);
            merge(arr, left, mid, right, stepCounter);
        }
        System.arraycopy(arr, 0, Sortedarr, 0, Sortedarr.length);
    }

    public static void insertionSort(int[] A, StepCounter stepCounter, int[] Sortedarr) {
        for (int j = 1; j < A.length; j++) {
            int key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i] > key) {
                stepCounter.increment(); // Count comparison
                A[i + 1] = A[i];
                i = i - 1;
                stepCounter.increment(); // Count shift
            }
            A[i + 1] = key;
            stepCounter.increment(); // Count insertion
        }
        System.arraycopy(A, 0, Sortedarr, 0, Sortedarr.length);
    }
}
