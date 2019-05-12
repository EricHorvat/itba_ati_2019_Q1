package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.math.NoiseImageFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.detection.Hough;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHoughLinesButtonListener implements ActionListener {
	
	public GenerateHoughLinesButtonListener() {
	}
	
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame inputImageFrame = EditableImageFrame.instance();
    PpmImage image = Hough.transform(inputImageFrame.getAtiImage(), 4, 2,198);
  
    FrameFactory.fixedImageFrame("Hough", image).buildAndShow();
  }
}
