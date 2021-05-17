package modell;

import simulator.Event;

import simulator.InternalEvent;

public class Region {
	
	
	String regionen[] = new String[] {"Wood","Meadow","Fire"};
	public void regeln(String regionZstd) {
		
		//interne Ereignisse
		if(regionZstd.equals("Wood")) {
			return;//keine interne
		}else if(regionZstd.equals("Meadow")) {
			
			
			//5++ Meadow -> Wood
		}else if(regionZstd.equals("Fire")) {
			
			//11++ Fire -> Meadow
			//feuernachricht zu den gekoppelten regionen senden
		}
		
	}

	
}


