import data.DataFrameReader;
import data.rowCol;

import java.io.IOException;


// Object

public class Testing {
    public static void main(String[] args) throws IOException {
        /*String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";

        String line;
        List<List<String>> data = new ArrayList();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                List<String> column = new ArrayList(Arrays.asList(line.split(",")));
                data.add(column);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println(data.toString());

        String [] newColumn = {"Colour", "Green", "Blue", "Black","Yellow"};
        for(int i = 0 ; i<data.size(); i++){
            List<String> temp = data.get(i);
            temp.add(newColumn[i]);
        }

        System.out.println(data.toString());*/
        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";
        final long startTime = System.nanoTime();

        //DBReader read = new DBReader("groupmemberdb", "generalinfo", "root", "");
        DataFrameReader r = new DataFrameReader();
        r.readCSV(filePath);
        //read.readDB();
        System.out.println(r.DATA.toString());
        System.out.println(r.header.toString());

        rowCol rc = new rowCol();
        String [] temp = {"Colour", "red", "blue", "yellow", ""};
        rc.concatColumn(temp);
        System.out.println(r.DATA.toString());
        System.out.println(r.header.toString());
        /*Statistics s = new Statistics();
        Statistics s1 = new Statistics();
        //rowCol rowRange= new rowCol();
        //rowCol.colRange(new String[]{"name", "salary"});

        System.out.println(s.COLUMNDATA.toString());

        System.out.println(Arrays.toString(s.mean()));
        System.out.println(Arrays.toString(s.sd()));

        List<float[]> sdScale = s.StandardScale();

        for(int i = 0; i<sdScale.size(); i++){
            float[] temp1 = s.calculate.get(i);
            System.out.println(Arrays.toString(temp1));
            float[] temp = sdScale.get(i);
            System.out.println(Arrays.toString(temp));
        }

        //DBWriter writer = new DBWriter();
        //writer.writeDB("jdbc:mysql://localhost/groupmemberdb", "newTable", "root", "");

        float [] sd = s.sd();
        float [] min = s.min();
        float [] max = s.max();
        float[] range = s.range();
        float [] median = s.median();
        System.out.println(r.DATA.toString());
        System.out.println(s.COLUMNDATA.toString());
        System.out.println(Arrays.toString(min));
        System.out.println(Arrays.toString(max));
        System.out.println(Arrays.toString(range));
        System.out.println(Arrays.toString(median));


        for(int i = 0; i<s.calculate.size(); i++){
            float[] temp = s.calculate.get(i);
            System.out.println(Arrays.toString(temp));
        }

        System.out.println(Arrays.toString(sd));
        System.out.println(r.DATA.toString());
        System.out.println(s.COLUMNDATA.toString());

        System.out.println(r.DATA.toString());

        Column c = new Column();

        String [] column = {"Colour", "Yellow", "Green", "", "Black", "White", "Red"};
        c.concatColumn(column);*/

        //String url = "";
        //DBWriter write = new DBWriter("jdbc:mysql://localhost/groupmemberdb", "newTable", "root", "");
        //write.writeDB();
    }
}