import java.util.List;

public interface Writer {
    /**
     * Create a new csv file with the edited
     * name is default to
     * @param dataFrame all the data from the data frame
     */
    void writeCSV(List<List<String>> dataFrame);


    /**
     * Create a new csv file with the name given by the user
     * @param dataFrame all the data from the data frame
     * @param name name of the new csv file
     */
    void writeCSV(List<List<String>> dataFrame, String name);
}