package ar.ed.itba.noise;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

public class SaltAndPepper {

    //contamination in percentage, p is the salt probability
    public static PpmImage generateNoise(final ATIImage image, final double contamination, final double p) {
        if (contamination < 0 || contamination > 100 || p < 0 || p > 1)
            throw new IllegalArgumentException("Contamination must be a percentage and p a probability");
        
        final int[] pixels = image.toRGB();
        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j = 0 ; j < image.getWidth() ; j++) {
                if (Math.random() < contamination/100) {
                    if (Math.random() < p) {
                        pixels[(i * image.getWidth() + j) * 3] = 255;
                        pixels[(i * image.getWidth() + j) * 3 + 1] = 255;
                        pixels[(i * image.getWidth() + j) * 3 + 2] = 255;
                    }
                    else {
                        pixels[(i * image.getWidth() + j) * 3] = 0;
                        pixels[(i * image.getWidth() + j) * 3 + 1] = 0;
                        pixels[(i * image.getWidth() + j) * 3 + 2] = 0;
                    }
                }
            }
        }
        return new PpmImage(pixels, image.getWidth(), image.getHeight());
    }
}
