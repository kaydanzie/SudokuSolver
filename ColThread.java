import java.util.*;

class ColThread implements Runnable{
    public Thread t;
    public String oneCol;
    public String value = "";
    
    ColThread(String oneCol){
        this.oneCol = oneCol;
    }
    
    
    public void run(){


        ArrayList<String> asArray = new ArrayList<String>();
        ArrayList<String> endArray = new ArrayList<String>();
        ArrayList<Integer> occur = new ArrayList<Integer>();
        for(int i=0; i<oneCol.length(); i++){
            String temp = ""+oneCol.charAt(i)+"";
            asArray.add(temp);

            endArray.add(i, "0");
        }
        
        for(int k=1; k<=9; k++){

            String ck = ""+k+"";
            if(Collections.frequency(asArray, ck) > 1){
                
                occur = allOccur(asArray, ck);

                for(int j=0; j<occur.size(); j++){
                    endArray.set(occur.get(j), "1");
                }
            }
        }


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
            t = new Thread(this, oneCol);
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