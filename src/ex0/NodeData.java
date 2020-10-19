package ex0;

import java.util.Collection;
import utils.Point3D;
public class NodeData implements node_data {
	private Collection<Point3D> points;
	private Collection<node_data> NInodes;
	private int key;
	private double weight;
	private String info = "";
	private int tag; // for algorithms
	private NodeData father; // for shortest path algo
	
	@Override
	public int getKey() {
		return key;
	}

	@Override
	public Collection<node_data> getNi() {
		return NInodes;
	}

	@Override
	public boolean hasNi(int key) {
		return !NInodes.isEmpty();
	}

	@Override
	public void addNi(node_data t) {
		NInodes.add(t);
		
	}

	@Override
	public void removeNode(node_data node) {
		NInodes.remove(node);
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

}
