import java.util.*;

class SquareThread implements Runnable{
	public Thread newT;
	public String rowsStr;
	ArrayList<String> allRows = new ArrayList<String>();
	ArrayList<String> theseRows = new ArrayList<String>();
	ArrayList<String> endArray = new ArrayList<String>();
	String value = "";

	ArrayList<String> oneSquare = new ArrayList<String>();

	SquareThread(String rowsStr){
		this.rowsStr = rowsStr;
	}



	

	public void run(){
		endArray.clear();
		ArrayList<Integer> occur = new ArrayList<Integer>();

		for(String ret : rowsStr.split("\\s*,\\s*")){
			allRows.add(ret);

			//utilize the fact that this will run 9 times
			//number of elements endArray will have
			//that initially need to be 0
			endArray.add("0");
		}

		int index = 0;
		for(int i=0; i<9; i+=3){
			//start with blank array
			theseRows.clear();
			oneSquare.clear();

			//add two to i when indexing
			theseRows.add(allRows.get(i));
			theseRows.add(allRows.get(i+1));
			theseRows.add(allRows.get(i+2));

			//row for loop goes through 9 cols every time
			index = 0;

			//have already narrowed it down to 3
			//CAN start at 0 every time

			for(int row=0; row<3; row++){
				String temp = theseRows.get(row);
				oneSquare.add(String.valueOf(temp.charAt(index)));
				oneSquare.add(String.valueOf(temp.charAt(++index)));
				oneSquare.add(String.valueOf(temp.charAt(++index)));
				++index;
			}

			//oneSquare filled at this point
			if(isValid(oneSquare)){}
			else{
				//need for loop to figure out which #s are wrong
				for(int k=1; k<=9; k++){
            		System.out.println(oneSquare.get(k-1));
		            String rk = ""+ k + "";
		            //check if value rk shows up more than once in row
		            if(Collections.frequency(oneSquare, rk) > 1){
		                
		                //get indexes of where rk occurs
		                //more than once
		                occur = allOccur(oneSquare, rk);

		                //put 1 in indeces of repeated values
		                for(int j=0; j<occur.size(); j++){
		                    endArray.set(occur.get(j), "1");
		                }
            		}

        		}//end inner for loop
			}//end else
		}

		//change from array to concatenated string to be returned
        for(int h=0; h<endArray.size(); h++){
            this.value += endArray.get(h);
        }

	}



	public boolean isValid(ArrayList<String> square){
		for(int h=1; h<=9; h++){
			String t= ""+h+"";
			if(!square.contains(h)){
				return false;
			}
		}
		return true;
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