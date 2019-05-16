package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.utils.CoordinatePair;
import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.Region;
import javafx.util.Pair;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class ATIImage {
	
	/* Mode */
	
	public enum ImageMode {
		GRAY, COLOR
	}
	
	/* Variables */
	
	protected final BufferedImage bufferedImage;
	
	protected final ImageMode imageMode;
  
  private final String filePath;
  
  /* Constructors */
	public ATIImage(BufferedImage bufferedImage, ImageMode imageMode) {
		this.bufferedImage = bufferedImage;
		this.imageMode = imageMode;
		this.filePath = null;
	}

	public ATIImage(String filePath, ImageMode imageMode) throws IOException {
		this.bufferedImage = open(filePath);
		this.imageMode = imageMode;
		this.filePath = filePath;
	}
	
	/* Abstract methods */
	
	public ImageMode getMode() {
		return imageMode;
	}
	
	protected abstract BufferedImage open(final String filePath) throws IOException;
	
	public abstract BufferedImage view();
	
	public BufferedImage regionatedView(Region region) {
		BufferedImage view = ImageUtils.deepCopy(getBufferedImage());
		int ox = region.getX();
		int oy = region.getY();
		int w = region.getW();
		int h = region.getH();
		
		for (int x = ox; x < ox + w; x++) {
			view.setRGB(x, oy, ImageUtils.negateRGB(view.getRGB(x, oy)));
			view.setRGB(x, oy + h, ImageUtils.negateRGB(view.getRGB(x, oy + h)));
		}
		for (int y = oy; y < oy + h; y++) {
			view.setRGB(ox, y, ImageUtils.negateRGB(view.getRGB(ox, y)));
			view.setRGB(ox + w, y, ImageUtils.negateRGB(view.getRGB(ox + w, y)));
		}

		byte[] imageBytes = ((DataBufferByte) view.getRaster().getDataBuffer()).getData();
		if (this instanceof PpmImage)
			return new PpmImage(imageBytes, view.getWidth(), view.getHeight()).view();
		else if (this instanceof PbmImage)
			return new PbmImage(imageBytes, view.getWidth(), view.getHeight()).view();
		else
			return new PgmImage(imageBytes, view.getWidth(), view.getHeight()).view();
	}
	
	public abstract Pixel getPixel(final int i, final int j);
	
	public abstract void setPixel(final int i, final int j, final Pixel pixel);
	
	public abstract String getPixelInfo(final int i, final int j);
	
	public abstract String getRegionInfo(final int oi, final int oj, final int w, final int h);
	
	/* Methods */
	
	protected BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public byte[] getImage() {
		return ((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData();
	}
	
	public int getWidth() {
		return bufferedImage.getWidth();
	}
	
	public int getHeight() {
		return bufferedImage.getHeight();
	}
	
	public static BufferedImage byte2Buffered(byte[] pixels, int width, int height, final int imageType) throws IllegalArgumentException {
		BufferedImage image = new BufferedImage(width, height, imageType);
		byte[] imgData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		System.arraycopy(pixels, 0, imgData, 0, pixels.length);
		return image;
	}
	
	public String getFilePath(){
	  return filePath;
  }

	//only for rgb images
	public static BufferedImage byte2Buffered(int[] pixels, int width, int height, final int imageType) throws IllegalArgumentException {
		byte[] aux = new byte[pixels.length];
		for (int i = 0 ; i < pixels.length ; i++)
			aux[i] = (byte) pixels[i];
		return byte2Buffered(aux, width, height, imageType);
	}
	
	/*
	 * Where bi is your image, (x0,y0) is your upper left coordinate, and (w,h)
	 * are your width and height respectively
	 */
	public Color averageColor(int x0, int y0, int w, int h) {
		return ATIImage.averageColor(getBufferedImage(), x0, y0, w, h);
	}
	
	/*
	 * Where bi is your image, (x0,y0) is your upper left coordinate, and (w,h)
	 * are your width and height respectively
	 */
	public static Color averageColor(BufferedImage bi, int x0, int y0, int w, int h) {
		int x1 = x0 + w;
		int y1 = y0 + h;
		long sumR = 0, sumG = 0, sumB = 0;
		long sumr = 0, sumg = 0, sumb = 0;
		for (int x = x0; x < x1; x++) {
			for (int y = y0; y < y1; y++) {
				Color pixel = new Color(bi.getRGB(x, y));
				sumr += pixel.getGreen();
				sumg += pixel.getBlue();
				sumb += pixel.getRed();
			}
			sumR += sumr;
			sumG += sumg;
			sumB += sumb;
			sumr = 0;
			sumg = 0;
			sumb = 0;
		}
		int num = w * h;
		return new Color((int) (sumG / num), (int) (sumR / num), (int) (sumB / num));
	}
	
	public abstract void save(final String fileName) throws Exception;

	public abstract int[] toRGB();

	public abstract void negative();
	
	public abstract ATIImage deepCopy();
}


