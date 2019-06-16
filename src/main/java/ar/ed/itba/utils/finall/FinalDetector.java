package ar.ed.itba.utils.finall;

import org.opencv.core.*;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.features2d.KeyPoint;
import org.opencv.highgui.Highgui;

import java.util.*;
import java.util.stream.Collectors;

public abstract class FinalDetector {
  
  protected FeatureDetector KEYPOINT_DETECTOR;
  protected String NAME = "NOT_NAME";
  
  private static final int COLOR = Highgui.CV_LOAD_IMAGE_COLOR;
  
  public Integer detect(
    boolean ignoreNeighbours,
    String imageFilePath,
    String filename,
    String license,
    int width,
    int height
  ){
    System.out.println("Running " + NAME + "detect");
    this.ignoreNeighbours = ignoreNeighbours;
    final Mat imageMat = Highgui.imread(imageFilePath, COLOR);
  
    // Calculate keypoints
    MatOfKeyPoint keyPointsImage = new MatOfKeyPoint();
    KEYPOINT_DETECTOR.detect(imageMat, keyPointsImage);
  
    for (int i = 0; i < height/2; i++) {
      MatOfKeyPoint matOfKeyPoint = tryToMatch(keyPointsImage, width, height, i, license);
      if(matOfKeyPoint != null) {
        saveKeyPoints(imageMat, matOfKeyPoint, filename, i + "");
        //TODO return i;
      }
    }
  
    //TODO return -1
    return 1;
  }
  
  private static final Scalar KEYPOINT_COLOR = new Scalar(0, 255);
  
  private void saveKeyPoints(final Mat image, final MatOfKeyPoint keyPoints, final String fileName, String... strings) {
    Mat outputImage = new Mat(image.rows(), image.cols(), COLOR);
    Features2d.drawKeypoints(image, keyPoints, outputImage, KEYPOINT_COLOR, 0);
    String extra = Arrays.stream(strings).reduce("",(accum, elem) -> accum + "_" + elem);
    String outputFileName = "./output/" + NAME + "_" + fileName + extra +".png";
    Highgui.imwrite(outputFileName, outputImage);
  
  }
  
  private MatOfKeyPoint tryToMatch(MatOfKeyPoint kMat, int width, int height, double delta, String license){
    List<KeyPoint> kList = kMat.toList();
    int count = 0;
    Map<KeyPoint, KeyPoint> map = new HashMap<>();
    for (KeyPoint k : kList) {
      if (mapContainsSomeNeighbour(k, map, delta)){
        continue;
      }
    
      List<KeyPoint> urList = kList.stream().filter(kp -> this.tryToMatch(kp, k.pt.x + width, k.pt.y, delta)).collect(Collectors.toList());
      Set<KeyPoint> urSet = new TreeSet<>(KeyPointComparator.URComparator());
      urSet.addAll(urList);
      urList = new ArrayList<>(urSet);
  
      List<KeyPoint> dlList = kList.stream().filter(kp -> this.tryToMatch(kp, k.pt.x, k.pt.y + height, delta)).collect(Collectors.toList());
      Set<KeyPoint> dlSet = new TreeSet<>(KeyPointComparator.DLComparator());
      dlSet.addAll(dlList);
      dlList = new ArrayList<>(dlSet);
  
      List<KeyPoint> drList = kList.stream().filter(kp -> this.tryToMatch(kp, k.pt.x + width, k.pt.y + height, delta)).collect(Collectors.toList());
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
    System.out.println("FOUND " + count);
    MatOfKeyPoint ansKeyPointMat = new MatOfKeyPoint();
    List<KeyPoint> ans = new ArrayList<>();
    for (KeyPoint key: map.keySet()) {
      ans.add(key);
      KeyPoint value = map.get(key);
      ans.add(value);
      ans.add(new KeyPoint((float)key.pt.x, (float)value.pt.y,1));
      ans.add(new KeyPoint((float)value.pt.x, (float)key.pt.y,1));
    }
    ansKeyPointMat.fromList(ans);
    return licenseMatch(license)?ansKeyPointMat:null;
    
  }
  
  private boolean tryToMatch(KeyPoint kp, double x, double y, double delta){
    double distance = Math.sqrt(Math.pow(x - kp.pt.x,2) + Math.pow(y - kp.pt.y,2));
    return distance < delta;
  }
  
  private boolean licenseMatch(String license){
    return true; //TODO GET RECTANGLES AND ORIGINAL DATA AND DEFINE IF ANY MATCH
  }
  
  //WITH THIS, IF ACTIVE, IGNORE THE NEIGHBOURS, BUT WITH BIG d, IT IS A MESS
  // IF DEACTIVE SCAN TWO CLOSER KEYPOINTS AND PROBABLY THERE ARE (OR NOT) ALREADY THE LICENSE
  private boolean ignoreNeighbours = false;
  
  private boolean mapContainsSomeNeighbour(KeyPoint k, Map<KeyPoint,KeyPoint> map, double delta){
    return ignoreNeighbours && map.keySet().stream().anyMatch(keyPoint -> Math.sqrt(Math.pow(keyPoint.pt.x - k.pt.x,2) +
      Math.pow(keyPoint.pt.y - k.pt.y,2)) < delta);
  }
  
  public String getName(){
    return NAME;
  }
  
}
