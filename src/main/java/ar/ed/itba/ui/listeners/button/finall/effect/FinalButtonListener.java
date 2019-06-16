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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FinalButtonListener implements ActionListener {
  
  private final JCheckBox runAllCheckBox;
  private final JCheckBox ignoreNeighboursCheckBox;
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
  
  public FinalButtonListener(JCheckBox runAllCheckBox, JCheckBox ignoreNeighboursCheckBox){
    this.runAllCheckBox = runAllCheckBox;
    this.ignoreNeighboursCheckBox = ignoreNeighboursCheckBox;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if(!runAllCheckBox.isSelected()){
      singleRun(
        "/home/eric/aati_final_database/benchmarks/endtoend/eu/test_001",
        ignoreNeighboursCheckBox.isSelected()
      );
    } else {
      HashMap<String, Map<String, Boolean>> resultAccum = new HashMap<>();
      for(String s : testCase){
        resultAccum.put(
          s,
          singleRun(
            "/home/eric/aati_final_database/benchmarks/endtoend/eu/" + s,
            ignoreNeighboursCheckBox.isSelected()
          )
        );
      }
      /*TODO GET RESULTS FOR THE TOTAL PROCESS*/
    }
  }
  
  private Map<String, Boolean> singleRun(String filename, boolean ignoreNeighbours){
    /*File process*/
    String imageFilename = filename + ".jpg";
    String configFilename = filename + ".txt";
    Map<String, String> params = CSVReader.read(configFilename, "\t");
    Map<String, Boolean> results = new HashMap<>();
    String shortFilename = params.get(CSVReader.Column.FILENAME.name());
    /*Run the Match detector*/
    detectorList.forEach(
      finalDetector -> results.put(finalDetector.getName(), finalDetector.detect(
        ignoreNeighbours,
        imageFilename,
        shortFilename.substring(0,shortFilename.length()-4),
        params.get(CSVReader.Column.LICENSE.name()),
        Integer.parseInt(params.get(CSVReader.Column.W.name())),
        Integer.parseInt(params.get(CSVReader.Column.H.name()))
        )
      )
    );
    return results;
  }
}
