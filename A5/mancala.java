package A5;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;


public class mancala {

public static void NbMove(int [] b, int [] ans, int j) {

	int n = b.length;
	int c = 0;
	
	for(int i =0;i < n; i++) {
		if(b[i] == 1 ) {
			c +=1;}}
	
	if(c<ans[j]) {
		ans[j]= c;}
	
	for(int m = 0 ; m  < n-2; m++) {
		if((b[m]==1 && b[m+1]==1 && b[m+2]==0) || (b[m] == 0 && b[m+1]==1 && b[m+2]==1)){
			int [] move= new int[12];
			for(int k=0; k<n;k++) {
				move[k]=b[k];}
			if(move[m]  == 0) {
				move[m] = 1;
			} else {
				move[m] = 0;}
			if(move[m+2]  == 0) {
				move[m+2] = 1;
			} else {
				move[m+2] = 0;}
			move[m+1] = 0;		
			NbMove(move, ans,j);
		 }}
	
}

public static void main(String[] args) throws IOException{
	
	long start = System.currentTimeMillis();

	BufferedReader br = new BufferedReader(new FileReader("testMancala.txt"));
	FileWriter writer = new FileWriter("testMancala_solution.txt");
	String strFirst= br.readLine();
	int cases = Integer.parseInt(strFirst);
	int[][]bd = new int[cases][12];
	int[]ans = new int [cases];
	

	for(int i=0; i< cases; i++){	
		ans[i]= 12;}
	int j =0;
	
	for(String line= br.readLine(); line!=null; line=br.readLine()){
		String [] spl = line.split(" ");
		int []b = new int [12];
		for(int i=0; i<12; i++) {b[i]=Integer.parseInt(spl[i]);}
		bd[j]= b;
		j+=1;}
	
	for(int i=0 ; i <cases ; i++) {
		NbMove(bd[i],ans,i);
		writer.write(""+ans[i]+"\n");	}
	
	br.close();
	writer.close();
	long end = System.currentTimeMillis();;
	System.out.println((end-start)+"ms");
}


}