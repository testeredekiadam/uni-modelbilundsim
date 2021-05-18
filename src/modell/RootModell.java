package modell;

import java.util.ArrayList;

import simulator.*;

public class RootModell extends Coordinator{

	
	private int y_axis;
	private int x_axis;
	

	public RootModell(RootCoordinator parent, String name, int x_axis, int y_axis) {
		super(parent, name);
		this.x_axis = x_axis;
		this.y_axis = y_axis;
	}
	
	public void createField() {
		Region[][] field = new Region[x_axis][y_axis];
		for (int i = 0; i < x_axis; i++) {
			for (int j = 0; j < y_axis; j++) {					
				}
			}
		
		}
	
	

	public Node select(long t) {
		Node t_select = null;
		ArrayList<Node> selectedList = new ArrayList<>();
		
		// Wähle Simulator dessen tonie = t
		for (Node node : children) {
			if (node.tonie == t) {
				selectedList.add(node);
			}
		}
		
		// Prioritätsimplementierung
		// TODO
		
		return t_select;
	}
}
