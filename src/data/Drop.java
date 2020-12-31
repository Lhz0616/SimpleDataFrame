package data;
import java.util.ArrayList;
import java.util.List;

public class Drop{

    public void dropDuplicate(String[] category, int num) {
            String[] header = dfr.header;
            int[] a=new int[category.length];
            int counter=0;
            String duplicate="";
            inner:
            for (int i = 0; i < category.length; i++) {

                for (int j = 0; j < dfr.header.length; j++) {
                    if (category[i].equals(header[j])) {
                        a[i]=j;

                    }
                }
            }
            List<String> arr = new ArrayList<String>();

            //for every category detected and saved in array a
            for(int i=0;i<a.length;i++){
                //get column and store in a list
                for(int j=0;j<dfr.DATA.size();j++) {
                    arr.add(dfr.DATA.get(j).get(a[i]));
                }
                //for every element in list find duplicate and remove the duplicate row based on the num
                for(int k=0; k< arr.size(); k++) {
                    inner:
                    if (arr.lastIndexOf(arr.get(k)) != k)  {
                        duplicate=arr.get(k);
                        break inner;
                    }

                    if (arr.get(k).equals(duplicate))
                    {
                        counter++;
                        if(counter==num){
                            dfr.DATA.remove(k);
                            break ;
                        }
                    }

                }
                arr.clear();
                counter=1;
            }


            System.out.println( dfr.DATA.toString());
        }
}
