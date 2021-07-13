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
    double[] arr = new double[2];
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

    public double[] reaktionEuler(double argwald, double argfeuer, double step){
        double nwald;
        double nfeuer;

        nwald = argwald + step*waldDerivation(argwald, argfeuer);
        nfeuer = argfeuer + step*feuerDerivation(argwald, argfeuer);

        if(nwald < 0){
            nwald = 0;
        }
        if(nfeuer < 0){
            nfeuer = 0;
        }

        this.arr[0] = nwald;
        this.arr[1] = nfeuer;
        return this.arr;

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


            reaktionEuler(this.wald, this.feuer, this.step);//reaktion Forward Euler
            this.wald = this.arr[0];
            this.feuer = this.arr[1];
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



    public double[] reaktionRungeKutta(double argwald, double argfeuer, double step){
        double nwald;
        double nfeuer;


        double weval1 = argwald;
        double feval1 = argfeuer;
        double wordnung1 = waldDerivation(weval1, feval1);
        double fordnung1 = feuerDerivation(weval1, feval1);

        double weval2 = eval(weval1, wordnung1,step*0.5);
        double feval2 = eval(feval1, fordnung1,step*0.5);
        double wordnung2 = waldDerivation(weval2, feval2);
        double fordnung2 = feuerDerivation(weval2, feval2);


        double weval3 = eval(weval1, wordnung2,step*0.5);
        double feval3 = eval(feval1, fordnung2,step*0.5);
        double wordnung3 = waldDerivation(weval3, feval3);
        double fordnung3 = feuerDerivation(weval3, feval3);


        double weval4 = eval(weval1, wordnung3, step);
        double feval4 = eval(feval1, fordnung3, step);
        double wordnung4 = waldDerivation(weval4, feval4);
        double fordnung4 = feuerDerivation(weval4, feval4);


        nwald = argwald + step*(wordnung1 + 2*wordnung2 + 2*wordnung3 + wordnung4)/6;
        nfeuer = argfeuer +  step*(fordnung1 + 2*fordnung2 + 2*fordnung3 + fordnung4)/6;

        if(nwald < 0){
            nwald = 0;
        }
        if(nfeuer < 0){
            nfeuer = 0;
        }

        this.arr[0] = nwald;
        this.arr[1] = nfeuer;

        return this.arr;
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
            reaktionRungeKutta(this.wald, this.feuer, this.step);//reaktion runge kutta method
            this.wald = this.arr[0];
            this.feuer = this.arr[1];
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





    //Adaptive schrittweite methoden

    public void adaptiveStepSizeEuler(){
        this.wald = 1000;
        this.feuer = 1000;
        double normalwald;
        double normalfeuer;
        double halfwald;
        double halffeuer;
        double doublewald;
        double doublefeuer;
        double genauigkeit = 5000;
        double minimalstepsize = 0.0001;


        double initstepsize = 0.025;
        double maxcontrol = 0.01;
        double mincontrol = 0.0001;
        double instep = 0;

        String timer;
        DefaultCategoryDataset newdataset = new DefaultCategoryDataset();

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.####", otherSymbols);

        //Adresse der CSV-datei
        String filePath = "AdaptiveEuler.csv";

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

            reaktionEuler(this.wald, this.feuer, initstepsize);
            normalwald = this.arr[0];
            normalfeuer = this.arr[1];

            reaktionEuler(this.wald, this.feuer, initstepsize/2);
            halfwald = this.arr[0];
            halffeuer = this.arr[1];

            reaktionEuler(this.wald, this.feuer, initstepsize*2);
            doublewald = this.arr[0];
            doublefeuer = this.arr[1];

            if(normalwald < genauigkeit && normalfeuer < genauigkeit){
                if(initstepsize != minimalstepsize){
                    initstepsize = minimalstepsize;
                }
                this.wald = normalwald;
                this.feuer = normalfeuer;
            }
            else{
                if((normalwald > genauigkeit && normalfeuer > genauigkeit) && (Math.abs(normalwald-halfwald)/normalwald > maxcontrol || Math.abs(normalfeuer-halffeuer)/normalfeuer > maxcontrol)){
                    initstepsize = initstepsize/2; //Error too large, decrease stepsize
                    this.wald = halfwald;
                    this.feuer = halffeuer;
                }
                else if((normalwald > genauigkeit && normalfeuer > genauigkeit) && (Math.abs(normalwald-doublewald)/normalwald < mincontrol || Math.abs(normalfeuer-doublefeuer)/normalfeuer < mincontrol)){
                    initstepsize = initstepsize * 2; //Error acceptable, increase stepsize
                    this.wald = doublewald;
                    this.feuer = doublefeuer;
                }
                else{
                    this.wald = normalwald;
                    this.feuer = normalfeuer;
                }
            }

            instep += initstepsize;



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






    public void adaptiveStepSizeRunge(){
        this.wald = 1000;
        this.feuer = 1000;
        double normalwald;
        double normalfeuer;
        double halfwald;
        double halffeuer;
        double doublewald;
        double doublefeuer;
        double genauigkeit = 5000;
        double minimalstepsize = 0.0001;


        double initstepsize = 0.025;
        double maxcontrol = 0.01;
        double mincontrol = 0.0001;
        double instep = 0;

        String timer;
        DefaultCategoryDataset newdataset = new DefaultCategoryDataset();

        Locale currentLocale = Locale.getDefault();
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(currentLocale);
        otherSymbols.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("#.####", otherSymbols);

        //Adresse der CSV-datei
        String filePath = "AdaptiveRunge.csv";

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

            reaktionRungeKutta(this.wald, this.feuer, initstepsize);
            normalwald = this.arr[0];
            normalfeuer = this.arr[1];

            reaktionRungeKutta(this.wald, this.feuer, initstepsize/2);
            halfwald = this.arr[0];
            halffeuer = this.arr[1];

            reaktionRungeKutta(this.wald, this.feuer, initstepsize*2);
            doublewald = this.arr[0];
            doublefeuer = this.arr[1];

            if(normalwald < genauigkeit && normalfeuer < genauigkeit){
                if(initstepsize != minimalstepsize){
                    initstepsize = minimalstepsize;
                }
                this.wald = normalwald;
                this.feuer = normalfeuer;
            }
            else{
                if((normalwald > genauigkeit && normalfeuer > genauigkeit) && (Math.abs(normalwald-halfwald)/normalwald > maxcontrol || Math.abs(normalfeuer-halffeuer)/normalfeuer > maxcontrol)){
                    initstepsize = initstepsize/2; //Error too large, decrease stepsize
                    this.wald = halfwald;
                    this.feuer = halffeuer;
                }
                else if((normalwald > genauigkeit && normalfeuer > genauigkeit) && (Math.abs(normalwald-doublewald)/normalwald < mincontrol || Math.abs(normalfeuer-doublefeuer)/normalfeuer < mincontrol)){
                    initstepsize = initstepsize * 2; //Error acceptable, increase stepsize
                    this.wald = doublewald;
                    this.feuer = doublefeuer;
                }
                else{
                    this.wald = normalwald;
                    this.feuer = normalfeuer;
                }
            }

            instep += initstepsize;



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




