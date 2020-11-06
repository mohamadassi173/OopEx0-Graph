package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


/*
 *	@author Mohamad Assi
 */
public class Graph_Algo implements graph_algorithms{
	
	private graph myGraph;
	///// for BFS algo
	private Queue<Integer> q;
	private final int START=-1; 
	private final String VISITED="VISITED", NOT_YET="NOT YET",NOT_VISITED="NOT VISITED";
	private int source;
	
	/**
	 * Basic constructor
	 * @param g0 - to g the graph
 	 */
	public Graph_Algo(graph g0) {
		myGraph = g0;
	}

	/**
	 * Basic constructor
	 */
	public Graph_Algo() {
		myGraph = new Graph_DS();
	}

	@Override
	public void init(graph g1) {
		myGraph = g1;
		// make the source to be the first key in the graph
		Collection<node_data> temp_nodes = myGraph.getV();
		if(temp_nodes.iterator().hasNext())
		source = temp_nodes.iterator().next().getKey();

		q = new PriorityQueue<Integer>();
	}

	@Override
	public graph copy() {
		graph g = new Graph_DS();
		// add g1 nodes to graph g
		for (node_data n : myGraph.getV()) {
			if (n != null) {
				NodeData n1 = new NodeData(n); // create new node for deep copy
				g.addNode(n1);
			}
		}
		// add g1 edges to graph g
		Iterator<node_data> n2 = myGraph.getV().iterator();
		for(node_data n1 : myGraph.getV()){
			while(n2.hasNext()) {
				node_data temp = n2.next();
				if(n1.hasNi(temp.getKey()))
					g.connect(n1.getKey(), temp.getKey());
			}
		}
		return g;
	}

	@Override
	public boolean isConnected() {
		Collection<node_data>   n = myGraph.getV(); // return all vertices 
		if(n.isEmpty()) return true;
		bfs(); // do bfs algo to color all connected vertices 
		for(node_data   n1 : myGraph.getV()) {
			//return false  if the node not connected
			if(n1.getInfo() != VISITED ) return false; 
		}
		return true;
	}

	@Override
	public int shortestPathDist(int src, int dest) {
		// if theres is no path between src node and dest node
		if(shortestPath(src,dest)==null || shortestPath(src,dest).isEmpty()) return -1;
		// get the shortest path then return the size of it..
		return shortestPath(src,dest).size()-1;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> path =  new ArrayList<node_data>(); // the path we want to return
		if (dest==src) { // return the path with only source node
		path.add(new NodeData(src));
		return path;
		}
		source = src;
		bfs();
		if (myGraph.getNode(dest).getTag()==START) return null; 
		else{ 
			path.add(new NodeData(dest)); // adds the dest node
			int t= ((NodeData) myGraph.getNode(dest)).getPred();
			while (t != START){ 
				path.add(new NodeData(t));
				t = ((NodeData) myGraph.getNode(t)).getPred();;
			}
		}
		return list_reverse(path); // reverse the list then return it
		}

	
	
    ////////////////////// Private Functions /////////////////////
	
	/**
	 * 
	 * 
	 */
	public void bfs() {
		for (node_data n : myGraph.getV()) {
			((NodeData) n).setPred(START);
				n.setInfo(NOT_VISITED);
				n.setTag(START);
		}
		myGraph.getNode(source).setTag(0);
		myGraph.getNode(source).setInfo(NOT_YET);
		q.add(source);
		while (!q.isEmpty()) { // check if we finished modifed all nieghbors..
		int u = q.poll();
		if(myGraph.getV(u) != null && !myGraph.getV(u).isEmpty())
		for(node_data n1 : myGraph.getV(u)) { // check every node with nieghbors
			if(myGraph.getNode(n1.getKey()).getInfo() == NOT_VISITED) {
				myGraph.getNode(n1.getKey()).setTag(myGraph.getNode(u).getTag()+1);
				((NodeData) myGraph.getNode(n1.getKey())).setPred(u);
				myGraph.getNode(n1.getKey()).setInfo(NOT_YET);
				q.add(n1.getKey());
			}
		}
		myGraph.getNode(u).setInfo(VISITED);
		}
	}
	
	/**
	 * reverse list of node_data
	 * @param a - the list to reverse 
	 * @return reversed list
	 */
	private List<node_data> list_reverse(List<node_data> a) {
		List<node_data> b =  new ArrayList<node_data>();
		for (int i = 0; i < a.size(); i++) {
			b.add(a.get(a.size()-i-1));// copy from the end of the list
		}
		return b;
		
	}
	
	
}


