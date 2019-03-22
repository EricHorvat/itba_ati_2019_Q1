package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateRayleighButtonListener implements ActionListener {
	
	private final JTextField xiField;
	
	public GenerateRayleighButtonListener(JTextField xiField) {
		this.xiField = xiField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		DialogFactory.notImplementedDialog();
	}
}
