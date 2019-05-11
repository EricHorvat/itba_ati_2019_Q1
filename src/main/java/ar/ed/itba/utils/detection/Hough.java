package ar.ed.itba.utils.detection;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.ImageUtils;
import javafx.util.Pair;

import java.util.*;

public class Hough {
    private static final double EPSILON = 0.9;
    private static final double THETA_MIN = 0;
    private static final double THETA_MAX = 360;
    private static final Map<Pair<Integer, Integer>, List<Pair<Integer, Integer>>> candidatesPoints = new HashMap<>();
    private static final PriorityQueue<Integer> storageMaxValues = new PriorityQueue<>(Collections.reverseOrder());

    public static PpmImage transform(final ATIImage image, final int sinusoidalCount,
                                 final int fromTheta, final int toTheta, final int thetaStep,
                                 final int fromPhi, final int toPhi, final int phiStep) {
        // [min, max] in each case
        if ((toTheta - fromTheta) % thetaStep != 0 || (toPhi - fromPhi) % phiStep != 0)
            throw new IllegalArgumentException("One of the steps is not valid for their interval");

        if (fromTheta < THETA_MIN || toTheta > THETA_MAX)
            throw new IllegalArgumentException("Theta is out of bounds");

        final int d = Math.max(image.getWidth(), image.getHeight());
        if (fromPhi < - Math.sqrt(2) * d || toPhi > Math.sqrt(2) * d)
            throw new IllegalArgumentException("Phi is out of bounds");

        final Pair<Integer, Integer> storageMatrixDim = new Pair<>(((toTheta - fromTheta) / thetaStep), ((toPhi - fromPhi) / phiStep));
        final int storageMatrix[][] = new int[storageMatrixDim.getKey()][storageMatrixDim.getValue()];
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
                        sinusoidals.add(new Pair(i, j));
                        //search for next sinusoidal
                        j = storageMatrixDim.getValue();
                        i = storageMatrixDim.getKey();
                    }
                }
            }
        }

        final int[] imageArray = new int[image.getHeight() * image.getWidth() * 3];
        for (final Pair<Integer, Integer> sinusoidal : sinusoidals) {
            final List<Pair<Integer, Integer>> points = candidatesPoints.get(new Pair<>(sinusoidal.getKey(), sinusoidal.getValue()));
            Pair<Integer, Integer> min = points.get(0);
            Pair<Integer, Integer> max = points.get(points.size() - 1);
            ImageUtils.drawLine(imageArray, image.getWidth(), points.get(0), points.get(points.size() - 1));
        }

        return new PpmImage(imageArray, image.getWidth(), image.getHeight());
    }

    private static void calculateStorageMatrix(final ATIImage image, final int[][] storageMatrix,
                                               final int fromTheta, final int storageX, final int thetaStep,
                                               final int fromPhi, final int storageY, final int phiStep) {
        final int[] imageArray = image.toRGB();
        final Pair<Integer, Integer> storageXY = new Pair<>(storageX, storageY);
        candidatesPoints.put(storageXY, new LinkedList<>());
        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j = 0 ; j < image.getWidth() ; j++) {
                // image must be binary
                if (isSinusoidal(j - image.getWidth()/2, image.getHeight()/2 - i, fromTheta + storageX * thetaStep,
                        fromPhi + storageY * phiStep)) {
                    if (imageArray[(i * image.getWidth() + j) * 3] == 255)
                        storageMatrix[storageX][storageY]++;
                    candidatesPoints.get(storageXY).add(new Pair<>(i, j));
                }
            }
        }
        storageMaxValues.add(storageMatrix[storageX][storageY]);
    }

    private static boolean isSinusoidal(final int currentPixelX, final int currentPixelY, final int currentTheta, final int currentPhi) {
        return Math.abs(currentPhi - currentPixelX * Math.cos(currentTheta) - currentPixelY * Math.sin(currentTheta)) < EPSILON;
    }
}
