package sql;

import data.DataFrameReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The DBReader is a class which will connect to mySQL (I am using XAMPP)
 * This will only connect to the localhost database
 * The driver version for the mySQL is {@code mysql-connector-java-8.0.22}
 */
public class DBReader extends DataFrameReader {

    /**
     * {@code con} is a variable where it will save the connection status to the database
     */
    private Connection con;

    /**
     * This is the table where the user want to import into this library
     */
    private String tableName;

    /**
     * {@code dbName} will be the database where you want to access the data
     */
    private String dbName;

    /**
     * This is the constructor for the class {@code DBReader}
     * It will initialize all the things required and establish the connection to the database
     * using the username and the password user provided
     * @param dbName    the name of the database where you want to access the data
     * @param tableName the name of the table
     * @param user      the user of the database that you want to connect to
     * @param password  the password of the user
     */
    public DBReader(String dbName, String tableName, String user, String password){
        this.dbName = dbName;
        this.tableName = tableName;
        try{
            System.out.println("Trying to connect to the database: " + dbName);

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/"+dbName,user,password);

            System.out.println("Connected to the database successfully!");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            try {
                throw new ConnectionErrorException("There is an error connecting to the database. Please try again!");
            } catch (ConnectionErrorException e) {
                e.printStackTrace();
            }
            System.exit(-1);
        }
    }

    /**
     * The {@code readDB} method will read all the data from the table of the database
     * It will exclude the header of the table
     */
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
}
