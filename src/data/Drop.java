package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Drop {
    private static DataFrameReader dfr = new DataFrameReader();
    private int i;

    /**
     * {@code dropDuplicate} is a method where it will drop the selected column with duplicates
     * according to the occurence given
     *
     * Example: If the given number is 2, this method will remove the 2nd duplicate data
     *
     * @param category  an array of {@code String} to select to column
     * @param num       choose the number of occurence
     */
    public void dropDuplicate(String[] category, int num) {
        List<String> header = dfr.header;
        int[] a = new int[category.length];
        int counter = 0;
        String duplicate = "";
        for (int i = 0; i < category.length; i++) {

            for (int j = 0; j < dfr.header.size(); j++) {
                if (category[i].equals(header.get(j))) {
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

    /**
     * {@code dropNull} is a method where it will drop the row of data that has
     * null value at the given column
     *
     * @param category  An array of {@code String} to select the column
     */
    public void dropNull(String[] category) {
        List<String> header = dfr.header;
        int counter = 0;
        int[] a = new int[category.length];
        for (int i = 0; i < category.length; i++) {
            for (int j = 0; j < dfr.header.size(); j++) {
                if (category[i].equals(header.get(j))) {
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

            for (int k = 0; k < arr.size(); k++) {
                if (arr.get(k).equals(" ")) {
                    NUll.add(k);
                }

            }
            arr.clear();
            counter = 0;
        }


        ArrayList<Integer> popular = new ArrayList<>();
        popular = getFrequentNumber(NUll, category.length);
        ArrayList<List<String>> word=new ArrayList<>();
        for(int i=0;i< popular.size();i++) {
            word.add(dfr.DATA.get(popular.get(i)));
        }
        dfr.DATA.removeAll(word);
        System.out.println(dfr.DATA.toString());
    }

    /**
     * {@code getFrequentNumber} is a method where it will remove duplicate number and also
     * remove the index that happens once only
     *
     * @param arr   an ArrayList of the index
     * @param y     the length of the column the user given
     *
     * @return      an ArrayList of the index without duplicates
     */
    private ArrayList<Integer> getFrequentNumber(ArrayList<Integer> arr, int y) {
        ArrayList<Integer> Nothing = new ArrayList<>();
        int occurrences = 0;
        for (int i = 0; i < arr.size(); i++) {
            occurrences = Collections.frequency(arr, arr.get(i));
            if (occurrences == y) {
                Nothing.add(arr.get(i));
            }
        }
        List<Integer>newlist=Nothing.stream().distinct().collect(Collectors.toList());
        ArrayList<Integer> listOfStrings = new ArrayList<>(newlist.size());
        listOfStrings.addAll(newlist);
        return listOfStrings;
    }
}

