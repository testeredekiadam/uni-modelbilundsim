package modell;

import simulator.*;

public class ModelTry {

	public static void main(String[] args) {
		
		RootCoordinator rCoord = new RootCoordinator("Root-Coordinator", 1000);
		Coordinator rModell = new Coordinator(rCoord, "Root-Modell");
		
		
		Region[][] field = new Region[21][21];
		for(int i = 0; i < 21; i++) {
			for(int j = 0; j < 21; j++) {
				String tName = String.format("Region (%d,%d)", i, j);
				try {
					field[i][j] = new Region(rModell, tName);
			
				}catch(Exception e) {
				}
			}
		}
	}

}
