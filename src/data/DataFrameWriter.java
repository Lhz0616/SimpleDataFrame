package data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataFrameWriter implements Writer{

    DataFrameReader r;

    @Override
    public void writeCSV() {
        writeCSV("DataFrame");
    }

    @Override
    public void writeCSV(String name) {
        r = new DataFrameReader();

        try {
            FileWriter writer = new FileWriter(new File(name+".csv"));
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
}
