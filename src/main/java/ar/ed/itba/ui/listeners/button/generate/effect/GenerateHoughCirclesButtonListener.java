package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.detection.HoughCircular;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHoughCirclesButtonListener implements ActionListener {

  private final JTextField fromA;
  private final JTextField toA;
  private final JTextField aIntervals;
  private final JTextField fromB;
  private final JTextField toB;
  private final JTextField bIntervals;
  private final JTextField fromR;
  private final JTextField toR;
  private final JTextField rIntervals;

  public GenerateHoughCirclesButtonListener(JTextField fromA, JTextField toA, JTextField aIntervals,
                                        JTextField fromB, JTextField toB, JTextField bIntervals,
                                        JTextField fromR, JTextField toR, JTextField rIntervals) {
    this.fromA = fromA;
    this.toA = toA;
    this.aIntervals = aIntervals;
    this.fromB = fromB;
    this.toB = toB;
    this.bIntervals = bIntervals;
    this.fromR = fromR;
    this.toR = toR;
    this.rIntervals = rIntervals;
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame inputImageFrame = EditableImageFrame.instance();
    PpmImage image = HoughCircular.transform(inputImageFrame.getAtiImage(), 10);
  
    FrameFactory.fixedImageFrame("HoughCircular", image).buildAndShow();
  }
}
