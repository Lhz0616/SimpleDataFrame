package sql;

import data.DataFrameReader;
import data.DataFrameWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;


/**
 * The {@code DBWriter} class will establish a connection to the database using the URL
 */
public class DBWriter {
    /**
     * {@code r} is a DataFrameReader created to access the {@code DATA}
     */
    DataFrameReader r = new DataFrameReader();

    /**
     * {@code write} is a DataFrameWriter which is used to access the writeCSV method
     */
    DataFrameWriter write = new DataFrameWriter();

    /**
     * {@code Statement} is used to execute the SQL commands
     * {@code conn} is used to get the connection status
     * {@code dbURL} is the URL of the database the the user wanted to connect to
     * {@code tableName} is the name of the table where the data will be stored in
     * {@code user} is the user that wanted to connect to the database
     * {@code password} is the password of the user
     */
    private Statement statement = null;
    private Connection conn = null;
    private String dbURL;
    private String tableName;
    private String user;
    private String password;

    /**
     * The {@code DBWriter}
     * @param dbURL
     * @param tableName
     * @param user
     * @param password
     */
    public DBWriter(String dbURL, String tableName, String user, String password) {
        this.dbURL = dbURL;
        this.tableName = tableName;
        this.user = user;
        this.password = password;

        try {
            conn = DriverManager.getConnection(dbURL, user, password);
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        }
    }

    public void writeDB() {

        String state = "CREATE TABLE " + tableName + "(";

        for (int i = 0; i < r.header.length; i++) {
            if (i == r.header.length - 1) state += r.header[i] + " VARCHAR(255) ";
            else state += r.header[i] + " VARCHAR(255), ";
        }

        state += ")";

        try {
            statement.executeUpdate(state);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        write.writeCSV();
        String fileName = write.getFileName() + ".csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] temp = line.split(",");
                String command = "INSERT INTO " + tableName + " VALUES (";

                for (int i = 0; i < temp.length; i++) {
                    if (i == temp.length - 1) command += "\'" + temp[i] + "\'";
                    else command += "\'" + temp[i] + "\'" + ", ";
                }

                command += ")";

                statement.executeUpdate(command);
            }

            statement.close();
            conn.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
