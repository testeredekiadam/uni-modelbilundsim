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
	public String delta_int(String state) {
		
		return "EmptyMessage";	
	}
	
	@Override
	public String delta_ext(String state, long elapsedTime, String x) {
		
		return "EmptyMessage";
	}
	
	@Override
	public long timeAdvance(String state) {
		
		return 10;		
	}

}
