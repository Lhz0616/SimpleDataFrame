import data.DataFrameReader;
import data.rowCol;
import math.Statistics;

import java.io.IOException;
import java.util.Arrays;

import static math.Scalers.*;

// Object

public class Testing {
    public static void main(String[] args) throws IOException {
//        /*String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";
//
//        String line;
//        List<List<String>> data = new ArrayList();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            while ((line = br.readLine()) != null) {
//                List<String> column = new ArrayList(Arrays.asList(line.split(",")));
//                data.add(column);
//            }
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//
//        System.out.println(data.toString());
//
//        String [] newColumn = {"Colour", "Green", "Blue", "Black","Yellow"};
//        for(int i = 0 ; i<data.size(); i++){
//            List<String> temp = data.get(i);
//            temp.add(newColumn[i]);
//        }
//
//        System.out.println(data.toString());*/
////        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";
//        final long startTime = System.nanoTime();
//
//        //DBReader read = new DBReader("groupmemberdb", "generalinfo", "root", "");
//        DataFrameReader r = new DataFrameReader();
////        r.readCSV(filePath);
//        //read.readDB();
//
//        System.out.println(r.DATA.toString());
//
////        Statistics s = new Statistics();
//        System.out.println(s.COLUMNDATA.toString());
//
//        float [] median = s.median();
//        float [] interquartile = s.interquartile();
//        rowCol rc = new rowCol();
//
//        System.out.println(Arrays.toString(median));
//        System.out.println(Arrays.toString(interquartile) + "\n");
//        robustScale();
//        standardScale();
//        minMaxScaling();
//        System.out.println(s.COLUMNDATA.toString());

        //rowCol rowRange= new rowCol();
        //rowCol.colRange(new String[]{"name", "salary"});


        //DBWriter writer = new DBWriter();
        //writer.writeDB("jdbc:mysql://localhost/groupmemberdb", "newTable", "root", "");


        String filepath = "C:\\Users\\User\\Documents\\NetBeansProjects\\DonaldProjectUpdated\\src\\Book1.csv";

        DataFrameReader ror = new DataFrameReader();
        ror.readCSV(filepath);

        Statistics hello = new Statistics();
        System.out.println(Arrays.toString(hello.mode()));


    }
}