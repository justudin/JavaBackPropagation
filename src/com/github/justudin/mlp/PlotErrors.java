package com.github.justudin.mlp;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class PlotErrors extends ApplicationFrame {
	private static final long serialVersionUID = 1L;

	public PlotErrors(String applicationTitle, String chartTitle, ArrayList<Object> dt) throws IOException {
		
		super(applicationTitle);
	      JFreeChart xylineChart = ChartFactory.createXYLineChart(
	         chartTitle ,
	         "# of Errors" ,
	         "Sum Square Errors" ,
	         createDataset(dt) ,
	         PlotOrientation.VERTICAL ,
	         true , true , false);
	         
	      ChartPanel chartPanel = new ChartPanel( xylineChart );
	      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
	      final XYPlot plot = xylineChart.getXYPlot( );
	      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
	      renderer.setSeriesPaint( 0 , Color.RED );
	      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
	      plot.setRenderer( renderer ); 
	      setContentPane( chartPanel ); 
	}

	private XYSeriesCollection createDataset(ArrayList<Object> dt) throws IOException {
		final XYSeries errorsplot = new XYSeries( "Errors Sum Square" );      
		
		for (int i = 0; i < dt.size(); i++) {
			double db = Double.valueOf((String) dt.get(i));
			errorsplot.add(i,db);
		}
		
		final XYSeriesCollection dataset = new XYSeriesCollection( );          
	    dataset.addSeries( errorsplot );  
		return dataset;
	}
}
