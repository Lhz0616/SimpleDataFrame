import data.DataFrameReader;
import data.Sorting;

// Object

public class Testing {
    public static void main(String[] args){
//        String filePath = "C:\\Users\\user\\Desktop\\SimpleDataFrame\\src\\Book1.csv";
//
//
//        //DBReader read = new DBReader("groupmemberdb", "generalinfo", "root", "");
//        DataFrameReader r = new DataFrameReader();
//        r.readCSV(filePath);
//        //read.readDB();
//        System.out.println(r);
        /*Statistics s = new Statistics();


        float [] median = s.median();
        float [] interquartile = s.interquartile();
        rowCol rc = new rowCol();

        System.out.println(Arrays.toString(median));
        System.out.println(Arrays.toString(interquartile) + "\n");
        robustScale();
        standardScale();
        minMaxScaling();

        rowCol rowRange= new rowCol();
        rowRange.colRange(new String[]{"name", "salary"});*/


        //DBWriter writer = new DBWriter();
        //writer.writeDB("jdbc:mysql://localhost/groupmemberdb", "newTable", "root", "");


        String filepath = "C:\\Users\\User\\Documents\\NetBeansProjects\\DonaldProjectUpdated\\src\\Book1.csv";
        DataFrameReader rd =new DataFrameReader();
        rd.readCSV(filepath);

        Sorting st = new Sorting();
        st.sort("Name", "descending");

    }
}