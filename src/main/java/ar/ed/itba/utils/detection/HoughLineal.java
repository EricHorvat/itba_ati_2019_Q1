package ar.ed.itba.utils.detection;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import javafx.util.Pair;

import java.util.*;

import static ar.ed.itba.utils.ImageUtils.*;

public class HoughLineal {
  private static final double EPSILON = 0.9;
  private static final double THETA_MAX = Math.PI / 2;
  private static final double THETA_MIN = - THETA_MAX;
  private static final Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> candidatesPoints = new HashMap<>();
  private static final PriorityQueue<Integer> storageMaxValues = new PriorityQueue<>(Collections.reverseOrder());
  
  public static PpmImage transform(final ATIImage image, final int sinusoidalCount,
                                   final int thetaIntervals,
                                   final int phiIntervals) {
    int toPhi = (int) (Math.max(image.getWidth(), image.getHeight()) * Math.sqrt(2) * 1);
    return transform(image, sinusoidalCount, THETA_MIN, THETA_MAX, thetaIntervals, -toPhi, toPhi, phiIntervals);
  }

  //Consider all possibles phis
  public static PpmImage transform(final ATIImage image, final int sinusoidalCount, final int thetaIntervals) {
    int toPhi = (int) (Math.max(image.getWidth(), image.getHeight()) * Math.sqrt(2));
    return transform(image, sinusoidalCount, THETA_MIN, THETA_MAX, thetaIntervals, -toPhi, toPhi, toPhi * 2);
  }
  
  private static PpmImage transform(final ATIImage image, final int sinusoidalCount,
                                   final double fromTheta, final double toTheta, final int thetaIntervals,
                                   final int fromPhi, final int toPhi, final int phiIntervals) {

    if (thetaIntervals < 1|| phiIntervals < 1)
      throw new IllegalArgumentException("One of the intervals its not valid");
    
    if (fromTheta < THETA_MIN || toTheta > THETA_MAX)
      throw new IllegalArgumentException("Theta is out of bounds");
    
    final int d = Math.max(image.getWidth(), image.getHeight());
    if (fromPhi < - Math.sqrt(2) * d || toPhi > Math.sqrt(2) * d)
      throw new IllegalArgumentException("Phi is out of bounds");
    
    double thetaStep = (toTheta - fromTheta) / thetaIntervals;
    double phiStep = Math.round((double) (toPhi - fromPhi) / phiIntervals);
    
    final Pair<Integer, Integer> storageMatrixDim = new Pair<>(thetaIntervals + 1, phiIntervals + 1);
    final int[][] storageMatrix = new int[storageMatrixDim.getKey()][storageMatrixDim.getValue()];
    for (int i = 0 ; i < storageMatrixDim.getKey() ; i++) {
      for (int j = 0 ; j < storageMatrixDim.getValue() ; j++)
        calculateStorageMatrix(image, storageMatrix, fromTheta, i, thetaStep, fromPhi, j, phiStep);
    }
    
    //take top N storage values
    final Set<Pair<Integer, Integer>> sinusoidals = new HashSet<>();
    for (int k = 0 ; k < sinusoidalCount ; k++) {
      for (int i = 0; i < storageMatrixDim.getKey(); i++) {
        for (int j = 0; j < storageMatrixDim.getValue(); j++) {
          if (!storageMaxValues.isEmpty() && storageMatrix[i][j] == storageMaxValues.peek()
            && !sinusoidals.contains(new Pair<>(i, j))) {
            storageMaxValues.poll();
            sinusoidals.add(new Pair<>(i, j));
            //search for next sinusoidal
            j = storageMatrixDim.getValue();
            i = storageMatrixDim.getKey();
          }
        }
      }
    }
    
    //IntStream.range(0,sinusoidalCount).map(i -> storageMaxValues.poll()).map(j -> findValue(j))
    
    final int[] imageArray = image.toRGB();
    for (final Pair<Integer, Integer> sinusoidal : sinusoidals) {
      final List<Pair<Integer, Integer>> points = candidatesPoints.get(new Pair<>(sinusoidal.getKey(), sinusoidal.getValue()));
      for (Pair<Integer, Integer> point : points) {
        imageArray[red(indexRGB(point.getKey(), point.getValue(), image.getWidth()))] = 255;
        imageArray[green(indexRGB(point.getKey(), point.getValue(), image.getWidth()))] = 0;
        imageArray[blue(indexRGB(point.getKey(), point.getValue(), image.getWidth()))] = 0;
      }
    }
    return new PpmImage(imageArray, image.getWidth(), image.getHeight());
  }
    
    /*private Pair<Integer, Integer> findValue(int[][] dict, int value){
      Arrays.stream(dict).map(arr -> new HashMap<>arr, Arrays.stream(arr).max().orElse(0)).collect(Collectors.toList());
      return new Pair<>(0,0);
    }*/
  
  private static void calculateStorageMatrix(final ATIImage image, final int[][] storageMatrix,
                                             final double fromTheta, final int storageX, final double thetaStep,
                                             final double fromPhi, final int storageY, final double phiStep) {
    final int[] imageArray = image.toRGB();
    final Pair<Integer, Integer> storageXY = new Pair<>(storageX, storageY);
    candidatesPoints.put(storageXY, new LinkedList<>());
    for (int i = 0 ; i < image.getHeight() ; i++) {
      for (int j = 0 ; j < image.getWidth() ; j++) {
        // image must be binary
        if (isSinusoidal(j, i, fromTheta + storageX * thetaStep,
          fromPhi + storageY * phiStep)) {
          if (imageArray[indexRGB(i,j,image.getWidth())] == 255)
            storageMatrix[storageX][storageY]++;
          candidatesPoints.get(storageXY).add(new Pair<>(i, j));
        }
      }
    }
    storageMaxValues.add(storageMatrix[storageX][storageY]);
  }
  
  private static boolean isSinusoidal(final int currentPixelX, final int currentPixelY, final double currentTheta, final double currentPhi) {
    return Math.abs(currentPhi - currentPixelX * Math.cos(currentTheta) - currentPixelY * Math.sin(currentTheta)) < EPSILON;
  }
}
