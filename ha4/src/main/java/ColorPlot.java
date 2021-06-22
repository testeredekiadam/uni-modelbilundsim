
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYZDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;

import java.awt.*;


public class ColorPlot extends ApplicationFrame {


    double[] k1 = new double[1000 * 100];
    double[] k3 = new double[1000 * 100];
    double[] distanz = new double[1000 * 100];

    public ColorPlot() {
        super("Parameter Scan HeatMap");
        final JFreeChart heatmap = createHMap(createDataset());
        setContentPane(new ChartPanel(heatmap));
    }

    private static JFreeChart createHMap(XYZDataset dataset) {

        double j = 1.0;
        int i = 0;
        String labels[] = new String[10];
        while (i < 10) {
            labels[i] = Double.toString(j);
            i++;
            j++;
        }


        //x-achse für k1
        SymbolAxis k1_axis = new SymbolAxis("k1", labels);
        k1_axis.setLowerMargin(0);
        k1_axis.setUpperMargin(0);
        k1_axis.setTickUnit(new NumberTickUnit(2.0));

        //y-achse für k3
        SymbolAxis k3_axis = new SymbolAxis("k3", labels);
        k3_axis.setLowerMargin(0);
        k3_axis.setUpperMargin(0);
        k3_axis.setTickUnit(new NumberTickUnit(2.0));


        //farben und legende
        LookupPaintScale paintScale = new LookupPaintScale(0, 300, Color.black);
        Color c = Color.blue;
        paintScale.add(0.0, c = c.darker());
        paintScale.add(1.0, c = c.brighter());
        paintScale.add(1.3, c = c.brighter());
        paintScale.add(1.6, c = Color.green);
        paintScale.add(2.0, c = c.brighter());
        paintScale.add(2.3, c = Color.yellow);
        paintScale.add(2.6, c = c.darker());
        paintScale.add(3.0, c = Color.red);
        paintScale.add(3.3, c = c.darker());

        PaintScaleLegend legend = new PaintScaleLegend(paintScale, new NumberAxis());
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setAxisLocation(AxisLocation.TOP_OR_RIGHT);
        legend.setMargin(50.0, 20.0, 80.0, 0.0);


        // Plotter
        XYPlot plot = new XYPlot(dataset, k1_axis, k3_axis, new XYBlockRenderer());
        ((XYBlockRenderer) plot.getRenderer()).setPaintScale(paintScale);


        JFreeChart hMap = new JFreeChart(null, null, plot, false);
        hMap.addSubtitle(legend);
        return hMap;
    }

    public XYZDataset createDataset(double k1, double k3, double distanz) {


        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries(new double[][]{k1, k3, distanz});
        return dataset;

    }
}
