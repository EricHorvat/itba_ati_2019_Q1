package ar.ed.itba.utils.detection;

/* Based on http://dummyscodes.blogspot.com/2015/12/using-siftsurf-for-object-recognition.html
 * Mat: Image container that can be used in many methods such as sift:
 *     This class can be divided in two main data parts:
 *     - a header (containing information the matrix, e.g. its size, how and where is stored).
 *     - a pointer to the actual matrix.
 */

import org.opencv.core.*;
import org.opencv.features2d.*;
import org.opencv.highgui.Highgui;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SIFT {
  
  private static SIFT instance;
  
  public static SIFT getInstance() {
    if (instance == null){
      instance = new SIFT();
    }
    return instance;
  }
  
  private SIFT() {
    nu.pattern.OpenCV.loadLocally();
  }
  
  // Parameter to read color images (as gray can be taken as colored use the same parameter)
  private static final int COLOR = Highgui.CV_LOAD_IMAGE_COLOR;
  
  private static final FeatureDetector KEYPOINT_DETECTOR = FeatureDetector.create(FeatureDetector.SIFT);
  private static final DescriptorExtractor DESCRIPTOR_EXTRACTOR = DescriptorExtractor.create(DescriptorExtractor.SIFT);
  private static final DescriptorMatcher DESCRIPTOR_MATCHER = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);
  
  
  private void applyWith(final String imageFileA, final String imageFileB,
                         final int matchingDistance, final double matchingPercentage){
    // Saving files! TODO VISUALIZATION OF PHOTOS!
    
    final Mat imageA = Highgui.imread(imageFileA, COLOR);
    final Mat imageB = Highgui.imread(imageFileB, COLOR);
    
    // Calculate keypoints
    MatOfKeyPoint keyPointsImageA = new MatOfKeyPoint();
    KEYPOINT_DETECTOR.detect(imageA, keyPointsImageA);
    MatOfKeyPoint keyPointsImageB = new MatOfKeyPoint();
    KEYPOINT_DETECTOR.detect(imageB, keyPointsImageB);
    // Extract descriptors
    final MatOfKeyPoint descriptorsImageA = new MatOfKeyPoint();
    DESCRIPTOR_EXTRACTOR.compute(imageA, keyPointsImageA, descriptorsImageA);
    final MatOfKeyPoint descriptorsImageB = new MatOfKeyPoint();
    DESCRIPTOR_EXTRACTOR.compute(imageB, keyPointsImageB, descriptorsImageB);
  
    // Match descriptor of the images set, using the second image descriptors as the one to train.
    final MatOfDMatch matchesMat = new MatOfDMatch();
    DESCRIPTOR_MATCHER.match(descriptorsImageA, descriptorsImageB, matchesMat);
    
    List<DMatch> matchesList = matchesMat.toList();
    matchesList = matchesList.stream().filter(match -> matchingDistance > match.distance).collect(toList());
    
    // Show matches! (Number of matches is  matrix size)
    int keypointsA = keyPointsImageA.cols() * keyPointsImageA.rows();
    int keypointsB = keyPointsImageB.cols() * keyPointsImageB.rows();
    System.out.println("keyPoints A: " + keypointsA);
    System.out.println("keyPoints B: " + keypointsB);
    System.out.println("filteredMatches: " + matchesList.size());
  
    if (Math.min(keypointsA, keypointsB) * matchingPercentage < matchesList.size()){
      System.out.println("The images match");
    } else {
      System.out.println("The images don't match");
    }
  
    // Saving files! TODO Open them and show!
    String resultA = saveKeyPoints(imageA, keyPointsImageA, "A");
    String resultB = saveKeyPoints(imageB, keyPointsImageB, "B");
    String matching = saveMatches(imageA, keyPointsImageA, imageB, keyPointsImageB, matchesList);
  
  }
  
  private static final Scalar KEYPOINT_COLOR = new Scalar(0, 255);
  
  private String saveKeyPoints(final Mat image, final MatOfKeyPoint keyPoints, final String fileName) {
    final Mat outputImage = new Mat(image.rows(), image.cols(), COLOR);
    Features2d.drawKeypoints(image, keyPoints, outputImage, KEYPOINT_COLOR, 0);
    final String outputFileName = "./output/" + fileName + ".png";
    Highgui.imwrite(outputFileName, outputImage);
    return outputFileName;
  }
  
  private static final Scalar MATCH_COLOR = new Scalar(255, 0, 255);
  
  private String saveMatches(final Mat imageA, final MatOfKeyPoint keyPointsImageA,
                           final Mat imageB, final MatOfKeyPoint keyPointsImageB,
                           final List<DMatch> filteredMatchesList) {
    final Mat outputImage = new Mat(imageA.rows() + imageB.rows(),
      imageA.cols() + imageB.cols(),
      COLOR);
    final MatOfDMatch filteredMatches = new MatOfDMatch();
    filteredMatches.fromList(filteredMatchesList);
    Features2d.drawMatches(imageA, keyPointsImageA, imageB, keyPointsImageB, filteredMatches,
      outputImage, MATCH_COLOR,
      KEYPOINT_COLOR, new MatOfByte(), 2);
    final String outputFileName = "./output/matches.png";
    Highgui.imwrite(outputFileName, outputImage);
    return outputFileName;
  }
  
}
