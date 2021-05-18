package simulator;

public class Simulator extends Event{
	
	private String name;
	public String[] xInputEvent;
	public String[] xOutputEvent;
	
	public Simulator(Node parent, String name) {
		super(parent, name);
		children = null;
		
	}

	@Override
	public void receiveMessage(String nachricht) {
		String state;
		
		//*-Nachricht triggert Internal Event
		if(nachricht.split(",")[0].equals("*")) {
			
			state = nachricht.split(",")[0];
			
			//führe lambda aus, Ausgabe an Parent-node senden
			parent.receiveMessage(String.format("y,%s", lambda(state)));
			
			//Zustandsübergang und an Vater senden
			state = delta_int(state);
			tonie = time + timeAdvance(state);
			parent.receiveMessage(String.format("%s,%d", state, tonie));
			//
			
			
		//x-Nachricht triggert External Event 
		} else if(nachricht.split(",")[0].equals("x")) {
			
			state = nachricht.split(",")[0];
			
			state = delta_ext(state, elapsedTime, state);
			tonie = time + tAdvance;
			parent.receiveMessage(String.format("d,%d", tonie));
		}
	}
	
	@Override
	public Node createChildNode(Node node) {
		return null;
	}
	
	
	public long timeAdvance(String zustand) {
		return 13;//TODO
	}
	
	
	public String lambda(String zustand) {
		
		return "y_i";
	}

	
	@Override
	public String delta_int(String nachricht) {
		
		return "d";
	}

	
	@Override
	public String delta_ext(String state, long elapsedTime, String x) {
		// TODO Auto-generated method stub
		return null;
	}
	 
}
