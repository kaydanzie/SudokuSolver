import java.io.*;
import java.util.ArrayList;

public class Multithread{
    
    String[] rows = new String[9];

     public static void main(String []args){
        Multithread m = new Multithread();
        m.readIn("Test1.txt");
        
        for(int i=0; i<m.rows.length; i++){
            System.out.println(m.rows[i]);
        }
        
        
        ThreadRunnable test = new ThreadRunnable(m.rows[0]);
        test.start();
        test.join();
        System.out.println(test.getValue());
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


class ThreadRunnable implements Runnable{
    public Thread t;
    public String oneRow;
    public int value =0;
    
    ThreadRunnable(String oneRow){
        this.oneRow = oneRow;
    }
    
    
    public void run(){
        String[] stringNums = oneRow.split(", ");
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        for(int i=0; i<stringNums.length; i++){
            nums.add(Integer.parseInt(stringNums[i]));
        }
        
        for(int k=1; k<9; k++){
            //if k number is not in row, return which number
            //leave loop immediately
            if(!nums.contains(k)){
                this.value = k;
                //System.out.println(value);
                break;
            }
        }
    }
    
    
    
    public void join(){
        try{
            Thread.sleep(2000, 500);
        }
        catch(InterruptedException e){
            
        }
        
    }
    
    
    public void start(){
        if(t==null){
            t = new Thread(this, oneRow);
            t.start();
        }
    }
    
    
    public int getValue(){
        return value;
    }
}