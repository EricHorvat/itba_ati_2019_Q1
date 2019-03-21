package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public abstract class ATIImage {
	
	/* Mode */
	
	public enum ImageMode{
		GRAY, COLOR
	}
	
	/* Variables */
	
	protected final BufferedImage bufferedImage;
	
	protected final ImageMode imageMode;
	
	/* Constructors */
	public ATIImage(BufferedImage bufferedImage, ImageMode imageMode) {
		this.bufferedImage = bufferedImage;
		this.imageMode = imageMode;
	}
	
	public ATIImage(String filePath, ImageMode imageMode) throws IOException{
		this.bufferedImage = open(filePath);
		this.imageMode = imageMode;
	}
	
	/* Abstract methods */
	
	public ImageMode getMode(){
		return imageMode;
	}
	
	protected abstract BufferedImage open(final String filePath) throws IOException;
	
	public abstract BufferedImage view();
	
	public abstract Pixel getPixel(final int i, final int j);
	
	public abstract void setPixel(final int i, final int j, final Pixel pixel);
	
	public abstract String getPixelInfo(final int i, final int j);
	
	public abstract String getRegionInfo(final int oi, final int oj, final int w, final int h);
	
	/* Methods */
	
	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}
	
	public byte[] getImage() {
		return ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
	}
	
	public int getWidth() {
		return bufferedImage.getWidth();
	}
	
	public int getHeight() {
		return bufferedImage.getHeight();
	}
	
	public static BufferedImage byte2Buffered(byte[] pixels, int width, int height, final int imageType) throws IllegalArgumentException {
		BufferedImage image = new BufferedImage(width, height, imageType);
		byte[] imgData = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		System.arraycopy(pixels, 0, imgData, 0, pixels.length);
		return image;
	}
	
	/*
	 * Where bi is your image, (x0,y0) is your upper left coordinate, and (w,h)
	 * are your width and height respectively
	 */
	public Color averageColor(int x0, int y0, int w, int h) {
		return ATIImage.averageColor(getBufferedImage(),x0, y0, w, h);
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
				sumr += pixel.getRed();
				sumg += pixel.getGreen();
				sumb += pixel.getBlue();
			}
			sumR += sumr;
			sumG += sumg;
			sumB += sumb;
			sumr = 0;
			sumg = 0;
			sumb = 0;
		}
		int num = w * h;
		return new Color((int)(sumR / num), (int)(sumG / num), (int)(sumB / num));
	}
}
