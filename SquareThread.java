import java.util.*;

class SquareThread implements Runnable{
	public Thread newT;
	public String rowsStr;
	ArrayList<String> allRows = new ArrayList<String>();
	ArrayList<String> first3 = new ArrayList<String>();
	ArrayList<String> middle3 = new ArrayList<String>();
	ArrayList<String> bottom3 = new ArrayList<String>();

	SquareThread(String rowsStr){
		this.rowsStr = rowsStr;
	}



	

	public void run(){

		int count = 0;
		for(String ret : rowsStr.split("\\s*,\\s*")){
			allRows.add(ret);
			if(count==0 || count==1 || count==2) first3.add(ret);
			else if(count==3 || count==4 || count==5) middle3.add(ret);
			else bottom3.add(ret);

			count++;
		}


	}


	public void start(){
		if(newT == null){
			newT = new Thread(this, rowsStr);
			newT.start();
		}
	}



	public void join(){
        try{
            Thread.sleep(1000, 500);
        }
        catch(InterruptedException e){
            
        }
    }
}