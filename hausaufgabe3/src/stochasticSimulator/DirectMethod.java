package stochasticSimulator;
import java.util.Random;
import java.lang.Math;



public class DirectMethod {

	
	private static int wald = 1000;
	static int feuer = 1000;
	
	public DirectMethod(int wald, int feuer) {
		this.wald = wald;
		this.feuer = feuer;
	}
	
	
	
	public long r_0(int wald, int feuer) {
		return (wald*20);
	}
	
	public double r_1(int wald, int feuer) {
		return (wald*feuer*0.01);
	}
	
	public long r_2(int wald, int feuer) {
		return (feuer*20);
	}
	
	public double a_sum() {
		return (r_0(wald, feuer) + r_1(wald, feuer) + r_2(wald, feuer));
	} 
	
	public void reaktion() {
		double x;
		double rate = 0.5; //Change
		Random random = new Random();
		double y = random.nextDouble();
		System.out.println(y);
		x = a_sum() * Math.log(1-y)/(-rate);
		System.out.println(x);
	}
	
	
	
	
	
	public void directMethod() {
		while(true) {
			
	
			
			
			
		}
	}
	

}

