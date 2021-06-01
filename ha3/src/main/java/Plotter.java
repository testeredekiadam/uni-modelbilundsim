import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Plotter extends JFrame{
    String title;
    public Plotter(String appTitle){
        this.title = appTitle;

        DefaultCategoryDataset dataset = getData();

        JFreeChart lineChart = ChartFactory.createLineChart("Waldbrandmodel-SSA", "Time", "Amount", dataset);

        try {
            ChartUtilities.saveChartAsJPEG(new File("graph.jpg"), lineChart, 2000, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ChartPanel panel = new ChartPanel(lineChart);
        setContentPane(panel);
    }

    public DefaultCategoryDataset getData(){
        String stream1 = "Wald";
        String stream2 = "Feuer";

        int wald;
        int feuer;


        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String filePath = "trial.csv";
        BufferedReader reader;
        FileReader fileReader = null;
        String line;

        try{

            fileReader = new FileReader(filePath);
            reader = new BufferedReader(fileReader);
            reader.readLine();
            while((line = reader.readLine()) != null){
                String[] row = line.split(",");
                wald = Integer.parseInt(row[1]);
                feuer = Integer.parseInt(row[2]);
                dataset.addValue(wald, stream1, row[0]);
                dataset.addValue(feuer, stream2, row[0]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                fileReader.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }

        return dataset;

    }


}