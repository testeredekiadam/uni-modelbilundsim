package modell;

import simulator.*;

public class Region extends Simulator{
	protected long tonie;
	
	public Region(Node parent, String name) {
		super(parent, name);
	}
	
	String regionen[] = new String[] {"Wood","Meadow","Fire"};
	public void regeln(String regionZstd) {
		
		//interne Ereignisse
		if(regionZstd.equals("Wood")) {
			return;//keine Ereignisse
		}else if(regionZstd.equals("Meadow")) {
			tonie = 5;
			
			
			
			//5++ Meadow -> Wood
		}else if(regionZstd.equals("Fire")) {
			
			//11++ Fire -> Meadow
			//feuernachricht zu den gekoppelten regionen senden
		}
		
	}
	@Override
	public String lambda(String state) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delta_int(String state) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String delta_ext(String state, long elapsedTime, String x) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long timeAdvance(String state) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}


