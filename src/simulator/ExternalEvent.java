package simulator;

public class ExternalEvent extends Event{
	
	public ExternalEvent(long duration) {
		super(duration);
	}


	public String trigger(long duration, String nZustand) {
		System.out.println("Hallo external event");
		return "d";
	}

}
