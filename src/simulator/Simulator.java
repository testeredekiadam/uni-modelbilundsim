package simulator;

public abstract class Simulator extends Node{
	
	private static long elapsedTime;
	private String name;
	private String state;
	private String actstate;
	public String[] xInputEvent;
	public String[] xOutputEvent;
	
	public Simulator(Coordinator parent, String name) {
		super(parent, name, elapsedTime);
		children = null;
		
	}
	
	public String getState() {
		return state;
	}
	
	public void setState(String newState) {
		this.state = newState;
	}
	

	
	@Override
	public void receiveMessage(String nachricht) {
		
		
		
		//*-Nachricht triggert Internal Event
		if(nachricht.split(",")[0].equals("*")) {
			time = Long.parseLong(nachricht.split(",")[1]);
			
			
			//führe lambda aus, Ausgabe an Parent-node senden
			parent.receiveMessage(String.format("y,%s", lambda(getState())));
			
			//Zustandsübergang und an Vater senden
			delta_int(getState());
			tonie = time + timeAdvance(getState());
			setState(getState());
			parent.receiveMessage(String.format("d,%d", getState(), tonie));
			//
			
			
		//x-Nachricht triggert External Event 
		} else if(nachricht.split(",")[0].equals("x")) {
		
			String x = nachricht.split(",")[1];
			
			
			delta_ext(getState(), elapsedTime, x);
			
			tonie = time + timeAdvance(getState());
			setState(getState());
			parent.receiveMessage(String.format("d,%d", tonie));
		}
	}
	
	@Override
	public Node createChildNode(Node node) {
		return null;
	}
	
	
	public abstract String lambda(String state);
	
	public abstract void delta_int(String state);

	
	public abstract void delta_ext(String state, long elapsedTime, String x);
	
	public abstract long timeAdvance(String state);
	 
}
