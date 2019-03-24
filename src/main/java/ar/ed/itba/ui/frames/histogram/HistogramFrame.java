package ar.ed.itba.ui.frames.histogram;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.components.GrayHistogramPanel;
import ar.ed.itba.ui.frames.ATIFrame;
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

public abstract class HistogramFrame extends ATIFrame {
	
	public HistogramFrame(String title, ATIImage atiImage) throws HeadlessException {
		super(title);
		image = atiImage.view();
	}
	
	@Override
	protected JPanel getMainPanel() {
		return mainPanel;
	}
	
	protected JPanel mainPanel;
	
	private static final int BINS = 256;
	protected final BufferedImage image;
	private HistogramDataset dataset;
	private XYBarRenderer renderer;
	private final Map<Integer,String> seriesNames = seriesNames();
	private final int colorCount = colorCount();
	
	protected abstract int colorCount();
	protected abstract Map<Integer,String> seriesNames();
	protected abstract Paint[] paintArray();
	
	protected class VisibleAction extends AbstractAction {
		
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
	
	protected abstract JPanel createControlPanel();
	
	private ChartPanel createChartPanel(){
		dataset = new HistogramDataset();
		Raster raster = image.getRaster();
		final int w = image.getWidth();
		final int h = image.getHeight();
		double[] r = new double[w * h];
		for (int i = 0; i < colorCount; i++) {
			r = raster.getSamples(0, 0, w, h, i, r);
			dataset.addSeries(seriesNames.get(i), r, BINS);
		}
		// chart
		JFreeChart chart = ChartFactory.createHistogram(getTitle(), "Value",
			"Count", dataset, PlotOrientation.VERTICAL, true, true, false);
		XYPlot plot = (XYPlot) chart.getPlot();
		renderer = (XYBarRenderer) plot.getRenderer();
		renderer.setBarPainter(new StandardXYBarPainter());
		
		plot.setDrawingSupplier(new DefaultDrawingSupplier(
			paintArray(),
			DefaultDrawingSupplier.DEFAULT_FILL_PAINT_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE,
			DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE));
		ChartPanel panel = new ChartPanel(chart);
		panel.setMouseWheelEnabled(true);
		return panel;
		
	}
	
	/*ATIFrame methods*/
	
	@Override
	public void build(){
		mainPanel = new JPanel();
		mainPanel.add(createChartPanel());
		JPanel controlPanel = createControlPanel();
		if (controlPanel != null){
			mainPanel.add(controlPanel, BorderLayout.SOUTH);
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		add(mainPanel);
	}
	
	
	
}
