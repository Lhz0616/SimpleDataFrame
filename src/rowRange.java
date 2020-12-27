import data.DataFrameReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class rowRange {
    DataFrameReader r = new DataFrameReader();
    List<List<String>> COLUMNDATA = new ArrayList<>();
    private void changetocolumn() {
        int numCols = r.DATA.get(1).size();
        ArrayList<String> rowData = new ArrayList();
        for (int j = 0; j < numCols; j++) {
            rowData.clear();
            for (int z = 1; z < r.DATA.size(); z++) {
                String[] temp = r.DATA.get(z).toArray(new String[0]);
                String temp1 = temp[j];
                rowData.add(temp1);
            }
            COLUMNDATA.add((List<String>) rowData.clone());
        }
    }

    public void rowRange(int x, int y){
        x=x+1;
        y=y+1;
        List<List<String>> rowRange= new ArrayList<>();
        //add header
        rowRange.add(r.DATA.get(0));
        for (int i=x; i<y; i++){
            rowRange.add(r.DATA.get(i));

        }
        System.out.println(rowRange.toString());
    }


    public void colRange (String[] category){
        changetocolumn();
        List<List<String>> colRange= new ArrayList<>();
        String[] header= r.DATA.get(0).toArray(new String[0]);
        int j=0;
        for(int i=0; i<COLUMNDATA.size(); i++){
           //for (int a=0; a<COLUMNDATA.size(); a++){

                if (category[j].equalsIgnoreCase(header[i])) {

                    //colRange.add(Collections.singletonList(header[i]));
                    colRange.add(COLUMNDATA.get(i));
                    j++;
                    if(j==category.length)
                        break;
                }
        }System.out.println(colRange.toString());
    }

}
