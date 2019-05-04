package ar.ed.itba.utils;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.util.*;
import java.util.List;

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
	
	public static PpmImage sum(final ATIImage image1, final ATIImage image2) {
		if (image1.getWidth() < image2.getWidth() || image1.getHeight() < image2.getHeight())
			throw new IllegalArgumentException("Image 2 must be contained in image 1");
		
		final int[] pixels2 = image2.toRGB();
		
		return sum(image1,pixels2);
	}
	
	public static PpmImage sum(final ATIImage image, final int[] modifier){
		if (lengthRGB(image.getWidth(), image.getHeight()) != modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		final int[] pixels = image.toRGB();
		return new PpmImage(sum(pixels,modifier,image.getWidth(),image.getWidth()), image.getWidth(), image.getHeight());
	}
  
  public static int[] sum(final int[] image, final int[] modifier, int width, int height){
    if (lengthRGB(width, height) != modifier.length)
      throw new IllegalArgumentException("modifier must have width*height*3 length");
  
    final int[] pixels = new int[lengthRGB(width, height)];
    
    for (int i = 0 ; i < width ; i++) {
      for (int j = 0 ; j < height ; j++) {
        int indexRGB = indexRGB(i,j,width) ;
        pixels[red(indexRGB)] = image[red(indexRGB)] + modifier[red(indexRGB)];
        pixels[green(indexRGB)] =  image[green(indexRGB)] + modifier[green(indexRGB)];
        pixels[blue(indexRGB)] = image[blue(indexRGB)] + modifier[blue(indexRGB)];
      }
    }
    normalize(pixels, width);
    return pixels;
  }
	
	public static PpmImage avg(final ATIImage image1, final ATIImage image2) {
		if (image1.getWidth() < image2.getWidth() || image1.getHeight() < image2.getHeight())
			throw new IllegalArgumentException("Image 2 must be contained in image 1");
		
		final int[] pixels2 = image2.toRGB();
		
		return avg(image1,pixels2);
	}
	
	public static PpmImage avg(final ATIImage image, final int[] modifier) {
		if (lengthRGB(image.getWidth(), image.getHeight()) != modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		final int[] pixels = image.toRGB();
		
		return new PpmImage(avg(pixels,modifier, image.getWidth(), image.getHeight()),image.getWidth(), image.getHeight());
	}
	
	public static int[] avg(final int[] image, final int[] modifier, int width, int height){
		if (lengthRGB(width,height) != modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
    
    final int[] pixels = new int[lengthRGB(width, height)];
		
		for (int i = 0 ; i < width ; i++) {
			for (int j = 0 ; j < height ; j++) {
				int indexRGB = indexRGB(i,j,width);
				pixels[red(indexRGB)] = (image[red(indexRGB)] + modifier[red(indexRGB)]) / 2;
				pixels[green(indexRGB)] = (image[green(indexRGB)] + modifier[green(indexRGB)]) / 2;
				pixels[blue(indexRGB)] = (image[blue(indexRGB)] + modifier[blue(indexRGB)]) / 2;
			}
		}
		return pixels;
	}
	
	public static PpmImage mod2(final ATIImage image1, final ATIImage image2) {
		if (image1.getWidth() < image2.getWidth() || image1.getHeight() < image2.getHeight())
			throw new IllegalArgumentException("Image 2 must be contained in image 1");
		
		final int[] pixels2 = image2.toRGB();
		
		return mod2(image1,pixels2);
	}
	
	public static PpmImage mod2(final ATIImage image, final int[] modifier) {
		if (lengthRGB(image.getWidth(), image.getHeight())!= modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		final int[] pixels = image.toRGB();
		
		return new PpmImage(mod2(pixels,modifier, image.getWidth(), image.getHeight()),image.getWidth(), image.getHeight());
	}
  
  public static int[] mod2(final int[] image, final int[] modifier, int width, int height){
	  return mod2(image, modifier, width, height, true);
	  
  }
  
  public static int[] mod2(final int[] image, final int[] modifier, int width, int height, boolean normalize){
		if (lengthRGB(width, height) != modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
    
    final int[] pixels = new int[lengthRGB(width, height)];
		
		for (int i = 0 ; i < width ; i++) {
			for (int j = 0 ; j < height ; j++) {
				int indexRGB = indexRGB(i,j,width);
				pixels[red(indexRGB)] = (int) (Math.sqrt(Math.pow(image[red(indexRGB)],2) + Math.pow(modifier[red(indexRGB)],2)));
				pixels[green(indexRGB)] = (int) (Math.sqrt(Math.pow(image[green(indexRGB)],2) + Math.pow(modifier[green(indexRGB)],2)));
				pixels[blue(indexRGB)] = (int) (Math.sqrt(Math.pow(image[blue(indexRGB)],2) + Math.pow(modifier[blue(indexRGB)],2)));
			}
		}
		if(normalize){
		  return normalize(pixels, width);
		} else {
		  return pixels;
    }
	}
	
	public static PpmImage max(final ATIImage image1, final ATIImage image2) {
		if (image1.getWidth() < image2.getWidth() || image1.getHeight() < image2.getHeight())
			throw new IllegalArgumentException("Image 2 must be contained in image 1");
		
		final int[] pixels2 = image2.toRGB();
		
		return max(image1,pixels2);
	}
	
	public static PpmImage max(final ATIImage image, final int[] modifier) {
		if (lengthRGB(image.getWidth(), image.getHeight()) != modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		final int[] pixels = image.toRGB();
		
		return new PpmImage(max(pixels,modifier, image.getWidth(), image.getHeight()),image.getWidth(), image.getHeight());
	}

  public static int[] max(final int[] image, final int[] modifier, int width, int height ) {
	  return max(image, modifier, width, height, true);
  }
  
  public static int[] max(final int[] image, final int[] modifier, int width, int height, boolean normalize){
		if (image.length != modifier.length)
			throw new IllegalArgumentException("modifier must have same length as image array");
		if (lengthRGB(width, height) != modifier.length){
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		}
		int pixels[] = new int[lengthRGB(width, height)];
		
		for (int i = 0 ; i < width ; i++) {
			for (int j = 0 ; j < height ; j++) {
				int indexRGB = indexRGB(i,j,width);
				pixels[red(indexRGB)] = Math.max(image[red(indexRGB)], modifier[red(indexRGB)]);
				pixels[green(indexRGB)] = Math.max(image[green(indexRGB)], modifier[green(indexRGB)]);
				pixels[blue(indexRGB)] = Math.max(image[blue(indexRGB)], modifier[blue(indexRGB)]);
			}
		}
		if (normalize){
		  return normalize(pixels,width);
    }
		return pixels;
	}
	
	public static PpmImage min(final ATIImage image1, final ATIImage image2) {
		if (image1.getWidth() < image2.getWidth() || image1.getHeight() < image2.getHeight())
			throw new IllegalArgumentException("Image 2 must be contained in image 1");
		
		final int[] pixels2 = image2.toRGB();
		
		return min(image1,pixels2);
	}
	
	public static PpmImage min(final ATIImage image, final int[] modifier) {
		if (lengthRGB(image.getWidth(), image.getHeight()) != modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		final int[] pixels = image.toRGB();
		
		return new PpmImage(normalize(min(pixels,modifier, image.getWidth(), image.getHeight()), image.getWidth()),image.getWidth(), image.getHeight());
	}
  
  public static int[] min(final int[] image, final int[] modifier, int width, int height ) {
	  return min(image, modifier, width, height, true);
  }
  
  public static int[] min(final int[] image, final int[] modifier, int width, int height, boolean normalize){
		if (lengthRGB(width,height)!= modifier.length)
			throw new IllegalArgumentException("modifier must have width*height*3 length");
		
		int pixels[] = new int[lengthRGB(width,height)];
		
		for (int i = 0 ; i < width ; i++) {
			for (int j = 0 ; j < height; j++) {
				int indexRGB = indexRGB(i,j,width);
        pixels[red(indexRGB)] = Math.min(image[red(indexRGB)], modifier[red(indexRGB)]);
        pixels[green(indexRGB)] = Math.min(image[green(indexRGB)], modifier[green(indexRGB)]);
        pixels[blue(indexRGB)] = Math.min(image[blue(indexRGB)], modifier[blue(indexRGB)]);
			}
		}
    if (normalize){
      return normalize(pixels,width);
    }
		return pixels;
	}

	public static PpmImage subtraction(final ATIImage image1, final ATIImage image2) {
		if (image1.getWidth() != image2.getWidth() || image1.getHeight() != image2.getHeight())
			throw new IllegalArgumentException("Image 2 size and image 1 size must be equal");

		final int[] pixels1 = image1.toRGB();
		final int[] pixels2 = image2.toRGB();

		for (int i = 0 ; i < image2.getWidth() ; i++) {
			for (int j = 0 ; j < image2.getHeight() ; j++) {
        int indexRGB1 = indexRGB(i,j,image1.getWidth());
        int indexRGB2 = indexRGB(i,j,image2.getWidth());
				pixels1[red(indexRGB1)] -= pixels2[red(indexRGB2)];
				pixels1[green(indexRGB1)] -= pixels2[green(indexRGB2)];
				pixels1[blue(indexRGB1)] -= pixels2[blue(indexRGB2)];
			}
		}
		normalize(pixels1, image1.getWidth());
		return new PpmImage(pixels1, image1.getWidth(), image1.getHeight());
	}
  
  public static int[] normalize(final int[] pixels, int width, int borderWidth) {
    int minValue = pixels[0];
    int maxValue = pixels[0];
    int height = pixels.length / width / 3;
    for (int i = borderWidth ; i < width - borderWidth - 1 ; i++) {
      for (int j = borderWidth ; j < height - borderWidth -1 ; j++) {
        minValue = Math.min(pixels[indexRGB(i, j, width)], minValue);
        maxValue = Math.max(pixels[indexRGB(i, j, width)], maxValue);
      }
    }
    System.out.println("Normalizing between " + minValue + " and " +maxValue);
    for (int i = borderWidth ; i < width - borderWidth - 1 ; i++) {
      for (int j = borderWidth ; j < height - borderWidth -1 ; j++) {
        pixels[red(indexRGB(i,j,width))] = normalize(pixels[red(indexRGB(i,j,width))], minValue, maxValue);
        pixels[green(indexRGB(i,j,width))] = normalize(pixels[green(indexRGB(i,j,width))], minValue, maxValue);
        pixels[blue(indexRGB(i,j,width))] = normalize(pixels[blue(indexRGB(i,j,width))], minValue, maxValue);
      }
    }
    return pixels;
  }
  
  public static int[] normalize(final int[] pixels, int width) {
    return normalize(pixels, width, 0);
  }

	private static int normalize(double value, double minValue, double maxValue) {
		return (int) (((value - minValue) / (maxValue - minValue)) * 255);
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

	public static javafx.util.Pair<List<Integer>, List<Integer>> thresholdGroups(final ATIImage image, final int threshold) {
		final List<Integer> group1 = new LinkedList<>();
		final List<Integer> group2 = new LinkedList<>();
		final byte[] pixels = image.getImage();
		for (int i = 0 ; i < pixels.length ; i++) {
			final int pixel = pixels[i] & 0xFF;
			if (pixel <= threshold)
				group1.add(i);
			else
				group2.add(i);
		}
		return new javafx.util.Pair(group1, group2);
	}

	public static PpmImage equalize(final ATIImage image) {
		final Map<Integer, Double> relativeFreq = new HashMap<>();

		final int[] pixels = image.toRGB();

		for (int i = 0 ; i < pixels.length ; i++) {
			if (relativeFreq.containsKey(pixels[i]))
				relativeFreq.put(pixels[i], relativeFreq.get(pixels[i]) + 1);
			else
				relativeFreq.put(pixels[i], 1D);
		}

		for (Map.Entry<Integer, Double> entry : relativeFreq.entrySet())
			relativeFreq.put(entry.getKey(), entry.getValue() / pixels.length);

		final Map<Integer, Double> equalizedFreq = new HashMap<>();

		for (final int grayLevel : relativeFreq.keySet()) {
			double sum = 0;
			for (int j = 0 ; j <= grayLevel ; j++) {
				if (relativeFreq.containsKey(j))
					sum += relativeFreq.get(j);
			}
			equalizedFreq.put(grayLevel, sum);
		}

		double minValue = equalizedFreq.values().stream().min(Double::compareTo).get();

		for (int i = 0 ; i < pixels.length ; i++)
			pixels[i] = normalize(equalizedFreq.get(pixels[i]), minValue, 1);

		return new PpmImage(pixels, image.getWidth(), image.getHeight());
	}

	public static PpmImage multiply(final ATIImage image, final double value) {
		final int[] pixels = image.toRGB();

		for (int i = 0 ; i < pixels.length ; i++)
			pixels[i] = (int) (pixels[i] * value);

		dynamicRangeCompression(pixels);
		return new PpmImage(pixels, image.getWidth(), image.getHeight());
	}
	
	public static PpmImage multiply(final ATIImage image, final double[] noiseImage) {
		final int[] pixels = image.toRGB();
		if(image.getWidth()*image.getHeight() * 3 != noiseImage.length ){
			throw new IllegalArgumentException("Noise image must have the same dimension as source image");
		}
		for (int i = 0 ; i < noiseImage.length ; i++){
			pixels[i] = (int) (pixels[i] * noiseImage[i]);
		}
		normalize(pixels, image.getWidth());
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
			rgbImage[i] = (int) (c * Math.log(1 + rgbImage[i]));
	}
	
	public static int[] toIntArray(final double[] image){
		int[] ans = new int[image.length];
		for (int i = 0; i < image.length; i++) {
			ans[i] = (int) image[i];
		}
		return ans;
	}
  
  public static int indexRGB(int i, int j, int width){
    return indexGray(i,j,width) * 3;
  }
  
  public static int red(int indexRGB){
    return indexRGB;
  }
  
  public static int green(int indexRGB){
    return indexRGB + 1;
  }
  
  public static int blue(int indexRGB){
    return indexRGB + 2;
  }
  
  public static int indexGray(int i, int j, int width){
    return i * width + j;
  }
  
  public static int lengthRGB(int width, int heigth){
	  return width * heigth * 3;
  }
}
