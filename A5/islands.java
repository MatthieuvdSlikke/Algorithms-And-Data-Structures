package A5;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.math.*;
import static java.lang.System.out;

public class islands {
	
	public static int IslndFd(char[][] o){
		int cl=o[0].length;
		int rl =o.length;
		if(o.length==0 || o==null || o[0].length==0){
	        return 0;}
	    int is = 0;
	    for(int r = 0;r < rl; r ++){
	        for(int c = 0; c < cl; c++){
	            if(o[r][c] == '-'){
	            	is++;
	                comb(o,r,c);}}}
	    return is;	
	}
	
	public static void comb(char[][] map, int r, int c){
		int cl= map[0].length;
		int rl =map.length;
	    if( c<0 || c>=cl || r<0 || r>=rl || map[r][c]!='-') {
	        return;}
	    map[r][c]='X';
	    comb(map,r-1,c);
	    comb(map,r, c-1);
	    comb(map,r+1, c);
	    comb(map,r, c+1);
	}


	public static void main(String[] args)throws IOException{
		
		long start = System.currentTimeMillis();
		
		
		BufferedReader br = new BufferedReader(new FileReader("testIslands.txt"));
		int cases =  Integer.parseInt(br.readLine());
		int NumberofIslands[] = new int[cases];
		for(int ct=0; ct< cases; ct++){
			String[] firstline = br.readLine().split(" ");
			int r = Integer.parseInt(firstline[0]);
			int c = Integer.parseInt(firstline[1]); 
			char[][] o = new char[r][c];
			for (int i= 0; i <r; i++) {
				String line = br.readLine();
				for (int j= 0; j < c; j++) {
					o[i][j] = line.charAt(j);}}
			NumberofIslands[ct] = IslndFd(o);
		 }
		 br.close();
		 FileWriter writer = new FileWriter("testIslands_solution.txt");
		 for(int i = 0 ; i < cases ; i++){
			 writer.write(NumberofIslands[i] + "\n");
		 }
		 writer.close();
		 
		 long end = System.currentTimeMillis();;
		 System.out.println((end-start)+"ms");
	}

}
