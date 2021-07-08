import org.jfree.data.category.DefaultCategoryDataset;

public class ForwardEuler {

    //variables
    long wald;
    long feuer;
    double step;
    double k1 = 20;
    double k2= 0.01;
    double k3 = 20;
    DefaultCategoryDataset dataset;

    //constructor


    // dWald / dt
    public double waldDerivation(){
        return (k1*this.wald - k2*this.wald*this.feuer);
    }

    // dFeuer / dt
    public double feuerDerivation(){
        return (k2*this.wald*this.feuer - k3*this.feuer);
    }


    public void reaktion(){
        long nwald;
        long nfeuer;

        nwald = this.wald + Math.round(step*waldDerivation());
        nfeuer = this.feuer + Math.round(step*feuerDerivation());

        if(nwald < 0){
            nwald = 0;
        }
        if(nfeuer < 0){
            nfeuer = 0;
        }

        this.wald = nwald;
        this.feuer = nfeuer;

    }

    public void forwardEulerMethod(double step){
        this.wald = 1000;
        this.feuer = 1000;
        this.step = step;
        double instep = 0;
        String timer;
        DefaultCategoryDataset newdataset = new DefaultCategoryDataset();


        while(instep < 1.0){

            timer = Double.toString(instep);
            newdataset.addValue(this.wald, "Wald", timer);
            newdataset.addValue(this.feuer, "Feuer", timer);

           // System.out.println("Wald " + this.wald + " Feuer " + this.feuer + " Step " + instep);

            reaktion();


            instep += step;

        }
        this.dataset = newdataset;
    }
/*
    public DefaultCategoryDataset getData(){
        String timer = Double.toString(this.step);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();


        dataset.addValue(this.wald, "Wald", timer);
        dataset.addValue(this.feuer, "Feuer", timer);

        return dataset;

    }

 */

}
