package data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    DataFrameReader r = new DataFrameReader();
    private static int index = 0;

    public void sortAsc(String column) {
        //for-loop to locate the index of column to be sorted
        for (int i = 0; i < r.header.size(); i++) {
            if (r.header.get(i).equalsIgnoreCase(column)) {
                index = i;
            }
        }

        //After getting index, loop through the column data to check wether contain non-numeric String
        try {
            for (int i = 0; i < r.DATA.size(); i++) {
                parseStringToDouble(r.DATA.get(i).get(index));
            }
            sortNumberAsc(r.DATA);  //if all can be parsed as Double, sort it as Double
        } catch (NumberFormatException e) { //catch if contains non-number, and proceed to check if it is String

            for (int i = 0; i < r.DATA.size(); i++) {
                for (int j = 0; j < r.DATA.get(i).get(index).length(); j++) {
                    char ch = r.DATA.get(i).get(index).charAt(j);
                    if (!(Character.isLetter(ch) || ch == ' ')) {   // exit if contains not letters and spaces
                        System.out.println("This column comprises data mixed of character and number!");    
                        System.exit(-1);
                    }
                }
            }
            sortStringAsc(r.DATA);  //if no other except characters and spaces, sort it as String
        }
    }

    public void sortDesc(String column) {
        //for-loop to locate the index of column to be sorted
        for (int i = 0; i < DataFrameReader.header.size(); i++) {
            if (r.header.get(i).equalsIgnoreCase(column)) {
                index = i;
            }
        }

        //After getting index, loop through the column data to check wether contain non-numeric String
        try {
            for (int i = 0; i < r.DATA.size(); i++) {
                parseStringToDouble(r.DATA.get(i).get(index));
            }
            sortNumberDesc(r.DATA); //if all can be parsed as Double, sort it as Double
        } catch (NumberFormatException e) { //catch if contains non-number, and proceed to check if it is String
            for (int i = 0; i < r.DATA.size(); i++) {
                for (int j = 0; j < r.DATA.get(i).get(index).length(); j++) {

                    char ch = r.DATA.get(i).get(index).charAt(j);

                    if (!(Character.isLetter(ch) || ch == ' ')) {
                        System.out.println("This column comprises data mixed of character and number!");    // exit if contains not letters and spaces
                        System.exit(-1);
                    }
                }
            }
            sortStringDesc(r.DATA); //if no other except characters and spaces, sort it as String
        }
    }

    public List<List<String>> sortNumberAsc(List<List<String>> DATA) {
        //create a customized comparator that compare Double value parsed from String
        Comparator<List<String>> comparator = new Comparator<List<String>>() {

            @Override
            //ascending comparison
            public int compare(List<String> object1, List<String> object2) {
                return Double.compare(parseStringToDouble(object1.get(index)), parseStringToDouble(object2.get(index)));
            }

        };
        //sort DATA with the customized comparator
        Collections.sort(DATA, comparator);
        return DATA;
    }

    public List<List<String>> sortNumberDesc(List<List<String>> DATA) {
        //create a customized comparator that compare Double value parsed from String 
        Comparator<List<String>> comparator = new Comparator<List<String>>() {

            @Override
            //desending comparison
            public int compare(List<String> object1, List<String> object2) {
                return -1 * Double.compare(parseStringToDouble(object1.get(index)), parseStringToDouble(object2.get(index)));
            }

        };
        //sort DATA with the customized comparator
        Collections.sort(DATA, comparator);
        return DATA;
    }

    public List<List<String>> sortStringAsc(List<List<String>> DATA) {
        //create a customized comparator that compare String
        Comparator<List<String>> comparator = new Comparator<List<String>>() {

            @Override
            public int compare(List<String> object1, List<String> object2) {
                return object1.get(index).compareTo(object2.get(index));
            }
        };
        //sort DATA with the customized comparator
        Collections.sort(DATA, comparator);
        return DATA;
    }

    public List<List<String>> sortStringDesc(List<List<String>> DATA) {
        //create a customized comparator that compare String
        Comparator<List<String>> comparator = new Comparator<List<String>>() {

            @Override
            public int compare(List<String> object1, List<String> object2) {
                return -1 * object1.get(index).compareTo(object2.get(index));
            }
        };
        //sort DATA with the customized comparator
        Collections.sort(DATA, comparator);
        return DATA;
    }

    //parse 0 if empty String is found
    public double parseStringToDouble(String value) {
        return value.isEmpty() || value == "" ? 0.0 : Double.parseDouble(value);
    }

}
