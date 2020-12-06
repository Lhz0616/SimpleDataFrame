import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFrameReader implements Reader, DataFrame{
    private final char DEFAULT_SEPARATOR = ',';
    public static List<List<String>> DATA;
    private String dataFrameName;

    public DataFrameReader() {}

    public List<List<String>> readCSV(String filePath) {
        return readCSV(filePath, DEFAULT_SEPARATOR);
    }

    @Override
    public List<List<String>> readCSV(String filePath, char separator) {
        if(separator == ' '){
            separator = DEFAULT_SEPARATOR;
        }

        String line;
        DATA = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                List<String> values = Arrays.asList(line.split(String.valueOf(separator)));
                DATA.add(values);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return DATA;
    }

    @Override
    public String getName() {
        return dataFrameName;
    }

    @Override
    public void setName(String name) {
        dataFrameName = name;
    }

    @Override
    public String head() {
        return DATA.get(0).toString();
    }
}