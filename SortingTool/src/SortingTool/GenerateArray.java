package SortingTool;

import java.util.Arrays;
import java.util.Random;

public class GenerateArray {


        public static int[] RandomCaseArray(int size) {
            Random rand = new Random();
            int[] randomcasearray = new int[size];
            int range = size * 10;

            for (int i = 0; i < size; i++) {
                randomcasearray[i] = rand.nextInt(range + 1); // Generate random numbers
            }

            return randomcasearray;
        }
        public static int[] BestCaseArray(int size)
        {
            int[] bestcasearray = RandomCaseArray(size) ;
            Arrays.sort(bestcasearray);
            return bestcasearray;
        }
        public static int[] WorstCaseArray(int size)
        {
            int[] worstcasearray = RandomCaseArray(size);
            Arrays.sort(worstcasearray);
            reverseArray(worstcasearray);
            return worstcasearray;
        }

        private static void reverseArray(int[] arr) {
            int start = 0, end = arr.length - 1;
            while (start < end) {
                int temp = arr[start];
                arr[start] = arr[end];
                arr[end] = temp;
                start++;
                end--;
            }
        }


}
