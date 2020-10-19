package ex0;

import java.util.Collection;
import java.util.HashMap;

public class Graph_DS implements graph{

	private HashMap<Integer, node_data> nodes = new HashMap<Integer, node_data>();
	private HashMap<Integer, HashMap<Integer, edge_data>> edges = new HashMap<Integer, HashMap<Integer, edge_data>>();
	
	@Override
	public node_data getNode(int key) {
		return nodes.get(key);
	}

	@Override
	public boolean hasEdge(int node1, int node2) {
		if(!nodes.containsKey(node1) || !nodes.containsKey(node2))
			return false;
		else if(!edges.containsKey(node1)) return false;
		 return edges.get(node1).containsKey(node2);
	}

	@Override
	public void addNode(node_data n) {
		try {
			if(nodes.get(n.getKey()) == null) 
				nodes.put(n.getKey(), n);
		} catch (Exception e) {
			throw new RuntimeException("ERROR: this node ia already used");
		}
	}

	@Override
	public void connect(int node1, int node2) {
		try {
		if(this.nodes.get(node1) != null && this.nodes.get(node2)!=null){
		if (this.edges.get(node1) == null) { // add a new hash if there is no any edge from this key yet
			this.edges.put(node1, new HashMap<Integer, edge_data>()); // the key is the source key
			}
		this.edges.get(node1).put(node2, new edgeData(node1,node2));
		}
		 else {
			throw new RuntimeException("1 or 2 of the nodes doesnt't exist");
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
		 nodes.get(node_id);
		 return null;
	}

	@Override
	public node_data removeNode(int key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int nodeSize() {
		return nodes.size();
	}

	@Override
	public int edgeSize() {
		return edges.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return 0;
	}

}
