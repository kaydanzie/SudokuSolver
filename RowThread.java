import java.util.*;

class RowThread implements Runnable{
    public Thread t;
    public String oneRow;
    public String value = "";
    
    RowThread(String oneRow){
        this.oneRow = oneRow;
    }
    
    
    public void run(){

        ArrayList<String> asArray = new ArrayList<String>();
        ArrayList<String> endArray = new ArrayList<String>();
        ArrayList<Integer> occur = new ArrayList<Integer>();
        for(int i=0; i<oneRow.length(); i++){
            String temp = ""+oneRow.charAt(i)+"";
            asArray.add(temp);

            endArray.add(i, "0");
        }

        for(int k=1; k<=9; k++){
            
            String rk = ""+ k + "";

            //check if value rk shows up more than once in row
            if(Collections.frequency(asArray, rk) > 1){
                
                //get indexes of where rk occurs
                //more than once
                
                occur = allOccur(asArray, rk);

                //put 1 in indeces of repeated values
                for(int j=0; j<occur.size(); j++){
                    endArray.set(occur.get(j), "1");
                }
            }

        }

        //change from array to concatenated string to be returned
        for(int h=0; h<endArray.size(); h++){
            this.value += endArray.get(h);
        }
    }
    
    
    
    public void join(){
        try{
            Thread.sleep(100, 500);
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
    
    
    public String getValue(){
        return value;
    }


    public ArrayList<Integer> allOccur(ArrayList<String> lis, String find){
        ArrayList<Integer> ret = new ArrayList<Integer>();
        
        for(int i=0; i<lis.size(); i++){
            if(lis.get(i).equals(find)){
                ret.add(i);
            }
        }
        return ret;
    }
}