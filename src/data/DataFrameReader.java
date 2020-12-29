package data;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataFrameReader{
    private final char DEFAULT_SEPARATOR = ',';
    public static List<List<String>> DATA = new ArrayList<>();
    private String dataFrameName;
    public static String [] header;

    public List<List<String>> readCSV(String filePath) {
        return readCSV(filePath, DEFAULT_SEPARATOR);
    }

    public List<List<String>> readCSV(String filePath, char separator) {
        if(separator == ' '){
            separator = DEFAULT_SEPARATOR;
        }

        String line;
        int i =0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            while ((line = br.readLine()) != null) {
                if(i == 0) {
                    header = line.split(String.valueOf(separator));
                    i++;
                    continue;
                }
                List<String> values = new ArrayList(Arrays.asList(line.split(String.valueOf(separator))));
                DATA.add(values);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return DATA;
    }

    public String getName() {
        return dataFrameName;
    }


    public void setName(String name) {
        dataFrameName = name;
    }


    public String head() {
        return DATA.get(0).toString();
    }

    public void print(){
        for(int i = 0; i< DATA.size(); i++){
            System.out.println(DATA.get(i).toString());
        }
    }
}
