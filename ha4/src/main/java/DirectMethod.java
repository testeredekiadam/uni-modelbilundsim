/*
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import sun.awt.X11.XSystemTrayPeer;
*/


import java.util.Random;


public class DirectMethod {

    //variables
    long seed = 1000;
    long initwald;
    long initfeuer;

    long wald = initwald;
    long feuer = initfeuer;
    double next_time;
    double time;
    long middle_sum;//für experiment

    double waldRate = 1.0; //1.0 - 10.0
    double loeschRate; //1.0 - 10.0


    double maxFeuer;
    double maxFeuerZeit;

    Random random = new Random(seed);

    ColorPlot hMap = new ColorPlot();

    double distanzFunktion;
    //variables


    public long getMiddle_sum(){
        return this.middle_sum;
    }

    public void setMiddle_sum(long temp){
        this.middle_sum = temp;
    }

    public DirectMethod(long wald, long feuer) {
        this.initwald = wald;
        this.initfeuer = feuer;
    }

    //Wald -> 2Wald @ 1.0 - 10.0
    public double r_0() {

        return (this.wald*this.waldRate);

    }

    //Wald + Feuer ->  2Feuer @ 0.01
    public double r_1() {

        return (this.wald*this.feuer*0.01);
    }

    //Feuer ->  @ 1.0 - 10.0
    public double r_2() {
        return (this.feuer*this.loeschRate);
    }

    //Summe
    public double a_sum() {
        return (r_0() + r_1() + r_2());
    }


    //Reaktion
    public void reaktion() {
        double x;

        this.maxFeuer = this.feuer;

        double rate = a_sum(); //Lambda

        //U(0,1) Gleichverteilung

        double y = random.nextDouble();


        x = (Math.log(1-y))/(-rate); //Formel aus der Aufgabenstellung
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

        this.time += this.next_time;

    }



    //Direct Method von SSA

    public void directMethod(double lim){
        long avgsum = 0;
        double writingCount = 0.01;


/*
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

*/
        while(this.time < lim) {
            avgsum++;
            reaktion();


            //Zeitpunkt der maximalen Feuerausbreitung
            if(this.feuer > this.maxFeuer){
                this.maxFeuer = this.feuer;
                this.maxFeuerZeit = this.time;
            }



/*
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


*/
        }
 }



    //parameter scan
    public void parameterScan(){


        while(this.waldRate<=10.0){
            this.loeschRate=1.0;
            while(this.loeschRate<=10.0){
                this.maxFeuer = 500;
                this.maxFeuerZeit = 0.3;
                this.wald = this.initwald;
                this.feuer = this.initfeuer;
                this.time = 0;

                directMethod(1.0);

                //Funktion aus der Aufgabenstellung
                this.distanzFunktion = Math.sqrt(Math.pow((Math.abs(1-(this.maxFeuer/500.0))) , 2) + Math.pow((Math.abs(1-(this.maxFeuerZeit/0.3))), 2) );

                hMap.createDataset(this.waldRate, this.loeschRate, this.distanzFunktion);
               // System.out.println("Waldrate " + this.waldRate + " Loeschrate "+ this.loeschRate + " Distanzfunktion " + this.distanzFunktion);
                this.loeschRate += 1;

            }
            this.waldRate += 1;
        }

        hMap.pack();
        hMap.setVisible(true);


    }

}

