package sql;

import data.DataFrameReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader extends DataFrameReader {

    Connection con;

    public DBReader(String dbName){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,"root","");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("There is an error connecting to the database. Please try again!");
        }
        System.out.println("Connected to the database successfully!");
    }

    public void readDB(){
        try {
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery("select * from generalInfo");
            ResultSetMetaData md = set.getMetaData();
            List<String> empty = new ArrayList<>();
            DATA.add(empty);
            while(set.next()){
                List<String> getData = new ArrayList<>();
                for(int j = 1; j<=md.getColumnCount() ; j++){
                    getData.add(set.getString(j));
                }
                DATA.add(getData);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
