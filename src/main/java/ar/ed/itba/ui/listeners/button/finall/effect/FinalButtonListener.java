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
  private final JCheckBox withDeltaCheckBox;
  private static List<FinalDetector> detectorList;
  //private static String[] testCase;
  private static List<String> testCase = new ArrayList<>();
  
  static {
    nu.pattern.OpenCV.loadLocally();
    detectorList = new ArrayList<>();
    detectorList.add(new HarrisDetector());
    detectorList.add(new BRISKDetector());
    detectorList.add(new SIFTDetector());
    detectorList.add(new SURFDetector());
    detectorList.add(new MSERDetector());
    detectorList.add(new FASTDetector());
    int test_size = 5;
    for (int i = 1; i <= test_size; i++) {
      testCase.add(String.format("test_0%02d",i));
    }
  }
  
  public FinalButtonListener(JCheckBox runAllCheckBox, JCheckBox ignoreNeighboursCheckBox, JCheckBox withDeltaCheckBox){
    this.runAllCheckBox = runAllCheckBox;
    this.ignoreNeighboursCheckBox = ignoreNeighboursCheckBox;
    this.withDeltaCheckBox = withDeltaCheckBox;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    HashMap<String, Map<String, Map<String, Long>>> resultAccum = new HashMap<>();
    if(!runAllCheckBox.isSelected()){
      resultAccum.put("",
        singleRun(
        "/home/eric/aati_final_database/benchmarks/endtoend/eu/test_001",
          ignoreNeighboursCheckBox.isSelected(),
          withDeltaCheckBox.isSelected()
        )
      );
    } else {
      for(String s : testCase){
        resultAccum.put(
          s,
          singleRun(
            "/home/eric/aati_final_database/benchmarks/endtoend/eu/" + s,
            ignoreNeighboursCheckBox.isSelected(),
            withDeltaCheckBox.isSelected()
          )
        );
      }
    }
    printResults(resultAccum);
  }
  
  private Map<String, Map<String,Long>> singleRun(String filename, boolean ignoreNeighbours, boolean withDelta){
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
        withDelta,
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
      List<Long> triesList = ansMap.get(detector).stream().map(stringLongMap -> stringLongMap.get(FinalDetector.RECTANGLE_TRIES)).collect(Collectors.toList());
      List<Long> foundList = ansMap.get(detector).stream()
        .filter(stringLongMap -> stringLongMap.get(FinalDetector.FOUND) > 0)
        .map(stringLongMap -> stringLongMap.get(FinalDetector.ITERATIONS))
        .collect(Collectors.toList());
      long found = foundList.size();
      System.out.println(detector);
      System.out.println("Found " + found + "/" + iterList.size() + "(" + 1.0*found/iterList.size() + ")");
      printList("Full iter", iterList);
      printList("Found iter", foundList);
      printList("Time", timeList);
      printList("Rectangle tries", triesList);
    }
  }
  
  private void printList(String prefix, List<?> list){
    System.out.print(prefix);
    System.out.print(" [");
    list.forEach(e -> System.out.print(e.toString()+","));
    System.out.println("]");
  }
}
