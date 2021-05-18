package simulator;

public abstract class Coordinator extends Node {

	public Coordinator(Node parent, String name) {
		super(parent, name);
	}
	
	@Override
	public void receiveMessage(String nachricht) {
		
		// *-Nachricht
		if (nachricht.split(",")[0].equals("*")) {
			
			// 1. wähle mit select das Kind dessen tonie = t
			Node select_t = null;
			select_t = select(Long.parseLong(nachricht.split(",")[1], 10));
			
			// 2. weiterleiten (↓*, t) an das Kind mit tonie = t
			select_t.receiveMessage(nachricht);
			
		}
		
		// y-Nachricht
		if (nachricht.split(",")[0].equals("y")) {
			
			// 1. Weiterleiden der Ausgaben an Vater (↑y,toFather(y))
			parent.receiveMessage(nachricht);
			
			// 2. entsprechend der Ports weiterleiden an eigene Kinder
			
			// TODO
			
		}
		
		// x-Nachricht
		
		// 1. Eingabe von oben Weiterleiden an Kinder,↓x
		if (nachricht.split(",")[0].equals("x")) {
			
			for(Node node : children) {
				node.receiveMessage(nachricht);
			}		
		}
		
		// d-Nachricht
		if (nachricht.split(",")[0].equals("d")) {
			
			// 1. aktualisiere tonies der Kinder
			// TODO
			
			// 2. errechne minimum der tonies aller Kinder, tonie_min
			long tonie_min = 1000;
			// TODO tonie_min muss variabel sein
			for (Node node : children) {
				if (node.tonie < tonie_min) {
					tonie_min = node.tonie;
				}
			}
			
			// 3. sende minimum der tonies an Vater, ↑d,tonie_min
			parent.receiveMessage("d," + tonie_min);			
		}
	}
	
	
	// Select-Funktion
	public Node select(long t) {
		Node t_select = null;
		
		// Wähle Simulator dessen tonie = t
		for (Node node : children) {
			if (node.tonie == t) {
				t_select = node;
			}
		}
		
		// Prioritätsimplementierung
		
		return t_select;
	}

}
