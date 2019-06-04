package ar.ed.itba.utils.finall;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.core.Scalar;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;

public abstract class FinalDetector {
  
  protected FeatureDetector KEYPOINT_DETECTOR;
  protected String PREFIX = "NOTPREFIX";
  
  private static final int COLOR = Highgui.CV_LOAD_IMAGE_COLOR;
  static{
    nu.pattern.OpenCV.loadLocally();
  }
  
  public MatOfKeyPoint detect(String imageFilePath){
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
    String resultA = saveKeyPoints(imageMat, keyPointsImage, "A");
    return keyPointsImage;
  }
  
  private static final Scalar KEYPOINT_COLOR = new Scalar(0, 255);
  
  private String saveKeyPoints(final Mat image, final MatOfKeyPoint keyPoints, final String fileName) {
    final Mat outputImage = new Mat(image.rows(), image.cols(), COLOR);
    Features2d.drawKeypoints(image, keyPoints, outputImage, KEYPOINT_COLOR, 0);
    final String outputFileName = "./output/" + PREFIX + fileName + ".png";
    Highgui.imwrite(outputFileName, outputImage);
    return outputFileName;
  }
  
}
