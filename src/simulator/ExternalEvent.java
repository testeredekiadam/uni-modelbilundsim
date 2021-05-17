package simulator;

public class ExternalEvent extends Event{
	
	public ExternalEvent(long time, long duration) {
		super(time, duration);
	}


	public String trigger() {
		System.out.println("Hallo external event");
		return "d";
	}

}
