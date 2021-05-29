import java.util.Random;
import java.lang.Math;

public class DirectMethod {

    int wald;
    int feuer;

    public DirectMethod(int wald, int feuer) {
        this.wald = wald;
        this.feuer = feuer;
    }

    public long r_0() {
        return (this.wald*20);
    }

    public double r_1() {
        return (this.wald*this.feuer*0.01);
    }

    public long r_2() {
        return (this.feuer*20);
    }

    public double a_sum() {
        return (r_0() + r_1() + r_2());
    }

    public void reaktion() {
        double x;
        double rate = 0.5; //Change
        Random random = new Random();
        double y = random.nextDouble();


        /*      Explanation:
        *
        * Will come here
        *
        *
        *
        */
        x = (Math.log(1-(1-Math.exp(-rate))*y))/(-rate); //Works
        x = a_sum()*x;

        System.out.println("wald  " + this.wald);
        System.out.println("feuer  " + this.feuer);
/*

        System.out.println("wald -> 2 wald  " + r_0());
        System.out.println("feuer+wald -> 2feuer  " + r_1());
        System.out.println("feuer ->  " + r_2());
        System.out.println("sum  " + a_sum());

        System.out.println("y  " + y);
        System.out.println("x  " + x);
*/


        if(r_0() > x){
            this.wald+=1;

        }
        else if((r_0()+r_1()) > x){
            this.wald-=1;
            this.feuer+=1;
        }
        else if((r_0()+r_1()+r_2()) > x){
            this.feuer-=1;
        }

        System.out.println();


    }

    public void directMethod(int lim) {
        int count = 0;


        while(count < lim) {
            reaktion();
            count++;

        }
    }
}

