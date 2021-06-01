import javax.swing.*;

public class Main {
    public static void main(String[] args){
        double final_time = 1.0; // Zeiteinheiten bis zum Zeitpunkt 1.0
        long sum;
        int wiederholung = 100;
        int initialStep = 0;
        double stdab;
        double durchschnitt;
        StandardAbweichung standardAbweichung = new StandardAbweichung(wiederholung);

        //Experiment
        while(initialStep<wiederholung) {
            DirectMethod dm = new DirectMethod(1000, 1000);
            dm.directMethod(final_time);
            standardAbweichung.insertElement(initialStep, dm.getMiddle_sum());
            initialStep++;
        }

        sum = standardAbweichung.sum();
        durchschnitt = standardAbweichung.durchschnitt();
        stdab = standardAbweichung.stdAbw();

        System.out.println("Sum =  " + sum);
        System.out.println("Durchschnitt =  " + durchschnitt);
        System.out.println("Standardabweichung = " + stdab);

        /*Graph
        *
        */

       SwingUtilities.invokeLater(()->{
            Plotter example = new Plotter("SSA");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(2000, 1000);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });


    }
}
