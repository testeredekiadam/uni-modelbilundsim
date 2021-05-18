package simulator;

public abstract class Event {
	private long duration;
	
	public Event(long duration) {
		this.duration = duration;
	}
	
	public abstract String trigger(String nZustand, long duration);
}
