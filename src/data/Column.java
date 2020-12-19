package data;

import java.util.List;

public class Column{

    DataFrameReader r = new DataFrameReader();

    public void concatColumn(String[] column) {
        for(int i = 0; i<r.DATA.size(); i++){
            List<String> temp = r.DATA.get(i);
            temp.add(column[i]);
        }
    }
}
