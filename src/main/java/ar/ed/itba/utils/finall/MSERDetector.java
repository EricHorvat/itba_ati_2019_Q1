package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class MSERDetector extends FinalDetector{
  
  public MSERDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.MSER);
    PREFIX = "MSER";
  
  }
  
}
