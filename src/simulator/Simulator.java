package simulator;

public abstract class Simulator extends Node{
	
	private String name;
	private String state;
	public String[] xInputEvent;
	public String[] xOutputEvent;
	
	public Simulator(Node parent, String name) {
		super(parent, name);
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
		
		String tempState = getState();
		
		//*-Nachricht triggert Internal Event
		if(nachricht.split(",")[0].equals("*")) {
			
			
			
			//führe lambda aus, Ausgabe an Parent-node senden
			parent.receiveMessage(String.format("y,%s", lambda(tempState)));
			
			//Zustandsübergang und an Vater senden
			tempState = delta_int(tempState);
			tonie = time + timeAdvance(tempState);
			parent.receiveMessage(String.format("%s,%d", tempState, tonie));
			//
			
			
		//x-Nachricht triggert External Event 
		} else if(nachricht.split(",")[0].equals("x")) {
			
			
			
			state = delta_ext(tempState, elapsedTime, state);
			tonie = time + tAdvance;
			parent.receiveMessage(String.format("d,%d", tonie));
		}
	}
	
	@Override
	public Node createChildNode(Node node) {
		return null;
	}
	
	
	public abstract String lambda(String state);
	
	public abstract String delta_int(String state);
	
	public abstract String delta_ext(String state, long elapsedTime, String x);
	
	public abstract long timeAdvance(String state);
	 
}
