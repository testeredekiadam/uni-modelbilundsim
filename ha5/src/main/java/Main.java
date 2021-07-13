import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Main {

    public static void main( String[] args ) {

        Kontinuierlich model = new Kontinuierlich();



        JFrame f3 = new JFrame( "Kontinuierlich" );
        f3.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        f3.setLayout( new FlowLayout() );
        ChartPanel cp;



        //Anfang Eulers

        model.forwardEulerMethod(0.025);
        cp = new ChartPanel(ChartFactory.createLineChart("Forward Euler 0.025", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);



        model.forwardEulerMethod(0.01);
        cp = new ChartPanel(ChartFactory.createLineChart("Forward Euler 0.01", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        model.forwardEulerMethod(0.001);
        cp = new ChartPanel(ChartFactory.createLineChart("Forward Euler 0.001", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        model.forwardEulerMethod(0.0001);
        cp = new ChartPanel(ChartFactory.createLineChart("Forward Euler 0.0001", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);








        //Ende Eulers


        //Anfang Runges

        model.rungeKuttaMethod(0.025);
        cp = new ChartPanel(ChartFactory.createLineChart("Runge Kutta 0.025", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);


        model.rungeKuttaMethod(0.01);
        cp = new ChartPanel(ChartFactory.createLineChart("Runge Kutta 0.01", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        model.rungeKuttaMethod(0.001);
        cp = new ChartPanel(ChartFactory.createLineChart("Runge Kutta 0.001", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);


        model.rungeKuttaMethod(0.0001);
        cp = new ChartPanel(ChartFactory.createLineChart("Runge Kutta 0.0001", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        //Ende Runges

        //Anfang Adaptives
        model.adaptiveStepSizeRunge();
        cp = new ChartPanel(ChartFactory.createLineChart("Adaptive runge-kutta 0.025", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        model.adaptiveStepSizeEuler();
        cp = new ChartPanel(ChartFactory.createLineChart("Adaptive Euler 0.025", "Time", "Amount", model.dataset));
        cp.setPreferredSize(new Dimension(300, 300));
        f3.add( cp);

        //Ende Adaptives



        f3.setSize( 1920, 1080 );
        f3.setLocationRelativeTo( null );




        f3.setVisible( true );



    }

}