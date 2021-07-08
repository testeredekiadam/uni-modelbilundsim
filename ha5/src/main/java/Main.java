import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        /*
        ForwardEuler firat = new ForwardEuler(1000, 1000, 0.01);
        firat.forwardEulerMethod();
         */


        Plotter trial = new Plotter("Kontinuerlich");
        trial.setAlwaysOnTop(true);
        trial.pack();
        trial.setSize(500, 300);
        trial.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        trial.setVisible(true);




    }
}
