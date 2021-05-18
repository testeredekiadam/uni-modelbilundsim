package simulator;

public class ExternalEvent extends Event{
	
	public ExternalEvent(long duration) {
		super(duration);
	}


	public String trigger(String nZustand, long duration) {
		System.out.println("Hallo external event");
		return "d";
	}

}
