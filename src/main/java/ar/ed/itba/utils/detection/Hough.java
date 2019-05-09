package ar.ed.itba.utils.detection;

import ar.ed.itba.file.image.ATIImage;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Hough {
    private static final double EPSILON = 0.9;
    private static final double GAMMA_MIN = -90;
    private static final double GAMMA_MAX = 90;
    private static final Map<Integer, List<Pair<Integer, Integer>>> candidatesPoints = new HashMap<>();

    public static void transform(final ATIImage image, final int sinusoidalThreshold,
                                 final int fromGamma, final int toGamma, final int gammaStep,
                                 final int fromPhi, final int toPhi, final int phiStep) {
        // [min, max] in each case
        if ((toGamma - fromGamma) % gammaStep != 0 || (toPhi - fromPhi) % phiStep != 0)
            throw new IllegalArgumentException("One of the steps is not valid for their interval");

        if (fromGamma < GAMMA_MIN || toGamma > GAMMA_MAX)
            throw new IllegalArgumentException("Gamma is out of bounds");

        final int d = Math.max(image.getWidth(), image.getHeight());
        if (fromPhi < - Math.sqrt(2) * d || toPhi > Math.sqrt(2) * d)
            throw new IllegalArgumentException("Phi is out of bounds");

        final Pair<Integer, Integer> storageMatrixDim = new Pair<>(((toGamma - fromGamma) / gammaStep) + 1, ((toPhi - fromPhi) / phiStep) + 1);
        final int storageMatrix[][] = new int[storageMatrixDim.getKey()][storageMatrixDim.getValue()];

        for (int i = 0 ; i < storageMatrixDim.getKey() ; i++) {
            for (int j = 0 ; j < storageMatrixDim.getValue() ; j++)
                findSinusoidalPoints(image, storageMatrix, fromGamma, i, gammaStep, fromPhi, j, phiStep);
        }

        LinkedList<Pair<Integer, Integer>> curves = new LinkedList<>();
        for (int i = 0 ; i < storageMatrixDim.getKey() ; i++) {
            for (int j = 0 ; j < storageMatrixDim.getValue() ; j++) {
                if (storageMatrix[i][j] > sinusoidalThreshold)
                    curves.add(new Pair(i, j));
            }
        }

        for (final Pair<Integer, Integer> curve : curves) {
            final int hash = curve.getKey() * 21 + curve.getValue();
            final List<Pair<Integer, Integer>> points = candidatesPoints.get(hash);
            points.stream().min((p1, p2) -> )
        }
    }

    private static void findSinusoidalPoints(final ATIImage image, final int[][] storageMatrix,
                                             final int fromGamma, final int storageX, final int gammaStep,
                                             final int fromPhi, final int storageY, final int phiStep) {
        final int[] imageArray = image.toRGB();
        final int hash = storageX * 21 + storageY;
        candidatesPoints.put(hash, new LinkedList<>());
        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j = 0 ; j < image.getWidth() ; j++) {
                // image must be binary
                if (imageArray[(i * image.getWidth() + j) * 3] == 255
                        && isSinusoidal(i, j, fromGamma + storageX * gammaStep,
                        fromPhi + storageY * phiStep)) {
                    storageMatrix[storageX][storageY]++;
                    candidatesPoints.get(hash).add(new Pair<>(storageX, storageY));
                }
            }
        }
    }

    private static boolean isSinusoidal(final int currentPixelX, final int currentPixelY, final int currentGamma, final int currentPhi) {
        return Math.abs(currentPhi - currentPixelX * Math.cos(currentGamma) - currentPixelY * Math.sin(currentGamma)) < EPSILON;
    }
}
