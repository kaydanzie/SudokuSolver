import java.io.*;
import java.util.*;

public class Multithread{
    
    String[] rows = new String[9];
    ArrayList<String> rowValues = new ArrayList<String>();//has indexes of incorrect, or 0s
    String[] cols = new String[]{"","","","","","","","",""};//so not null
    ArrayList<String> colValues = new ArrayList<String>();


    //row and colValues: value is number that's missing
    //position is where it appears
    //doesn't give current value, can be looked up



    public static void main(String []args){
        Multithread m = new Multithread();
        m.readIn("Test.txt");


        //get cols from rows
        String oneRow;
        for(int i=0; i<9; i++){
            oneRow = m.rows[i];

            //now go through elements of one row
            //without changing rows (need 2 loops)
            for(int k=0; k<9; k++){
                m.cols[k] += oneRow.charAt(k);
            }
        }


        //every row has 9 possible incorrect
        //fill arrays for correct or incorrect values
        //rows and columns separate
        RowThread rowT;
        ColThread colT;
        for(int i=0; i<9; i++){
            rowT = new RowThread(m.rows[i]);
            rowT.start();
            rowT.join();
            //p("row "+(i+1)+": "+rowT.getValue());
            p(rowT.getValue());
            m.rowValues.add(rowT.getValue());


            colT = new ColThread(m.cols[i]);
            colT.start();
            colT.join();
            p("column "+(i+1)+": "+colT.getValue());
            m.colValues.add(colT.getValue());
        }





    }
     




    //temp shortened print function
    public static void p(Object a){
        System.out.println(a);
    }



    //read file, put lines as strings into array "rows"
    public void readIn(String fileN){
         
        try{
            FileReader fStream = new FileReader(fileN);
            BufferedReader reader = new BufferedReader(fStream);

            String line = "";
            int count = 0;
            while((line = reader.readLine()) != null){
                rows[count] = line.replaceAll(",", "");
                count++;
            } 
        }
        catch(Exception e){
         
        }
    }//end readIn method
     
     
}

