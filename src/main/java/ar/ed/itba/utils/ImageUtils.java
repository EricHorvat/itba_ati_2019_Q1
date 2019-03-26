package ar.ed.itba.utils;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public final class ImageUtils {
	
	private ImageUtils(){}
	
	public static BufferedImage deepCopy(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}
	
	public static int negateRGB(int rgb){
		Color c = new Color(rgb);
		c = new Color(255-c.getRed(),255-c.getBlue(),255-c.getGreen());
		return c.getRGB();
	}
	
	public static int sumRGB(Collection<Integer> rgbs, int min){
		Optional<Integer> result = rgbs.stream().reduce((int1, int2) -> int1 + int2);
		return result.map(integer -> (integer - min)/(rgbs.size() - min)).orElse(0);
	}
	
	public static int sumRGB(Collection<Integer> rgbs){
		return sumRGB(rgbs, 0);
	}
	
	public static int lessRGB(Integer rgb1, Integer rgb2){
		ArrayList<Integer>  a = new ArrayList<>();
		a.add(rgb1);
		a.add(rgb2 - 255);
		return sumRGB(a, -255);
	}

	public static void increaseContrast(final ATIImage image, final int x1, final int y1, final int x2, final int y2) {

		int inputSegmentStart;
		int inputSegmentEnd;
		int outputSegmentStart;
		int outputSegmentEnd;

		final byte[] pixels = image.getImage();
		for (int i = 0 ; i < pixels.length ; i++) {
			final int pixel = pixels[i] & 0xFF;
			if (pixel < x1) {
				inputSegmentStart = 0;
				inputSegmentEnd = x1;
				outputSegmentStart = 0;
				outputSegmentEnd = y1;
			}
			else if (pixel < x2) {
				inputSegmentStart = x1;
				inputSegmentEnd = x2;
				outputSegmentStart = y1;
				outputSegmentEnd = y2;
			}
			else {
				inputSegmentStart = x2;
				inputSegmentEnd = 255;
				outputSegmentStart = y2;
				outputSegmentEnd = 255;
			}
			pixels[i] = (byte) linearMapping(pixel, inputSegmentStart, inputSegmentEnd, outputSegmentStart, outputSegmentEnd);
		}
	}

	private static int linearMapping(final int grayInput, final int inputSegmentStart, final int inputSegmentEnd,
									 final int outputSegmentStart, final int outputSegmentEnd) {
		return ((grayInput - inputSegmentStart) * (outputSegmentEnd - outputSegmentStart) / (inputSegmentEnd - inputSegmentStart)) + outputSegmentStart;
	}

	public static void threshold(final ATIImage image, final int threshold) {

		final byte[] pixels = image.getImage();
		for (int i = 0 ; i < pixels.length ; i++) {
			final int pixel = pixels[i] & 0xFF;
			if (pixel <= threshold)
				pixels[i] = 0;
			else
				pixels[i] = (byte) 255;
		}
	}

	public static PpmImage multiply(final ATIImage image, final double value) {
		final int[] pixels = image.toRGB();

		for (int i = 0 ; i < pixels.length ; i++)
			pixels[i] = (int) (pixels[i] * value);

		dynamicRangeCompression(pixels);
		return new PpmImage(pixels, image.getWidth(), image.getHeight());
	}

	public static void gammaPower(final ATIImage image, final double gamma) {
		if (gamma <= 0 || gamma > 2 || gamma == 1)
			throw new IllegalArgumentException("gamma must be between 0 and 2 and cant be 1");

		final byte[] pixels = image.getImage();
		final double c = Math.pow(255, 1 - gamma);
		for (int i = 0 ; i < pixels.length ; i++)
			pixels[i] = (byte) (c * Math.pow((pixels[i] & 0xFF), gamma));
	}

	public static void dynamicRangeCompression(final int[] rgbImage) {
		int maxValue = 0;
		for (int i = 0 ; i < rgbImage.length ; i++) {
			final int currentValue = rgbImage[i];
			if (currentValue > maxValue)
				maxValue = currentValue;
		}

		final double c = 255 / Math.log(1 + maxValue);

		for (int i = 0 ; i < rgbImage.length ; i++)
			rgbImage[i] = (int) (c * Math.log(1 + (rgbImage[i] & 0xFF)));
	}
}
