package simulator;

import java.util.*;

public abstract class Node {
	protected String nachricht;
	protected Node parent;
	protected String name;
	protected List<Node> children = new ArrayList<Node>();
	
	public long tonie;
	protected long tAdvance;
	protected long time;
	protected long elapsedTime;
	
	public Node(Node parent, String name, long runtime) {
		this.parent = parent;
		this.name = name;
	}
	
	public Node createChildNode(Node node) {
		children.add(node);
		return node;
	}
	
	public abstract void receiveMessage(String nachricht);
	

	
}
