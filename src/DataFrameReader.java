import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFrameReader implements Reader{
    private final char DEFAULT_SEPARATOR = ',';
    private List DATA;

    @Override
    public List<String[]> readCSV(String filePath) {
        return readCSV(filePath, DEFAULT_SEPARATOR);
    }

    @Override
    public List readCSV(String filePath, char separator) {
        if(separator == ' '){
            separator = DEFAULT_SEPARATOR;
        }

        String line;
        List lines = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                List values = Arrays.asList(line.split(String.valueOf(separator)));
                lines.add(values);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        DATA = lines;
        return DATA;
    }
}
