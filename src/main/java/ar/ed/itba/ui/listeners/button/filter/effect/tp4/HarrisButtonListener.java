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
  
  private final JTextField thresholdField;
  
  public HarrisButtonListener(JTextField thresholdField) {
    this.thresholdField = thresholdField;
  }
  
  public String getTitle() {
    return "Harris";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
  
    int threshold = 0 ;
    if (!thresholdField.getText().equals("")) {
      threshold = Integer.parseInt(thresholdField.getText());
    } else {
      threshold = 150;
    }
  
  
    ATIImage resultImage = new Harris().applyFilter(EditableImageFrame.instance().getAtiImage(),false, threshold);
  
    FrameFactory.fixedImageFrame(getTitle(), resultImage).buildAndShow();
    
  }

}
