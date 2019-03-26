package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddImageButtonListener implements ActionListener {

	private final JTextField filePathField;

	public AddImageButtonListener(JTextField filePathField) {
		this.filePathField = filePathField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		ImageOpener imageOpener = new ImageOpener();
		ATIImage image = imageOpener.open(filePathField.getText());

		if (image != null) {
			EditableImageFrame editableImageFrame = EditableImageFrame.instance();
			editableImageFrame.setAtiImage(ImageUtils.sum(editableImageFrame.getAtiImage(), image));
			editableImageFrame.buildAndShow();
		}
	}
}
