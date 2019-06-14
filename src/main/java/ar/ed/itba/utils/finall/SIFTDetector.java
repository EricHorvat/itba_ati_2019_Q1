package ar.ed.itba.utils.finall;

import org.opencv.features2d.FeatureDetector;

public class SIFTDetector extends FinalDetector{
  
  public SIFTDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.SIFT);
    PREFIX = "SIFT";
  
  }
  
}
