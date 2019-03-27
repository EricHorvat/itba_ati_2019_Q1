package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.CheckUIUtils;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContrastButtonListener implements ActionListener {
	private final JTextField r1xField;
	private final JTextField r1yField;
	private final JTextField r2xField;
	private final JTextField r2yField;

	public ContrastButtonListener(JTextField r1xField, JTextField r1yField, JTextField r2xField, JTextField r2yField) {
		this.r1xField = r1xField;
		this.r1yField = r1yField;
		this.r2xField = r2xField;
		this.r2yField = r2yField;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (CheckUIUtils.checkEditableImageVisible()) {
			final int r1x = Integer.parseInt(r1xField.getText());
			final int r1y = Integer.parseInt(r1yField.getText());
			final int r2x = Integer.parseInt(r2xField.getText());
			final int r2y = Integer.parseInt(r2yField.getText());
			EditableImageFrame editableImageFrame = EditableImageFrame.instance();
			ImageUtils.increaseContrast(editableImageFrame.getAtiImage(), r1x, r1y, r2x, r2y);
			editableImageFrame.buildAndShow();
		}
	}
}
