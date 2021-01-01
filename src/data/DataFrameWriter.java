package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * {@code DataFrameWriter} is a class to write the {@code DATA} into a CSV file
 * This class will create a DataFrameReader object to access the {@code DATA} variable
 */
public class DataFrameWriter {

    /**
     * {@code r} is an DataFrameReader to access the {@code DATA}
     * {@code DATA} is needed to write all the data into the CSV file
     */
    DataFrameReader r = new DataFrameReader();

    /**
     * {@code fileName} is a variable to store the fileName that the user wanted
     * The written file will be set to that name
     * Example: fileName = "abc"
     * Output file: abc.csv
     */
    private String fileName;

    /**
     * This {@code writeCSV} has no arguments.
     * The default fileName for the {@code writeCSV} is "DataFrame"
     */
    public void writeCSV() {
        fileName = "DataFrame";
        writeCSV("DataFrame");
    }

    /**
     * This {@code writeCSV} has an argument, which is the name of the output file.
     * @param name the name of the output file
     */
    public void writeCSV(String name) {
        fileName = name;

        try {
            FileWriter writer = new FileWriter(new File(name+".csv"));

            // put the header into the file
            for(int z = 0; z<r.header.size(); z++){
                writer.write(String.valueOf(r.header));
                if(z<r.header.size()-1) writer.write(",");
                else writer.write("\n");
            }

            // read all the data in the variable DATA and write it to the file
            for(int i = 0; i<r.DATA.size(); i++){
                List<String> stringList = r.DATA.get(i);

                for(int j = 0; j<stringList.size(); j++){
                    writer.write(stringList.get(j));
                    if(j<stringList.size()-1) writer.write(",");
                    else writer.write("\n");

                }

            }
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * This method is used for getting the file Name
     * @return the fileName of {@code String} type
     */
    public String getFileName() {
        return fileName;
    }
}
