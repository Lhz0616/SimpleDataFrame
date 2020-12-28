package data;

public interface Drop {
    /**
     * Read the specific column from CSV file and the no that want to drop.
     * Return the data without the duplicate
      */
    void dropduplicates(String[]x,Integer y);

    /**
     *Read the specific column from CSV file and detect if both column are null
     * drop and return the data without that row.
     */
    void dropNull(String[]x,String y,String z);
}
