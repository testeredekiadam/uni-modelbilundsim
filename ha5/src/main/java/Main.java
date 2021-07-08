import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

    public static void main( String[] args ) {

        ForwardEuler firat = new ForwardEuler();



        JFrame f3 = new JFrame( "Kontinuerlich" );
        f3.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f3.setLayout( new FlowLayout() );
        ChartPanel cp;



        firat.forwardEulerMethod(0.025);
        cp = new ChartPanel(ChartFactory.createLineChart("Schrittweite 0.025", "Time", "Amount", firat.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        firat.forwardEulerMethod(0.01);
        cp = new ChartPanel(ChartFactory.createLineChart("Schrittweite 0.01", "Time", "Amount", firat.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        firat.forwardEulerMethod(0.001);
        cp = new ChartPanel(ChartFactory.createLineChart("Schrittweite 0.001", "Time", "Amount", firat.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        firat.forwardEulerMethod(0.0001);
        cp = new ChartPanel(ChartFactory.createLineChart("Schrittweite 0.0001", "Time", "Amount", firat.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);







        f3.setSize( 1920, 1080 );
        f3.setLocationRelativeTo( null );




        f3.setVisible( true );



    }

}