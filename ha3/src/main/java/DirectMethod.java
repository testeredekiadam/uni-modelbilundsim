import java.io.FileWriter;
import java.util.Random;


public class DirectMethod {

    long wald;
    long feuer;
    double next_time;
    double time;
    long[] data = new long[2];


    public DirectMethod(long wald, long feuer) {
        this.wald = wald;
        this.feuer = feuer;
    }

    public long r_0() {

        return (this.wald*20);

    }

    public double r_1() {

        return (this.wald*this.feuer*0.01);
    }

    public double r_2() {
        return (this.feuer*20);
    }

    public double a_sum() {
        return (r_0() + r_1() + r_2());
    }

    public void reaktion() {
        double x;


        double rate = 3; //Changeable
        Random random = new Random();
        double y = random.nextDouble();


        /*      Explanation:
        *
        * Will come here
        *TODO
        *TODO
        *TODO
        */
        x = (Math.log(1-(1-Math.exp(-rate))*y))/(-rate); //Works
        this.next_time = x;
        x = x * a_sum();

        System.out.println("wald  " + this.wald);
        System.out.println("feuer  " + this.feuer);


        System.out.println("wald -> 2 wald  " + r_0());
        System.out.println("feuer+wald -> 2feuer  " + r_1());
        System.out.println("feuer ->  " + r_2());
        System.out.println("sum  " + a_sum());

        System.out.println("y  " + y);
        System.out.println("x  " + x);
        System.out.println("time " + this.time);
        System.out.println("next time  " + next_time);



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




    public void directMethod(long lim){
        String strTime;
        String dataWald;
        String dataFeuer;




        String filePath = "trial.csv";
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(filePath);
            fileWriter.append("Time, Wald, Feuer");
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



        while(this.time < lim) {
            reaktion();
            strTime = Double.toString(this.time);
            dataWald = Long.toString(this.wald);
            dataFeuer = Long.toString(this.feuer);
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


            this.time += this.next_time;


        }
        try{
            fileWriter.close();
        }catch(Exception e) {

        }
    }


}

