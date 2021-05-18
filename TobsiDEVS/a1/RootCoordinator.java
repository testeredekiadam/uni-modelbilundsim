package simulator;

public abstract class RootCoordinator extends Coordinator {

	public RootCoordinator(Node parent, String name) {
		super(null, name);
	}
	

	
	public void initializeNextStep() {
		for(Node node : children) {
			node.receiveMessage(nachricht);
		}
	}

}
