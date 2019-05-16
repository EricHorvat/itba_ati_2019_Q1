package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.detection.HoughCircular;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHoughCirclesButtonListener implements ActionListener {

  private final JTextField circumferenceCount;
  private final JTextField fromA;
  private final JTextField toA;
  private final JTextField aIntervals;
  private final JTextField fromB;
  private final JTextField toB;
  private final JTextField bIntervals;
  private final JTextField fromR;
  private final JTextField toR;
  private final JTextField rIntervals;

  public GenerateHoughCirclesButtonListener(JTextField circumferenceCount,
                                        JTextField fromA, JTextField toA, JTextField aIntervals,
                                        JTextField fromB, JTextField toB, JTextField bIntervals,
                                        JTextField fromR, JTextField toR, JTextField rIntervals) {
    this.circumferenceCount = circumferenceCount;
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
    PpmImage image = HoughCircular.transform(inputImageFrame.getAtiImage(), Integer.parseInt(circumferenceCount.getText()),
            Integer.parseInt(fromA.getText()), Integer.parseInt(toA.getText()), Integer.parseInt(aIntervals.getText()),
            Integer.parseInt(fromB.getText()), Integer.parseInt(toB.getText()), Integer.parseInt(bIntervals.getText()),
            Integer.parseInt(fromR.getText()), Integer.parseInt(toR.getText()), Integer.parseInt(rIntervals.getText()));
  
    FrameFactory.fixedImageFrame("HoughCircular", image).buildAndShow();
  }
}
