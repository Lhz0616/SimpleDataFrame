package data;

import java.util.Arrays;
import java.util.List;

public class Row{

    DataFrameReader r ;

    public void concatRow(String[] row) {
        r = new DataFrameReader();
        try {
            if (row.length != r.DATA.get(0).size()) {
                throw new UnequalNumberException("The number of columns are not equal!");
            } else {
                List<String> temp = Arrays.asList(row);
                r.DATA.add(temp);
            }
        } catch (UnequalNumberException e) {
            e.printStackTrace();
        }

    }
}
