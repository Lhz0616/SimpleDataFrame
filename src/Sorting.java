import java.util.List;

public interface Sorting{

    /**
     * Sort the dataframe according to the column selected with the header
     * The default sort is ascending
     *
     * @param column the column that wanted to sort
     * @return
    */
    List<List<String>> sort(String column);
}
