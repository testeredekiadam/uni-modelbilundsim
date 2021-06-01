import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Random;

public class FirstReactionMethod {
    long wald;
    long feuer;
    double time_r0;
    double time_r1;
    double time_r2;
    double next_time;
    double time;
    long middle_sum;

    public long getMiddle_sum(){
        return this.middle_sum;
    }

    public void setMiddle_sum(long temp){
        this.middle_sum = temp;
    }


    public FirstReactionMethod(long wald, long feuer){
        this.wald = wald;
        this.feuer = feuer;
    }

    //Wald -> 2Wald @ 20
    public double r_0(){
        return (this.wald * 20);
    }

    //Wald + Feuer ->  2Feuer @ 0.01
    public double r_1() {

        return (this.wald*this.feuer*0.01);
    }

    //Feuer ->  @ 20
    public double r_2() {

        return (this.feuer*20);
    }


    public double expVer(double rate){
        double x;

        //U(0,1) Gleichverteilung
        Random random = new Random();
        double y = random.nextDouble();

        x = (Math.log(1-(1-Math.exp(-rate))*y))/(-rate); //Works
        //Zeitsinkrementierer
        return x;
    }

    public void reaktion(){
        Double[] array;
        this.time_r0 =  expVer(r_0());
        this.time_r1 = expVer(r_1());
        this.time_r2 = expVer(r_2());
        array = new Double[]{this.time_r0, this.time_r1, this.time_r2};
        double min = array[0];
        for(int i = 1; i < 3 ; i++){
            if(array[i]<min){
                min = array[i];
            }
        }

        if(min == array[0]){
            this.wald+=1;
        }else if(min == array[1]){
            this.wald-=1;
            this.feuer+=1;
        }else if(min == array[2]){
            this.feuer-=1;
        }


        this.next_time = min;
    }

    public void firstReactionMethod(double lim){
        long avgsum = 0;
        double writingCount = 0.01;

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.##", otherSymbols);

        //Adresse der CSV-datei
        String filePath = "firstReactionMethod.csv";

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
