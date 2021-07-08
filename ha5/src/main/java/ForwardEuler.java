import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ForwardEuler {

    //variablen
    long wald;
    long feuer;
    double step;
    double k1 = 20;
    double k2= 0.01;
    double k3 = 20;
    DefaultCategoryDataset dataset;




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

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.####", otherSymbols);

        //Adresse der CSV-datei
        String filePath = "forwardEuler-"+step+".csv";

        //Die Datei entleeren
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(filePath);
            //HEADER
            fileWriter.append("Time, Wald, Feuer");
            fileWriter.append("\n");
            //Anfangszustand
            fileWriter.append(instep + "," + this.wald+ "," + this.feuer);
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



        while(instep < 1.0){

            timer = Double.toString(instep);
            newdataset.addValue(this.wald, "Wald", timer);
            newdataset.addValue(this.feuer, "Feuer", timer);

           // System.out.println("Wald " + this.wald + " Feuer " + this.feuer + " Step " + instep);

            reaktion();//reac

            instep += step;


            try{

                fileWriter.append(df.format(instep) + "," + this.wald+ "," + this.feuer);
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
