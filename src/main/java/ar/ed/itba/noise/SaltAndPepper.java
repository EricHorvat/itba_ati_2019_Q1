package ar.ed.itba.noise;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

public class SaltAndPepper {

    //contamination in percentage, p is the salt probability
    public static PpmImage generateNoise(final ATIImage image, final double p) {
        if ( p < 0 || p > 1)
            throw new IllegalArgumentException("P a probability [0,1]");
        
        final int[] pixels = image.toRGB();
        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j = 0 ; j < image.getWidth() ; j++) {
            	double x = Math.random();
             
            	if (x < p) {
            		pixels[(i * image.getWidth() + j) * 3] = 255;
            		pixels[(i * image.getWidth() + j) * 3 + 1] = 255;
            		pixels[(i * image.getWidth() + j) * 3 + 2] = 255;
            	} else if (x > (1 - p)){
            		pixels[(i * image.getWidth() + j) * 3] = 0;
            		pixels[(i * image.getWidth() + j) * 3 + 1] = 0;
            		pixels[(i * image.getWidth() + j) * 3 + 2] = 0;
            	}
            }
        }
        return new PpmImage(pixels, image.getWidth(), image.getHeight());
    }
}
