package simulator;

public class Coordinator extends Node {
	
	
	
	private static long elapsedTime;

	public Coordinator(RootCoordinator parent, String name) {
		super(parent, name, elapsedTime);
	}
	
	@Override
	public void receiveMessage(String nachricht) {
		
		//*-Nachricht an Child-node weiterleiten
		if(nachricht.split(",")[0].equals("*")) {
			
			// 1. wähle mit select das Kind dessen tonie = t
			Node select_t = null;
			select_t = select(Long.parseLong(nachricht.split(",")[1]));
			
			// 2. weiterleiten (*, t) an das Kind mit tonie = t
			select_t.receiveMessage(nachricht);

			
		// y-Nachricht an Parent-node weiterleiten
		}else if(nachricht.split(",")[0].equals("y")) {
			
			// 1. Weiterleiten der Ausgaben an Vater (y,toFather(y))
			parent.receiveMessage(nachricht);
			
			// 2. entsprechend der Ports weiterleiten an eigene Kinder
			
			// TODO
			
			
			
		
		}
		
		//x-Nachricht an Child-node weiterleiten
		else if(nachricht.split(",")[0].equals("x")) {
			for(Node node : children) {
				node.receiveMessage(nachricht);
			}
			
			
			
		//d-Nachricht an Parent-node weiterleiten
		}else if(nachricht.split(",")[0].equals("d")) {
			long tempTonie;
			// 1. aktualisiere tonies der Kinder
			// TODO
			//Ich verstehe das nicht was damit gemeint
			
			
			// 2. errechne minimum der tonies aller Kinder, tonie_min
			long tonie_min = 2000;
			// TODO tonie_min muss variabel sein
			
			
			for (Node node : children) {
				tempTonie = node.tonie;
				if (node.tonie < tonie_min) {
					tonie_min = node.tonie;
				}
			}
			
			// 3. sende minimum der tonies an Vater, â†‘d,tonie_min
			parent.receiveMessage("d," + tonie_min);
			
		}
	}
	
	//Select funktion
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
