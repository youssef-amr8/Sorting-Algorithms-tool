package SortingTool;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class gui extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Layouts
        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.setStyle("-fx-background-color: #f0f8ff;");

        // Array size input
        Label sizeLabel = new Label("Enter Array Size:");
        TextField sizeInput = new TextField();
        sizeInput.setPromptText("e.g., 100");

        // Step size input
        Label stepLabel = new Label("Enter Step Size:");
        TextField stepInput = new TextField();
        stepInput.setPromptText("e.g., 10");

        // Array case selection
        Label caseLabel = new Label("Choose Array Case:");
        ToggleGroup caseGroup = new ToggleGroup();
        RadioButton randomCase = new RadioButton("Random (Average Case)");
        RadioButton bestCase = new RadioButton("Best Case (Sorted)");
        RadioButton worstCase = new RadioButton("Worst Case (Reversed Sorted)");
        randomCase.setToggleGroup(caseGroup);
        bestCase.setToggleGroup(caseGroup);
        worstCase.setToggleGroup(caseGroup);
        randomCase.setSelected(true);

        // Algorithm selection
        Label algoLabel = new Label("Select Algorithms:");
        CheckBox bubbleSort = new CheckBox("Bubble Sort");
        CheckBox countingSort = new CheckBox("Counting Sort");
        CheckBox quickSort = new CheckBox("Quick Sort");
        CheckBox selectionSort = new CheckBox("Selection Sort");
        CheckBox heapSort = new CheckBox("Heap Sort");
        CheckBox radixSort = new CheckBox("Radix Sort");
        CheckBox mergeSort = new CheckBox("Merge Sort");
        CheckBox insertionSort = new CheckBox("Insertion Sort");

        // Run and Graph buttons
        Button runButton = new Button("Run");
        Button graphButton = new Button("Show Graph");

        // Results display
        TextArea resultsArea = new TextArea();
        resultsArea.setEditable(false);
        resultsArea.setWrapText(true);
        resultsArea.setPromptText("Algorithm results will appear here...");

        // Add elements to layout
        mainLayout.getChildren().addAll(
                sizeLabel, sizeInput,
                stepLabel, stepInput,
                caseLabel, randomCase, bestCase, worstCase,
                algoLabel, bubbleSort, countingSort, quickSort, selectionSort,
                heapSort, radixSort, mergeSort, insertionSort,
                runButton, graphButton, resultsArea
        );

        // Run button logic
        runButton.setOnAction(e -> {
            try {
                int size = Integer.parseInt(sizeInput.getText());
                int stepSize = Integer.parseInt(stepInput.getText());
                int selectedCase = randomCase.isSelected() ? 1 : bestCase.isSelected() ? 2 : 3;

                ArrayList<String> selectedAlgorithms = new ArrayList<>();
                if (bubbleSort.isSelected()) selectedAlgorithms.add("Bubble Sort");
                if (countingSort.isSelected()) selectedAlgorithms.add("Counting Sort");
                if (quickSort.isSelected()) selectedAlgorithms.add("Quick Sort");
                if (selectionSort.isSelected()) selectedAlgorithms.add("Selection Sort");
                if (heapSort.isSelected()) selectedAlgorithms.add("Heap Sort");
                if (radixSort.isSelected()) selectedAlgorithms.add("Radix Sort");
                if (mergeSort.isSelected()) selectedAlgorithms.add("Merge Sort");
                if (insertionSort.isSelected()) selectedAlgorithms.add("Insertion Sort");

                StringBuilder results = new StringBuilder("Results:\n");
                int[] steps = new int[10000];
                double[] asymptoticEfficiencies = new double[10000];
                StepCounter stepCounter = new StepCounter();
                int[] dummyArray = new int[size];

                for (String algorithm : selectedAlgorithms) {
                    int algorithmIndex = getAlgorithmIndex(algorithm);
                    Calculate.calculate(algorithmIndex, dummyArray, stepCounter, size, steps, selectedCase, asymptoticEfficiencies);

                    results.append(algorithm).append(":\n");
                    for (int i = stepSize, j = 0; i <= size; i += stepSize, j++) {
                        results.append("Step Size ").append(i).append(": ")
                                .append("Steps = ").append(steps[j]).append(", ")
                                .append("Asymptotic Efficiency = ").append(asymptoticEfficiencies[j]).append("\n");
                    }
                    results.append("\n");
                }

                resultsArea.setText(results.toString());
            } catch (NumberFormatException ex) {
                resultsArea.setText("Please enter valid numbers for size and step size.");
            }
        });

        // Graph button logic
        graphButton.setOnAction(e -> {
            try {
                int size = Integer.parseInt(sizeInput.getText());
                int stepSize = Integer.parseInt(stepInput.getText());
                int selectedCase = randomCase.isSelected() ? 1 : bestCase.isSelected() ? 2 : 3;

                ArrayList<String> selectedAlgorithms = new ArrayList<>();
                if (bubbleSort.isSelected()) selectedAlgorithms.add("Bubble Sort");
                if (countingSort.isSelected()) selectedAlgorithms.add("Counting Sort");
                if (quickSort.isSelected()) selectedAlgorithms.add("Quick Sort");
                if (selectionSort.isSelected()) selectedAlgorithms.add("Selection Sort");
                if (heapSort.isSelected()) selectedAlgorithms.add("Heap Sort");
                if (radixSort.isSelected()) selectedAlgorithms.add("Radix Sort");
                if (mergeSort.isSelected()) selectedAlgorithms.add("Merge Sort");
                if (insertionSort.isSelected()) selectedAlgorithms.add("Insertion Sort");

                int[] steps = new int[10000];
                double[] asymptoticEfficiencies = new double[10000];
                StepCounter stepCounter = new StepCounter();
                int[] dummyArray = new int[size];

                LineChart<String, Number> lineChart = createGraph(selectedAlgorithms, dummyArray, stepCounter, size, stepSize, steps, selectedCase, asymptoticEfficiencies);

                Stage graphStage = new Stage();
                VBox graphLayout = new VBox(lineChart);
                Scene graphScene = new Scene(graphLayout, 800, 600);
                graphStage.setScene(graphScene);
                graphStage.setTitle("Algorithm Performance Graph");
                graphStage.show();

            } catch (NumberFormatException ex) {
                resultsArea.setText("Please enter valid numbers for size and step size.");
            }
        });

        // Show stage
        Scene scene = new Scene(mainLayout, 600, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sorting Tool");
        primaryStage.show();
    }

    private int getAlgorithmIndex(String algorithm) {
        switch (algorithm) {
            case "Bubble Sort": return 1;
            case "Counting Sort": return 2;
            case "Quick Sort": return 3;
            case "Selection Sort": return 4;
            case "Heap Sort": return 5;
            case "Radix Sort": return 6;
            case "Merge Sort": return 7;
            case "Insertion Sort": return 8;
            default: return -1;
        }
    }

    private LineChart<String, Number> createGraph(ArrayList<String> algorithms, int[] dummyArray, StepCounter stepCounter, int size, int stepSize, int[] steps, int selectedCase, double[] asymptoticEfficiencies) {
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Step Size");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Steps and Asymptotic Efficiency");

        for (String algorithm : algorithms) {
            int algorithmIndex = getAlgorithmIndex(algorithm);
            Calculate.calculate(algorithmIndex, dummyArray, stepCounter, size, steps, selectedCase, asymptoticEfficiencies);

            XYChart.Series<String, Number> stepSeries = new XYChart.Series<>();
            stepSeries.setName(algorithm + " Steps");

            XYChart.Series<String, Number> efficiencySeries = new XYChart.Series<>();
            efficiencySeries.setName(algorithm + " Efficiency");

            for (int i = stepSize, j = 0; i <= size; i += stepSize, j++) {
                stepSeries.getData().add(new XYChart.Data<>(String.valueOf(i), steps[j]));
                efficiencySeries.getData().add(new XYChart.Data<>(String.valueOf(i), asymptoticEfficiencies[j]));
            }

            lineChart.getData().add(stepSeries);
            lineChart.getData().add(efficiencySeries);
        }

        return lineChart;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
