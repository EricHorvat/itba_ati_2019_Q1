package ar.ed.itba.utils;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

public class GrayHistogram {
	
	public GrayHistogram(BufferedImage image) {
		this.image = image;
	}
	
	private static final int BINS = 256;
	private final BufferedImage image;
	private HistogramDataset dataset;
	private XYBarRenderer renderer;
	
	private BufferedImage getImage() {
		return image;
	}
	
	private ChartPanel createChartPanel() {
		// dataset
		dataset = new HistogramDataset();
		Raster raster = image.getRaster();
		final int w = image.getWidth();
		final int h = image.getHeight();
		double[] r = new double[w * h];
		r = raster.getSamples(0, 0, w, h, 0, r);
		dataset.addSeries("Values", r, BINS);
		// chart
		JFreeChart chart = ChartFactory.createHistogram("GrayHistogram", "Value",
			"Count", dataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = (XYPlot) chart.getPlot();
		renderer = (XYBarRenderer) plot.getRenderer();
		renderer.setBarPainter(new StandardXYBarPainter());
		// translucent red, green & blue
		Paint[] paintArray = {
			new Color(0x000000, false),
		};
		plot.setDrawingSupplier(new DefaultDrawingSupplier(
			paintArray,
			DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		return panel;
	}
	
	private class VisibleAction extends AbstractAction {
		
		private final int i;
		
		public VisibleAction(int i) {
			this.i = i;
			this.putValue(NAME, (String) dataset.getSeriesKey(i));
			this.putValue(SELECTED_KEY, true);
			renderer.setSeriesVisible(i, true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			renderer.setSeriesVisible(i, !renderer.getSeriesVisible(i));
		}
	}
	
	public void display() {
		JFrame f = new JFrame("GrayHistogram");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(createChartPanel());
		//f.add(new JLabel(new ImageIcon(image)), BorderLayout.WEST);
		f.pack();
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		
	}
	
}
