package simulator;

public abstract class Simulator extends Node {
	
	private String name;
	private String state;
	public String[] xInputEvent;
	public String[] xOutputEvent;
	
	public Simulator(Node parent, String name) {
		super(parent, name);
		children = null;
	}
	
	@Override
	public void receiveMessage(String nachricht) {
		
		// *-Nachricht
		if (nachricht.split(",")[0].equals("*")) {
			
			// 1. lambda ausführen
			// Senden der eigenen Ausgaben an Vater ↑y,y_i,
			parent.receiveMessage("y," + lambda(state));
			
			
			// 2.neuen State anhand δ_int herausfinden
			// internen Zustandsübergang ausführen
			state = delta_int(state);
			
			// 3. tonie vom aktuellen State herausfinden und
			// sende ↑d,tonie_i an Vater			
			parent.receiveMessage("d," + timeAdvance(state));
		}
		
		// x-Nachricht
		else if (nachricht.split(",")[0].equals("x")) {
			
			// 1. externen Zustandsübergang ausführen
			state = delta_ext(state, elapsedTime, x);
			
			// 2. berechne tonie, tonie = t + timeAdvance(s)
			tonie = time + timeAdvance(state);
			
			// 3. sende tonie an Vater, ↑d,tonie_i
			parent.receiveMessage("d," + tonie);
			
		}
	}
	
	public abstract String lambda(String state);
	
	public abstract String delta_int(String state);
	
	public abstract String delta_ext(String state, long elapsedTime, String x);
	
	public abstract long timeAdvance(String state);
}

/*
//lambda-Funktion
	public String lambda(String state) {
		String returnMessage = null;
		
		// wähle anhand des aktuellen Zustands die Nachricht aus
		// die gesendet werden muss
		// TODO
		
		return returnMessage;
	}
	
	
	// Delta-Internal
	public String delta_int(String state) {
		String newState = null;
		
		// Logik um interne Zustandsübergänge herauszufiden
		
		return newState;
	}
*/
