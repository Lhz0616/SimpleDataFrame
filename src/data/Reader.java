package data;

import java.util.List;

public interface Reader {
    /**
     * Read the CSV file and return a List<List<String>> of the data
     * @param filePath the filePath of the CSV file
     * @return List<List<String>> of the data
     */
    List<List<String>> readCSV(String filePath);

    /**
     * Read the CSV file and return an Arraylist of the data
     * @param separator used to split the data
     * @return ArrayList with a wildcard
     */
    List<List<String>> readCSV(String filePath, char separator);


}
