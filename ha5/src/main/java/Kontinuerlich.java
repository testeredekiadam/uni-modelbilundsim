import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Kontinuerlich {

    //variablen
    double wald;
    double feuer;
    double step;
    double k1 = 20;
    double k2= 0.01;
    double k3 = 20;


    DefaultCategoryDataset dataset;



    //Ableitung
    //Ordnung Variablen sind für Runge Kutta
    //Forward Euler Ordnung = 0

    // dWald / dt
    public double waldDerivation(){
        return (this.k1*(this.wald) - this.k2*(this.wald)*this.feuer);
    }

    // dFeuer / dt
    public double feuerDerivation(){
        return (this.k2*this.wald*(this.feuer) - this.k3*(this.feuer));
    }


    //evaluation der Anzahl für Runge-Kutta
    public double eval(double anzahl, double ordnung, double step){
            return anzahl + ordnung*step;
    }


    //Forward Euler Section

    public void reaktionEuler(){
        double nwald;
        double nfeuer;

        nwald = this.wald + this.step*waldDerivation();
        nfeuer = this.feuer + this.step*feuerDerivation();

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



        while(instep <= 1.0){

            timer = Double.toString(instep);
            newdataset.addValue(this.wald, "Wald", timer);
            newdataset.addValue(this.feuer, "Feuer", timer);



            reaktionEuler();//reaktion Forward Euler

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



    public void reaktionRungeKutta(){
        double nwald;
        double nfeuer;


        double weval1 = this.wald;
        double feval1=this.feuer;
        double wordnung1;
        double fordnung1;

        double weval2;
        double feval2;
        double wordnung2;
        double fordnung2;


        double weval3;
        double feval3;
        double wordnung3;
        double fordnung3;


        double weval4;
        double feval4;
        double wordnung4;
        double fordnung4;



        wordnung1 = waldDerivation();
        wordnung2 = waldDerivation();
        wordnung3 = waldDerivation();
        wordnung4 = waldDerivation();

        nwald = this.wald + this.step*(wordnung1 + 2*wordnung2 + 2*wordnung3 + wordnung4)/6;

        fordnung1 = feuerDerivation();
        fordnung2 = feuerDerivation();
        fordnung3 = feuerDerivation();
        fordnung4 = feuerDerivation();

        nfeuer = this.feuer +  this.step*(fordnung1 + 2*fordnung2 + 2*fordnung3 + fordnung4)/6;

        if(nwald < 0){
            nwald = 0;
        }
        if(nfeuer < 0){
            nfeuer = 0;
        }

        this.wald = nwald;
        this.feuer = nfeuer;
    }




    // Runge Kutta Section
    public void rungeKuttaMethod(double step){
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
        String filePath = "rungeKutta-"+step+".csv";

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



        while(instep <= 1.0){

            timer = Double.toString(instep);
            newdataset.addValue(this.wald, "Wald", timer);
            newdataset.addValue(this.feuer, "Feuer", timer);



            reaktionRungeKutta();//reaktion runge kutta method

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




}
