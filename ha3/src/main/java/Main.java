import javax.swing.*;

public class Main {
    public static void main(String[] args){
        long final_time = 28000; // Schritte innerhalb einer Sekunde
        DirectMethod dm = new DirectMethod(1000, 1000);
        dm.directMethod(final_time);

        //Dauert ein bisschen, ungefähr 1 Min für 28k schritte. Wir bedanken uns im Voraus für ihr Verständnis und ihre Geduld
        SwingUtilities.invokeLater(()->{
            Plotter example = new Plotter("Line Chart Example");
            example.setAlwaysOnTop(true);
            example.pack();
            example.setSize(600, 400);
            example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            example.setVisible(true);
        });


    }
}
