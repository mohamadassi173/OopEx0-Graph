package ex0;

public class edgeData implements edge_data{
	
	private int src,dest,tag,weight;
	private String info;
	public edgeData(int node1, int node2) {
		src=node1;
		dest=node2;
	}

	@Override
	public int getSrc() {
		return src;
	}

	@Override
	public int getDest() {
		return dest;
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
