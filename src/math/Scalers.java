package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static data.DataFrameReader.*;
import static math.Statistics.*;

public class Scalers {

    /**
     * {@code StandardScale} is a method to find the scaling of the dataframe by columns
     * It will standardize all the data by subtracting the data with mean and divide it by the standard deviation
     *
     */
    public static void standardScale(){
        float [] mean = mean();
        float [] sd = sd();
        List<float[]> sdScale = new ArrayList<>();

        int z = 0;
        for(int i = 0; i< calculate.size(); i++){
            float [] insert = new float[calculate.get(i).length];
            float[] temp = calculate.get(i);
            for(int j = 0; j<temp.length; j++){
                insert[j] = (temp[j] - mean[z]) / sd[z];
            }

            z++;
            sdScale.add(insert);
        }
        System.out.println(print(sdScale));

    }

    /**
     * {@code minMaxScaling} is a scaler where it will normalize all the data that have been stored into {@code calculate}
     * Normalization is used in the minMaxScaling where it will subtract the minimum from the given data and
     * divide it by the maximum
     *
     * Yet it has a limitations where the result will be affected by the outliers
     *
     * If you want to remove the outliers to have a result, see here also.
     * @See robustScale()
     */
    public static void minMaxScale(){
        float [] range = range();
        float [] min = min();
        List<float[]> minMaxScale = new ArrayList<>();

        int z = 0;
        for(int i = 0; i< calculate.size(); i++){
            float [] insert = new float[calculate.get(i).length];
            float[] temp = calculate.get(i);
            for(int j = 0; j<temp.length; j++){
                insert[j] = (temp[j] - min[z]) / range[z];
            }

            z++;
            minMaxScale.add(insert);
        }

        System.out.println(print(minMaxScale));
    }

    /**
     * {@code robustScale} is a method where it will remove the limitations of {@code minMaxScaling}
     * {@code minMaxScaling} have a limitations when the data have an outlier
     * {@code robustScale} uses the median and interquartile range to remove the outlier
     * It will subtract the data with interquartile range and divide it by the median
     *
     */
    public static void robustScale(){
        float [] interquartile = interquartile();
        float [] median = median();
        List<float[]> robustScale = new ArrayList<>();
        List<float[]> tempFloatData = new ArrayList<>(Statistics.calculate);

        int z = 0;
        for(int i = 0; i< tempFloatData.size(); i++){
            float [] insert = new float[tempFloatData.get(i).length];
            float[] temp = tempFloatData.get(i);
            for(int j = 0; j<temp.length; j++){
                insert[j] = (temp[j] - median[z]) / interquartile[z];
            }

            z++;
            robustScale.add(insert);
        }

        System.out.println(print(robustScale));
    }

    private static String print(List<float[]> scale){
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< header.size(); i++){
            sb.append(String.format("%-20s", header.get(i)));
        }
        sb.append("\n");

        int size = scale.get(0).length;

        for(int i = 0; i<size; i++){

            for(int j = 0; j< scale.size(); j++){
                float[] temp = scale.get(j);
                sb.append(String.format("%-20s", temp[i]));
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}
