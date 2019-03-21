package ar.ed.itba.ui.frames.interfaces;

import javax.swing.*;

public class FixedImageInterface extends ImageInterface{
	/**/ JPanel mainPanel;
	/**/ JLabel imageLabel;
	/**/ JLabel extraDataPanel;
	
	@Override
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}
}
