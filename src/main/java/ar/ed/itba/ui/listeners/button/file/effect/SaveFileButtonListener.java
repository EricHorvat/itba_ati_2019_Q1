package ar.ed.itba.ui.listeners.button.file.effect;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.OriginalImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SaveFileButtonListener implements ActionListener {
	
	private final JTextField filePathField;
	
	public SaveFileButtonListener(JTextField filePathField) {
		this.filePathField = filePathField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		try {
			
			EditableImageFrame.instance().getAtiImage().save(filePathField.getText());
		}catch (Exception e){
			DialogFactory.promptError("Can not save image :(");
		}
	}
}
