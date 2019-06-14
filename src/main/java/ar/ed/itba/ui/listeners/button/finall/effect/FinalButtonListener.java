package ar.ed.itba.ui.listeners.button.finall.effect;

import ar.ed.itba.utils.detection.SIFT;
import ar.ed.itba.utils.finall.*;
import org.opencv.core.*;
import org.opencv.core.Point;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
    String filename = "/home/eric/aati_final_database/benchmarks/endtoend/eu/test_001";
    String imageFilename = filename + ".jpg";
    String configFilename = filename + ".txt";
    Map<String, String> params = CSVReader.read(configFilename, "\t");
    String shortFilename = params.get(CSVReader.Column.FILENAME.name());
    l.forEach(
      finalDetector -> finalDetector.detect(
        imageFilename,
        Integer.parseInt(params.get(CSVReader.Column.W.name())),
        Integer.parseInt(params.get(CSVReader.Column.H.name())),
        shortFilename.substring(0,shortFilename.length()-4)
      )
    );
    
    
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
