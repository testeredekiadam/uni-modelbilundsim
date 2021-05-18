package modell;

import simulator.*;

public class Match extends Simulator{

	public Match(Coordinator parent, String name) {
		super(parent, name);
		
	}

	@Override
	public String lambda(String state) {
		// TODO Auto-generated method stub
		return "Fire-Message";
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
