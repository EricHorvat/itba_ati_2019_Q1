package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class SURFDetector extends FinalDetector{
  
  public SURFDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.SURF);
    NAME = "SURF";
  
  }
  
}
