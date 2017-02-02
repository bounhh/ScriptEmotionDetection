package org.emotiondetection.campus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

public class DrawingBarChart extends JFrame {
    public DrawingBarChart( String applicationTitle, String chartTitle ) throws IOException {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Time",
                "Score",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false );

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560, 367 ) );
        setContentPane( chartPanel );
    }

    private CategoryDataset createDataset() throws IOException {
        final String[] tabSentiments = { "Happiness", "Sadness", "Anger", "Disgust", "Surprise", "Fear" };
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try ( BufferedReader br = new BufferedReader( new FileReader( "resultFile.txt" ) ) ) {
            int j = 0;
            for ( String line; ( line = br.readLine() ) != null; ) {
                line = line.trim();
                if ( line != "" ) {
                    String[] tabWords = line.split( " " );
                    for ( int i = 0; i < tabWords.length / 10; i++ ) {
                        dataset.addValue( Double.parseDouble( tabWords[i] ), tabSentiments[j], "page-" + i );
                    }
                    j++;
                }
            }
        }
        return dataset;
    }

    public static void main( String[] args ) throws IOException {
        DrawingBarChart chart = new DrawingBarChart( "Emotion Dection BarChart", "" );
        chart.pack();
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
    }
}