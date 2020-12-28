package math;

import data.DataFrameReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Statistics implements InterfaceStatistics{

    DataFrameReader r = new DataFrameReader();
    public static List<List<String>> COLUMNDATA = new ArrayList<>();;
    public static List<float[]> calculate = new ArrayList<>();

    public Statistics() {

        int numCols = r.DATA.get(1).size();
        ArrayList<String> rowData = new ArrayList();
        for(int j = 0; j<numCols; j++) {
            rowData.clear();
            for (int z = 1; z < r.DATA.size(); z++) {
                String[] temp = r.DATA.get(z).toArray(new String[0]);
                String temp1 = temp[j];
                rowData.add(temp1);
            }
            COLUMNDATA.add((List<String>) rowData.clone());
        }

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
                calculate.add(temp1);
            } catch (NullPointerException e) {
                return;
            }
        }
    }

    @Override
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

    @Override
    public float[] sd() {
        float [] variance = variance();
        float [] sd = new float[variance.length];
        for(int i = 0; i<sd.length; i++){
            sd[i] = (float) Math.sqrt(variance[i]);
        }
        return sd;
    }

    @Override
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

    @Override
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

    @Override
    public float[] mean() {
        int size = calculate.size();
        float[] avg = new float[size];

        for(int j = 0; j<calculate.size(); j++){
            avg[j] = sum(calculate.get(j))/calculate.get(j).length;
        }
        return avg;
    }

    @Override
    public float mode() {
        return 0;
    }

    @Override
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

    @Override
    public float[] range() {
        float[] max = max();
        float [] min = min();
        float [] range = new float[calculate.size()];

        for(int i = 0; i<range.length; i++){
            range[i] = max[i] - min[i];
        }

        return range;
    }

    private boolean isFloat(String str){
        try{
            Float.parseFloat(str);
        } catch (NumberFormatException e){
            return false;
        }
        return true;
    }

    private float sum(float [] numbers){
        float sum = 0;
        for(int first = 0; first<numbers.length; first++){
            sum+= numbers[first];
        }
        return sum;
    }

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
}
