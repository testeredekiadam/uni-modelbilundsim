package simulator;

public class Match extends Simulator {

	public Match(Node parent, String name) {
		super(parent, name);
	}
	
	@Override
	public String lambda(String state) {
		
		return "FireMessage";
	}
	
	@Override
	public void delta_int(String state) {
		
	}
	
	@Override
	public void delta_ext(String state, long elapsedTime, String x) {
		
	}
	
	@Override
	public long timeAdvance(String state) {
		
		return 10;		
	}

}
