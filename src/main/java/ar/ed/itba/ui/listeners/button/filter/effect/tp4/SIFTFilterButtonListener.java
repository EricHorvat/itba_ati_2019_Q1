package ar.ed.itba.ui.listeners.button.filter.effect.tp4;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.detection.SIFT;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SIFTFilterButtonListener implements ActionListener {
  
  private final JTextField matchingDistanceField;
  private final JTextField matchingPercentageField;
  
  public SIFTFilterButtonListener(JTextField matchingDistanceField, JTextField matchingPercentageField) {
    this.matchingDistanceField = matchingDistanceField;
    this.matchingPercentageField = matchingPercentageField;
  }
  
  public String getTitle() {
    return "Active Contour";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
  
    int matchingDistance = 0;
    double matchingPercentage = 0.0;
    if (!matchingDistanceField.getText().equals("")) {
      matchingDistance = Integer.parseInt(matchingDistanceField.getText());
    }
    if (!matchingPercentageField.getText().equals("")) {
      matchingPercentage = Double.parseDouble(matchingPercentageField.getText());
    }
  
    SIFT.getInstance().apply(matchingDistance,matchingPercentage);
  
  }
}
