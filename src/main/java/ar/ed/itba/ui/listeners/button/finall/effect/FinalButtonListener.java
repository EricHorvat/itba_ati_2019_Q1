package ar.ed.itba.ui.listeners.button.finall.effect;

import ar.ed.itba.utils.detection.SIFT;
import ar.ed.itba.utils.finall.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FinalButtonListener implements ActionListener {
  
  //private final JTextField matchingDistanceField;
  //private final JTextField matchingPercentageField;
  //private final JCheckBox statusCheckBox;
  
  public FinalButtonListener(){
    /*JTextField matchingDistanceField, JTextField matchingPercentageField,
                             JCheckBox statusCheckBox) {*/
    //this.matchingDistanceField = matchingDistanceField;
    //this.matchingPercentageField = matchingPercentageField;
    //this.statusCheckBox = statusCheckBox;
  }
  
  public String getTitle() {
    return "Active Contour";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    List<FinalDetector> l = new ArrayList<>();
    
    l.add(new SIFTDetector());
    l.add(new SURFDetector());
    l.add(new FASTDetector());
    l.add(new BRISKDetector());
    l.add(new MSERDetector());
    l.add(new HarrisDetector());
    l.forEach(finalDetector -> finalDetector.detect("./res/a.jpg"));
    /*int matchingDistance = 0;
    double matchingPercentage = 0.0;
    if (!matchingDistanceField.getText().equals("")) {
      matchingDistance = Integer.parseInt(matchingDistanceField.getText());
    }
    if (!matchingPercentageField.getText().equals("")) {
      matchingPercentage = Double.parseDouble(matchingPercentageField.getText());
    }
  
    SIFT.getInstance().apply(matchingDistance,matchingPercentage, statusCheckBox.isSelected());*/
  
  }
}
