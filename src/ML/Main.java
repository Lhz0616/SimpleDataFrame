package com.company;

import java.io.*;
import java.util.*;
import java.lang.Math;


public class Main {
    public static void main(String[] args) {
        //read csv file and convert to 2d String arraylist
        List<List<String>> dataframe = readCSV("C:/Users/Chest/Downloads/iris.csv");
        System.out.println(dataframe);

        //convert 2d String arraylist into 2d double array
        int numOfRows = dataframe.size();
        int numOfColumns = dataframe.get(0).size();
        double[][] dataset = new double[numOfRows][numOfColumns];
        for(int i = 0; i < numOfRows; i++){
            for(int j = 0; j < numOfColumns; j++){
                dataset[i][j] = Double.parseDouble(dataframe.get(i).get(j));
            }
        }
        System.out.println(Arrays.deepToString(dataset));

        minmaxScaling(dataset);
        System.out.println(Arrays.deepToString(dataset));
        double[][] datasetToPredict = Arrays.copyOfRange(dataset, 70, 75);
        System.out.println(Arrays.deepToString(datasetToPredict));
        int[][] predictionColumn = new int[datasetToPredict.length][1];
        for (int i = 0; i < datasetToPredict.length; i++){
            predictionColumn[i][0] = predict(dataset, datasetToPredict[i], 5);
        }
        System.out.println(Arrays.deepToString(predictionColumn));
        errorEvaluation(dataset, 10);
    }

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

    public static void minmaxScaling(double[][] dataset){
        double[] min = new double[dataset[0].length-1];
        double[] max = new double[dataset[0].length-1];
        for(int i = 0; i < dataset[0].length-1; i++){
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
        System.out.println(Arrays.toString(min));
        System.out.println(Arrays.toString(max));
        for(int i = 0; i < dataset[0].length-1; i++){
            for(int j = 0; j < dataset.length; j++){
                dataset[j][i] = (dataset[j][i] - min[i]) / (max[i] - min[i]);
            }
        }
    }

    public static double EuclideanDistance(double[] train, double[] test){
        double distanceSquares = 0;
        for(int i = 0; i < train.length; i++){
            distanceSquares += (train[i]-test[i])*(train[i]-test[i]);
        }
        return Math.sqrt(distanceSquares);
    }

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

    public static int predict(double[][] trainDataset, double[] testDataset, int numOfNeighbours){
        double[][] result = new double[trainDataset.length][2];
        for(int i = 0; i < trainDataset.length; i++){
            result[i][0] = EuclideanDistance(Arrays.copyOfRange(trainDataset[i], 0, trainDataset[0].length-1), Arrays.copyOfRange(testDataset, 0, trainDataset[0].length-1));
            result[i][1] = trainDataset[i][trainDataset[0].length-1];
        }
        Arrays.sort(result, Comparator.comparingDouble(o -> o[0]));
        //System.out.println(Arrays.deepToString(result));
        int[] neighbours = new int[numOfNeighbours];
        for(int i = 0; i < numOfNeighbours; i++){
            neighbours[i] = (int) result[i][1];
        }
        //System.out.println(Arrays.toString(neighbours));
        return mode(neighbours);
    }

    public static void errorEvaluation(double[][] dataset, int numOfFolds){
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
        System.out.println(Arrays.deepToString(shuffledDataset));
        System.out.println(" ");

        int foldSize = shuffledDataset.length / numOfFolds;
        float sumAccuracy = 0;
        for(int i = 0; i < foldSize * numOfFolds; i += foldSize){
            int[] actual = new int[foldSize];
            int[] prediction = new int[foldSize];
            double[][] trainSet = new double[shuffledDataset.length-foldSize][shuffledDataset[0].length];
            double[][] testSet = new double[foldSize][shuffledDataset[0].length];
            for (int k = 0; k < foldSize; k++) {
                actual[k] = (int) shuffledDataset[k + i][shuffledDataset[0].length - 1];
            }
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
            System.out.println(Arrays.deepToString(trainSet));
            System.out.println(Arrays.deepToString(testSet));

            int correctCounter = 0;
            for (int j = 0; j < foldSize; j++){
                prediction[j] = predict(trainSet, testSet[j], 5);
                if(prediction[j] == actual[j]){
                    correctCounter++;
                }
            }
            System.out.println(correctCounter);
            float accuracy = (float) correctCounter / foldSize;
            sumAccuracy += accuracy;
            System.out.println(Arrays.toString(actual));
            System.out.println(Arrays.toString(prediction));
            System.out.println(accuracy);
        }
        float averageAccuracy = sumAccuracy / numOfFolds;
        System.out.println("Accuracy : " + averageAccuracy * 100 + "%");
    }
}












