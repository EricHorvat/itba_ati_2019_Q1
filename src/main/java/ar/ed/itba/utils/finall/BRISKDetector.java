package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class BRISKDetector extends FinalDetector{
  
  public BRISKDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.BRISK);
    PREFIX = "BRISK";
  }
  
}
