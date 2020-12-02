import java.io.File;
import java.util.ArrayList;

/**
* This interface will connect all the classes so that it can be easier to make updates and maintenance
* @author Lim Hong Zhi,
 * */

public interface DataFrame {
    /**
     * Return the name of the dataFrame
     * If no name is give, the default will be DataFrame1
     * @return name
     */
    String getName();

    /**
     * Return the name that is given to the dataframe
     * @param name
     * @return name of dataframe
     */
    String setName(String name);

    /**
     * Read the CSV file and return an Arraylist of the data, default separator is ','
     * @param file      input file
     * @return
     */
    ArrayList<ArrayList<?>> readCSV(File file);

    /**
     * Read the CSV file and return an Arraylist of the data
     * @param file      input file
     * @param separator used to split the data
     * @return ArrayList with a wildcard
     */
    ArrayList<ArrayList<?>> readCSV(File file,char separator);

    /**
     * Sort the dataframe according to the column selected with the header
     * The default sort is ascending
     *
     * @param column the column that wanted to sort
     * @return
     */
    DataFrame sort(String column);

    /**
     * Return the head (attribute) of the dataframe
     * @return head of the dataframe
     */
    DataFrame head();

}
