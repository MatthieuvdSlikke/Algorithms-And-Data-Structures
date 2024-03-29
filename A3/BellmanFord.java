package A3;

import java.io.*;
import java.util.*;

public class BellmanFord{

	
	/**
	 * Utility class. Don't use.
	 */
	public class BellmanFordException extends Exception{
		private static final long serialVersionUID = -4302041380938489291L;
		public BellmanFordException() {super();}
		public BellmanFordException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 * 
	 * Use this to specify a negative cycle has been found 
	 */
	public class NegativeWeightException extends BellmanFordException{
		private static final long serialVersionUID = -7144618211100573822L;
		public NegativeWeightException() {super();}
		public NegativeWeightException(String message) {
			super(message);
		}
	}
	
	/**
	 * Custom exception class for BellmanFord algorithm
	 *
	 * Use this to specify that a path does not exist
	 */
	public class PathDoesNotExistException extends BellmanFordException{
		private static final long serialVersionUID = 547323414762935276L;
		public PathDoesNotExistException() { super();} 
		public PathDoesNotExistException(String message) {
			super(message);
		}
	}
	
    private int[] distances = null;
    private int[] predecessors = null;
    private int source;

    BellmanFord(WGraph g, int source) throws BellmanFordException{
        /* Constructor, input a graph and a source
         * Computes the Bellman Ford algorithm to populate the
         * attributes 
         *  distances - at position "n" the distance of node "n" to the source is kept
         *  predecessors - at position "n" the predecessor of node "n" on the path
         *                 to the source is kept
         *  source - the source node
         *
         *  If the node is not reachable from the source, the
         *  distance value must be Integer.MAX_VALUE
         *  
         *  When throwing an exception, choose an appropriate one from the ones given above
         */
    	int numNodes = g.getNbNodes();     
        distances = new int [numNodes];
        distances[source]= 0;
        predecessors= new int [numNodes];
        
        for ( Edge e : g.getEdges() ){ 
           	int u= e.nodes[0];
           	int v= e.nodes[1];
           	int weight= e.weight;
           		if (distances[u]+ weight< distances[v]) {
                 throw new NegativeWeightException ();}} 
       
         for ( int i = 0; i < numNodes-1; i++ ){
             predecessors[i]= -1;
             distances[i]=Integer.MAX_VALUE;
             for( Edge e : g.getEdges() ){
            	 if(distances [ e.nodes[0] ] + e.weight< distances [ e.nodes[1] ] ){
                     distances [ e.nodes[1] ]= distances[ e.nodes[0] ]+ e.weight;
                     predecessors [ e.nodes[1]]= e.nodes[0];}}}   
    }

    public int[] shortestPath(int destination) throws BellmanFordException{
        /*Returns the list of nodes along the shortest path from 
         * the object source to the input destination
         * If not path exists an Exception is thrown
         * Choose appropriate Exception from the ones given 
         */

        /* YOUR CODE GOES HERE (update the return statement as well!) */
    	ArrayList <Integer> rev = new ArrayList<Integer>();
        rev.add(destination);
        int predecessor = this.predecessors[destination];
        int c = 0;
        while ( predecessor != source ) {
            rev.add(predecessor);
            predecessor = this.predecessors [predecessor];
            if(predecessor == -1) {
                throw new  PathDoesNotExistException();}}
        rev.add(predecessor);
        int [] sP= new int [rev.size()];
        for (int i= rev.size()-1; i>=0; i--){
          sP[c]= rev.get(i);
          c++;}
    return sP;
  }

    public void printPath(int destination){
        /*Print the path in the format s->n1->n2->destination
         *if the path exists, else catch the Error and 
         *prints it
         */
        try {
            int[] path = this.shortestPath(destination);
            for (int i = 0; i < path.length; i++){
                int next = path[i];
                if (next == destination){
                    System.out.println(destination);
                }
                else {
                    System.out.print(next + "-->");
                }
            }
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }
    }

    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        try{
            BellmanFord bf = new BellmanFord(g, g.getSource());
            bf.printPath(g.getDestination());
        }
        catch (BellmanFordException e){
            System.out.println(e);
        }

   } 
}
