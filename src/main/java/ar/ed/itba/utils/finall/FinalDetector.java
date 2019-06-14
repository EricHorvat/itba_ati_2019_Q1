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
  protected String PREFIX = "NOTPREFIX";
  
  private static final int COLOR = Highgui.CV_LOAD_IMAGE_COLOR;
  static{
    nu.pattern.OpenCV.loadLocally();
  }
  
  public MatOfKeyPoint detect(String imageFilePath, int w, int h, String filename){
    System.out.println("Running " + PREFIX + "detect");
    final Mat imageMat = Highgui.imread(imageFilePath, COLOR);
  
    // Calculate keypoints
    MatOfKeyPoint keyPointsImage = new MatOfKeyPoint();
    KEYPOINT_DETECTOR.detect(imageMat, keyPointsImage);
    /*
    // Extract descriptors
    final MatOfKeyPoint descriptorsImageA = new MatOfKeyPoint();
    DESCRIPTOR_EXTRACTOR.compute(imageA, keyPointsImageA, descriptorsImageA);
    */
    String resultA = saveKeyPoints(imageMat, keyPointsImage, filename, w, h);
    return keyPointsImage;
  }
  
  private static final Scalar KEYPOINT_COLOR = new Scalar(0, 255);
  
  private String saveKeyPoints(final Mat image, final MatOfKeyPoint keyPoints, final String fileName, int w, int h) {
    for (int i = 0; i < h/2; i++) {
      Mat outputImage = new Mat(image.rows(), image.cols(), COLOR);
      MatOfKeyPoint matOfKeyPoint = tryToMatch(keyPoints, w, h, i);
      Features2d.drawKeypoints(image, matOfKeyPoint, outputImage, KEYPOINT_COLOR, 0);
      String outputFileName = "./output/" + PREFIX + "_" + fileName + "_" + i +".png";
      Highgui.imwrite(outputFileName, outputImage);
      
    }
    return "outputFileName";
  }
  
  private MatOfKeyPoint tryToMatch(MatOfKeyPoint kMat, int w, int h, double d){
    List<KeyPoint> kList = kMat.toList();
    int count = 0;
    Map<KeyPoint, KeyPoint> map = new HashMap<>();
    for (KeyPoint k : kList
         ) {
      if (mapContainsSomeNeighbour(k, map, d)){
        continue;
      }
    
      List<KeyPoint> urList = kList.stream().filter(kp -> this.tryToMatch(kp, k.pt.x + w, k.pt.y, d)).collect(Collectors.toList());
      Set<KeyPoint> urSet = new TreeSet<>(KeyPointComparator.URComparator());
      urSet.addAll(urList);
      urList = new ArrayList<>(urSet);
  
      List<KeyPoint> dlList = kList.stream().filter(kp -> this.tryToMatch(kp, k.pt.x, k.pt.y + h, d)).collect(Collectors.toList());
      Set<KeyPoint> dlSet = new TreeSet<>(KeyPointComparator.DLComparator());
      dlSet.addAll(dlList);
      dlList = new ArrayList<>(dlSet);
  
      List<KeyPoint> drList = kList.stream().filter(kp -> this.tryToMatch(kp, k.pt.x + w, k.pt.y + h, d)).collect(Collectors.toList());
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
    MatOfKeyPoint mmmasd = new MatOfKeyPoint();
    List<KeyPoint> ans = new ArrayList<>();
    for (KeyPoint key: map.keySet()) {
      ans.add(key);
      KeyPoint value = map.get(key);
      ans.add(value);
      ans.add(new KeyPoint((float)key.pt.x, (float)value.pt.y,1));
      ans.add(new KeyPoint((float)value.pt.x, (float)key.pt.y,1));
    }
    mmmasd.fromList(ans);
    return mmmasd;
  }
  
  private boolean tryToMatch(KeyPoint kp, double x, double y, double d){
    double distance = Math.sqrt(Math.pow(x - kp.pt.x,2) + Math.pow(y - kp.pt.y,2));
    return distance < d;
  }
  
  //WITH THIS, IF ACTIVE, IGNORE THE NEIGHBOURS, BUT WITH BIG d, IT IS A MESS
  // IF DEACTIVE SCAN TWO CLOSER KEYPOINTS AND PROBABLY THERE ARE (OR NOT) ALREADY THE LICENSE
  private static boolean ACTIVE = true;
  
  private boolean mapContainsSomeNeighbour(KeyPoint k, Map<KeyPoint,KeyPoint> map, double d){
    return ACTIVE && map.keySet().stream().anyMatch(keyPoint -> Math.sqrt(Math.pow(keyPoint.pt.x - k.pt.x,2) +
      Math.pow(keyPoint.pt.y - k.pt.y,2)) < d);
  }
  
  
}
