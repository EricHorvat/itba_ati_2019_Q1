package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.ImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FixedImageInterface extends ImageInterface{
	/**/ JPanel mainPanel;
	/**/ JLabel imageLabel;
	/**/ JLabel extraDataPanel;
	/**/ JButton useButton;
	
	/*private*/ ImageFrame frame;
	public FixedImageInterface() {
		useButton.setVisible(false);
		useButton.addActionListener(new SetInEditableImage());
	}
	
	@Override
	public JLabel getImageLabel() {
		return imageLabel;
	}
	
	@Override
	public JPanel getMainPanel() {
		return mainPanel;
	}
	
	public JButton getUseButton() {
		return useButton;
	}
	
	public void setFrame(ImageFrame frame) {
		this.frame = frame;
	}
	
	private class SetInEditableImage implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			EditableImageFrame.instance().setAtiImage(frame.getAtiImage());
			EditableImageFrame.instance().makeShow();
		}
	}
}
