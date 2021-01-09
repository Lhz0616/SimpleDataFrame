package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * {@code Sorting} is a class that will conduct the sorting method according to
 * the column that the user want
 */
public class Sorting {

    /**
     * {@code index} is used to save the index the user input
     */
    private static int index = 0;

    /**
     * {@code order} is used to save the sorting order the user input
     */
    private static String order = "";

    /**
     * {@code r} is used to access the dataframe
     */
    DataFrameReader r = new DataFrameReader();

    /**
     * {@code sort} is used to sort the data by the selected column
     * This method will print out the sorted data
     *
     * @param column the column wanted to be sort
     * @param order  in ascending order or descending order
     */
    public void sort(String column, String order) {
        //for-loop to locate the index of column to be sorted
        for (int i = 0; i < r.header.size(); i++) {
            if (r.header.get(i).equalsIgnoreCase(column)) {
                this.index = i;
            }
        }
        //store the order of the sorting the user wants
        if(order.equalsIgnoreCase("ascending") || order.equalsIgnoreCase("descending"))
        this.order = order;
        else {
            System.out.println("Please specify in which ascending order or descending order");
            System.exit(-1);
        }

        List<List<String>> SortedData = new ArrayList<>(r.DATA);    //clone the data into new List

        //After getting index, loop through the column data to check whether contain non-numeric String
        try {
            for (int i = 0; i < SortedData.size(); i++) {
                parseStringToFloat(SortedData.get(i).get(index));
            }
            sortNumber(SortedData);   //if all can be parsed as Float, sort it as Float

        } catch (NumberFormatException e) { //catch if contains non-number, and proceed to check if it is String
            for (int i = 0; i < SortedData.size(); i++) {
                for (int j = 0; j < SortedData.get(i).get(index).length(); j++) {
                    char ch = SortedData.get(i).get(index).charAt(j);
                    if (!(Character.isLetter(ch) || ch == ' ')) {   // exit if contains not letters and spaces
                        System.out.println("This column comprises data mixed of character and number!");
                        System.exit(-1);
                    }
                }
            }
            sortString(SortedData); //if no other except characters and spaces, sort it as String
        }

        //display the SortedData
        System.out.println(r.header);
        for (int i = 0; i < SortedData.size(); i++) {
            System.out.println(SortedData.get(i));
        }
    }

    /**
     * {@code sortNumberAsc} is a method to sort the column that is numbers
     *
     * @param SortedData data to be sorted
     */
    public void sortNumber(List<List<String>> SortedData) {
        //create a customized comparator that compare Float value parsed from String
        Comparator<List<String>> comparator = new Comparator<List<String>>() {
            //return result of comparison according to ascending order or descending order
            @Override
            public int compare(List<String> object1, List<String> object2) {
                if (order.equalsIgnoreCase("ascending")) {
                    return Float.compare(parseStringToFloat(object1.get(index)), parseStringToFloat(object2.get(index)));
                } else if (order.equalsIgnoreCase("descending")) {
                    return -1 * Float.compare(parseStringToFloat(object1.get(index)), parseStringToFloat(object2.get(index)));
                }
                return 0;   //return 0 to act as guard
            }
        };
        //sort SortedData with the customized comparator
        Collections.sort(SortedData, comparator);
    }

    /**
     * {@code sortStringr} is a method to sort the column that is letters
     *
     * @param SortedData data to be sorted
     */
    public void sortString(List<List<String>> SortedData) {
        //create a customized comparator that compare String
        Comparator<List<String>> comparator = new Comparator<List<String>>() {
            //return result of comparison according to ascending order or descending order
            @Override
            public int compare(List<String> object1, List<String> object2) {
                if (order.equalsIgnoreCase("ascending")) {
                    return object1.get(index).compareTo(object2.get(index));
                } else if (order.equalsIgnoreCase("descending")) {
                    return -1 * object1.get(index).compareTo(object2.get(index));
                } else
                    return 0;   //return 0 to act as guard
            }
        };
        //sort SortedDATA with the customized comparator
        Collections.sort(SortedData, comparator);
    }

    /**
     * {@code parseStringToFloat} is a method to sort the column that is letters in descending order
     * This method will parse 0 when a empty data of number type is found, so that empty data will not be an error
     *
     * @param value that is being parsed
     */
    //parse 0 if empty String is found
    public float parseStringToFloat(String value) {
        return value.isEmpty() || value.equals("") ? 0.0f : Float.parseFloat(value);
    }
}
