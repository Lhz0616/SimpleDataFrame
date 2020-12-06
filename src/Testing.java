import java.io.*;
import java.util.List;

public class Testing {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";

        DataFrameReader d = new DataFrameReader();
        List<List<String>> data = d.readCSV(filePath);

        System.out.println(d.head());

    }
}
