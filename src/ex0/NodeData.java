package ex0;

import java.util.ArrayList;
import java.util.Collection;

public class NodeData implements node_data {
	
	private Collection<node_data> NInodes;
	private static int id=0;
	private int key;
	private String info = "";
	private int tag; // for algorithms

	
	
	//////////////  Constructors
	
	/**
	 * Basic Constructor
	 */
	public NodeData() {
		this.NInodes = new ArrayList<node_data>();
		key=getId();
		NodeData.setId(NodeData.getId() + 1);
	}
	/**
	 * @param key - the Node number
	 */
	public NodeData(int key) {
		this.NInodes = new ArrayList<node_data>();
		this.key=key;
	}

	/**
	 * 
	 * @param NINodes - collection with all the Neighbour nodes of this node_data 
	 * @param key - the Node number
	 * @param info - the node info
	 * @param tag - Temporal data
	 */
	public NodeData(Collection<node_data> NINodes, int key, String info, int tag) {
		this.NInodes=NINodes;
		this.key=key;
		this.info=info;
		this.tag=tag;
	}
	
	/**
	 * Deep copy Constructor
	 * @param n - the node to copy
	 */
	public NodeData(node_data n) {
		NInodes=n.getNi();
		key=n.getKey();
		info=n.getInfo();
		tag=n.getTag();
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Collection<node_data> getNi() {
		if(NInodes.isEmpty()) { // check if the list is empty
			NInodes = new ArrayList<node_data>();
		}
		return NInodes;
	}

	@Override
	public boolean hasNi(int key) {
		for(node_data n1 : NInodes) {	// check if the key exist...
			if(n1.getKey()==key) return true;
		}
		return false;
	}

	@Override
	public void addNi(node_data t) {
		NInodes.add(t); // adds to list 
	}

	@Override
	public void removeNode(node_data node) {
		NInodes.remove(node); // remove from the list
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info=s;
	}

	@Override
	public int getTag() {
		return tag;
	}

	@Override
	public void setTag(int t) {
		this.tag=t;
	}
	
	public static int getId() {
		return id;
	}
	public static void setId(int id) {
		NodeData.id = id;
	}
	
	@Override
	public String toString() {
	return "" + this.getKey();
	}
}
