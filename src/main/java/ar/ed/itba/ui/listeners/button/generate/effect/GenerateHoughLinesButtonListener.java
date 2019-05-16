package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.detection.HoughLineal;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateHoughLinesButtonListener implements ActionListener {

  private final JTextField circumferenceCount;
  private final JTextField fromTheta;
  private final JTextField toTheta;
  private final JTextField thetaIntervals;
  private final JTextField fromPhi;
  private final JTextField toPhi;
  private final JTextField phiIntervals;

  public GenerateHoughLinesButtonListener(JTextField circumferenceCount,
                                            JTextField fromTheta, JTextField toTheta, JTextField thetaIntervals,
                                            JTextField fromPhi, JTextField toPhi, JTextField phiIntervals) {
    this.circumferenceCount = circumferenceCount;
    this.fromTheta = fromTheta;
    this.toTheta = toTheta;
    this.thetaIntervals = thetaIntervals;
    this.fromPhi = fromPhi;
    this.toPhi = toPhi;
    this.phiIntervals = phiIntervals;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame inputImageFrame = EditableImageFrame.instance();
    double fromThetaValue;
    if (fromTheta.getText().equals(""))
      fromThetaValue = -Math.PI/2;
    else
      fromThetaValue = Double.parseDouble(fromTheta.getText());

    double toThetaValue;
    if (toTheta.getText().equals(""))
      toThetaValue = Math.PI/2;
    else
      toThetaValue = Double.parseDouble(toTheta.getText());

    PpmImage image = HoughLineal.transform(inputImageFrame.getAtiImage(), Integer.parseInt(circumferenceCount.getText()),
            fromThetaValue, toThetaValue, Integer.parseInt(thetaIntervals.getText()),
            Integer.parseInt(fromPhi.getText()), Integer.parseInt(toPhi.getText()), Integer.parseInt(phiIntervals.getText()));
  
    FrameFactory.fixedImageFrame("HoughLineal", image).buildAndShow();
  }
}
