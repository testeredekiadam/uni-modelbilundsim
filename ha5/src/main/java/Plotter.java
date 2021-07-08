
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




            JFreeChart lineChart1 = ChartFactory.createLineChart("1", "Time", "Amount", getData());
            JFreeChart lineChart2 = ChartFactory.createLineChart("2", "Time", "Amount", getData());
            JFreeChart lineChart3 = ChartFactory.createLineChart("3", "Time", "Amount", getData());
            JFreeChart lineChart4 = ChartFactory.createLineChart("4", "Time", "Amount", getData());

            ChartPanel panel = new ChartPanel(lineChart1);
            panel.add(new ChartPanel(lineChart2));
            panel.add(new ChartPanel(lineChart3));
            panel.add(new ChartPanel(lineChart4));


            setContentPane(panel);
        }

        public DefaultCategoryDataset getData(){
            String stream1 = "Wald";
            String stream2 = "Feuer";
            String timer;

            int trialW;
            int trialF;
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();



            for(int i = 0; i < 100; i++){
                timer = Integer.toString(i);
                trialW = (int)(Math.random() * 100);
                trialF = (int)(Math.random() * 100);
                dataset.addValue(trialW, stream1, timer);
                dataset.addValue(trialF, stream2, timer);
            }

            return dataset;

        }




        /*
        public DefaultCategoryDataset getData(){
            String stream1 = "Wald";
            String stream2 = "Feuer";

            int wald;
            int feuer;


            DefaultCategoryDataset dataset = new DefaultCategoryDataset();

            String filePath = "directMethod.csv";
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


         */


    }



