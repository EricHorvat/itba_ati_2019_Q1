package ar.ed.itba.ui.frames;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.components.GrayHistogramPanel;

import javax.swing.*;
import java.awt.*;

public class HistogramFrame extends ATIFrame {
	
	GrayHistogramPanel mainPanel;
	
	public HistogramFrame(String title, ATIImage atiImage) throws HeadlessException {
		super(title);
		mainPanel = new GrayHistogramPanel(atiImage);
		mainPanel.setVisible(true);
	}
	
	@Override
	JPanel getMainPanel() {
		return mainPanel;
	}
}
