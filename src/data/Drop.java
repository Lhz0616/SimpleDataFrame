package data;

public class Drop {
    DataFrameReader dfr = new DataFrameReader();
    Statistics s=new Statistics();

    public void dropDuplicate(String[] category, int num) {
        String[] header = dfr.DATA.get(0).toArray(new String[0]);
        int[] a=new int[dfr.header.length];
        int counter=0;
        String duplicate="";
        for (int i = 0; i < category.length; i++) {
            inner:
            for (int j = 0; j < dfr.header.length; j++) {
                if (category[i].equals(header[j])) {
                    a[i]=j;
                    break inner;
                }
            }
        }
        List<String> arr = new ArrayList<String>();
        //for every category detected and saved in array a
        for(int i=0;i<a.length;i++){
            //get column and store in a list
            for(int j=0;j<s.COLUMNDATA.size();j++) {
                arr.add(dfr.DATA.get(j).get(a[i]));
            }
            //for every element in list find duplicate and remove the duplicate row based on the num
            for(int k=0; k< arr.size(); k++) {
                inner:
                if (arr.lastIndexOf(arr.get(k)) != k)  {
                    duplicate=arr.get(k);
                    break inner;
                }
                x:
                if (arr.get(k).equals(duplicate))
                {
                    counter++;
                    if(counter==num){
                        dfr.DATA.get(k).clear();
                        break x;
                    }
                }
            }

        }


        System.out.println( dfr.DATA.toString());
    }


    }
