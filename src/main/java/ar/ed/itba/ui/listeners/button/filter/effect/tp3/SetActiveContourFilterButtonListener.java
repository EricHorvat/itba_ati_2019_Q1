package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.CoordinatePair;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetActiveContourFilterButtonListener implements ActionListener {
  
  private final JTextField nMaxField;
  
  public SetActiveContourFilterButtonListener(JTextField nMaxField) {
    this.nMaxField = nMaxField;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame e = EditableImageFrame.instance();
    
    int nMax = Integer.parseInt(nMaxField.getText());
    
    ActiveContourFilter.getInstance().set(e.getAtiImage(),nMax);
  }
}
