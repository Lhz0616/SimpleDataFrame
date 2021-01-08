package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code rowCol} is a class that can carry out functions that are related to row and column
 */
public class rowCol {

    /**
     * {@code r} is a {@code DataFrameReader} that is used to access the {@code DATA}
     */
    private static DataFrameReader r = new DataFrameReader();

    /**
     * {@code COLUMNDATA} is used to store the dataframe by column
     */
    public static List<List<String>> COLUMNDATA = new ArrayList<>();

    /**
     * {@code rowRange} is used to display the row between the range that the user wants
     *
     * @param x the first index (included)
     * @param y the last index  (excluded)
     */
    public void rowRange(int x, int y) {
        List<List<String>> rowRange = new ArrayList<>();
        //add header
        rowRange.add(r.DATA.get(0));
        for (int i = x; i < y; i++) {
            rowRange.add(r.DATA.get(i));

        }
        System.out.println(rowRange.toString());
    }


    /**
     * {@code colRange} is a method that will accept an Array of {@code String}
     * which will be the column that wanted to be display
     * This method will print out the data by column
     *
     * @param category the column that wanted to be displayed
     */
    public void colRange(String[] category) {
        changeToColumn();
        List<List<String>> colRange = new ArrayList<>();
        String[] header = r.header.toArray(new String[0]);
        int j = 0;
        for (int i = 0; i < COLUMNDATA.size(); i++) {
            //for (int a=0; a<COLUMNDATA.size(); a++){

            if (category[j].equalsIgnoreCase(header[i])) {

                //colRange.add(Collections.singletonList(header[i]));
                colRange.add(COLUMNDATA.get(i));
                j++;
                if (j == category.length)
                    break;
            }
        }
        System.out.println(colRange.toString());
    }

    /**
     * {@code getCOLUMNDATA} is a method to get the COLUMNDATA
     * It will always update the newest data with changeToColumn()
     *
     * @return List<List < String>> of the COLUMNDATA
     */
    public List<List<String>> getCOLUMNDATA() {
        changeToColumn();
        return COLUMNDATA;
    }

    /**
     * {@code concatRow} is a method used to add a new row to {@code DATA} in the
     * {@code DataFrameReader}
     *
     * @param row the data that will be added to the {@code DATA}
     *            **take note that it must be the same number of column**
     */
    public void concatRow(String[] row) {
        try {
            if (row.length != r.DATA.get(0).size()) {
                throw new UnequalNumberException("The number of columns are not equal!");
            } else {
                List<String> temp = Arrays.asList(row);
                r.DATA.add(temp);
            }
        } catch (UnequalNumberException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * {@code concatColumn} is used to add a new column to the dataframe
     *
     * @param column    An Array input by the user (the first index of the array is
     *                  the header)
     */
    public void concatColumn(String[] column) {
        try {
            if(column.length != r.DATA.size()+1){
                throw new UnequalNumberException("The number of row is not equal!!");
            }else {
                r.header.add(column[0]);

                for (int i = 1; i <=r.DATA.size(); i++) {
                    if(column[i].equals("")){
                        List<String> temp = r.DATA.get(i - 1);
                        temp.add(" ");
                    }else {
                        List<String> temp = r.DATA.get(i - 1);
                        temp.add(column[i]);
                    }
                }
            }
        } catch(UnequalNumberException e){
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * This is a private method which will change the {@code DATA} by column
     * and save into {@code COLUMNDATA}
     */
    private void changeToColumn() {
        int numCols = r.header.size();
        ArrayList<String> rowData = new ArrayList();
        for (int j = 0; j < numCols; j++) {
            rowData.clear();
            for (int z = 0; z < r.DATA.size(); z++) {
                String[] temp = r.DATA.get(z).toArray(new String[0]);
                String temp1 = temp[j];
                rowData.add(temp1);
            }
            COLUMNDATA.add((List<String>) rowData.clone());
        }
    }

    /**
     * {@code Fill_missing} will accept the index of column and an Array of values to fill in
     * the cell that are empty
     *
     * @param column    the index of column
     * @param values    an Array of values to fill in the empty cell
     */
     public void Fill_missing(String column,String[]values){
        List<String> header = r.header;
        int a=0,frequancy=0;
        for (int j = 0; j < r.header.size(); j++) {
                if (column.equals(header.get(j))) {
                    a = j;
                }
        }
        List<String> arr = new ArrayList<String>();
        for(int j=0;j<r.DATA.size();j++){
            arr.add(r.DATA.get(j).get(a));
        }
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)==""){
                r.DATA.get(i).set(a,values[frequancy]);
                frequancy++;
            }
        }
        System.out.println(r.DATA.toString());
    }


}
