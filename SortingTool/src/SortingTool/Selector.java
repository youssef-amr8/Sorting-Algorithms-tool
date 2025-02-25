package SortingTool;

import java.util.Scanner;

import static SortingTool.AsymptoticNotation.calculateAsymptoticEfficiency;

public class Selector {

    public static void ChooseAlgorithms() {
        Scanner scanner = new Scanner(System.in);
        int [] arr = new int[10000];
        double[] arrnotation=new double[10000];
        int[] arrstep=new int[10000];
        StepCounter stepCounter= new StepCounter();
        // List of available algorithms
        String[] algorithms = {
                "Bubble Sort", "Counting Sort", "Quick Sort",
                "Selection Sort", "Heap Sort", "Radix Sort",
                "Merge Sort", "Insertion Sort"
        };

        System.out.println("Write 1 to compare one algorithm or 2 to compare multiple algorithms: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left over by nextInt()

        System.out.print("Enter the size of the array to be sorted: ");
        int size = scanner.nextInt();

        System.out.print("Enter the number of steps of the array to be sorted(must be greater than 10): ");
        int step = scanner.nextInt();

        System.out.println("Select the case to sort:");
        System.out.println("1. Random Array (Average Case)");
        System.out.println("2. Best Case Array (Sorted)");
        System.out.println("3. Worst Case Array (Reversed Sorted)");
        int selectedArray = scanner.nextInt();

        if (choice == 1) {
            // Choose one algorithm for comparison
            System.out.println("Please choose one algorithm to compare:");
            displayAlgorithms(algorithms);

            int chosenAlgorithm = scanner.nextInt();
            if (chosenAlgorithm < 1 || chosenAlgorithm > algorithms.length) {
                System.out.println("Invalid algorithm choice!");
            } else {
                // Call CalculateAlgorithm with asymptotic transformation
                   Calculate.calculate(chosenAlgorithm,arr,stepCounter, size,arrstep,selectedArray,arrnotation );
                System.out.print("sorted array :");
                   for (int i = 0; i < arr.length; i ++) {
                    System.out.print( arr[i]);
                }
                System.out.println();
                for (int k = step; k <= size; k += step)
                {
                    System.out.print("Asymptotic Efficiencys of array at size " +k+ " : " + arrnotation[k]+"\n");
                }
                for (int n = step; n <= size; n += step)
                {
                    System.out.print("step counter of array at size " +n+ " : " + arrstep[n]+"\n");
                }

            }

        } else if (choice == 2) {
            // Choose multiple algorithms for comparison
            System.out.println("Please choose multiple algorithms to compare (comma-separated numbers):");
            displayAlgorithms(algorithms);
            scanner.nextLine(); // Consume newline after nextInt()

            String chosenAlgorithms = scanner.nextLine();
            String[] chosenList = chosenAlgorithms.split(",");

            for (String algo : chosenList) {
                try {
                    int algorithmChoice = Integer.parseInt(algo.trim());
                    if (algorithmChoice >= 1 && algorithmChoice <= algorithms.length) {
                        // Call normal CalculateAlgorithm for each algorithm
                        Calculate.calculate(algorithmChoice,arr,stepCounter, size,arrstep,selectedArray,arrnotation ); // 0 for normal case
                    } else {
                        System.out.println("Invalid algorithm number: " + algorithmChoice);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input detected: " + algo);
                }
            }
        } else {
            System.out.println("Invalid choice! Please restart the program.");
        }

        scanner.close();
    }

    private static void displayAlgorithms(String[] algorithms) {
        for (int i = 0; i < algorithms.length; i++) {
            System.out.println((i + 1) + ". " + algorithms[i]);
        }
    }

    public static void chooseAlgorithms() {
    }
}