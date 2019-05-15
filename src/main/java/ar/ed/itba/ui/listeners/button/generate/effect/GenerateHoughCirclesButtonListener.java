package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.detection.HoughCircular;
import ar.ed.itba.utils.detection.HoughLineal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHoughCirclesButtonListener implements ActionListener {
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame inputImageFrame = EditableImageFrame.instance();
    PpmImage image = HoughCircular.transform(inputImageFrame.getAtiImage(), 1, 2,2);
  
    FrameFactory.fixedImageFrame("HoughCircular", image).buildAndShow();
  }
}
