package ex0;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;


/*
 * 
 * 
 *	@author Mohamad Assi
 */
public class Graph_Algo implements graph_algorithms{
	
	private graph myGraph;
	private int size;
	private Queue<Integer> q; // for bfs algo
	private int dist[], pred[],color[]; // for bfs algo
	private final int WHITE=1,GRAY=2,BLACK=3,NIL=-1; // for bfs algo
	private int source; // for bfs algo
	
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

	/**
	 * copy the graph, then get the size of the graph 
	 * and init some obj for bfs algo
	 * @param g0 - to copy the graph
 	 */
	@Override
	public void init(graph g1) {
		int m =0;
		myGraph = g1;
		// get the max key to init the size of arrays
		for(node_data n1 : myGraph.getV()) {
			int temp = n1.getKey();
			if(temp>m) {
				m=temp; // bigest key
			}
		}
		size =m+1;
		q = new ArrayBlockingQueue<Integer>(size);
		dist = new int[size];
		pred = new int[size];
		color = new int[size];
		source = 0;
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
		for(node_data n1 : myGraph.getV()){
			for(node_data n2 : myGraph.getV()) {
				if(n1.hasNi(n2.getKey()))
				g.connect(n1.getKey(), n2.getKey());
			}
		}
		return g;
	}

	@Override
	public boolean isConnected() {
		Collection<node_data>   n = myGraph.getV(); // return all vertices 
		if(n.isEmpty()) return true;
		bfs(n.iterator().next().getKey()); // do bfs algo to color all connected vertices 
		for(node_data   n1 : myGraph.getV()) {
			//return false  if the node not connected
			if(color[n1.getKey()] != BLACK ) return false; 
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
		bfs(src);
		if (dist[dest]==NIL) return null; 
		if (dest==src) { // return the path with only source node
		path.add(new NodeData(src));
		return path;
		}
		else{ 
			path.add(new NodeData(dest)); // adds the dest node
			int t = pred[dest];
			while (t != NIL){ 
				path.add(new NodeData(t));
				t = pred[t];
			}
		}
		return list_reverse(path); // reverse the list then return it
		}

	
	
    ////////////////////// Private Functions /////////////////////
	
	/**
	 * 
	 * @param src - the node we start to modefie 
	 */
	public void bfs(int src) {
		for (int i = 0; i < size; i++) {
			dist[i] = NIL;
			pred[i] = NIL;
			color[i] = WHITE;
		}
		source=src;
		dist[source]=0;
		color[source]=GRAY;
		q.add(source);
		while (!q.isEmpty()) { // check if we finished modifed all nieghbors..
		int u = q.poll();
		if(myGraph.getV(u) != null && !myGraph.getV(u).isEmpty())
		for(node_data n1 : myGraph.getV(u)) { // check every node with nieghbors
			if(color[n1.getKey()] == WHITE) {
				dist[n1.getKey()] = dist[u]+1;
				pred[n1.getKey()] = u;
				color[n1.getKey()] = GRAY;
				q.add(n1.getKey());
			}
		}
		color[u] = BLACK;	
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


