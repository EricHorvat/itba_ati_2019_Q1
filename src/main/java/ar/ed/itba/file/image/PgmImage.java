package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.BitPixel;
import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

import static ar.ed.itba.utils.ImageUtils.*;


public class PgmImage extends PortableImage {

    private static final int MAX_COLOR = 255;

    public PgmImage(final String filePath) throws IOException {
        super(filePath, ImageMode.GRAY);
    }

    public PgmImage(final byte[] image, final int width, final int height) {
        super(image, width, height, BufferedImage.TYPE_BYTE_GRAY, ImageMode.GRAY);
    }
    
    public PgmImage(BufferedImage bi){
	    this(((DataBufferByte)bi.getRaster().getDataBuffer()).getData(),bi.getWidth(),bi.getHeight());
    }

    @Override
    public BufferedImage open(final String filePath) {
        return super.open(filePath, BufferedImage.TYPE_BYTE_GRAY);
    }

    @Override
    protected byte[] parseBinary(final String filePath) throws IOException {
        return super.parseBinary(filePath);
    }

    @Override
    protected byte[] parseAscii(final String filePath, final Header header) throws IOException {
        return super.parseAscii(filePath, header, GRAY);
    }

    @Override
    public int[] toRGB() {
        return toRGB(this);
    }

    public static int[] toRGB(final ATIImage image) {
        if (!(image instanceof PgmImage || image instanceof RawImage))
            throw new UnsupportedOperationException("Image must be pgm or raw");

        final byte[] pixels = image.getImage();
        final int[] aux = new int[pixels.length * 3];
        for (int i = 0 ; i < image.getHeight() ; i++) {
            for (int j= 0 ; j < image.getWidth() ; j++) {
                int indexRGB = indexRGB(i,j,image.getWidth());
                int index = indexGray(i,j,image.getWidth());
                aux[red(indexRGB)] = pixels[index] & 0xFF;
                aux[green(indexRGB)] = pixels[index] & 0xFF;
                aux[blue(indexRGB)] = pixels[index] & 0xFF;
            }
        }
        return aux;
    }

    @Override
    public BufferedImage view() {
        return getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        return new GrayPixel(getImage()[indexGray(i,j,getWidth())]);
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        getImage()[indexGray(i,j,getWidth())] = ((GrayPixel) pixel).getGray();
    }

    @Override
    public void negative() {
        negative(this);
    }

    public static void negative(final ATIImage image) {
        if (!(image instanceof PgmImage || image instanceof RawImage))
            throw new UnsupportedOperationException("Image must be pgm or raw");

        byte[] pixels = image.getImage();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++)
                pixels[i * image.getWidth() + j] = (byte) (-(pixels[i * image.getWidth() + j] & 0xFF) + MAX_COLOR);
        }
    }

    @Override
    public void copy(final PortableImage image, final int imageFromX, final int imageToX,
                     final int imageFromY, final int imageToY, final int fromX, final int fromY) {
        if (!(image.betweenBounds(imageFromX, imageFromY)  && image.betweenBounds(imageToX, imageToY)
                && this.betweenBounds(fromX + (imageToX - imageFromX), fromY + (imageToY - imageFromY))))
            throw new IllegalArgumentException("Points must be between images bounds");

        if (!(image instanceof PbmImage || image instanceof PgmImage))
            throw new UnsupportedOperationException("Pgm extension is not supported");

        if (image instanceof PbmImage) {
            for (int i = 0; i <= imageToX - imageFromX ; i++) {
                for (int j = 0; j <= imageToY - imageFromY ; j++)
                    setPixel(fromX + i, fromY + j, binToGray((BitPixel) image.getPixel(imageFromX + i, imageFromY + j)));
            }
        } else {
            for (int i = 0; i <= imageToX - imageFromX ; i++) {
                for (int j = 0; j <= imageToY - imageFromY ; j++)
                    setPixel(fromX + i, fromY + j, image.getPixel(imageFromX + i, imageFromY + j));
            }
        }
    }

    public static GrayPixel binToGray(final BitPixel bitPixel) {
        if (bitPixel.getBit() == 1)
            return new GrayPixel((byte) 0);
        else
            return new GrayPixel((byte) 255);
    }

    public static PgmImage createGrayDowngrade(final int width, final int height) {
        final byte[] image = new byte[width * height];
        final int colors = 256;
        final int cols = (int) Math.ceil((float) height / colors);
        int currentColor = 255;

        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++)
                image[i * width + j] = (byte) currentColor;
            if (i % cols == 0 && i != 0)
                currentColor--;
        }
        return new PgmImage(image, width, height);
    }

    @Override
    protected Header generateHeader() throws Exception {
        return new Header(MagicNumber.P5.getMagicNumber() , getWidth(), getHeight(), 255);
    }
    
    @Override
    public String getPixelInfo(int i, int j) {
        return "Gray level: " + (((GrayPixel) getPixel(j, i)).getGray() & 0xFF);
    }
    
    @Override
    public String getRegionInfo(int oi, int oj, int w, int h) {
	    Color c = averageColor(oi, oj, w, h);
	    return "Region average : " + c.getBlue();
    }
	
	@Override
	public ATIImage deepCopy() {
		return new PgmImage(getImage().clone(), getWidth(), getHeight());
	}
  
  @Override
  public ATIImage from(int[] array, int width, int height) {
    return new PpmImage(array,width,height);
  }
}
