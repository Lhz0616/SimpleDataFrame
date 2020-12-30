package sql;

import data.DataFrameReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBReader extends DataFrameReader {

    public static Connection con;
    private String tableName;
    private String dbName;


    public DBReader(String dbName, String tableName, String username, String password){
        this.dbName = dbName;
        this.tableName = tableName;
        try{
            System.out.println("Trying to connect to the database: " + dbName);

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,username,password);

            System.out.println("Connected to the database successfully!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            System.out.println("There is an error connecting to the database. Please try again!");
            System.exit(-1);
        }
    }

    public void readDB(){
        try {
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery("select * from " + tableName);
            ResultSetMetaData md = set.getMetaData();

            while(set.next()){
                List<String> getData = new ArrayList<>();
                for(int j = 1; j<=md.getColumnCount() ; j++){
                    getData.add(set.getString(j));
                }
                DATA.add(getData);
            }
            stmt.close();
            con.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public String getDbName() {
        return dbName;
    }

}
