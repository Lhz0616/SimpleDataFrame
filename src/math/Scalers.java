package math;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static math.Statistics.calculate;

public class Scalers {

    /**
     * {@code StandardScale} is a method to find the scaling of the dataframe by columns
     *
     * @return a List of float arrays of the value of the scalers by column
     */
    public static void standardScale(){
        float [] mean = Statistics.mean();
        float [] sd = Statistics.sd();
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
        for(int first = 0; first< sdScale.size(); first++){
            float [] temp = sdScale.get(first);
            if(first < sdScale.size()-1 ) System.out.print(Arrays.toString(temp) + ", ");
            else System.out.print(Arrays.toString(temp));
        }
        System.out.println();

    }

    public static void minMaxScaling(){
        float [] range = Statistics.range();
        float [] min = Statistics.min();
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

        for(int first = 0; first< minMaxScale.size(); first++){
            float [] temp = minMaxScale.get(first);
            if(first < minMaxScale.size()-1 ) System.out.print(Arrays.toString(temp) + ", ");
            else System.out.print(Arrays.toString(temp));
        }
        System.out.println();
    }

    public static void robustScale(){
        float [] interquartile = Statistics.interquartile();
        float [] median = Statistics.median();
        List<float[]> robustScale = new ArrayList<>();

        int z = 0;
        for(int i = 0; i< calculate.size(); i++){
            float [] insert = new float[calculate.get(i).length];
            float[] temp = calculate.get(i);
            for(int j = 0; j<temp.length; j++){
                insert[j] = (temp[j] - median[z]) / interquartile[z];
            }

            z++;
            robustScale.add(insert);
        }

        for(int first = 0; first< robustScale.size(); first++){
            float [] temp = robustScale.get(first);
            if(first < robustScale.size()-1 ) System.out.print(Arrays.toString(temp) + ", ");
            else System.out.print(Arrays.toString(temp));
        }
        System.out.println();
    }
}
