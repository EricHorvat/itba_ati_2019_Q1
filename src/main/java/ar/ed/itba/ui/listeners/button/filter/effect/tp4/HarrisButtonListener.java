package ar.ed.itba.ui.listeners.button.filter.effect.tp4;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.detection.characteristic.Harris;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.advanced.CannyFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HarrisButtonListener implements ActionListener {
  
  private final JTextField toleranceField;
  
  public HarrisButtonListener(JTextField toleranceField) {
    this.toleranceField = toleranceField;
  }
  
  public String getTitle() {
    return "Harris";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
  
    double tolerance = 0 ;
    if (!toleranceField.getText().equals("")) {
      tolerance = Double.parseDouble(toleranceField.getText());
    } else {
      tolerance = 0.5;
    }
  
  
    ATIImage resultImage = new Harris().applyFilter(EditableImageFrame.instance().getAtiImage(),false, tolerance);
  
    FrameFactory.fixedImageFrame(getTitle(), resultImage).buildAndShow();
    
  }

}
