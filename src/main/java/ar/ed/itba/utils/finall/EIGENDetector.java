package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class EIGENDetector extends FinalDetector{
  
  public EIGENDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.HARRIS);
    NAME = "EIGEN";
  }
  
}
