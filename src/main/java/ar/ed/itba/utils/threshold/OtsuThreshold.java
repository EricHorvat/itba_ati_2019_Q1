package ar.ed.itba.utils.threshold;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.file.pixel.RGBPixel;
import ar.ed.itba.utils.ImageUtils;

import java.util.HashMap;
import java.util.Map;

public class OtsuThreshold {

    public static int threshold(final ATIImage image) {
        if (image instanceof PpmImage)
            throw new UnsupportedOperationException("Image must be gray");

        final Map<Integer, Double> relativeFreq = new HashMap<>();

        final byte[] pixels = image.getImage();

        for (int i = 0 ; i < pixels.length ; i++) {
            int value = pixels[i] & 0xFF;
            if (relativeFreq.containsKey(value))
                relativeFreq.put(value, relativeFreq.get(value) + 1);
            else
                relativeFreq.put(value, 1D);
        }

        for (Map.Entry<Integer, Double> entry : relativeFreq.entrySet())
            relativeFreq.put(entry.getKey(), entry.getValue() / pixels.length);

        final Map<Integer, Double> cumulativeFreq = new HashMap<>();

        for (final int grayLevel : relativeFreq.keySet()) {
            double sum = 0;
            for (int j = 0 ; j <= grayLevel ; j++) {
                if (relativeFreq.containsKey(j))
                    sum += relativeFreq.get(j);
            }
            cumulativeFreq.put(grayLevel, sum);
        }

        final Map<Integer, Double> cumulativeMean = new HashMap<>();
        double globalMean = 0;
        for (final int grayLevel : relativeFreq.keySet()) {
            double sum = 0;
            for (int j = 0 ; j <= grayLevel ; j++) {
                if (relativeFreq.containsKey(j))
                    sum += (j * relativeFreq.get(j));
            }
            cumulativeMean.put(grayLevel, sum);
            globalMean = sum;
        }

        final Map<Integer, Double> variance = new HashMap<>();

        for (final int grayLevel : relativeFreq.keySet()) {
            for (int j = 0 ; j <= grayLevel ; j++) {
                if (relativeFreq.containsKey(j) && cumulativeFreq.get(j) != 1)
                    variance.put(grayLevel, Math.pow(globalMean * cumulativeFreq.get(j) - cumulativeMean.get(j), 2)
                    /(cumulativeFreq.get(j) * (1 - cumulativeFreq.get(j))));
            }
        }

        double maxValue = variance.values().stream().mapToDouble(d -> d).max().getAsDouble();

        int count = 0;
        int sum = 0;
        for (final Integer grayLevel : relativeFreq.keySet()) {
            if (variance.get(grayLevel) == maxValue) {
                sum += grayLevel;
                count++;
            }
        }
        final int threshold = (int) Math.ceil((double) sum/count);
        System.out.println("Threshold is " + threshold);
        ImageUtils.threshold(image, threshold);
        return threshold;
    }

    public static void colorThreshold(final PpmImage image) {
        final int redThreshold = threshold(image.getRedChannel());
        final int blueThreshold = threshold(image.getBlueChannel());
        final int greenThreshold = threshold(image.getGreenChannel());

        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j = 0 ; j < image.getWidth() ; j++) {
                final RGBPixel rgbPixel = (RGBPixel) image.getPixel(i, j);
                final int newRed = (rgbPixel.getRed() & 0xFF) > redThreshold? 255 : 0;
                final int newGreen = (rgbPixel.getGreen() & 0xFF) > greenThreshold? 255 : 0;
                final int newBlue = (rgbPixel.getBlue() & 0xFF) > blueThreshold? 255 : 0;
                image.setPixel(i, j, new RGBPixel((byte) newRed, (byte) newGreen, (byte) newBlue));
            }
        }
    }
}
