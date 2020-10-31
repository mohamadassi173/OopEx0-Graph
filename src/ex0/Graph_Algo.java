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
	private Queue<Integer> q;
	private int dist[], pred[],color[];
	private final int WHITE=1,GRAY=2,BLACK=3,NIL=-1;
	private int source;
	
	
	public Graph_Algo(graph g0) {
		myGraph = g0;
		int s= 0;
		for(node_data n1 : myGraph.getV()) {
			int temp = n1.getKey();
			if(temp>s) {
				s=temp;
			}
		}
		size = s+1;
		q = new ArrayBlockingQueue<Integer>(size);
		dist = new int[size];
		pred = new int[size];
		color = new int[size];
		source = 0;
	}


	public Graph_Algo() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public void init(graph g1) {
		int s =0;
		myGraph = g1;
		for(node_data n1 : myGraph.getV()) {
			int temp = n1.getKey();
			if(temp>s) {
				s=temp;
			}
		}
		size =s+1;
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
//		if(myGraph.nodeSize() == 0 || myGraph.nodeSize() == 1 ) return true;
//		if (myGraph.nodeSize() > myGraph.edgeSize()+2) return false;
		Collection<node_data>   n = myGraph.getV();
		if(n.isEmpty()) return true;
		bfs(n.iterator().next().getKey());
		for(node_data   n1 : myGraph.getV()) {
			if(color[n1.getKey()] != BLACK ) return false;
		}
		return true;
	}

	@Override
	public int shortestPathDist(int src, int dest) {
		if(shortestPath(src,dest)==null || shortestPath(src,dest).isEmpty()) return -1;
		return shortestPath(src,dest).size()-1;
	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> a =  new ArrayList<node_data>();
		bfs(src);
		//print();
		if (dest >= dist.length || dist[dest]==NIL) return null;
		if (dest==src) {
		a.add(new NodeData(src));
		return a;
		}
		else{
			a.add(new NodeData(dest));
			int t = pred[dest];
			while (t != NIL){ 
				a.add(new NodeData(t));
				t = pred[t];
			}
		}
		return list_reverse(a);
		}

	
	
    ////////////////////// Private Functions /////////////////////
	
	
	private graph copy(graph g1) {
		graph g = new Graph_DS();
		// add g1 nodes to graph g
		for (node_data n : g1.getV()) {
			if (n != null) {
//				NodeData n1 = new NodeData(n); // create new node for deep copy
				g.addNode(n);
			}
		}
		// add g1 edges to graph g
		for(node_data n1 : g1.getV()){
			for(node_data n2 : g1.getV()) {
				if(n1.hasNi(n2.getKey()))
				g.connect(n1.getKey(), n2.getKey());
			}
		}
		return g;
	}

	public void bfs(int s) {
		for (int i = 0; i < size; i++) {
			dist[i] = NIL;
			pred[i] = NIL;
//			if(myGraph.getNode(i) == null) color[i] = BLACK;
			color[i] = WHITE;
		}
		source=s;
		dist[source]=0;
		color[source]=GRAY;
		q.add(source);
		while (!q.isEmpty()) {
		int u = q.poll();
		if(myGraph.getV(u) != null && !myGraph.getV(u).isEmpty())
		for(node_data n1 : myGraph.getV(u)) {
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
	
	private List<node_data> list_reverse(List<node_data> a) {
		
		List<node_data> b =  new ArrayList<node_data>();
		for (int i = 0; i < a.size(); i++) {
			b.add(a.get(a.size()-i-1));
		}
		return b;
		
	}
	
	
}


