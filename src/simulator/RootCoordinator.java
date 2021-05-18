package simulator;

public class RootCoordinator extends Node{
	
	public RootCoordinator(String name) {
		super(null, name);
	}
	

	
	//Nachricht aus Kind
	@Override
	public void receiveMessage(String nachricht) {
		String ntime = "2"; 												//TODO
		
	
		if(nachricht.split(",")[0].equals("y")) {
			for(Node node : children) {
				
				node.receiveMessage(String.format("x,%s", ntime)); 			//TODO control ntime
				System.out.println(nachricht.split(",")[1]);			    //  -> trial line works fine
				}
			}
			
	 // RootCoordinator kann keine x-Nachricht kriegen
		
		
		else if(nachricht.split(",")[0].equals("d")) {
			for(Node node : children) {
				node.receiveMessage(nachricht);
			}
		}
	}

	
	
	//Initialisiert den nächsten Schritt mit einer *-Nachricht
	public void initializeNextStep(String time) {
		
		String initNachricht = String.format("*,%s", time);
		
		for(Node node : children) {
			node.receiveMessage(initNachricht);
		}
	}


}
