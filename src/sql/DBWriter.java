package sql;

import data.DataFrameReader;
import data.DataFrameWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DBWriter {
    DataFrameReader r = new DataFrameReader();
    DataFrameWriter write = new DataFrameWriter();

    Statement statement = null;

    public void writeDB(String dbURL, String tableName, String user, String password){
        try {
            Connection conn = DriverManager.getConnection(dbURL, user, password);
            statement = conn.createStatement();

            String state = "CREATE TABLE " + tableName + "(";

            for(int i = 0; i<r.header.length;i++){
                if(i == r.header.length - 1) state+= r.header[i] + " VARCHAR(255) ";
                else state += r.header[i] + " VARCHAR(255), ";

            }
            state += ")";

            statement.executeUpdate(state);

            write.writeCSV();
            String fileName = write.getFileName()+ ".csv";
            String line;
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

                br.readLine();
                while ((line = br.readLine()) != null) {
                    String [] temp = line.split(",");
                    String command = "INSERT INTO " + tableName + " VALUES (";
                    for(int i = 0; i<temp.length; i++){
                        if(i == temp.length-1) command+="\'" + temp[i] + "\'" ;
                        else command+="\'" + temp[i] + "\'" + ", ";
                    }
                    command+=")";

                    statement.executeUpdate(command);
                }

                statement.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e);
            }

            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
