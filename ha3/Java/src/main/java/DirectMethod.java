import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;


public class DirectMethod {

    long wald;
    long feuer;
    double next_time;
    double time;
    long middle_sum;//für experiment

    public long getMiddle_sum(){
        return this.middle_sum;
    }

    public void setMiddle_sum(long temp){
        this.middle_sum = temp;
    }

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


        double rate = a_sum(); //Lambda

        //U(0,1) Gleichverteilung
        Random random = new Random();
        double y = random.nextDouble();

        /*      Begründung:
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
        
        //Gleichverteilung (0,a_sum)
        double xj;
        xj = random.nextDouble() * a_sum();

        //Minimale j bestimmen
        if(r_0() > xj){
            this.wald+=1;

        }
        else if((r_0()+r_1()) > xj){
            this.wald-=1;
            this.feuer+=1;
        }
        else if((r_0()+r_1()+r_2()) > xj){
            this.feuer-=1;
        }
    }

    //Direct Method von SSA
    public void directMethod(double lim){
        long avgsum = 0;
        double writingCount = 0.01;

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

        //Adresse der CSV-datei
        String filePath = "directMethod.csv";
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
            avgsum++;
            reaktion();
            this.time += this.next_time;

            if(this.time>writingCount){


                try{

                    fileWriter.append(df.format(this.time) + "," + this.wald+ "," + this.feuer);
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


                writingCount += 0.01;
            }


        }
        try{
            fileWriter.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        setMiddle_sum(avgsum);
    }



}

