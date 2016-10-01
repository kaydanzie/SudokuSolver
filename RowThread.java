
import java.util.*;

class RowThread implements Runnable{
    public Thread t;
    public String oneRow;
    public int value =0;
    
    RowThread(String oneRow){
        this.oneRow = oneRow;
    }
    
    
    public void run(){
        String[] stringNums = oneRow.split(",");
        ArrayList<Integer> nums = new ArrayList<Integer>();
        
        for(int i=0; i<stringNums.length; i++){
            nums.add(Integer.parseInt(stringNums[i]));
        }
        
        for(int k=1; k<9; k++){
            //if k number is not in row, return which number
            //leave loop immediately
            if(!nums.contains(k)){
                this.value = k;
                break;
            }
        }
    }
    
    
    
    public void join(){
        try{
            Thread.sleep(1000, 500);
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