import org.jfree.data.category.DefaultCategoryDataset;

import java.io.FileWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Kontinuierlich {

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
    public double waldDerivation(double iwald, double ifeuer){
        return (this.k1*(iwald) - this.k2*(iwald)*ifeuer);
    }

    // dFeuer / dt
    public double feuerDerivation(double iwald, double ifeuer){
        return (this.k2*iwald*(ifeuer) - this.k3*(ifeuer));
    }


    //evaluator für Runge-Kutta
    public double eval(double anzahl, double ordnung, double step){
            return anzahl + ordnung*step;
    }


    //Forward Euler Section

    public void reaktionEuler(){
        double nwald;
        double nfeuer;

        nwald = this.wald + this.step*waldDerivation(this.wald, this.feuer);
        nfeuer = this.feuer + this.step*feuerDerivation(this.wald, this.feuer);

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
        double feval1 = this.feuer;
        double wordnung1 = waldDerivation(weval1, feval1);
        double fordnung1 = feuerDerivation(weval1, feval1);

        double weval2 = eval(weval1, wordnung1,this.step*0.5);
        double feval2 = eval(feval1, fordnung1,this.step*0.5);
        double wordnung2 = waldDerivation(weval2, feval2);
        double fordnung2 = feuerDerivation(weval2, feval2);


        double weval3 = eval(weval1, wordnung2,this.step*0.5);
        double feval3 = eval(feval1, fordnung2,this.step*0.5);
        double wordnung3 = waldDerivation(weval3, feval3);
        double fordnung3 = feuerDerivation(weval3, feval3);


        double weval4 = eval(weval1, wordnung3,this.step);
        double feval4 = eval(feval1, fordnung3,this.step);
        double wordnung4 = waldDerivation(weval4, feval4);
        double fordnung4 = feuerDerivation(weval4, feval4);


        nwald = this.wald + this.step*(wordnung1 + 2*wordnung2 + 2*wordnung3 + wordnung4)/6;
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
