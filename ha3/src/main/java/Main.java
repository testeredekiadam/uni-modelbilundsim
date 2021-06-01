import javax.swing.*;

public class Main {
    public static void main(String[] args){

        double final_time = 1.0; // Zeiteinheiten bis zum Zeitpunkt 1.0
        int wiederholung = 100;
        int initialStep = 0;

        long sumA2;
        double stdabA2;
        double durchschnittA2;
        StandardAbweichung standardAbweichungA2 = new StandardAbweichung(wiederholung);

        double stdabA3;
        double durchschnittA3;
        long sumA3;
        StandardAbweichung standardAbweichungA3 = new StandardAbweichung(wiederholung);


        //Experiment Aufgabe-2
        while(initialStep<wiederholung) {
            DirectMethod dm = new DirectMethod(1000, 1000);
            dm.directMethod(final_time);
            standardAbweichungA2.insertElement(initialStep, dm.getMiddle_sum());
            initialStep++;
        }

        //Experimet Aufgabe-3

        initialStep = 0;
        while(initialStep<wiederholung) {
            FirstReactionMethod fr = new FirstReactionMethod(1000, 1000);
            fr.firstReactionMethod(final_time);
            standardAbweichungA3.insertElement(initialStep, fr.getMiddle_sum());
            initialStep++;
        }


        //Ergebnisse A2
        sumA2 = standardAbweichungA2.sum();
        durchschnittA2 = standardAbweichungA2.durchschnitt();
        stdabA2 = standardAbweichungA2.stdAbw();

        System.out.println("Direct Method");
        System.out.println("Sum =  " + sumA2);
        System.out.println("Durchschnitt =  " + durchschnittA2);
        System.out.println("Standardabweichung = " + stdabA2);

        //Ergebnisse A3
        sumA3 = standardAbweichungA3.sum();
        durchschnittA3 = standardAbweichungA3.durchschnitt();
        stdabA3 = standardAbweichungA3.stdAbw();

        System.out.println("\n\nFirst Reaction Method");
        System.out.println("Sum =  " + sumA3);
        System.out.println("Durchschnitt =  " + durchschnittA3);
        System.out.println("Standardabweichung = " + stdabA3);

        /*Graph
        *
        */


        /*
       SwingUtilities.invokeLater(()->{
            Plotter directMethod = new Plotter("SSA-directMethod");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(2000, 1000);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });

*/
    }
}
