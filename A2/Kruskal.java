package A2;
import java.util.*;

public class Kruskal{

    public static WGraph kruskal(WGraph g){
    	
    	ArrayList<Edge> edges = g.listOfEdgesSorted();
        DisjointSets d = new DisjointSets(g.getNbNodes());
        WGraph MST = new WGraph();
        
        for(Edge x : edges) {
        // loop through all edges	 
        
        	int nod1 = x.nodes[0];
        	int nod2 = x.nodes[1];
        	
        	
        	if(IsSafe(d, x)) { 
        		d.union(nod1, nod2);
        		MST.addEdge(x);} // add edge to MST if safe
        }
        
        return MST;
    }

    public static Boolean IsSafe(DisjointSets p, Edge e){
    	int nod1 = e.nodes[0];
    	int nod2 = e.nodes[1];
    	if(p.find(nod1) == p.find(nod2)) {
    		return false;}
        return true;    
    }


    public static void main(String[] args){

        String file = args[0];
        WGraph g = new WGraph(file);
        WGraph t = kruskal(g);
        System.out.println(t);

   } 
}
