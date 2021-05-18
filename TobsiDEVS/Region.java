package simulator;

public class Region extends Simulator {

	public Region(Node parent, String name) {
		super(parent, name);
	}
	
	
	@Override
	public String lambda(String state) {
		
		return "return";
		// TODO
	}
	
	@Override
	public String delta_int(String state) {
		
		// wood returns infinity
		
		if (state == "Meadow") {
			return "Wood";
		}
		
		else if (state == "Fire") {
			return "Meadow";
		}
		
		else {
			return "Wood";
		}
		
	}
	
	@Override
	public String delta_ext(String state, long elapsedTime, String x) {
		
		return "return";
		// TODO
	}
	
	@Override
	public long timeAdvance(String state) {
		
		// Wood returns infinity
		
		if (state == "Meadow") {
			return 5;
		}
		
		else if (state == "Fire") {
			return 11;
		}
		
		else {
			return 0;
		}
	}

}
