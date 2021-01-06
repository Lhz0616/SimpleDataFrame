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
    static rowCol rc = new rowCol();

    /**
     * {@code calculate} is the variable to store the float number that is available in the
     * dataframe. {@code calculate} will be used to conduct all the mathematical operations.
     */
    public static List<float[]> calculate = new ArrayList<>();

    /**
     *
     */
    static {
        COLUMNDATA = rc.getCOLUMNDATA();
        changeToFloat();
    }

    /**
     * {@code variance} will calculate the variance by column from the {@code calculate} ArrayList
     *
     * @return an Array of {@code float} numbers (by column)
     */
    public static float[] variance() {
        float[] mean = mean();
        float[] variance = new float[mean.length];

        for (int start = 0; start < variance.length; start++) {
            float[] temp = calculate.get(start);
            for (int j = 0; j < temp.length; j++) {
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
    public static float[] sd() {
        float[] variance = variance();
        float[] sd = new float[variance.length];
        for (int i = 0; i < sd.length; i++) {
            sd[i] = (float) Math.sqrt(variance[i]);
        }
        return sd;
    }

    /**
     * {@code min} will be used to find the minimum number by column from the {@code calculate} ArrayList
     *
     * @return an Array of {@code float} numbers
     */
    public static float[] min() {
        int minIndex = 0;
        float[] min = new float[calculate.size()];
        for (int i = 0; i < calculate.size(); i++) {
            float[] temp = calculate.get(i);
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] < temp[minIndex]) minIndex = j;
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
    public static float[] max() {
        int maxIndex = 0;
        float[] max = new float[calculate.size()];
        for (int i = 0; i < calculate.size(); i++) {
            float[] temp = calculate.get(i);
            for (int j = 0; j < temp.length; j++) {
                if (temp[j] > temp[maxIndex]) maxIndex = j;
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
    public static float[] mean() {
        int size = calculate.size();
        float[] avg = new float[size];

        for (int j = 0; j < calculate.size(); j++) {
            avg[j] = sum(calculate.get(j)) / calculate.get(j).length;
        }
        return avg;
    }


    public static String[] mode() {
//        System.out.println(COLUMNDATA);
        int numCol = COLUMNDATA.size();
        String[] Mode = new String[numCol];

        for (int i = 0; i < COLUMNDATA.size(); i++) {
            List<String> currentColumn = COLUMNDATA.get(i);
            String tempModeString = "";
            float tempModeFloat = 0;
            int highestCount = 0;
            Boolean allFloat = true;

            for (int z = 0; z < currentColumn.size(); z++) {
                if (!isFloat(currentColumn.get(z))) {   //check if each data is float number
                    allFloat = false;   //break and return false value if non-float is found
                    break;
                }
            }
            if (allFloat) { //mode finding process if the column is numbers
                float[] temp = calculate.get(i);
                for (int j = 0; j < temp.length; j++) {
                    Float temp1 = temp[j];
                    int tempCount = 0;

                    for (int k = 0; k < temp.length; k++) {
                        Float temp2 = temp[k];
                        if (Float.compare(temp1,temp2) == 0) {
                            tempCount++;

                        }
                    }   //end counting frequency loop
                    if (tempCount > highestCount) {     //update highestCount & mode Float
                        highestCount = tempCount;
                        tempModeFloat = temp1;
                    }
                }
                if (highestCount > 1) {     //check whether the mode is true mode
                    if (tempModeFloat % 1.0 != 0)
                        Mode[i] = String.format("%s", tempModeFloat);
                    else
                        Mode[i] = String.format("%.0f", tempModeFloat);
                } else {
                    Mode[i] = "No Mode";
                }
            } else {    //mode finding process if the column is not numbers
                for (int j = 0; j < currentColumn.size(); j++) {
                    String temp1 = currentColumn.get(j);
                    int tempCount = 0;

                    for (int k = 0; k < currentColumn.size(); k++) {
                        String temp2 = currentColumn.get(k);

                        if (temp1.equals(temp2)) {
                            tempCount++;
                        }
                    }   //end counting frequency loop
                    if (tempCount > highestCount) {     //update highestCount & mode String
                        highestCount = tempCount;
                        tempModeString = temp1;
                    }
                }   //end finding mode in the column
                if (highestCount > 1) {     //check whether the mode is true mode
                    Mode[i] = tempModeString;
                } else {
                    Mode[i] = "No Mode";
                }
            }
        }   //end Column loop
        return Mode;
    }

    /**
     * {@code median} is used to calculate the median of the dataframe by column
     *
     * @return an Array of median of the columns in {@code float}
     */
    public static float[] median() {
        float[] medianArr = new float[calculate.size()];
        for (int i = 0; i < calculate.size(); i++) {
            float[] temp = calculate.get(i);
            Arrays.sort(temp);
            if (temp.length % 2 == 1) {
                int median = (temp.length + 1) / 2 - 1;
                medianArr[i] = temp[median];
            } else {
                int median = temp.length / 2 - 1;
                float mean = (temp[median] + temp[median + 1]) / 2;
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
    public static float[] range() {
        float[] max = max();
        float[] min = min();
        float[] range = new float[calculate.size()];

        for (int i = 0; i < range.length; i++) {
            range[i] = max[i] - min[i];
        }

        return range;
    }

    /**
     * {@code interquartile} is a method that will sort the {@code calculate} and calculate the interquartile range
     *
     * @return an Array of {@code float} (take note that it will output the float number in ascending order
     */
    public static float[] interquartile() {
        float[] interquartile = new float[calculate.size()];

        for (int i = 0; i < calculate.size(); i++) {
            float[] temp = calculate.get(i);
            Arrays.sort(temp);
            if ((temp.length + 1) % 4 == 0) {
                int firstQuartTerm = (temp.length + 1) / 4;
                int thirdQuartTerm = (temp.length + 1) * 3 / 4;
                float quartileRange = temp[thirdQuartTerm] - temp[firstQuartTerm];
                interquartile[i] = quartileRange;
            } else {
                int firstQuartTerm = (int) Math.floor((temp.length + 1) / 4) - 1;
                int thirdQuartTerm = (int) Math.floor((temp.length + 1) * 3 / 4) - 1;
                float firstQuart = (temp[firstQuartTerm] + temp[firstQuartTerm + 1]) / 2;
                float thirdQuart = (temp[thirdQuartTerm] + temp[thirdQuartTerm + 1]) / 2;
                interquartile[i] = thirdQuart - firstQuart;
            }
        }
        return interquartile;
    }

    /**
     * {@code sum} is used to calculate the sum of the data by column
     *
     * @param numbers an Array that has been converted to {@code float}
     * @return a sum with the type of {@code float}
     */
    private static float sum(float[] numbers) {
        float sum = 0;
        for (int first = 0; first < numbers.length; first++) {
            sum += numbers[first];
        }
        return sum;
    }

    /**
     * {@code isFloat} is used to check whether a certain cell is able to
     * convert to a {@code float}
     *
     * @param str the String from the {@code COLUMNDATA}
     * @return a boolean that determine whether it is able to convert
     */
    private static boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * {@code changeToFloat} is a private method that will check for data that can change to {@code float} and
     * save into {@code calculate}
     */

    private static void changeToFloat() {
        List<float[]> floatN = new ArrayList<>();

        for (int i = 0; i < COLUMNDATA.size(); i++) {
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
        calculate = floatN;
    }

}
