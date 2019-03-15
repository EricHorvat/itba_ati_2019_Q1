package ar.ed.itba.ui.frames.interfaces;

import javax.swing.*;

public class FixedImageInterface extends ImageInterface{
	private JPanel mainPanel;
	private JLabel imageLabel;
	private JLabel extraDataPanel;
	
	@Override
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
