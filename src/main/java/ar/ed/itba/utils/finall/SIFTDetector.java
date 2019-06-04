package ar.ed.itba.utils.finall;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;

public class SIFTDetector extends FinalDetector{
  
  public SIFTDetector() {
    KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.SIFT);
    PREFIX = "SIFT";
  
  }
  
}
