package ar.ed.itba.ui.frames.histogram;


import ar.ed.itba.file.image.ATIImage;
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
import java.util.HashMap;
import java.util.Map;

public class GrayHistogramFrame extends HistogramFrame {
	
	
	public GrayHistogramFrame(ATIImage atiImage) {
		this("GrayHistogram", atiImage);
	}
	
	public GrayHistogramFrame(String title, ATIImage atiImage) {
		super(title, atiImage);
	}
	
	public GrayHistogramFrame(ATIImage atiImage, boolean ignoreZero) {
		super("GrayHistogram", atiImage, ignoreZero);
	}
	
	protected int colorCount(){
		return 1;
	}
	
	@Override
	protected Map<Integer, String> seriesNames() {
		Map<Integer, String> map = new HashMap<>();
		map.put(0,"Values");
		return map;
	}
	
	@Override
	protected JPanel createControlPanel() {
		return null; /*THIS IS OK*/
	}
	
	@Override
	protected Paint[] paintArray(){
		return new Paint[]{
			new Color(0x80000000, true),
		};
	}
	
}
