import java.io.*;
import java.util.*;

public class Multithread{
    
    String[] rows = new String[9];
    ArrayList<Integer> rowValues = new ArrayList<Integer>();
    String[] cols = new String[]{"","","","","","","","",""};//so not null


    public static void main(String []args){
        Multithread m = new Multithread();
        m.readIn("Test.txt");


        RowThread test;
        //thread join should wait for run() to finish
        for(int i=0; i<m.rows.length; i++){
            test = new RowThread(m.rows[i]);
            test.start();
            test.join();
            p(test.getValue());
            m.rowValues.add(test.getValue());
        }

        //should be if all values aren't 0
        //values are index of incorrect number
        if(!m.rowValues.contains(1)){
            p("file valid");
        }


        String oneRow;
        for(int i=0; i<9; i++){
            oneRow = m.rows[i];
            oneRow = oneRow.replaceAll(",", "");

            //now go through elements of one row
            //without changing rows (need 2 loops)
            for(int k=0; k<9; k++){
                m.cols[k] += oneRow.charAt(k);
            }
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
             rows[count] = line;
             count++;
            } 
         }
         catch(Exception e){
             
         }
     }//end readIn method
     
     
}

