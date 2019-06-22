package ar.ed.itba.utils.finall;

import javafx.util.Pair;
import org.opencv.core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FinalDetector {
  
  protected FeatureDetector KEYPOINT_DETECTOR;
  protected String NAME = "NOT_NAME";
  
  public static final String TIME = "TIME";
  public static final String ITERATIONS = "IT";
  public static final String FOUND = "FOUND";
  public static final String RECTANGLE_TRIES = "R_TRIES";
  
  private static final int COLOR = Highgui.CV_LOAD_IMAGE_COLOR;
  
  public Map<String,Long> detect(
    boolean ignoreNeighbours,
    String imageFilePath,
    String filename,
    String license,
    int width,
    int height,
    int test_from_D,
    int test_to_D
  ){
    HashMap<String, Long> map = new HashMap<>();
    System.out.println("Running " + NAME + " detector");
    this.ignoreNeighbours = ignoreNeighbours;
    final Mat imageMat = Highgui.imread(imageFilePath, COLOR);
  
    // Calculate keypoints
    long initTime = System.currentTimeMillis();
    MatOfKeyPoint keyPointsImage = new MatOfKeyPoint();
    KEYPOINT_DETECTOR.detect(imageMat, keyPointsImage);
    Integer rectangleTries = 0;
  
    long count = 0;
    for (long i = test_from_D; i < deltaMax(test_to_D,height); i++) {
      Pair<Integer,MatOfKeyPoint[]> pair = tryToMatch(keyPointsImage, width, height, i, imageFilePath, license);
      rectangleTries += pair.getKey();
      MatOfKeyPoint[] matsOfKeyPoint = pair.getValue();
      saveKeyPoints(imageMat, matsOfKeyPoint, filename, i + "");
      count++;
      if(matsOfKeyPoint[1].toList().size() > 0) {
        map.put(TIME, System.currentTimeMillis() - initTime);
        map.put(ITERATIONS, count);
        map.put(FOUND, 1L);
        map.put(RECTANGLE_TRIES, rectangleTries.longValue());
        return map;
      }
    }
    map.put(TIME, System.currentTimeMillis() - initTime);
    map.put(ITERATIONS, count);
    map.put(FOUND, -1L);
    map.put(RECTANGLE_TRIES, rectangleTries.longValue());
    return map;
  }
  
  private double deltaMax(int test_to_D, int height){
    return test_to_D<=-1?height:test_to_D;
  }
  
  private static final Scalar KEYPOINT_COLOR = new Scalar(0, 255);
  private static final Scalar MATCH_KEYPOINT_COLOR = new Scalar(0, 0, 255);
  
  private void saveKeyPoints(final Mat image, final MatOfKeyPoint[] matsOfKeyPoints, final String fileName, String... strings) {
    Mat outputImage = new Mat(image.rows(), image.cols(), COLOR);
    Features2d.drawKeypoints(image, matsOfKeyPoints[0], outputImage, KEYPOINT_COLOR, 0);
    Features2d.drawKeypoints(outputImage, matsOfKeyPoints[1], outputImage, MATCH_KEYPOINT_COLOR, 0);
    String extra = Arrays.stream(strings).reduce("",(accum, elem) -> accum + "_" + elem);
    String outputFileName = "./output/" + NAME + "_" + fileName + extra +".png";
    Highgui.imwrite(outputFileName, outputImage);
  
  }
  
  private Pair<Integer,MatOfKeyPoint[]> tryToMatch(MatOfKeyPoint kMat, int width, int height, double delta, String fullFilename, String license){
    List<KeyPoint> kList = kMat.toList();
    int count = 0;
    Map<KeyPoint, KeyPoint> map = new HashMap<>();
    for (KeyPoint k : kList) {
      if (mapContainsSomeNeighbour(k, map, delta)){
        continue;
      }
      double factor = 0.6;
      List<KeyPoint> urList = kList
                              .stream()
                              .filter(kp -> this.tryToMatch(kp, k.pt.x + width, k.pt.y, delta))
                              .filter(kp -> kp.pt.x - k.pt.x > width * factor)
                              .collect(Collectors.toList());
      Set<KeyPoint> urSet = new TreeSet<>(KeyPointComparator.URComparator());
      urSet.addAll(urList);
      urList = new ArrayList<>(urSet);
  
      List<KeyPoint> dlList = kList
                              .stream()
                              .filter(kp -> this.tryToMatch(kp, k.pt.x, k.pt.y + height, delta))
                              .filter(kp -> kp.pt.y - k.pt.y > height * factor)
                              .collect(Collectors.toList());
      Set<KeyPoint> dlSet = new TreeSet<>(KeyPointComparator.DLComparator());
      dlSet.addAll(dlList);
      dlList = new ArrayList<>(dlSet);
  
      List<KeyPoint> drList = kList
                              .stream()
                              .filter(kp -> this.tryToMatch(kp, k.pt.x + width, k.pt.y + height, delta))
                              .filter(kp -> kp.pt.x - k.pt.x > width * factor)
                              .filter(kp -> kp.pt.y - k.pt.y > height * factor)
                              .collect(Collectors.toList());
      Set<KeyPoint> drSet = new TreeSet<>(KeyPointComparator.DRComparator());
      drSet.addAll(drList);
      drList = new ArrayList<>(drSet);
  
      urList.sort(KeyPointComparator.URComparator());
      dlList.sort(KeyPointComparator.DLComparator());
      drList.sort(KeyPointComparator.DRComparator());
      if (urList.isEmpty() || dlList.isEmpty() || drList.isEmpty() ){
        continue;
      }
      map.put(k,drList.get(0));
      count++;
    }
    System.out.println("FOUND " + count + " possible matches with " + (int) delta + " delta");
    return new Pair<>(count,licenseMatch(fullFilename, license, map));
    
  }
  
  private boolean tryToMatch(KeyPoint kp, double x, double y, double delta){
    double distance = Math.sqrt(Math.pow(x - kp.pt.x,2) + Math.pow(y - kp.pt.y,2));
    return distance < delta;
  }
  
  private MatOfKeyPoint[] licenseMatch(String fileName, String license, Map<KeyPoint, KeyPoint> map){
    MatOfKeyPoint[] matsOfKeyPoint = new MatOfKeyPoint[2];
    List<KeyPoint> matchKeyPointList = new ArrayList<>();
    List<KeyPoint> nonMatchKeyPointList = new ArrayList<>();
    File file = new File(fileName);
    for (KeyPoint key: map.keySet()) {
      KeyPoint value = map.get(key);
      List<KeyPoint> list = OCREngine
                            .getInstance()
                            .match( file,
                              new Rectangle(
                                (int) key.pt.x,
                                (int) key.pt.y,
                                (int) (value.pt.x - key.pt.x),
                                (int) (value.pt.y - key.pt.y)),
                              license) ?
        matchKeyPointList :
        nonMatchKeyPointList;
      list.add(key);
      list.add(value);
      list.add(new KeyPoint((float)key.pt.x, (float)value.pt.y,1));
      list.add(new KeyPoint((float)value.pt.x, (float)key.pt.y,1));
    }
    MatOfKeyPoint keyPointMat = new MatOfKeyPoint();
    keyPointMat.fromList(nonMatchKeyPointList);
    matsOfKeyPoint[0] = keyPointMat;
    MatOfKeyPoint matchKeyPointMat = new MatOfKeyPoint();
    matchKeyPointMat.fromList(matchKeyPointList);
    matsOfKeyPoint[1] = matchKeyPointMat;
    return matsOfKeyPoint;
  }
  
  //WITH THIS, IF ACTIVE, IGNORE THE NEIGHBOURS, BUT WITH BIG d, IT IS A MESS
  // IF DEACTIVE SCAN TWO CLOSER KEYPOINTS AND PROBABLY THERE ARE (OR NOT) ALREADY THE LICENSE
  private boolean ignoreNeighbours = false;
  
  private boolean mapContainsSomeNeighbour(KeyPoint k, Map<KeyPoint,KeyPoint> map, double delta){
    return ignoreNeighbours && map.keySet().stream().anyMatch(keyPoint -> Math.sqrt(Math.pow(keyPoint.pt.x - k.pt.x,2) +
      Math.pow(keyPoint.pt.y - k.pt.y,2)) < delta / 4);
  }
  
  public String getName(){
    return NAME;
  }
  
}
