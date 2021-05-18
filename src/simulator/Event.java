package simulator;

public abstract class Event extends Node{
	
	public Event(Node parent, String name) {
		super(parent, name);
	}

	public abstract String lambda(String state);
	
	public abstract String delta_int(String state);
	
	public abstract String delta_ext(String state, long elapsedTime, String x);
	
	public abstract long timeAdvance(String state);
}
