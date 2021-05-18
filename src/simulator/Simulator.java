package simulator;

public class Simulator extends Node{
	
	private String name;
	public Simulator(Node parent, String name) {
		super(parent, name);
		children = null;
		
	}

	@Override
	public void receiveMessage(String nachricht) {
		
		
		
		//*-Nachricht triggert Internal Event
		if(nachricht.split(",")[0].equals("*")) {
			Event internal = new InternalEvent(Long.parseLong(nachricht.split(",")[1]));
			//führe lambda aus, Ausgabe an Parent-node senden
			lambda("21");
			//Zustandsübergang und an Vater senden
			parent.receiveMessage(internal.trigger(5, "d"));
																												//change 21 to y_i
			//
			
			
		//x-Nachricht triggert External Event 
		} else if(nachricht.split(",")[0].equals("x")) {
			Event external = new ExternalEvent(Long.parseLong(nachricht.split(",")[1]));
			external.trigger(5, "d");
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
	public void lambda(String zustand) {
		if(zustand.equals("*")) {
			parent.receiveMessage("y, y_i");
		}
	}
	 
}
