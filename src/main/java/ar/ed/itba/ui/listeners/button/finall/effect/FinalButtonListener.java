package ar.ed.itba.ui.listeners.button.finall.effect;

import ar.ed.itba.utils.finall.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FinalButtonListener implements ActionListener {
  
  private final JCheckBox ignoreNeighboursCheckBox;
  private final JCheckBox useOnlyHarrisCheckBox;
  private final JTextField fromTextField;
  private final JTextField toTextField;
  private final JTextField fromDTextField;
  private final JTextField toDTextField;
  private static List<FinalDetector> detectorList;
  
  static {
    nu.pattern.OpenCV.loadLocally();
    detectorList = new ArrayList<>();
    detectorList.add(new HarrisDetector());
    detectorList.add(new BRISKDetector());
    detectorList.add(new SIFTDetector());
    detectorList.add(new SURFDetector());
    detectorList.add(new MSERDetector());
    detectorList.add(new FASTDetector());
  }
  
  public FinalButtonListener(JCheckBox ignoreNeighboursCheckBox,
                             JCheckBox useOnlyHarrisCheckBox,
                             JTextField fromTextField,
                             JTextField toTextField,
                             JTextField fromDTextField,
                             JTextField toDTextField){
    this.ignoreNeighboursCheckBox = ignoreNeighboursCheckBox;
    this.useOnlyHarrisCheckBox = useOnlyHarrisCheckBox;
    this.fromTextField = fromTextField;
    this.toTextField = toTextField;
    this.fromDTextField = fromDTextField;
    this.toDTextField = toDTextField;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    HashMap<String, Map<String, Map<String, Long>>> resultAccum = new HashMap<>();
  
    int test_from = fromTextField.getText().length()==0?1:Integer.parseInt(fromTextField.getText());
    int test_to = toTextField.getText().length()==0?5:Integer.parseInt(toTextField.getText());
    int test_from_D = fromDTextField.getText().length()==0?0:Integer.parseInt(fromDTextField.getText());
    int test_to_D = toDTextField.getText().length()==0?-1:Integer.parseInt(toDTextField.getText());
  
    for (int i = test_from; i <= test_to; i++) {
      String s = String.format("test_%03d",i);
      resultAccum.put(
        s,
        singleRun(
          "/home/eric/aati_final_database/benchmarks/endtoend/eu/" + s,
          ignoreNeighboursCheckBox.isSelected(),
          useOnlyHarrisCheckBox.isSelected(),
          test_from_D,
          test_to_D
        )
      );
    }
    printResults(resultAccum);
  }
  
  private Map<String, Map<String,Long>> singleRun(String filename,
                                                  boolean ignoreNeighbours,
                                                  boolean useHarrisOnly,
                                                  int test_from_D,
                                                  int test_to_D){
    /*File process*/
    String imageFilename = filename + ".jpg";
    String configFilename = filename + ".txt";
    Map<String, String> params = CSVReader.read(configFilename, "\t");
    Map<String, Map<String,Long>> results = new HashMap<>();
    String shortFilename = params.get(CSVReader.Column.FILENAME.name());
    /*Run the Match detector*/
    detectorList.
      stream().
      filter(finalDetector -> useHarrisOnly && finalDetector.getName().equals("Harris")).
      forEach(
      finalDetector -> results.put(finalDetector.getName(), finalDetector.detect(
        ignoreNeighbours,
        imageFilename,
        shortFilename.substring(0,shortFilename.length()-4),
        params.get(CSVReader.Column.LICENSE.name()),
        Integer.parseInt(params.get(CSVReader.Column.W.name())),
        Integer.parseInt(params.get(CSVReader.Column.H.name())),
        test_from_D,
        test_to_D
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
