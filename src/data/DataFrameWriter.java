package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataFrameWriter {

    DataFrameReader r;
    private String fileName;

    public void writeCSV() {
        writeCSV("DataFrame");
    }

    public void writeCSV(String name) {
        r = new DataFrameReader();
        fileName = name;

        try {
            FileWriter writer = new FileWriter(new File(name+".csv"));

            // put the header into the file
            for(int z = 0; z<r.header.length; z++){
                writer.write(r.header[z]);
                if(z<r.header.length-1) writer.write(",");
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

    public String getFileName() {
        return fileName;
    }
}
