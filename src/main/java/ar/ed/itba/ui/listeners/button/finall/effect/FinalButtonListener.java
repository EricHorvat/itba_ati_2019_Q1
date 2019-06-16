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
import java.util.List;
import java.util.Map;

public class FinalButtonListener implements ActionListener {
  
  private final JCheckBox runAllCheckBox;
  private static List<FinalDetector> detectorList;
  private static String[] testCase;
  
  static {
    nu.pattern.OpenCV.loadLocally();
    detectorList = new ArrayList<>();
    detectorList.add(new SIFTDetector());
    detectorList.add(new SURFDetector());
    detectorList.add(new FASTDetector());
    detectorList.add(new BRISKDetector());
    detectorList.add(new MSERDetector());
    detectorList.add(new HarrisDetector());
    testCase = new String[] {
      "test_001",
      "test_002",
      "test_003",
      "test_004",
      "test_005"
    };
  }
  
  public FinalButtonListener(JCheckBox runAllCheckBox){
    this.runAllCheckBox = runAllCheckBox;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(!runAllCheckBox.isSelected()){
      singleRun("/home/eric/aati_final_database/benchmarks/endtoend/eu/test_001");
    } else {
      for(String s : testCase){
        singleRun("/home/eric/aati_final_database/benchmarks/endtoend/eu/" + s);
      }
    }
  }
  
  private void singleRun(String filename){
    /*File process*/
    String imageFilename = filename + ".jpg";
    String configFilename = filename + ".txt";
    Map<String, String> params = CSVReader.read(configFilename, "\t");
    String shortFilename = params.get(CSVReader.Column.FILENAME.name());
    /*Run the Match detector*/
    detectorList.forEach(
      finalDetector -> finalDetector.detect(
        imageFilename,
        Integer.parseInt(params.get(CSVReader.Column.W.name())),
        Integer.parseInt(params.get(CSVReader.Column.H.name())),
        shortFilename.substring(0,shortFilename.length()-4)
      )
    );
  }
}
