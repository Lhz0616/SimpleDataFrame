import data.DataFrameReader;
import data.rowCol;
import math.Statistics;
import sql.DBReader;
import sql.DBWriter;

import java.io.IOException;
import java.util.Arrays;

import static math.Scalers.*;

// Object

public class Testing {
    public static void main(String[] args){
        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";


        //DBReader read = new DBReader("groupmemberdb", "generalinfo", "root", "");
        DataFrameReader r = new DataFrameReader();
        r.readCSV(filePath);
        //read.readDB();
        System.out.println(r);
        /*Statistics s = new Statistics();


        float [] median = s.median();
        float [] interquartile = s.interquartile();
        rowCol rc = new rowCol();

        System.out.println(Arrays.toString(median));
        System.out.println(Arrays.toString(interquartile) + "\n");
        robustScale();
        standardScale();
        minMaxScaling();

        rowCol rowRange= new rowCol();
        rowRange.colRange(new String[]{"name", "salary"});*/


        //DBWriter writer = new DBWriter();
        //writer.writeDB("jdbc:mysql://localhost/groupmemberdb", "newTable", "root", "");



    }
}