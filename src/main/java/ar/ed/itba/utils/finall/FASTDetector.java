package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class FASTDetector extends FinalDetector{
  
  public FASTDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.FAST);
    NAME = "FAST";
  
  }
  
}
