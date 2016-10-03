import java.io.*;
import java.util.*;

public class Multithread{
    
    String[] rows = new String[9];
    ArrayList<String> rowValues = new ArrayList<String>();//has locations of incorrect, or 0s
    String[] cols = new String[]{"","","","","","","","",""};//fill so not null when appending values
    ArrayList<String> colValues = new ArrayList<String>();



    public static void main(String []args){
        Multithread m = new Multithread();
        m.readIn("Test.txt");


        //get cols from rows
        


        //every row has 9 possible incorrect
        //fill arrays for correct or incorrect values
        
        RowThread rowT;
        ColThread colT;
        //one row/col at a time
        //returns whether the value in the cell is repeated elsewhere in row/col
        //match 1s in rows and cols to see which repeated value is incorrect
        for(int i=0; i<9; i++){
            rowT = new RowThread(m.rows[i]);
            rowT.start();
            rowT.join();
            m.rowValues.add(rowT.getValue());


            colT = new ColThread(m.cols[i]);
            colT.start();
            colT.join();
            m.colValues.add(colT.getValue());
        }


        //if row in col matches col in row with # missing
        for(int row=0; row<9; row++){
            
            //if the same cell in row and column have a 1, print location
            //add 1 to printout make it not 0-index based
            String tempR = m.rowValues.get(j);
            for(int a=0; a<9; a++){
                if(tempR.charAt(row)==('1') && m.colValues.get(a).charAt(row)=='1'){
                    p("Incorrect value at row "+(row+1)+ " column "+(a+1));
                }
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
                rows[count] = line.replaceAll(",", "");
                count++;
            } 
        }
        catch(Exception e){
         
        }
    }//end readIn method
     
     
}

