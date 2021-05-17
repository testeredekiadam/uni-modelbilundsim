package simulator;

public abstract class Event {
	private long time;
	private long duration;
	
	public Event(long time, long duration) {
		this.time = time;
	}
	
	public abstract String trigger();
}
