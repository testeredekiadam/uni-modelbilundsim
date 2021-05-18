package simulator;

public class RootCoordinator extends Coordinator{
	
	private long runtime;
	private long initTime = 0;



	public RootCoordinator(String name, long runtime) {
		super(null, name);
		setTime(runtime);
	}
	
	public void setTime(long runtime) {
		this.runtime = runtime;
	}
	
	public long getTime() {
		return runtime;
	}
	
	
	public void setInitTime(long initime) {
		initime = getInitTime() + initime;
		this.initTime = initime;
	}
	
	public long getInitTime() {
		return initTime;
	}
	


	//Nachricht aus Kind
	@Override
	public void receiveMessage(String nachricht) {
		
		
		while(getInitTime() < getTime()) {
			
			//Initialisiert den nächsten Schritt mit einer *-Nachricht
			if(nachricht.isEmpty()) {
				for(Node node : children) {
					node.receiveMessage(String.format("*,%d", getInitTime()));
				}
			}
		
		//
			if(nachricht.split(",")[0].equals("y")) {
				for(Node node : children) {
					node.receiveMessage(String.format("x,%s", getInitTime()));		    
					}
				}
				
		 // RootCoordinator kann keine x-Nachricht kriegen
			
			
			else if(nachricht.split(",")[0].equals("d")) {
				for(Node node : children) {
					node.receiveMessage(nachricht);
				}
			}
			
		}

	}

	
	
	//Initialisiert den nächsten Schritt mit einer *-Nachricht

	


}
