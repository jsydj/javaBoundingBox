import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BoundingBox implements BoundingBoxInterface {

	//Below fetchBoundingTriples() are not static, so it is reentrant-safe and multi-thread acess safe
	public List<String> fetchBoundingTriples(int leftXInt, int rightXInt, int lowerYInt, int upperYInt) {

		File directory = new File("./");
		//System.out.println(directory.getAbsolutePath());
		
        String csvFile = "./sample_data.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = " ";
        List<String> foundCoordinates=new ArrayList<String>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] triples = line.split(" ");

                double x=0, y=0, value=0;
                for(String oneTriple:triples) {
                	String[] csvValue = oneTriple.split(",");
                	x = Double.parseDouble(csvValue[0]);
                	y = Double.parseDouble(csvValue[1]);
                	value = Double.parseDouble(csvValue[2]);
                	
                	if (x>=leftXInt && x<=rightXInt && y>=lowerYInt && y<=upperYInt) {
                		foundCoordinates.add(x+","+y+","+value+" ");
                		//System.out.println("***"+"x="+x+", y="+y+", value="+value+"\n");
                	}
                	//else System.out.println("x="+x+", y="+y+", value="+value+"\n");
                	
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return (foundCoordinates);
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub


		int leftXInt=5, rightXInt=200, lowerYInt=10, upperYInt=80;

		
		leftXInt=readInput("Enter Left x value: ");

		rightXInt=readInput("Enter Right x value:");

		lowerYInt=readInput("Enter Lower y value: ");
		
		upperYInt=readInput("Enter Upper y value: ");



		//System.out.println(leftXInt+","+upperYInt+","+rightXInt+","+lowerYInt);
		BoundingBox boundingBoxInstance=new BoundingBox();
		List<String> foundCoordinates=boundingBoxInstance.fetchBoundingTriples(leftXInt, rightXInt, lowerYInt, upperYInt); 
		writeCSV(foundCoordinates);
        
        System.out.println("Done. Thank you!");

	        
	}
	
	private static void writeCSV(List<String> foundCoordinates) {
		

		try{
		    PrintWriter writer = new PrintWriter("query_results.txt", "UTF-8");
		    int count=0;
		    for (String coordinate:foundCoordinates) {
		    	writer.println(coordinate);
		    	count++;
		    }
		    writer.close();
		    System.out.println("Wrote "+count+" coordinates found to output file query_results.txt");
		} catch (IOException e) {
		   // do something
		}
	}	
	
	private static int readInput(String prompt) {
		
		Scanner scan;
		int value=0;
		boolean validData = false;
		do{
		    try{
			    System.out.println(prompt);
				scan = new Scanner(System.in);
		    	value = scan.nextInt();//tries to get data. Goes to catch if invalid data
			    validData=true;   
		    }catch(InputMismatchException e){
		        System.out.println("Input has to be a number. ");		        
		    }
 
		}while(validData==false);//loops until validData is true
		
		return value;
		    
	}

}
