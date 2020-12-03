import java.io.File;
import java.util.List;

public interface Reader {
    /**
     * Read the CSV file and return an Arraylist of the data, default separator is ','
     * @param filePath      input file
     * @return Array of
     */
    List<String[]> readCSV(String filePath);

    /**
     * Read the CSV file and return an Arraylist of the data
     * @param filePath     input file
     * @param separator used to split the data
     * @return ArrayList with a wildcard
     */
    List readCSV(String filePath, char separator);


}
