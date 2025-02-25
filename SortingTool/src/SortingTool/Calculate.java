package SortingTool;

import java.util.Arrays;

import static SortingTool.AsymptoticNotation.calculateAsymptoticEfficiency;

public class Calculate {

    public static void calculate(int algorithm, int[] arr ,StepCounter stepCounter, int maxsize ,int[] arrstep, int arrtype , double[] arr2 ) {
        int maxValue = Arrays.stream(arr).max().orElse(0);
        int step = maxsize / (maxsize/2);
        int j = 0;
        int[] sortedArr = {0};
        switch (algorithm) {
            case 1:     //bubble sort
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }

                    Algorithms.bubbleSort(arr, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 2:     //counting sort
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);

                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }
                    Algorithms.countingSort(arr, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 3:
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }
                    Algorithms.quickSort(arr, 0, arr.length - 1, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 4:
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }
                    Algorithms.selectionSort(arr, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 5:
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }
                    Algorithms.heapSort(arr, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 6:
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }

                    Algorithms.radixSort(arr, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 7:
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }

                    Algorithms.mergeSort(arr, 0, arr.length - 1, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            case 8:
                for (int i = step; i < maxsize; i += step) {
                    if (arrtype == 1) {
                        arr = GenerateArray.RandomCaseArray(i);
                    } else if (arrtype == 2) {
                        arr = GenerateArray.BestCaseArray(i);
                    } else {
                        arr = GenerateArray.WorstCaseArray(i);
                    }

                    Algorithms.insertionSort(arr, stepCounter, sortedArr);
                    arr2[j] = calculateAsymptoticEfficiency(algorithm,i,arrtype,maxValue,arr);
                    arrstep[j] = stepCounter.getSteps();
                    j++;
                }
                break;
            default:
                System.out.println("Invalid Value for Asymptotic Type");
                break;
        }

    }


}


