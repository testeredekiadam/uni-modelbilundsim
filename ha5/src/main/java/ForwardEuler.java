public class ForwardEuler {

    //variables
    double wald;
    double feuer;
    double step;
    double k1 = 20;
    double k2= 0.01;
    double k3 = 20;

    //constructor
    public ForwardEuler(double wald, double feuer, double step){
        this.wald = wald;
        this.feuer = feuer;
        this.step = step;
    }

    // dWald / dt
    public double waldDerivation(){
        return (k1*this.wald - k2*this.wald*this.feuer);
    }

    // dFeuer / dt
    public double feuerDerivation(){
        return (k2*this.wald*this.feuer - k3*this.feuer);
    }


    public void reaktion(){
        double nwald;
        double nfeuer;

        nwald = this.wald + step*waldDerivation();
        nfeuer = this.feuer + step*feuerDerivation();

        if(nwald < 0){
            nwald = 0;
        }
        if(nfeuer < 0){
            nfeuer = 0;
        }

        this.wald = nwald;
        this.feuer = nfeuer;

    }

    public void forwardEulerMethod(){
        double instep = this.step;
        while(instep < 1.0){
            reaktion();
            System.out.println("Wald " + this.wald + " Feuer " + this.feuer + " Step " + instep);
            instep += this.step;


        }

    }

    
}
