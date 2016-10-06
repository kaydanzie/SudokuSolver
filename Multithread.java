import java.io.*;
import java.util.*;

public class Multithread{
    
    String[] rows = new String[9];
    ArrayList<String> rowValues = new ArrayList<String>();//has locations of incorrect, or 0s
    String[] cols = new String[]{"","","","","","","","",""};//fill so not null when appending values
    ArrayList<String> colValues = new ArrayList<String>();
    boolean correct = true;


    public static void main(String []args){
        Multithread m = new Multithread();
        m.readIn("Test3.txt");


        //get cols from rows
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
        
        RowThread rowT;
        ColThread colT;
        //one row/col at a time
        //returns whether the value in the cell is repeated elsewhere in row/col
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
            String tempR = m.rowValues.get(row);
            for(int a=0; a<9; a++){
                if(tempR.charAt(row)==('1') && m.colValues.get(a).charAt(row)=='1'){
                    p("Incorrect value at row "+(row+1)+ " column "+(a+1));
                    m.correct = false;
                }
            }
        }

        //correct never changed to false
        //in previous for loop
        if(m.correct) p("Puzzle is valid.");


        //make single string to pass to square thread
        String temp = "";
        for(int a=0; a<9; a++){
            temp += m.rows[a]+",";
        }

        //return [r1c1, r1c2, r1c3, r2c1, r2c2, r2c3, r3c1, r3c2, r3c3]
        SquareThread sqt = new SquareThread(temp);
        sqt.start();
        sqt.join();
        String allSquares = sqt.getValue();
        int squareNum = 1;
        
        //can't necessarily narrow down which is actually wrong if there are duplicates

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

