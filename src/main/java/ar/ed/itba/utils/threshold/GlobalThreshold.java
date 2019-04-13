package ar.ed.itba.utils.threshold;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.ImageUtils;
import javafx.util.Pair;

import java.util.List;

public class GlobalThreshold {

    private static final int deltaT = 2;

    static public int threshold(final ATIImage image) {
        int sum = 0;
        for (int i = 0 ; i < image.getImage().length ; i++)
            sum += (image.getImage()[i] & 0xFF);
        final int avg = sum / image.getImage().length;
        int threshold = avg;
        int prevThreshold = 0;
        while (Math.abs(threshold - prevThreshold) > deltaT) {
            prevThreshold = threshold;
            Pair<List<Integer>, List<Integer>> thresholdGroups = ImageUtils.thresholdGroups(image, threshold);
            threshold = (int) (0.5 * (groupMean(thresholdGroups.getKey(), image) + groupMean(thresholdGroups.getValue(), image)));
        }
        ImageUtils.threshold(image, threshold);
        return threshold;
    }

    static private double groupMean(final List<Integer> positions, final ATIImage image) {
        int sum = 0;
        for (final Integer p : positions)
            sum += (image.getImage()[p] & 0xFF);
        return (double) sum / positions.size();
    }
}
