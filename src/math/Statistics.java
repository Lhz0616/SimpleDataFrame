package math;

import data.DataFrameReader;

import java.util.ArrayList;
import java.util.List;

public class Statistics implements InterfaceStatistics{

    DataFrameReader r = new DataFrameReader();
    public static List<List<String>> COLUMNDATA;
    public static List<float[]> calculate = new ArrayList<>();

    @Override
    public float[] variance() {
        float [] mean = mean();
        float [] variance = new float[mean.length];

        for(int i = 0; i<variance.length; i++){
            float [] temp = calculate.get(i);
            for(int j = 0; j<temp.length; j++) {
                variance[i] += Math.pow(temp[j] - mean[i], 2);
            }
            variance[i] /= temp.length;
        }
        return variance;
    }

    @Override
    public float[] sd() {
        float [] variance = variance();
        float [] sd = new float[variance.length];
        for(int i= 0; i<sd.length; i++){
            sd[i] = (float) Math.sqrt(variance[i]);
        }
        return sd;
    }

    @Override
    public float min() {
        // minIndex = 0;
        // for(int i= 0; i<data.length; i++){
        //
        // if data[i] < data[minIndex] minIndex = i;
        // data[minIndex] is min
        return 0;
    }

    @Override
    public float max() {
        return 0;
    }

    @Override
    public float[] mean() {
        changeToColumn();
        for(int i = 0; i<COLUMNDATA.size(); i++){
            List<String> temp = COLUMNDATA.get(i);
            checkFloat(temp);
        }
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
    public float median() {
        return 0;
    }

    @Override
    public float range() {
        return 0;
    }

    private void changeToColumn(){
        COLUMNDATA = new ArrayList<>();

        int numCols = r.DATA.get(0).size();
        ArrayList<String> rowData = new ArrayList();
        for(int j = 0; j<numCols; j++) {
            rowData.clear();
            for (int z = 0; z < r.DATA.size(); z++) {
                String[] temp = r.DATA.get(z).toArray(new String[0]);
                String temp1 = temp[j];
                rowData.add(temp1);
            }
            COLUMNDATA.add((List<String>) rowData.clone());
        }
    }

    private void checkFloat(List<String> row){
        float[] temp1 = new float[row.size()-1];
        int j = 0;
        for(int start = 0; start<row.size(); start++){
            String temp = row.get(start);
            if(isFloat(temp)){
                temp1[j] = Float.parseFloat(temp);
                j++;
            }
        }
        try {
            calculate.add(temp1);
        } catch(NullPointerException e){
            return;
        }
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
}
