package ar.ed.itba.utils.detection;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.ImageUtils;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ar.ed.itba.utils.ImageUtils.*;

public class Hough {
    private static final double EPSILON = 0.9;
    private static final double THETA_MAX = Math.PI / 2;
    private static final double THETA_MIN = - THETA_MAX;
    private static final Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> candidatesPoints = new HashMap<>();
    private static final PriorityQueue<Integer> storageMaxValues = new PriorityQueue<>(Collections.reverseOrder());

    public static PpmImage linearTransform(final ATIImage image, final int sinusoidalCount,
                                     final int thetaIntervals, final int phiIntervals) {
        int toPhi = (int) (Math.max(image.getWidth(), image.getHeight()) * Math.sqrt(2));
        return linearTransform(image, sinusoidalCount, THETA_MIN, THETA_MAX, thetaIntervals, -toPhi, toPhi, phiIntervals);
    }

    //Consider all possibles phis
    public static PpmImage linearTransform(final ATIImage image, final int sinusoidalCount, final int thetaIntervals) {
        int toPhi = (int) (Math.max(image.getWidth(), image.getHeight()) * Math.sqrt(2));
        return linearTransform(image, sinusoidalCount, THETA_MIN, THETA_MAX, thetaIntervals, -toPhi, toPhi, toPhi * 2);
    }

    private static PpmImage linearTransform(final ATIImage image, final int sinusoidalCount,
                                      final double fromTheta, final double toTheta, final int thetaIntervals,
                                      final int fromPhi, final int toPhi, final int phiIntervals) {
        if (thetaIntervals < 1|| phiIntervals < 1)
            throw new IllegalArgumentException("One of the intervals its not valid");

        double thetaStep = (toTheta - fromTheta) / thetaIntervals;
        int phiStep = (toPhi - fromPhi) / phiIntervals;

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

        final int[] imageArray = image.toRGB();
        for (final Pair<Integer, Integer> sinusoidal : sinusoidals) {
            final List<Pair<Integer, Integer>> points = candidatesPoints.get(new Pair<>(sinusoidal.getKey(), sinusoidal.getValue()));
//            Pair<Integer, Integer> min = points.get(0);
//            Pair<Integer, Integer> max = points.get(points.size() - 1);
//            ImageUtils.drawLine(imageArray, image.getWidth(), points.get(0), points.get(points.size() - 1));
            for (Pair<Integer, Integer> point : points) {
                imageArray[red(indexRGB(point.getKey(), point.getValue(), image.getWidth()))] = 255;
                imageArray[green(indexRGB(point.getKey(), point.getValue(), image.getWidth()))] = 0;
                imageArray[blue(indexRGB(point.getKey(), point.getValue(), image.getWidth()))] = 0;
            }
        }
        return new PpmImage(imageArray, image.getWidth(), image.getHeight());
    }

    private static void calculateStorageMatrix(final ATIImage image, final int[][] storageMatrix,
                                               final double fromTheta, final int storageX, final double thetaStep,
                                               final int fromPhi, final int storageY, final int phiStep) {
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

    private static boolean isSinusoidal(final int currentPixelX, final int currentPixelY, final double currentTheta, final int currentPhi) {
        return Math.abs(currentPhi - currentPixelX * Math.cos(currentTheta) - currentPixelY * Math.sin(currentTheta)) < EPSILON;
    }
}
