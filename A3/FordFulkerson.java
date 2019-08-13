package A3;

import java.io.*;
import java.util.*;




public class FordFulkerson {

	
	public static ArrayList<Integer> pathDFS(Integer source, Integer destination, WGraph graph){
		
		ArrayList<Integer> Stack = new ArrayList<Integer>();
		ArrayList<Integer> Stackbis = new ArrayList<Integer>();
		Stack.add(source);
		Stackbis.add(source);
       
        while (Stackbis.size()>0){
            Integer a = Stackbis.remove(0);
            Integer b;
            if (a == destination) {
                break;}
            for ( Edge e: graph.listOfEdgesSorted() ){
            	if (a == e.nodes[0]){
                    b = e.nodes[1];
                    if (!Stack.contains(b) && e.weight != 0){
                        Stack.add(b);
                        Stackbis.add(b);
                        break;}}}
        }
		return Stack;
		
	}
	
	
	
	public static void fordfulkerson(Integer source, Integer destination, WGraph graph, String filePath){
		String answer="";
		String myMcGillID = "260662602"; //Please initialize this variable with your McGill ID
		int maxFlow = 0;
		WGraph caps = new WGraph(graph);
		WGraph res = new WGraph(graph);
		
		for(Edge e: graph.getEdges()){
			graph.setEdge(e.nodes[0], e.nodes[1], 0);}
		
		while(pathDFS(source, res.getDestination(), res).contains(destination) && pathDFS(source, destination, res).contains(source) ){
			
			ArrayList<Integer> DFSpath = pathDFS(source, destination, res);
			int bot = Integer.MAX_VALUE;
			
			for(int i = 0; i< DFSpath.size()-1; i++){
				Edge r = res.getEdge(DFSpath.get(i), DFSpath.get(i+1) );
				if(r != null && r.weight < bot){
					bot= r.weight;}}
			
			for(int i = 0; i < DFSpath.size() - 1; i++){ // augment and set residual flow
				Integer u = DFSpath.get(i);
				Integer v = DFSpath.get(i + 1);
				Edge e = graph.getEdge(u, v);
				Edge capE = caps.getEdge(u, v);
				if( e != null){graph.setEdge( u, v, e.weight+ bot );}
				else{ graph.setEdge ( u, v, e.weight- bot );}
				if( e.weight<= capE.weight ){res.setEdge( u, v, capE.weight-  e.weight );} 
				else if ( e.weight > 0 ) {Edge r = res.getEdge(u, v);
					if( r == null ){
						Edge bck = new Edge(u, v, e.weight);
						res.addEdge(bck);}
					else{
						res.setEdge( u, v, e.weight );}}}
			maxFlow += bot;
			bot = Integer.MAX_VALUE;
		}
		
		answer += maxFlow + "\n" + graph.toString();	
		writeAnswer(filePath+myMcGillID+".txt",answer);
		System.out.println(answer);
	}
	
	
	public static void writeAnswer(String path, String line){
		BufferedReader br = null;
		File file = new File(path);
		// if file doesnt exists, then create it
		
		try {
		if (!file.exists()) {
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(line+"\n");	
		bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	 public static void main(String[] args){
		 String file = args[0];
		 File f = new File(file);
		 WGraph g = new WGraph(file);
		 fordfulkerson(g.getSource(),g.getDestination(),g,f.getAbsolutePath().replace(".txt",""));
	 }
}
