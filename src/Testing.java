import data.Column;
import data.DataFrameReader;
import math.Statistics;

import java.io.*;
import java.util.Arrays;
import java.util.List;

// Object

public class Testing {
    public static void main(String[] args) throws IOException {
        /*String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";

        String line;
        List<List<String>> data = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                List<String> column = new ArrayList(Arrays.asList(line.split(",")));
                data.add(column);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(data.toString());

        String [] newColumn = {"Colour", "Green", "Blue", "Black","Yellow"};
        for(int i = 0 ; i<data.size(); i++){
            List<String> temp = data.get(i);
            temp.add(newColumn[i]);
        }

        System.out.println(data.toString());*/

        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";

        DataFrameReader r = new DataFrameReader();
        r.readCSV(filePath);

        Statistics s = new Statistics();

        float [] sd = s.sd();
        float [] min = s.min();
        float [] max = s.max();
        float[] range = s.range();
        float [] median = s.median();
        System.out.println(r.DATA.toString());
        System.out.println(s.COLUMNDATA.toString());
        System.out.println(Arrays.toString(min));
        System.out.println(Arrays.toString(max));
        System.out.println(Arrays.toString(range));
        System.out.println(Arrays.toString(median));
        /*for(int i = 0; i<s.calculate.size(); i++){
            float[] temp = s.calculate.get(i);
            System.out.println(Arrays.toString(temp));
        }*/

        System.out.println(Arrays.toString(sd));
        /*System.out.println(r.DATA.toString());
        System.out.println(s.COLUMNDATA.toString());
        System.out.println(Arrays.toString(mean));*/

        /*System.out.println(r.DATA.toString());

        Column c = new Column();

        String [] column = {"Colour", "Yellow", "Green", "Blue", ""};
        c.concatColumn(column);
        r.print();*/
    }
}