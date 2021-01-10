package com.ML;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import static com.ML.K_NearestNeighbours.*;

public class NaiveBayesClassifier {
    //Get the specified index of column of dataset in type double, index 0 = first column
    public static double[] getSpecifiedDoubleColumnOfDataset(double[][] dataset, int index){
        double[] column = new double[dataset.length];
        for (int i = 0; i < dataset.length; i++){
            column[i] = dataset[i][index];
        }
        return column;
    }

    //Calculate mean of given column
    public static double mean(double[] X_Column){
        double sum = 0;
        for (int i=0; i<X_Column.length; i++){
            sum += X_Column[i];
        }
        return sum / X_Column.length;
    }

    //Calculate standard deviation of given column
    public static double standardDeviation(double[] X_Column){
        double mean = mean(X_Column);
        double sumOfSquares = 0;
        for (int i=0; i<X_Column.length; i++){
            sumOfSquares += (X_Column[i] - mean) * (X_Column[i] - mean);
        }
        return Math.sqrt(sumOfSquares / (X_Column.length - 1));
    }

    //Calculate Gaussian Probability Distribution Function
    public static double probability(double x, double mean, double standardDeviation){
        return (1/(Math.sqrt(2*Math.PI)*standardDeviation))*Math.exp(-((x-mean)*(x-mean))/(2*standardDeviation*standardDeviation));
    }

    //Naive Bayes Classifier
    //Calculate the probability of given data belonging to each class using simplified formula of Bayes' Theorem
    //Example: P(class=0|X1,X2) = P(X1|class=0) * P(X2|class=0) * P(class=0)
    //Use the class with the highest probability as prediction
    public static int[] NBClassifier(double[][] trainDataset, double[][] testDataset){
        //get the number of classes
        int[] y = getLastIntColumnOfDataset(trainDataset);
        ArrayList<Integer> classValues = new ArrayList<Integer>();
        classValues.add(y[0]);
        int counter = 0;
        for (int i=1; i<y.length; i++){
            counter = 0;
            for (int j=0; j<classValues.size(); j++){
                if (y[i] != classValues.get(j)){
                    ++counter;
                }
            }
            if (counter == classValues.size()){
                classValues.add(y[i]);
            }
        }
        //get the number of elements of each class
        int[] numberOfElementOfEachClass = new int[classValues.size()];
        for (int i=0; i<y.length; i++){
            for (int j=0; j<classValues.size(); j++){
                if (y[i] == classValues.get(j)){
                    numberOfElementOfEachClass[classValues.get(j)]++;
                    break;
                }
            }
        }
        //sort the dataset according to the class values
        Arrays.sort(trainDataset, Comparator.comparingDouble(o -> o[trainDataset[0].length-1]));
        //calculate the mean and standard deviation of each variable for each class
        //calculate the probability of each class, P(class=Y) , Y=0,1,2,...
        int counter2 = 0;
        double[] columnPerClass;
        double[][] standardDeviationForClasses = new double[classValues.size()][trainDataset[0].length-1];
        double[][] meanForClasses = new double[classValues.size()][trainDataset[0].length-1];
        double[] probabilityOfClass = new double[classValues.size()];
        for(int i=0; i<classValues.size(); i++){
            probabilityOfClass[i] = (double) numberOfElementOfEachClass[i] / trainDataset.length;
            counter2 += numberOfElementOfEachClass[i];
            for(int k=0; k < trainDataset[0].length-1; k++){
                columnPerClass = getSpecifiedDoubleColumnOfDataset(Arrays.copyOfRange(trainDataset, counter2-numberOfElementOfEachClass[i], counter2), k);
                standardDeviationForClasses[i][k] = standardDeviation(columnPerClass);
                meanForClasses[i][k] = mean(columnPerClass);
            }
        }
        //calculate the probability belongs to each class
        double temp;
        double[][] probabilities = new double[testDataset.length][classValues.size()];
        for(int i=0; i<testDataset.length; i++){
            for(int j=0; j<classValues.size(); j++){
                temp = 1;
                for(int k=0; k<trainDataset[0].length-1; k++){
                    temp *= probability(testDataset[i][k], meanForClasses[j][k], standardDeviationForClasses[j][k]);
                }
                probabilities[i][j] = temp * probabilityOfClass[j];
            }
        }
        //return the class with the highest probability as prediction
        double max;
        int[] prediction = new int[testDataset.length];
        for(int i = 0; i < testDataset.length; i++){
            max = probabilities[i][0];
            for(int j = 1; j < classValues.size(); j++){
                if(probabilities[i][j] > max){
                    max = probabilities[i][j];
                    prediction[i] = j;
                }
            }
        }
        return prediction;
    }
}
