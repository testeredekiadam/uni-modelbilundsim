package modell;

import java.util.Random;

import simulator.*;

public class Region extends Simulator{
	protected long tonie;
	
	public Region(Coordinator parent, String name) {
		super(parent, name);
	}
	
	@Override
	public String lambda(String state) {
		
		return "return";
		// TODO
	}
	
	@Override
	public void delta_int(String state) {
		
		// wood returns infinity
		
		if (state == "Meadow") {
			state = "Wood";
		}
		
		else if (state == "Fire") {
			state = "Meadow";
		}
		
		else {
			state = "Wood";
		}
		
	}
	
	@Override
	// changes state
	public void delta_ext(String state, long elapsedTime, String x) {
		
		if (x == "MatchMessage") {
			if (state == "Fire") {
				;
			}
			else {
				// 1% warscheinlichkeit , dass sich Region enzündet
				Random match = new Random();
				int randMatchZahl = 1 + match.nextInt(100);
				if (randMatchZahl <= 1) {
					state = "Fire";
				}
			}
		}
		
		else if (x == "NeighborFireMessage") {
			if (state == "Fire") {
				;
			}
			// EineRegionim  ZustandWoodkann  aberauch anfangen zu brennen mit einer 10% 
			// Wahrscheinlichkeit, wenn eine Nachbarregion eine Feu-ernachricht sende
			else if (state == "Wood") {
				Random neighbor = new Random();
				int randNeighborZahl = 1 + neighbor.nextInt(100);
				if (randNeighborZahl <= 20) {
					state = "Fire";
				}
				
			}
			
			// Befindet sich eine Region im ZustandMeadow, fängt sie beim Eingang 
			// einerFeuernachricht einer Nachbarregion immer an zu brennen.
			else if (state == "Meadow") {
				state = "Fire";
			}
		}
		
		
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


