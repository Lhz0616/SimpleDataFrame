package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Drop {
    DataFrameReader dfr = new DataFrameReader();

    public void dropDuplicate(String[] category, int num) {
        String[] header = dfr.header;
        int[] a = new int[category.length];
        int counter = 0;
        String duplicate = "";
        for (int i = 0; i < category.length; i++) {

            for (int j = 0; j < dfr.header.length; j++) {
                if (category[i].equals(header[j])) {
                    a[i] = j;

                }
            }
        }
        List<String> arr = new ArrayList<String>();

        //for every category detected and saved in array a
        for (int i = 0; i < a.length; i++) {
            //get column and store in a list
            for (int j = 0; j < dfr.DATA.size(); j++) {
                arr.add(dfr.DATA.get(j).get(a[i]));
            }
            //for every element in list find duplicate and remove the duplicate row based on the num
            for (int k = 0; k < arr.size(); k++) {
                inner:
                if (arr.lastIndexOf(arr.get(k)) != k) {
                    duplicate = arr.get(k);
                    break inner;
                }

                if (arr.get(k).equals(duplicate)) {
                    counter++;
                    if (counter == num) {
                        dfr.DATA.remove(k);
                        break;
                    }
                }

            }
            arr.clear();
            counter = 0;
        }


        System.out.println(dfr.DATA.toString());
    }

    public void dropNull(String[] category) {
        String[] header = dfr.header;
        int counter = 0;
        int[] a = new int[category.length];
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < dfr.header.length; j++) {
                if (category[i].equals(header[j])) {
                    a[i] = j;
                }
            }
        }
        List<String> arr = new ArrayList<>();
        ArrayList<Integer> NUll = new ArrayList<>();
        //for every category detected and saved in array a
        for (int i = 0; i < a.length; i++) {
            //get column and store in a list
            for (int j = 0; j < dfr.DATA.size(); j++) {
                arr.add(dfr.DATA.get(j).get(a[i]));
            }
            //for every element in list find duplicate and remove the duplicate row based on the num
            for (int k = 0; k < arr.size(); k++) {
                if (arr.get(k).equals(" ")) {
                    NUll.add(k);
                }

            }
            arr.clear();
            counter = 0;
        }
        int popular=getFrequentNumber(NUll);
        int occurrences = Collections.frequency(NUll,popular);
        if(occurrences== category.length){
            dfr.DATA.remove(popular);

        }
        System.out.println(dfr.DATA.toString());
    }
    private int getFrequentNumber(ArrayList<Integer> arr){
        int popular = arr.get(0);
        int count = 1;
        int tempcount = 0;
        int temp = 0;

        for(int i = 0; i < arr.size(); i++) {
            temp = arr.get(i);
            tempcount = 0;
            for(int j = 1; j < arr.size(); j++) {
                if(temp == arr.get(j))
                    tempcount++;
            }
            if (tempcount > count) {
                popular = temp;
                count = tempcount;
            }
        }
        return popular;
    }
}



