package ex0;

import java.util.Collection;
import java.util.HashMap;

public class Graph_DS implements graph{

	private HashMap<Integer, node_data> nodes;
	private int MC;
	private int edges_size;	
	
	//// Basic constructor
	public Graph_DS() {
		nodes = new HashMap<Integer, node_data>();
		edges_size=0;
		MC=0;
		NodeData.setId(0);
	}
	
	@Override
	public node_data getNode(int key) {
		if(!nodes.containsKey(key)) return null;
		return nodes.get(key);
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		if(!nodes.containsKey(node1) || !nodes.containsKey(node2)) // check if node1 or node2 exist in the graph
			return false;
		// return true if node1 and node2 are Neighbours
		return nodes.get(node1).hasNi(node2) || nodes.get(node2).hasNi(node1);
	}
	
	
	@Override
	public void addNode(node_data n) {
		try {
			if(nodes.get(n.getKey()) == null) { // if node doesn't already exist.
				nodes.put(n.getKey(), n); // Add the node n.
			MC++;
			}
		} catch (Exception e) {
			throw new RuntimeException("ERROR: this node ia already added in the graph");
		}
	}

	@Override
	public void connect(int node1, int node2) {
		try {
			if(!hasEdge(node1, node2)) { // checking if node1 and node2 not connected
					if(node1!=node2) {
					// add Neighbours
					nodes.get(node1).addNi(nodes.get(node2));
					nodes.get(node2).addNi(nodes.get(node1));
					edges_size++; // increase edge size
					MC++;
					}
		}
		 else {
			return;
		 }
	} catch (RuntimeException e) {
		throw new RuntimeException("1 or 2 of the nodes doesnt't exist");
		}
	}
	
	@Override
	public Collection<node_data> getV() {
		return nodes.values();
	}

	@Override
	public Collection<node_data> getV(int node_id) {
		if(nodes.get(node_id)!=null)
		return nodes.get(node_id).getNi(); // Returns all Neighbours of node_id
		return null; // there is no Neighbours
	}

	@Override
	public node_data removeNode(int key) {
		node_data n=null;
		if(nodes.containsKey(key)) n = nodes.get(key); // To return it after remove
		else return n; // key is not exist in graph
		try {

			// remove all edges with the node we removed from the graph
			Collection<node_data> connectedNodes = n.getNi(); // all jeran 
			if(connectedNodes!=null)
			for(node_data r : connectedNodes) {
				if(r!=null)
				r.removeNode(n);
			}
			
			// remove the node from the graph.
			int edges_removed =  n.getNi().size();
			nodes.remove(key);
			edges_size -= edges_removed;
			MC++;
		} catch (Exception e) {
			throw new RuntimeException("This node with this key doesnt't exist");
			}
		return n;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		// check if node1 and node2 connected to decrease edge size
		if(nodes.get(node1).hasNi(node2) 
		|| nodes.get(node2).hasNi(node1)) edges_size--;
		nodes.get(node1).removeNode(nodes.get(node2));
		nodes.get(node2).removeNode(nodes.get(node1));
		
	}


	@Override
	public int nodeSize() {
		return nodes.size();
	}

	@Override
	public int edgeSize() {
		return  edges_size;
	}
	
	@Override
	public int getMC() {
		return MC;
	}
}
