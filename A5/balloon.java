package A5;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class balloon {
	
	public static int minimum_numbers_of_arrows(int b[]) 
    { 
    int height;
    int arrows = 0; 
   
    for (int i=0; i< b.length;i++) {
    	if (b[i]!=-1) {
    		height = b[i];
    		b[i]=-1;
    		 arrows++;
    		
    for (int x = i; x < b.length-1; x++) 	
    { if (b[x+1] == height-1 && b[x+1] != -1) { 	
        	height=height-1;
        	b[x+1]= -1 ;}}
    }
    }  
	return arrows; 
    } 
	
    public static void main(String[] args) throws IOException {
  
    long start = System.currentTimeMillis();
    
    File file = new File("testBalloons.txt");
	Scanner sc = new Scanner(file);
	Writer wr = new FileWriter("testBalloons_solution.txt");
    
	int pb = sc.nextInt();
	int [] numberOfBalloons = new int [pb];
	
	for(int i=0;i<pb;i++)
    {
		int temp = sc.nextInt();
    	numberOfBalloons[i] = temp;
    }
	for(int i=0;i<pb;i++)
	{
		int [] b = new int [numberOfBalloons[i]];
		
		for(int j=0;j<numberOfBalloons[i];j++)
		{
			b[j] = sc.nextInt();
		}
    int arrows = minimum_numbers_of_arrows(b);		
    wr.write(Integer.toString(arrows)+ "\n");}
	
	wr.flush();
	wr.close(); 
    
	long end = System.currentTimeMillis();
    System.out.println((end - start)+"ms");
    }
}


