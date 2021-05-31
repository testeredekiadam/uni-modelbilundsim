import javax.swing.*;

public class Main {
    public static void main(String[] args){
        double final_time = 1.0; // Zeiteinheiten bis zum Zeitpunkt 1.0
        long sum = 0;
        long wiederholung = 10;
        long initialStep = 0;
        double stepSum;
        double durchschnitt;

        //Experiment
        while(initialStep<wiederholung) {
            DirectMethod dm = new DirectMethod(1000, 1000);
            dm.directMethod(final_time);
            stepSum = dm.getMiddle_sum();
            sum += stepSum;
            initialStep++;
        }


        durchschnitt = sum/wiederholung;
        System.out.println("Sum =  " + sum);
        System.out.println("Durchschnitt =  " + durchschnitt);


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
