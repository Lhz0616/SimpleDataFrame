import java.io.*;
import java.util.*;

public class Testing {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";

        String line;
        List<List<String>> COLUMNDATA = new ArrayList();
        List<List<String>> ROWDATA = new LinkedList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                List<String> column = Arrays.asList(line.split(","));
                COLUMNDATA.add(column);

            }

            int numCols = COLUMNDATA.get(0).size();
            ArrayList<String> rowData = new ArrayList();
            for(int j = 0; j<numCols; j++){
                rowData.clear();
                for(int z = 0; z<COLUMNDATA.size();z++){
                    String [] temp = COLUMNDATA.get(z).toArray(new String[0]);
                    String temp1 = temp[j];
                    rowData.add(temp1);
                }
                ROWDATA.add((List<String>) rowData.clone());
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(COLUMNDATA.toString());
        System.out.println(ROWDATA.toString());

    }
}
