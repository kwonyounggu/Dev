package com.sickkids.caliper.chart;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.function.Function2D;
import org.jfree.data.function.NormalDistributionFunction2D;
import org.jfree.data.general.DatasetUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.util.ShapeUtilities;

import com.sickkids.caliper.common.Utils;

public class Plot
{
	//return a scatter plot
	public static JFreeChart createChart(XYSeriesCollection xydataset, String analyte, int sex, String ethnic)
    {	
		analyte=(String)Utils.analyteMap.get(analyte);
        JFreeChart jfreechart = ChartFactory.createScatterPlot("Scatter Plot for "+analyte+", Ethnicity: "+ethnic, "Age (Years)", analyte+" Concentration", xydataset, PlotOrientation.VERTICAL, true, false, false);

        jfreechart.setBackgroundPaint(Color.white);
        XYPlot xyplot = (XYPlot)jfreechart.getPlot();
        xyplot.setNoDataMessage("NO DATA");
        xyplot.setDomainZeroBaselineVisible(true);
        xyplot.setRangeZeroBaselineVisible(true);
        //xyplot.setBackgroundPaint(Color.white);
        
        XYLineAndShapeRenderer xylineandshaperenderer = (XYLineAndShapeRenderer)xyplot.getRenderer();
        
        if(sex==3)
        {
        	xylineandshaperenderer.setSeriesPaint(0, Color.blue);
        	xylineandshaperenderer.setSeriesPaint(1, Color.red);
        	
        	xylineandshaperenderer.setSeriesShape(0, ShapeUtilities.createDiagonalCross(3.0f, 0.4f));
            xylineandshaperenderer.setSeriesShape(1, ShapeUtilities.createDiagonalCross(3.0f, 0.4f));
            //xylineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-3.0,-3.0,6.0,6.0));
            //xylineandshaperenderer.setSeriesShape(1, new Ellipse2D.Double(-3.0,-3.0,6.0,6.0));
            
            System.out.println("INFO: count from male="+xydataset.getItemCount(0));
            System.out.println("INFO: count from female="+xydataset.getItemCount(1));
            
            xydataset.getSeries(0).setKey("Male (n: "+xydataset.getItemCount(0)+")");
            xydataset.getSeries(1).setKey("Female (n: "+xydataset.getItemCount(1)+")");
        }
        else 
        {
        	if(sex==1) 
        	{
        		xylineandshaperenderer.setSeriesPaint(0, Color.blue);
        		xydataset.getSeries(0).setKey("Male (n: "+xydataset.getItemCount(0)+")");
        	}
        	else 
        	{
        		xylineandshaperenderer.setSeriesPaint(0, Color.red);
        		xydataset.getSeries(0).setKey("Female (n: "+xydataset.getItemCount(0)+")");
        	}
        	xylineandshaperenderer.setSeriesShape(0, ShapeUtilities.createDiagonalCross(3.0f, 0.4f));
        	//xylineandshaperenderer.setSeriesShape(0, new Ellipse2D.Double(-3.0,-3.0,6.0,6.0));
        	
        	System.out.println("INFO: count from male/female="+xydataset.getItemCount(0));
        }
       
      
        NumberAxis numberaxis = (NumberAxis)xyplot.getDomainAxis();
        numberaxis.setAutoRangeIncludesZero(false);
        numberaxis.setTickMarkInsideLength(2.0F);
        numberaxis.setTickMarkOutsideLength(0.0F);
        
        NumberAxis numberaxis1 = (NumberAxis)xyplot.getRangeAxis();
        numberaxis1.setTickMarkInsideLength(2.0F);
        numberaxis1.setTickMarkOutsideLength(0.0F);
        return jfreechart;
    }
	
	public static JFreeChart createNormalDisributionGraph(String analyte, String sex, String ethnic, String mean, String std)
	{
		double dStd=Double.parseDouble(std);
		double dMean=Double.parseDouble(mean);
		//System.out.println("mean="+dMean+", std="+dStd);
		Function2D normal = new NormalDistributionFunction2D(dMean, dStd); 
		 XYDataset dataset = DatasetUtilities.sampleFunction2D(normal, (dMean-dStd*3), (dMean+dStd*3), 100, "Gaussian Graph");
		 JFreeChart chart = ChartFactory.createXYLineChart(
		            "Gaussian Distribution for "+analyte,
		            analyte+" Concentration", 
		            "Probability", 
		            dataset,
		            PlotOrientation.VERTICAL,
		            true,
		            true,
		            false
		        );
		 chart.setBackgroundPaint(Color.white);
		 chart.removeLegend();
		 return chart;
	}
}
