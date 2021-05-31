import javax.swing.JFrame;
import java.io.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class Plotter extends JFrame{
    String title;
    public Plotter(String appTitle){
        this.title = appTitle;

        DefaultCategoryDataset dataset = getData();

        JFreeChart lineChart = ChartFactory.createLineChart("Waldbrandmodel-SSA", "Time", "Amount", dataset);

        ChartPanel panel = new ChartPanel(lineChart);
        setContentPane(panel);
    }

    public DefaultCategoryDataset getData(){
        String series1 = "Wald";
        String series2 = "Feuer";

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
                dataset.addValue(wald, series1, row[0]);
                dataset.addValue(feuer, series2, row[0]);

             /*   for(String index : row){
                    System.out.printf("%-30s", index);
                }
                System.out.println();
*/
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