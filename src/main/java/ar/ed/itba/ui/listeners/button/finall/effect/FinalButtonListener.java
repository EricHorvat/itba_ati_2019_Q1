package ar.ed.itba.ui.listeners.button.finall.effect;

import ar.ed.itba.utils.finall.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FinalButtonListener implements ActionListener {
  
  private final JCheckBox runAllCheckBox;
  private final JCheckBox ignoreNeighboursCheckBox;
  private static List<FinalDetector> detectorList;
  private static String[] testCase;
  
  static {
    nu.pattern.OpenCV.loadLocally();
    detectorList = new ArrayList<>();
    detectorList.add(new HarrisDetector());
    detectorList.add(new BRISKDetector());
    detectorList.add(new SIFTDetector());
    detectorList.add(new SURFDetector());
    detectorList.add(new FASTDetector());
    detectorList.add(new MSERDetector());
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
    HashMap<String, Map<String, Map<String, Long>>> resultAccum = new HashMap<>();
    if(!runAllCheckBox.isSelected()){
      resultAccum.put("",
        singleRun(
        "/home/eric/aati_final_database/benchmarks/endtoend/eu/test_010",
                ignoreNeighboursCheckBox.isSelected()
        )
      );
    } else {
      for(String s : testCase){
        resultAccum.put(
          s,
          singleRun(
            "/home/eric/aati_final_database/benchmarks/endtoend/eu/" + s,
            ignoreNeighboursCheckBox.isSelected()
          )
        );
      }
    }
    printResults(resultAccum);
  }
  
  private Map<String, Map<String,Long>> singleRun(String filename, boolean ignoreNeighbours){
    /*File process*/
    String imageFilename = filename + ".jpg";
    String configFilename = filename + ".txt";
    Map<String, String> params = CSVReader.read(configFilename, "\t");
    Map<String, Map<String,Long>> results = new HashMap<>();
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
  
  private void printResults(Map<String, Map<String, Map<String,Long>>> map) {
    Map<String, List<Map<String, Long>>> ansMap = new HashMap<>();
    for (String s : map.keySet()) {
      Map<String, Map<String,Long>> detectorMap = map.get(s);
      for (String detector : detectorMap.keySet()) {
        if(!ansMap.keySet().contains(detector)){
          ansMap.put(detector, new ArrayList<>());
        }
        ansMap.get(detector).add(detectorMap.get(detector));
      }
    }
    for (String detector : ansMap.keySet()) {
      List<Long> timeList = ansMap.get(detector).stream().map(stringLongMap -> stringLongMap.get(FinalDetector.TIME)).collect(Collectors.toList());
      List<Long> iterList = ansMap.get(detector).stream().map(stringLongMap -> stringLongMap.get(FinalDetector.ITERATIONS)).collect(Collectors.toList());
      int originalSize = iterList.size();
      iterList = iterList.stream().filter(i -> i > 0).collect(Collectors.toList());
      Double averageTime = timeList.stream().mapToLong(Long::longValue).average().orElse(0);
      double stdTime = Math.sqrt(timeList.stream().mapToDouble(i -> Math.pow(i - averageTime,2)).sum()/(timeList.size() - 1));
      Double averageIt = iterList.stream().mapToLong(Long::longValue).average().orElse(0);
      double stdIt = Math.sqrt(iterList.stream().mapToDouble(i -> Math.pow(i - averageIt,2)).sum()/(iterList.size() - 1));
      System.out.println(detector);
      System.out.println("Found " + iterList.size() + "/" + originalSize + "(" + iterList.size()/originalSize + ")");
      System.out.println("Average " + averageIt);
      System.out.println("Std" + stdIt);
      System.out.println("Average time " + averageTime);
      System.out.println("Std time" + stdTime);
    }
  }
}
