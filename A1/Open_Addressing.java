package A1;

import static A1.main.*;

public class Open_Addressing {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public int[] Table;

    //Constructor for the class. sets up the data structure for you
    protected Open_Addressing(int w, int seed) {

        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
        this.Table = new int[m];
        //empty slots are initalized as -1, since all keys are positive
        for (int i = 0; i < m; i++) {
            Table[i] = -1;
        }

    }

    /**
     * Implements the hash function g(k)
     */
    public int probe(int key, int i) {
       
        return (((A * key % power2(w))>>(w-r))+i) % m;
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table[hashValue] == -1;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
        	int i = 0; 
        	while (!isSlotEmpty( probe (key , i) )) {
        		if (i>m) {break;}
        		i++;
        	}
        	if (i>m) {
        		i=m;
        	}else {
        	Table[probe(key,i)]=key;}
        return i;
    }

    /**
     * Removes key k from hash table. Returns the number of collisions
     * encountered
     */
    public int removeKey(int key) {
       int i = 0;
     while (!isSlotEmpty( probe (key , i) ) && Table[probe(key,i)]!=key) {
    	   i++;
       }
       Table[probe(key,i)]=-1;
       return i;
    }

}
      

