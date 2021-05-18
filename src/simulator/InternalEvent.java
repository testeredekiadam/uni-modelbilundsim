package simulator;

public class InternalEvent extends Event{
	public InternalEvent(long duration) {
		super(duration);
	}

//duration and new S
	@Override
	public String trigger(String nZustand, long duration) {
		
		return String.format("%d,%s", duration, nZustand);
		
	}


		
}
