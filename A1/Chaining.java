package A1;

import java.util.*;
import static A1.main.*;

public class Chaining {

    public int m; // number of SLOTS AVAILABLE
    public int A; // the default random number
    int w;
    int r;
    public ArrayList<ArrayList<Integer>> Table;

    //Constructor for the class. sets up the data structure for you
    protected Chaining(int w, int seed) {
        this.w = w;
        this.r = (int) (w - 1) / 2 + 1;
        this.m = power2(r);
        this.Table = new ArrayList<ArrayList<Integer>>(m);
        for (int i = 0; i < m; i++) {
            Table.add(new ArrayList<Integer>());
        }
        this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
    }

    /**
     * Implements the hash function h(k)
     */
    public int chain(int key) {
    	
        return (A * key % power2(w))>>(w-r);
    }

    /**
     * Checks if slot n is empty
     */
    public boolean isSlotEmpty(int hashValue) {
        return Table.get(hashValue).size() == 0;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions
     * encountered
     */
    public int insertKey(int key) {
    	 
    	int listSize = Table.get(chain(key)).size();
    	Table.get(chain(key)).add(0, key);
    	return listSize;
        
    }

}

