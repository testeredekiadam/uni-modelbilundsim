package simulator;

public class Coordinator extends Node {
	
	
	
	public Coordinator(Node parent) {
		super(parent);
	}
	
	@Override
	public void receiveMessage(String nachricht) {
		//*-Nachricht an Child-node weiterleiten
		if(nachricht.split(",")[0].equals("*")) {
			for(Node node : children) {
				//tonie = t mit Select funktion
				tonie = select(nachricht);
				node.receiveMessage(nachricht);
			}
			
		// y-Nachricht an Parent-node weiterleiten
		}else if(nachricht.split(",")[0].equals("y")) {
			parent.receiveMessage(nachricht);
			
		//x-Nachricht an Child-node weiterleiten
		}else if(nachricht.split(",")[0].equals("x")) {
			for(Node node : children) {
				node.receiveMessage(nachricht);
			}
			
		//d-Nachricht an Parent-node weiterleiten
		}else if(nachricht.split(",")[0].equals("d")) {
			
			parent.receiveMessage(nachricht);
		//Aktualisiere tonies der Kinder
			
			
		}
	}
	
	//Select funktion
	public long select(String nachricht) {
		long t;
		t = Long.parseLong(nachricht.split(",")[1]);
		return t;
	}

}
