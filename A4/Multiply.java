package A4;
import java.util.*;
import java.io.*;

public class Multiply{

    private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }
    
    public static int[] naive(int size, int x, int y) {

       int [] result = new int [2];
       
       if (size == 1) {
    	   result[0]= x*y;
    	   result[1]= 1;
    	   return result;
       }
     
       else {
    	   
    	   int m = (int) Math.ceil ((double)size/2.0);
    	   
    	   int a = (int) Math.floor (x>>m);
           int b = x % (1<<m);
           int c = (int) Math.floor (y>>m);
           int d = y % (1<<m);
           
	       int[] e = naive(m, a, c);
	       int[] f = naive(m, b, d);
	       int[] g = naive(m, b, c);
	       int[] h = naive(m, a, d);
	       
	       result[0] = (e[0]<<(2*m)) + ((g[0]+h[0])<<m) + f[0];
	       result[1] = (e[1]+f[1]+g[1]+h[1]+(3*m)); 
	       return result;
       }
        
    }

    public static int[] karatsuba(int size, int x, int y) {
        

        int [] result = new int [2];
        
        if (size == 1) {
     	   result[0]= x*y;
     	   result[1]= 1;
     	   return result;
        }
      
        else {
     	   
     	   int m = (int) Math.ceil ((double)size/2.0);
     	   int p2= (int) Math.pow (2,m);
      	
     	   int a = (int) x/p2;
     	   int b =  x % p2; 
           int c = (int) y/p2;
           int d =  y % p2;
 
 	       int[] e = karatsuba(m, a, c);
 	       int[] f = karatsuba(m, b, d);
 	       int[] g = karatsuba(m, a-b, c-d);
 	       
 	      result[0] = (e[0]<<(2*m)) + ((e[0]+f[0]-g[0])<<m) + f[0];
 	      result[1] = (e[1]+f[1]+g[1]+(6*m));
 	      return result;
        
        }
        
    }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;
                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);
                    int y = randomInt(size);
                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);
            
                    if (resNaive[0] != resKaratsuba[0]) {
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];
                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;
                System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}
