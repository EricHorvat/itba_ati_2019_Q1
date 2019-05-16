package ar.ed.itba.utils.detection;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import javafx.util.Pair;

import java.util.*;

import static ar.ed.itba.utils.ImageUtils.*;

public class HoughCircular {
  private static final double EPSILON = 0.9;
  private static final Map<Pair<Integer, Pair<Integer,Integer>> , List<Pair<Integer, Integer>>> candidatesPoints = new HashMap<>();
  private static final PriorityQueue<Integer> storageMaxValues = new PriorityQueue<>(Collections.reverseOrder());

  public static PpmImage transform(final ATIImage image, final int circumferenceCount,
                                   final int fromA, final int toA, final int aIntervals,
                                   final int fromB, final int toB, final int bIntervals,
                                   final int fromR, final int toR, final int rStep) {

    if (aIntervals < 1|| bIntervals < 1 || rStep < 1)
      throw new IllegalArgumentException("One of the intervals its not valid");

    if (fromA < 0 || toA > image.getWidth())
      throw new IllegalArgumentException("a is out of bounds");

    if (fromB < 0 || toB > image.getHeight())
      throw new IllegalArgumentException("b is out of bounds");

    final double diagonal = Math.sqrt(Math.pow(image.getWidth(),2) + Math.pow(image.getHeight(),2));
    if (fromR < 0 || toR > diagonal)
      throw new IllegalArgumentException("r is out of bounds");

    double aStep = Math.round((double) (toA - fromA) / aIntervals);
    double bStep = Math.round((double) (toB - fromB) / bIntervals);
    double rIntervals = Math.round(toR - fromR) / rStep;

    final Pair<Integer, Pair<Integer, Integer>> storageMatrixDim = new Pair<>(aIntervals + 1, new Pair<>(bIntervals + 1, (int)rIntervals + 1));
    final int[][][] storageMatrix = new int[storageMatrixDim.getKey()][storageMatrixDim.getValue().getKey()][storageMatrixDim.getValue().getValue()];
    for (int i = 0 ; i < storageMatrixDim.getKey() ; i++) {
      for (int j = 0 ; j < storageMatrixDim.getValue().getKey() ; j++)
        for (int k = 0 ; k < storageMatrixDim.getValue().getValue() ; k++) {
          calculateStorageMatrix(image, storageMatrix, fromA, i, aStep, fromB, j, bStep, fromR, k, rStep);
        }
    }
    
    //take top N storage values
    final Set<Pair<Integer, Pair<Integer,Integer>>> circumferences = new HashSet<>();
    for (int l = 0 ; l < circumferenceCount ; l++) {
      for (int i = 0; i < storageMatrixDim.getKey(); i++) {
        for (int j = 0; j < storageMatrixDim.getValue().getKey(); j++) {
          for (int k = 0; k < storageMatrixDim.getValue().getValue(); k++) {
            if (!storageMaxValues.isEmpty() && storageMatrix[i][j][k] == storageMaxValues.peek()
              && !circumferences.contains(new Pair<>(i, new Pair<>(j,k)))) {
              storageMaxValues.poll();
              circumferences.add(new Pair<>(i, new Pair<>(j,k)));
              //search for next sinusoidal
              k = storageMatrixDim.getValue().getValue();
              j = storageMatrixDim.getValue().getKey();
              i = storageMatrixDim.getKey();
            }
          }
        }
      }
    }
    
    //IntStream.range(0,sinusoidalCount).map(i -> storageMaxValues.poll()).map(j -> findValue(j))
    
    final int[] imageArray = new int[image.getWidth() * image.getHeight() * 3];
    for (final Pair<Integer, Pair<Integer,Integer>> circumference : circumferences) {
      final List<Pair<Integer, Integer>> points = candidatesPoints.get(new Pair<>(circumference.getKey(), new Pair<>(circumference.getValue().getKey(),circumference.getValue().getValue())));
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
  
  private static void calculateStorageMatrix(final ATIImage image, final int[][][] storageMatrix,
                                             final double fromA, final int storageX, final double aStep,
                                             final double fromB, final int storageY, final double bStep,
                                             final double fromR, final int storageZ, final double rStep) {
    final int[] imageArray = image.toRGB();
    final Pair<Integer, Pair<Integer,Integer>> storageXYZ = new Pair<>(storageX, new Pair<>(storageY, storageZ));
    candidatesPoints.put(storageXYZ, new LinkedList<>());
    for (int i = 0 ; i < image.getHeight() ; i++) {
      for (int j = 0 ; j < image.getWidth() ; j++) {
        // image must be binary
        if (isCircumference(j, i, fromA + storageX * aStep,
            fromB + storageY * bStep, fromR + storageZ * rStep)) {
          if (imageArray[indexRGB(i, j, image.getWidth())] == 255)
            storageMatrix[storageX][storageY][storageZ]++;
          candidatesPoints.get(storageXYZ).add(new Pair<>(i, j));
        }
      }
    }
    storageMaxValues.add(storageMatrix[storageX][storageY][storageZ]);
  }
  
  private static boolean isCircumference(final int currentPixelX, final int currentPixelY, final double currentA, final double currentB, final double currentR) {
    
    double ans = Math.abs(Math.pow(currentR, 2) - Math.pow(currentPixelX - currentA,2) - Math.pow(currentPixelY - currentB,2));
    return ans < EPSILON;
  }
}
