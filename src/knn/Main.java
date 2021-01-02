package knn;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        //This part is the demo for knn.KNN Classification problem, uncomment to test it, dont uncomment both 2 parts at the same time
        /*
        List<List<String>> dataframe = readCSV("C:/Users/Chest/Downloads/iris.csv");
        double[][] dataset = convert2D_StringArrayListTo2D_DoubleArray(dataframe);
        double[][][] splittedDataset = trainTestSplit(dataset, 0.25);
        double[][] trainDataset = splittedDataset[0];
        double[][] testDataset = splittedDataset[1];
        System.out.println("Train dataset : ");
        displayDataset(trainDataset);
        System.out.println("Test dataset : ");
        displayDataset(testDataset);
        int[] y_actual = getLastIntColumnOfDataset(testDataset);
        int[] y_prediction = KNNClassifier(trainDataset, testDataset, 5);
        System.out.println("Actual y values : " + Arrays.toString(y_actual));
        System.out.println("Predicted y values : " + Arrays.toString(y_prediction));
        System.out.println("Classification Accuracy = " + classificationAccuracy(y_actual, y_prediction));
        System.out.println("F1-score = " + F1_Score(y_actual, y_prediction, 3));
        System.out.println("Classification Accuracy with k-Fold Cross-Validation Split = " + classificationAccuracyWithKFoldCrossValidationSplit(dataset, 10, 5));
         */


        //This part is the demo for knn.KNN Regression problem, uncomment to test it
        /*
        List<List<String>> dataframe = readCSV("C:/Users/Chest/Downloads/real estate.csv");
        double[][] dataset = convert2D_StringArrayListTo2D_DoubleArray(dataframe);
        double[][][] splittedDataset = trainTestSplit(dataset, 0.25);
        double[][] trainDataset = splittedDataset[0];
        double[][] testDataset = splittedDataset[1];
        System.out.println("Train dataset : ");
        displayDataset(trainDataset);
        System.out.println("Test dataset : ");
        displayDataset(testDataset);
        double[] y_actual = getLastDoubleColumnOfDataset(testDataset);
        double[] y_prediction = KNNRegressor(trainDataset, testDataset, 5);
        System.out.println("Actual y values : " + Arrays.toString(y_actual));
        System.out.println("Predicted y values : " + Arrays.toString(y_prediction));
        System.out.println("Mean Squared Error = " + meanSquaredError(y_actual, y_prediction));
        System.out.println("R Squared = " + R_Squared(y_actual, y_prediction));
        System.out.println("adjusted R Squared = " + adjustedR_Squared(trainDataset, testDataset, 5));
         */

    }
}
