
class ColThread implements Runnable{
    public Thread t;
    public String oneCol;
    public String value = "";
    
    ColThread(String oneCol){
        this.oneCol = oneCol;
    }
    
    
    public void run(){
        
        for(int k=1; k<=9; k++){
            //if k number is not in row, return which number
            //leave loop immediately
            String ck = ""+k+"";
            if(!oneCol.contains(ck)){
                this.value += k;
            }
            else{
                this.value += "0";
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
            t = new Thread(this, oneCol);
            t.start();
        }
    }
    
    
    public String getValue(){
        return value;
    }
}