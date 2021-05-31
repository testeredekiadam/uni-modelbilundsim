import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileWriter;
import java.util.Random;


public class DirectMethod {

    long wald;
    long feuer;
    double next_time;
    double time;


    public DirectMethod(long wald, long feuer) {
        this.wald = wald;
        this.feuer = feuer;
    }

    //Wald -> 2Wald @ 20
    public long r_0() {

        return (this.wald*20);

    }

    //Wald + Feuer ->  2Feuer @ 0.01
    public double r_1() {

        return (this.wald*this.feuer*0.01);
    }

    //Feuer ->  @ 20
    public double r_2() {
        return (this.feuer*20);
    }

    //Summe
    public double a_sum() {
        return (r_0() + r_1() + r_2());
    }


    //Reaktion
    public void reaktion() {
        double x;


        double rate = 0.5; //Lambda

        //U(0,1) Gleichverteilung
        Random random = new Random();
        double y = random.nextDouble();


        /*      Begründung:
        *
        * Idee : Exponentialverteilung(0,1) * a_sum ergibt eine Exponentialverteilung (0, a_sum)
        *
        * Zuerst berecehnet man die Exponentialverteilung (0,1)
        * Die cdf-Formel mit der unteren Grenze l und der oberen Grenze u mit l >= 0 und u > l
        * und F(x) = y ist U(0,1)
        *
        *               (e^(-rate*l) - e^(-rate*x))
        * F(x) = y = ------------------------------------
        *               (e^(-rate*l) - e^(-rate*u))
        *
        * nach der Umwandlung
        *
        *       ln[ e^(-rate*l) - (e^(-rate*l) - e^(-rate*u))*y ]
        * x = -----------------------------------------------------
        *                       -rate
        *
        * mitder unteren Grenze 0 und der oberen Grenze 1
        *
        *        ln[ 1 - (1 - e^(-rate))*y ]
        * x = ---------------------------------
        *              -rate
        *
        */
        x = (Math.log(1-(1-Math.exp(-rate))*y))/(-rate); //Works
        //Zeitsinkrementierer
        this.next_time = x;

        //Exponentialverteilung(0,a_sum)
        x = x * a_sum();

        //Minimale j bestimmen
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
    }

    //Direct Method von SSA
    public void directMethod(long lim){

        //Adresse der CSV-datei
        String filePath = "trial.csv";
        //Die Datei entleeren
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(filePath);
            //HEADER
            fileWriter.append("Time, Wald, Feuer");
            fileWriter.append("\n");
            //Anfangszustand
            fileWriter.append(this.time + "," + this.wald+ "," + this.feuer);
            fileWriter.append("\n");
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                fileWriter.flush();

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        while(this.time < lim){
            reaktion();
            this.time += this.next_time;

            try{

                fileWriter.append(this.time + "," + this.wald+ "," + this.feuer);
                fileWriter.append("\n");
            }
            catch(Exception e){
                e.printStackTrace();
            }finally{
                try{
                    fileWriter.flush();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        }
        try{
            fileWriter.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}

