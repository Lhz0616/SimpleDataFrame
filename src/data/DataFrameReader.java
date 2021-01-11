package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code DataFrameReader} class is where you will import the CSV file.
 * It consists only a readCSV function where it gives the user to import the file.
 */
public class DataFrameReader{

    /**
     * CSV file usually will use a ','
     * This is why the {@code DEFAULT_SEPARATOR} will be ','
     * If you wanted to use another separator, the readCSV is method overloaded where you can
     * change the separator based on the separator you want
     */
    private final char DEFAULT_SEPARATOR = ',';

    /**
     * {@code DATA} is the main place to store the data from the file
     *
     * Every part of the database will access the {@code DATA} to alter, print, concatenate the
     * It uses the ArrayList from the Java library as the size is not fixed
     *
     * Do take note that {@code DATA} do not contain the header of the CSV file.
     * If you want to get the header, refer to {@code header}
     */
    public static List<List<String>> DATA = new ArrayList<>();

    /**
     * This will be the header of the data
     * It will not change unless you use {@code concatColumn}
     */
    public static List<String> header = new ArrayList<>();

    /**
     * This is the {@code readCSV} method where it will accept the filePath of the CSV file
     * that you want to read
     * The default separator is ',' , {@code DEFAULT_SEPARATOR}
     * **Take note that you can just ue this method and do not need to store in a variable
     * You can use {@code DATA} in this class
     *
     * @param filePath the location of the CSV file
     * @return {@code DATA}
     * @see {@code DATA}
     */
    public List<List<String>> readCSV(String filePath) {
        return readCSV(filePath, DEFAULT_SEPARATOR);
    }


    /**
     * This method is overloading readCSV.
     * It can use the separator based on the user input to separate all the data
     *
     * @param filePath  the location of the CSV file
     * @param separator used to separate the data in the CSV file
     * @return {@code DATA}
     */
    public List<List<String>> readCSV(String filePath, char separator) {
        if(separator == ' '){
            separator = DEFAULT_SEPARATOR;
        }

        String line;
        int i =0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                if(i == 0) {
                    header = new ArrayList<>(Arrays.asList(line.split(String.valueOf(separator))));
                    i++;
                    continue;
                }
                List<String> values = new ArrayList(Arrays.asList(line.split(String.valueOf(separator))));
                DATA.add(values);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return DATA;
    }


    /**
     * This method is used to retrieve the header with a {@code String} type
     * It will printed in a format , [ a , b , c, ...]
     *
     * @return header of type {@code String}
     */
    public String header() {
        return header.toString();
    }


    /**
     * This method will be used to print the {@code DATA} in a format:
     * [a,b,c]
     * [d,e,f]
     * [g,h,i]
     * ...
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<header.size(); i++){
            sb.append(String.format("%-20s", header.get(i)));
        }
        sb.append("\n");

        for(int i = 0; i<DATA.size(); i++){
            List<String> temp = DATA.get(i);
            for(int j = 0; j<temp.size(); j++){
                sb.append(String.format("%-20s", temp.get(j)));
            }

            sb.append("\n");
        }

        return sb.toString();
    }



}



