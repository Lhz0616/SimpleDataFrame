import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DataFrameWriter implements Writer{

    @Override
    public void writeCSV(List<List<String>> dataFrame) {
        writeCSV(dataFrame, "DataFrame.csv");
    }

    @Override
    public void writeCSV(List<List<String>> dataFrame, String name) {

        try {
            FileWriter writer = new FileWriter(new File(name+".csv"));
            for(int i = 0; i<dataFrame.size(); i++){
                List<String> stringList = (List<String>) dataFrame.get(i);
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
