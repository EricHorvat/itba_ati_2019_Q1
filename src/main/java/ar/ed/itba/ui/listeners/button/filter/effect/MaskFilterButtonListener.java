package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class MaskFilterButtonListener extends FilterButtonListener {
	
	protected JTextField maskSideField;
	
	public MaskFilterButtonListener(JTextField maskSideField) {
		this.maskSideField = maskSideField;
	}
	
	public abstract String getName();
  
  @Override
  public String getTitle() {
    String text = maskSideField!=null?" "+maskSideField.getText() + "x"+maskSideField.getText():"";
  
    return
      getName() + text;
  }
}
