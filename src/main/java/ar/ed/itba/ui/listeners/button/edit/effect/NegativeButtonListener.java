package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.CheckUIUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NegativeButtonListener implements ActionListener {
	public NegativeButtonListener() {
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (CheckUIUtils.checkEditableImageVisible()) {
			EditableImageFrame editableImageFrame = EditableImageFrame.instance();
			editableImageFrame.getAtiImage().negative();
			editableImageFrame.buildAndShow();
		}
	}
}
