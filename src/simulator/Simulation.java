package simulator;



public class Simulation {
	
	public static void main(String args[]) {
		

		RootCoordinator rcoord = new RootCoordinator();
		Coordinator coord = new Coordinator(rcoord);
		Simulator smltr = new Simulator(rcoord);
		
		rcoord.createChildNode(coord);
		coord.createChildNode(smltr);
		
		rcoord.initializeNextStep("123");
		rcoord.initializeNextStep("25");
		
	
	}
}
