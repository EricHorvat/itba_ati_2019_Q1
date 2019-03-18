package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.Pixel;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;

public abstract class ATIImage {
	
	/* Variables */
	
	protected final BufferedImage bufferedImage;
	
	/* Constructors */
	public ATIImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
	public ATIImage(String filePath) throws IOException{
		this.bufferedImage = open(filePath);
	}
	
	/* Abstract methods */
	
	protected abstract BufferedImage open(final String filePath) throws IOException;
	
	public abstract BufferedImage view();
	
	public abstract Pixel getPixel(final int i, final int j);
	
	public abstract void setPixel(final int i, final int j, final Pixel pixel);
	
	public abstract String getPixelInfo(final int i, final int j);
	
	public abstract String getRegionInfo(final int oi, final int oj, final int ti, final int tj);
	
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
	
	
	
}
