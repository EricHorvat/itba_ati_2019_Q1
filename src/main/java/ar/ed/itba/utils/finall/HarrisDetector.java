package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class HarrisDetector extends FinalDetector{
  
  public HarrisDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.HARRIS);
    PREFIX = "Harris";
  
  }
  
}
