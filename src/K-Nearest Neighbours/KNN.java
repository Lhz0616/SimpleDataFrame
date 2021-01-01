package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Math;
import java.lang.String;
import java.lang.System;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class KNN {
    //Read csv file as 2-dimensional String arraylist
    public static List<List<String>> readCSV(String filePath) {
        char separator = ',';
        String line;
        List<List<String>> dataframe = new ArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                List<String> values = new ArrayList(Arrays.asList(line.split(String.valueOf(separator))));
                dataframe.add(values);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return dataframe;
    }

    //Convert 2-dimensional String arraylist to 2-dimensional Double array
    public static double[][] convert2D_StringArrayListTo2D_DoubleArray(List<List<String>> dataframe){
        int numOfRows = dataframe.size();
        int numOfColumns = dataframe.get(0).size();
        double[][] dataset = new double[numOfRows][numOfColumns];
        for(int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfColumns; j++){
                dataset[i][j] = Double.parseDouble(dataframe.get(i).get(j));
            }
        }
        return dataset;
    }

    //Get the last column values of dataset input in type double
    public static double[] getLastDoubleColumnOfDataset(double[][] dataset){
        double[] lastColumn = new double[dataset.length];
        for (int i = 0; i < dataset.length; i++){
            lastColumn[i] = dataset[i][dataset[0].length-1];
        }
        return lastColumn;
    }

    //Get the last column values of dataset input in type integer
    public static int[] getLastIntColumnOfDataset(double[][] dataset){
        int[] lastColumn = new int[dataset.length];
        for (int i = 0; i < dataset.length; i++){
            lastColumn[i] = (int) dataset[i][dataset[0].length-1];
        }
        return lastColumn;
    }

    //Display the input 2D dataset
    public static void displayDataset(double[][] dataset){
        System.out.println(Arrays.deepToString(dataset).replace("], ", "]\n"));
    }

    //To scale the dataset to range 0-1 using mix-max scaling method
    //Why scale? To prevent the algorithm from being biased towards variables with higher magnitude
    //So that every variable is given the same weightage by scaling it
    public static void minmaxScaling(double[][] trainDataset, double[][] testDataset){
        //create min and max array to store the mix and max values for each feature column
        double[] min = new double[trainDataset[0].length-1];
        double[] max = new double[trainDataset[0].length-1];
        //combine the trainDataset and testDataset to scale together
        double[][] dataset = new double[trainDataset.length + testDataset.length][trainDataset[0].length-1];
        for(int i = 0; i < trainDataset.length; i++){
            dataset[i] = trainDataset[i];
        }
        for(int i = 0; i < testDataset.length; i++){
            dataset[i+ trainDataset.length] = testDataset[i];
        }
        //find the mix and max values for each feature column
        for(int i = 0; i < trainDataset[0].length-1; i++){
            min[i] = dataset[0][i];
            max[i] = dataset[0][i];
            for(int j = 1; j < dataset.length; j++){
                if(dataset[j][i] < min[i]){
                    min[i] = dataset[j][i];
                }
                if(dataset[j][i] > max[i]){
                    max[i] = dataset[j][i];
                }
            }
        }
        //scale the dataset using min-max scaling formula
        for(int i = 0; i < trainDataset[0].length-1; i++){
            for(int j = 0; j < dataset.length; j++){
                dataset[j][i] = (dataset[j][i] - min[i]) / (max[i] - min[i]);
            }
        }
    }

    //To calculate the Euclidean Distance between 2 points using the formula
    public static double EuclideanDistance(double[] trainSet, double[] testSet){
        double distanceSquares = 0;
        for(int i = 0; i < trainSet.length; i++){
            distanceSquares += (trainSet[i]-testSet[i])*(trainSet[i]-testSet[i]);
        }
        return Math.sqrt(distanceSquares);
    }

    //To find the mode of the given array
    public static int mode(int[] x) {
        int maxValue = 0, maxCount = 0;
        for (int i = 0; i < x.length; i++) {
            int count = 0;
            for (int j = 0; j < x.length; j++) {
                if (x[j] == x[i]) {
                    ++count;
                }
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = x[i];
            }
        }
        return maxValue;
    }

    //To get n nearest neighbours values
    //mix-max scaling is used for the dataset
    public static double[][] getNearestNeighbours(double[][] trainDataset, double[][] testDataset, int numOfNearestNeighbours){
        minmaxScaling(trainDataset, testDataset);
        double[][] neighbours = new double[trainDataset.length][2];
        double[] selectedNearestNeighbours = new double[numOfNearestNeighbours];
        double[][] selectedNearestNeighboursList = new double[testDataset.length][numOfNearestNeighbours];
        for(int i = 0; i < testDataset.length; i++) {
            for (int j = 0; j < trainDataset.length; j++) {
                neighbours[j][0] = EuclideanDistance(Arrays.copyOfRange(trainDataset[j], 0, trainDataset[0].length - 1), Arrays.copyOfRange(testDataset[i], 0, trainDataset[0].length - 1));
                neighbours[j][1] = trainDataset[j][trainDataset[0].length - 1];
            }
            Arrays.sort(neighbours, Comparator.comparingDouble(o -> o[0]));
            for(int j = 0; j < numOfNearestNeighbours; j++){
                selectedNearestNeighbours[j] = neighbours[j][1];
            }
            selectedNearestNeighboursList[i] = selectedNearestNeighbours.clone();
        }
        return selectedNearestNeighboursList;
    }

    /*
    K-Nearest Neighbours Classifier
    Output a column of predicted values for the test dataset
    Suggested number of nearest neighbours = 5
     */
    public static int[] KNNClassifier(double[][] trainDataset, double[][] testDataset, int numOfNearestNeighbours){
        double[][] selectedNearestNeighboursDouble = getNearestNeighbours(trainDataset, testDataset, numOfNearestNeighbours);
        int[][] selectedNearestNeighboursInt = new int[testDataset.length][numOfNearestNeighbours];
        for(int i = 0; i < testDataset.length; i++){
            for(int j = 0; j < numOfNearestNeighbours; j++){
                selectedNearestNeighboursInt[i][j] = (int) selectedNearestNeighboursDouble[i][j];
            }
        }
        int[] modeColumn = new int[testDataset.length];
        for(int i = 0; i < testDataset.length; i++){
            modeColumn[i] = mode(selectedNearestNeighboursInt[i]);
        }
        return modeColumn;
    }

    /*
    K-Nearest Neighbours Regressor
    Output a column of predicted values for the test dataset
    Suggested number of nearest neighbours = 5
     */
    public static double[] KNNRegressor(double[][] trainDataset, double[][] testDataset, int numOfNearestNeighbours){
        double[][] selectedNearestNeighbours = getNearestNeighbours(trainDataset, testDataset, numOfNearestNeighbours);
        double sum;
        double[] meanColumn = new double[testDataset.length];
        for(int i = 0; i < testDataset.length; i++){
            sum = 0;
            for(int j = 0; j < numOfNearestNeighbours; j++){
                sum += selectedNearestNeighbours[i][j];
            }
            meanColumn[i] = sum / numOfNearestNeighbours;
        }
        return meanColumn;
    }

    /*
    To split the input dataset into train dataset and test dataset
    The dataset is shuffled randomly before splitting the dataset
    testDatasetSize is the proportion of the dataset to be included in test dataset
    Suggested range of testDatasetSize: 0.2 - 0.4. suggested value = 0.25
    Return value is a 3D array, index 0 of the array is the train dataset and index 1 is the test dataset
     */
    public static double[][][] trainTestSplit(double[][] dataset, double testDatasetSize){
        double[][] shuffledDataset = new double[dataset.length][dataset[0].length];
        for(int i = 0; i < dataset.length; i++){
            for(int j = 0; j < dataset[0].length; j++){
                shuffledDataset[i][j]=dataset[i][j];
            }
        }
        Random rand = new Random();
        for (int i = shuffledDataset.length-1; i > 0; i--) {
            int j = rand.nextInt(i+1);
            double[] temp = shuffledDataset[i];
            shuffledDataset[i] = shuffledDataset[j];
            shuffledDataset[j] = temp;
        }
        int testSetSize = (int) (shuffledDataset.length * testDatasetSize);
        double[][] testSet = Arrays.copyOfRange(shuffledDataset, 0, testSetSize);
        double[][] trainSet = Arrays.copyOfRange(shuffledDataset, testSetSize, shuffledDataset.length);
        return new double[][][]{trainSet, testSet};
    }

    /*
    Error metric 1 for KNNClassifier:
    Classification Accuracy
    Check whether the prediction is correct or not by comparing it with the actual values,
    Then get the accuracy
    */
    public static float classificationAccuracy(int[] actual_y_values, int[] predicted_y_values){
        int correctCounter = 0;
        for (int i = 0; i < actual_y_values.length; i++){
            if(predicted_y_values[i] == actual_y_values[i]){
                correctCounter++;
            }
        }
        float accuracy = (float) correctCounter / actual_y_values.length;
        return accuracy;
    }

    /*
    Add-on to error metric 1 for KNNClassifier
    Classification Accuracy with k-Fold Cross-Validation Split
    Get the average accuracy of the KNNClassifier on the given dataset
    Use the k-Fold Cross-Validation Split for resampling the dataset
    Suggested value of k = 10, k is the number of folds, where k >= 2
    What this method does:
    Step 1: Shuffle the dataset randomly. (To prevent similar data stick together)
    Step 2: Split the dataset into k groups of datasets, for each unique group:
                (i) Use the group as the test dataset
                (ii) Combine the remaining groups as the train dataset
                (iii) Predict the values for test dataset based on the train dataset using KNNClassifier
                (iv) Check whether the prediction is correct or not by comparing it with the actual values
                (v) Get the accuracy of prediction for this group
    Step 3: Get the mean accuracy score
     */
    public static float classificationAccuracyWithKFoldCrossValidationSplit(double[][] dataset, int numOfFolds, int numOfNearestNeighbours){
        if(numOfFolds < 2){
            System.out.println("k must be larger than 1");
            return -1;
        }
        double[][] shuffledDataset = new double[dataset.length][dataset[0].length];
        for(int i = 0; i < dataset.length; i++){
            for(int j = 0; j < dataset[0].length; j++){
                shuffledDataset[i][j]=dataset[i][j];
            }
        }
        Random rand = new Random();
        for (int i = shuffledDataset.length-1; i > 0; i--) {
            int j = rand.nextInt(i+1);
            double[] temp = shuffledDataset[i];
            shuffledDataset[i] = shuffledDataset[j];
            shuffledDataset[j] = temp;
        }

        int foldSize = shuffledDataset.length / numOfFolds;
        float sumAccuracy = 0;
        for(int i = 0; i < foldSize * numOfFolds; i += foldSize){
            double[][] trainSet = new double[shuffledDataset.length-foldSize][shuffledDataset[0].length];
            double[][] testSet = new double[foldSize][shuffledDataset[0].length];
            for(int m = 0; m < i; m++){
                for(int n = 0; n < shuffledDataset[0].length; n++){
                    trainSet[m][n] = shuffledDataset[m][n];
                }
            }
            for(int m = foldSize + i; m < shuffledDataset.length; m++){
                for(int n = 0; n < shuffledDataset[0].length; n++){
                    trainSet[m-foldSize][n] = shuffledDataset[m][n];
                }
            }
            for(int m = i; m < foldSize + i; m++){
                for(int n = 0; n < shuffledDataset[0].length; n++){
                    testSet[m-i][n] = shuffledDataset[m][n];
                }
            }

            int[] actual = new int[foldSize];
            for (int k = 0; k < foldSize; k++) {
                actual[k] = (int) testSet[k][shuffledDataset[0].length - 1];
            }
            int[] prediction = KNNClassifier(trainSet, testSet, numOfNearestNeighbours);
            int correctCounter = 0;
            for (int j = 0; j < foldSize; j++){
                if(prediction[j] == actual[j]){
                    correctCounter++;
                }
            }
            sumAccuracy += (float) correctCounter / foldSize;
        }
        float averageAccuracy = sumAccuracy / numOfFolds;
        return averageAccuracy;
    }

    /*
    Error metric 2 for KNNClassifier:
    F1-score
    Calculate the F1-score using the F1-score formula
    For binary classification problem,
    the number of classes is 2.
    For multi-class classification problem,
    the number of classes depends on there are how many different y values.
    Example: y values have 0,1,2 : then number of classes = 3
             y values have 0,1,2,3,4 : then number of classes = 5
    What this method does:
    Step 1: Construct a confusion matrix based on predicted values and actual values
    Step 2: Two scenarios: (i) Binary classification problem (ii) Multi-class classification problem
            For (i) : If it is a binary classification problem:
                      Calculate the precision, recall and F1-score values based on formula and confusion matrix,
                      then return the F1-score
            For (ii) : If it is a multi-class classification problem:
                       Calculate the precision, recall and F1-score values for each class
                       based on formula and confusion matrix,
                       then return the mean F1-score
     */
    public static float F1_Score(int[] actual_y_values, int[] predicted_y_values, int numberOfClasses){
        int[][] confusionMatrix = new int[numberOfClasses][numberOfClasses];
        for(int i = 0; i < actual_y_values.length; i++){
            confusionMatrix[actual_y_values[i]][predicted_y_values[i]]++;
        }
        if(numberOfClasses == 2){
            float precision = confusionMatrix[0][0] / (confusionMatrix[0][0] + confusionMatrix[0][1]);
            float recall = confusionMatrix[0][0] / (confusionMatrix[0][0] + confusionMatrix[1][0]);
            float F1_Score = 2 * (precision * recall) / (precision + recall);
            return F1_Score;
        }
        else{
            float[] precision = new float[numberOfClasses];
            float[] recall = new float[numberOfClasses];
            float[] F1_Score = new float[numberOfClasses];
            float sumF1_Score = 0;
            for (int i = 0; i < numberOfClasses; i++){
                for (int j = 0; j < numberOfClasses; j++){
                    precision[i] += confusionMatrix[i][j];
                    recall[i] += confusionMatrix[j][i];
                }
                precision[i] = confusionMatrix[i][i] / precision[i];
                recall[i] = confusionMatrix[i][i] / recall[i];
                F1_Score[i] = 2 * (precision[i] * recall[i]) / (precision[i] + recall[i]);
                sumF1_Score += F1_Score[i];
            }
            float meanF1_Score = sumF1_Score / numberOfClasses;
            return meanF1_Score;
        }
    }

    /*
    Error metric 1 for KNNRegressor:
    Mean Squared Error
    The average of the squared difference between the actual value and the value predicted by the regression model
    It is calculated using the formula of Mean Squared Error
     */
    public static double meanSquaredError(double[] actual_y_values, double[] predicted_y_values){
        double sumOfSquares = 0;
        for (int i = 0; i < actual_y_values.length; i++) {
            sumOfSquares += (actual_y_values[i] - predicted_y_values[i]) * (actual_y_values[i] - predicted_y_values[i]);
        }
        double meanSquaredError = sumOfSquares / actual_y_values.length;
        return meanSquaredError;
    }

    /*
    Error metric 2 for KNNRegressor:
    R Squared
    It is calculated using the formula of R Squared
     */
    public static double R_Squared(double[] actual_y_values, double[] predicted_y_values){
        double sumOfSquares1 = 0;
        double meanOfActual = 0;
        for (int i = 0; i < actual_y_values.length; i++) {
            sumOfSquares1 += (actual_y_values[i] - predicted_y_values[i]) * (actual_y_values[i] - predicted_y_values[i]);
            meanOfActual += actual_y_values[i];
        }
        meanOfActual = meanOfActual / actual_y_values.length;
        double sumOfSquares2 = 0;
        for (int i = 0; i < actual_y_values.length; i++) {
            sumOfSquares2 += (actual_y_values[i] - meanOfActual) * (actual_y_values[i] - meanOfActual);
        }
        double R_Squared = 1 - (sumOfSquares1 / sumOfSquares2);
        return R_Squared;
    }

    /*
    Add-on to error metric 2 for KNNRegressor
    Adjusted R Squared
    It is calculated using the formula of Adjusted R Squared
     */
    public static double adjustedR_Squared(double[][] trainDataset, double[][] testDataset, int numOfNearestNeighbours){
        double[] prediction = KNNRegressor(trainDataset, testDataset, numOfNearestNeighbours);
        double[] actual = new double[testDataset.length];
        for (int i = 0; i < testDataset.length; i++) {
            actual[i] = testDataset[i][testDataset[0].length - 1];
        }
        double adjustedR_Squared = 1-((1-R_Squared(actual, prediction))*(testDataset.length-1)/(testDataset.length-(testDataset[0].length-1)-1));
        return adjustedR_Squared;
    }
}