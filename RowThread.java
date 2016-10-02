

class RowThread implements Runnable{
    public Thread t;
    public String oneRow;
    public String value = "";
    
    RowThread(String oneRow){
        this.oneRow = oneRow;
    }
    
    
    public void run(){

        for(int k=1; k<=9; k++){
            //if k number is not in row, return which number
            //leave loop immediately
            String rk = ""+k+"";
            if(!oneRow.contains(rk)){
                this.value += k;
            }
            else{
                this.value += "0";
            }

            // for(int j=0; j<9; j++){
                
            // }
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
    
    
    public String getValue(){
        return value;
    }
}