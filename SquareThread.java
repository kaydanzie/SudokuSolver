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
		int colI = 0;
		ArrayList<String> subRows = new ArrayList<String>();
		ArrayList<Integer> occur = new ArrayList<Integer>();
		String endStr = "";

		for(String ret : rowsStr.split("\\s*,\\s*")){
			allRows.add(ret);
			endArray.add("0");
		}

		for(int i=0; i<9; i+=3){
			subRows.clear();
			subRows.add(allRows.get(i));
			subRows.add(allRows.get(i+1));
			subRows.add(allRows.get(i+2));

			//gets all squares for 3 columns
			for(int x=0; x<3; x++){

				oneSquare.clear();
				endArray.clear();

				//reset end array, adds to return value with each square
				for(int y=0; y<9; y++){
					endArray.add("0");
				}
				
				//gets one square in 3 columns
				for(int k=0; k<3; k++){
					//k increments through 3 rows

					//row 1 col 1
					//endStr += String.valueOf(subRows.get(k).charAt(colI));
					oneSquare.add(String.valueOf(subRows.get(k).charAt(colI)));

					//row 1 col 2
					//endStr += String.valueOf(subRows.get(k).charAt(++colI));
					oneSquare.add(String.valueOf(subRows.get(k).charAt(++colI)));

					//row 1 col 3
					//endStr += String.valueOf(subRows.get(k).charAt(++colI));
					oneSquare.add(String.valueOf(subRows.get(k).charAt(++colI)));
					
					//add 1 in last loop to prep for next 3 cols
					if(k==2) colI++;
					else colI -= 2;
				}

				for(int h=1; h<=9;h++){
					String strH = ""+h+"";
					if(Collections.frequency(oneSquare, strH) > 1){
						occur = allOccur(oneSquare, strH);
						for(int u=0; u<occur.size(); u++){
							//put a 1 at indeces where a number occurs more than once
							endArray.set(occur.get(u), "1");
						}
					}
				}


				//change from array to concatenated string to be returned
		        for(int h=0; h<endArray.size(); h++){
		            this.value += endArray.get(h);
		        }

			}

			//start at 0 for columns for next 3 rows
			colI = 0;

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


    public void p(Object a){
    	System.out.println(a);
    }

}