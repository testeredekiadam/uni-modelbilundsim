package simulator;

public class InternalEvent extends Event{
	public InternalEvent(long time, long duration) {
		super(time, duration);
	}


	public String trigger() {
		System.out.println("Hallo internal event");
		return "y";
		
	}
		
}
