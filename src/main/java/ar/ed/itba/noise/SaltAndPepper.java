package ar.ed.itba.noise;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import static ar.ed.itba.utils.ImageUtils.indexRGB;

public class SaltAndPepper {

    //contamination in percentage, p is the salt probability
    public static PpmImage generateNoise(final ATIImage image, final double p) {
        if ( p < 0 || p > 1)
            throw new IllegalArgumentException("P a probability [0,1]");
        
        final int[] pixels = image.toRGB();
        int count = 0;
        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j = 0 ; j < image.getWidth() ; j++) {
            	double x = Math.random();
              int indexRGB = indexRGB(i,j,image.getWidth());
            	// 0.05 > x
            	if (p > x) {
            		pixels[indexRGB] = 255;
            		pixels[indexRGB + 1] = 255;
            		pixels[indexRGB + 2] = 255;
            		count++;
            	} else if (x > (1 - p)){
            		// x > 0.95
            		pixels[indexRGB] = 0;
            		pixels[indexRGB + 1] = 0;
            		pixels[indexRGB + 2] = 0;
            		count++;
            	}
            }
        }
        System.out.println("Salt&Pepper Noise applied over " + count+ " pixel, from an image of " +
	        image.getWidth()*image.getHeight() + "pixels.");
        return new PpmImage(pixels, image.getWidth(), image.getHeight());
    }
}
