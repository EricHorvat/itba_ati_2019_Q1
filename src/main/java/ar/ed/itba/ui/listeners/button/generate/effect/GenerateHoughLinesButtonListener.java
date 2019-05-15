package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.detection.HoughLineal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHoughLinesButtonListener implements ActionListener {
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame inputImageFrame = EditableImageFrame.instance();
    PpmImage image = HoughLineal.transform(inputImageFrame.getAtiImage(), 4, 2,198);
  
    FrameFactory.fixedImageFrame("HoughLineal", image).buildAndShow();
  }
}
