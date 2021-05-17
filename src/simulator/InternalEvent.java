package simulator;

public class InternalEvent extends Event{
	public InternalEvent(long duration) {
		super(duration);
	}

//duration and new S
	public String trigger(long duration, String nZustand) {
		
		return String.format("%d,%s", duration, nZustand);
		
	}

		
}
