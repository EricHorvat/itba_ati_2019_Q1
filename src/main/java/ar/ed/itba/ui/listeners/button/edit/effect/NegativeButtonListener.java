package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NegativeButtonListener implements ActionListener {
	public NegativeButtonListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		editableImageFrame.getAtiImage().negative();
		editableImageFrame.buildAndShow();
	}
}
