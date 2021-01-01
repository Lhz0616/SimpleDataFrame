package math;

import data.rowCol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code Statistics} will be the class that can perform all the mathematical operations
 * {@code mean, variance, standard deviation, mode, median}
 * All of the mathematical operation will use {@code COLUMNDATA} to conduct
 * the mathematical operation
 */
public class Statistics {

    /**
     * {@code COLUMNDATA} is used to store the data in the column
     */
    public static List<List<String>> COLUMNDATA = new ArrayList<>();

    /**
     * {@code rc} is used to access the {@code getCOLUMNDATA} method to get COLUMNDATA
     */
    rowCol rc = new rowCol();

    /**
     * {@code calculate} is the variable to store the float number that is available in the
     * dataframe. {@code calculate} will be used to conduct all the mathematical operations.
     */
    public static List<float[]> calculate = new ArrayList<>();

    /**
     * {@code Statistics} constructor will get the COLUMNDATA from the rowCol class
     * and it will conduct the {@code changeToFloat} method to store all the
     * numbers that can convert to float number into the {@code calculate}
     */
    public Statistics(){
        COLUMNDATA = rc.getCOLUMNDATA();
        calculate = changeToFloat();
    }


    /**
     * {@code variance} will calculate the variance by column from the {@code calculate} ArrayList
     *
     * @return an Array of {@code float} numbers (by column)
     */
    public float[] variance() {
        float [] mean = mean();
        float [] variance = new float[mean.length];

        for(int start = 0; start<variance.length; start++){
            float [] temp = calculate.get(start);
            for(int j = 0; j<temp.length; j++) {
                variance[start] += Math.pow(temp[j] - mean[start], 2);
            }
            variance[start] /= temp.length;
        }
        return variance;
    }

    /**
     * {@code sd} will calculate the standard deviation by column from the {@code calculate} ArrayList
     *
     * @return an Array of {@code float} numbers (by column)
     */
    public float[] sd() {
        float [] variance = variance();
        float [] sd = new float[variance.length];
        for(int i = 0; i<sd.length; i++){
            sd[i] = (float) Math.sqrt(variance[i]);
        }
        return sd;
    }

    /**
     * {@code min} will be used to find the minimum number by column from the {@code calculate} ArrayList
     *
     * @return an Array of {@code float} numbers
     */
    public float[] min() {
        int minIndex = 0;
        float [] min = new float[calculate.size()];
        for(int i = 0; i<calculate.size(); i++){
            float [] temp = calculate.get(i);
            for(int j = 0; j<temp.length; j++){
                if(temp[j]<temp[minIndex]) minIndex = j;
            }

            min[i] = temp[minIndex];
        }
        return min;
    }

    /**
     * {@code max} will be used to find the maximum number by column from the {@code calculate} ArrayList
     *
     * @return an Array of {@code float} numbers
     */
    public float[] max() {
        int maxIndex = 0;
        float [] max = new float[calculate.size()];
        for(int i = 0; i<calculate.size(); i++){
            float [] temp = calculate.get(i);
            for(int j = 0; j<temp.length; j++){
                if(temp[j]>temp[maxIndex]) maxIndex = j;
            }

            max[i] = temp[maxIndex];
        }
        return max;
    }

    /**
     * {@code mean} is used to calculate the mean by column from the {@code calculate} ArrayList
     *
     * @return an Array of mean of the columns in {@code float}
     */
    public float[] mean() {
        int size = calculate.size();
        float[] avg = new float[size];

        for(int j = 0; j<calculate.size(); j++){
            avg[j] = sum(calculate.get(j))/calculate.get(j).length;
        }
        return avg;
    }

    public float mode() {
        return 0;
    }

    /**
     * {@code median} is used to calculate the median of the dataframe by column
     *
     * @return  an Array of median of the columns in {@code float}
     */
    public float[] median() {
        float [] medianArr = new float[calculate.size()];
        for(int i = 0; i<calculate.size(); i++){
            float [] temp = calculate.get(i);
            Arrays.sort(temp);
            if(temp.length%2 == 1){
                int median = (temp.length+1)/2 - 1;
                medianArr[i] = temp[median];
            }
            else{
                int median = temp.length/2 - 1;
                float mean = (temp[median] + temp[median+1])/2;
                medianArr[i] = mean;
            }
        }
        return medianArr;
    }

    /**
     * {@code range} is used to calculate the difference between the minimum and the maximum
     * of the dataframe by columns
     *
     * @return an Array of the range by columns in {@code float}
     */
    public float[] range() {
        float[] max = max();
        float [] min = min();
        float [] range = new float[calculate.size()];

        for(int i = 0; i<range.length; i++){
            range[i] = max[i] - min[i];
        }

        return range;
    }

    /**
     * {@code StandardScale} is a method to find the scaling of the dataframe by columns
     *
     * @return a List of float arrays of the value of the scalers by column
     */
    public List<float[]> StandardScale(){
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
        return sdScale;
    }

    public float[] minMaxScaling(){

        return new float[]{};
    }

    /**
     * {@code isFloat} is used to check whether a certain cell is able to
     * convert to a {@code float}
     *
     * @param   str the String from the {@code COLUMNDATA}
     * @return  a boolean that determine whether it is able to convert
     */
    private boolean isFloat(String str){
        try{
            Float.parseFloat(str);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    /**
     * {@code sum} is used to calculate the sum of the data by column
     *
     * @param numbers   an Array that has been converted to {@code float}
     * @return          a sum with the type of {@code float}
     */
    private float sum(float [] numbers){
        float sum = 0;
        for(int first = 0; first<numbers.length; first++){
            sum+= numbers[first];
        }
        return sum;
    }

    /**
     *
     *
     * @return
     */
    private List<float[]> changeToFloat(){
        List<float[]> floatN = new ArrayList<>();

        for(int i = 0; i<COLUMNDATA.size(); i++) {
            List<String> temp = COLUMNDATA.get(i);
            float[] temp1 = new float[temp.size()];
            int j = 0;
            for (int start = 0; start < temp.size(); start++) {
                String temp2 = temp.get(start);
                if (isFloat(temp2)) {
                    temp1[j] = Float.parseFloat(temp2);
                    j++;
                }
            }
            try {
                floatN.add(temp1);
            } catch (NullPointerException e) {

            }

        }
        return floatN;
    }

}
