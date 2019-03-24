package ar.ed.itba.ui.frames.histogram;


import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.HashMap;
import java.util.Map;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.histogram.HistogramFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class RGBHistogramFrame extends HistogramFrame {
	
	/**/
	public RGBHistogramFrame(ATIImage image) {
		super("RGB Histogram",image);
	}
	
	@Override
	protected JPanel createControlPanel() {
		JPanel panel = new JPanel();
		panel.add(new JCheckBox(new VisibleAction(0)));
		panel.add(new JCheckBox(new VisibleAction(1)));
		panel.add(new JCheckBox(new VisibleAction(2)));
		return panel;
	}
	
	@Override
	protected int colorCount() {
		return 3;
	}
	
	@Override
	protected Map<Integer, String> seriesNames() {
		Map<Integer, String> map = new HashMap<>();
		map.put(0,"Red");
		map.put(1,"Green");
		map.put(2,"Blue");
		return map;
	}
	
	@Override
	protected Paint[] paintArray(){
		return new Paint[]{
			new Color(0x80ff0000, true),
			new Color(0x8000ff00, true),
			new Color(0x800000ff, true)
		};
	}
	
}
