package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.BitPixel;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.file.pixel.RGBPixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

import static ar.ed.itba.utils.ImageUtils.*;

public class PbmImage extends PortableImage {

    private static final int MAX_COLOR = 1;

    public PbmImage(final String filePath) throws IOException {
        super(filePath, ImageMode.GRAY);
    }

    public PbmImage(final byte[] image, final int width, final int height) {
        super(image, width, height, BufferedImage.TYPE_BYTE_BINARY, ImageMode.GRAY);
    }

    @Override
    public BufferedImage open(final String filePath) {
        return super.open(filePath, BufferedImage.TYPE_BYTE_BINARY);
    }

    @Override
    protected byte[] parseBinary(final String filePath) throws IOException {
        return super.parseBinary(filePath);
    }

    @Override
    protected byte[] parseAscii(final String filePath, final Header header) throws IOException {
        final byte[] aux = super.parseAscii(filePath, header, BIN);
        return compress(aux);
    }

    @Override
    public int[] toRGB() {
        final int[] aux = new int[lengthRGB(getWidth(), getHeight())];
        for (int i = 0 ; i < getHeight() ; i++) {
            for (int j = 0 ; j < getWidth() ; j++) {
             final RGBPixel rgbPixel = PpmImage.binToColor((BitPixel) getPixel(i, j));
             int indexRGB = indexRGB(i,j,getWidth());
             aux[red(indexRGB)] = rgbPixel.getRed() & 0xFF;
             aux[green(indexRGB)] = rgbPixel.getGreen() & 0xFF;
             aux[blue(indexRGB)] = rgbPixel.getBlue() & 0xFF;
            }
        }
        return aux;
    }

    @Override
    public void copy(final PortableImage image, final int imageFromX, final int imageToX,
                     final int imageFromY, final int imageToY, final int fromX, final int fromY) {
        if (!(image.betweenBounds(imageFromX, imageFromY)  && image.betweenBounds(imageToX, imageToY)
                && this.betweenBounds(fromX + (imageToX - imageFromX), fromY + (imageToY - imageFromY))))
            throw new IllegalArgumentException("Points must be between images bounds");

        if (!(image instanceof PbmImage))
            throw new UnsupportedOperationException("Only bpm extension is supported");

        for (int i = 0 ; i <= imageToX - imageFromX ; i++) {
            for (int j = 0; j <= imageToY - imageFromY ; j++)
                setPixel(fromX + i, fromY + j, image.getPixel(imageFromX + i, imageFromY + j));
        }
    }

    @Override
    protected Header generateHeader() throws Exception {
        return new Header(MagicNumber.P4.getMagicNumber(), getWidth(), getHeight());
    }

    @Override
    public BufferedImage view() {
        return new PbmImage(negateImage(getImage()), getWidth(), getHeight()).getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        final int position = i * getWidth() + j;
        final int arrayPosition = (int) Math.floor(position/8);
        final int offset = position % 8;
        final byte pixelByte = getImage()[arrayPosition];
        return new BitPixel((byte) ((pixelByte >> (7 - offset)) & 1));
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        final int position = i * getWidth() + j;
        final int arrayPosition = (int) Math.floor(position/8);
        final int offset = position % 8;
        final byte pixelByte = getImage()[arrayPosition];
        if (((BitPixel) pixel).getBit() == (byte) 1)
            getImage()[arrayPosition] = (byte) (pixelByte | (1 << (7 - offset)));
        else
            getImage()[arrayPosition] = (byte) (pixelByte & ~(1 << (7 - offset)));
    }

    @Override
    public void negative() {
        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                final byte bitPixel = ((BitPixel) getPixel(i, j)).getBit();
                setPixel(i, j, new BitPixel((byte) (-(bitPixel & 0xFF) + MAX_COLOR)));
            }
        }
    }

    private byte[] negateImage(final byte[] image) {
        final byte[] aux = new byte[image.length];
        for (int i = 0 ; i < aux.length ; i++)
            aux[i] = (byte) ~image[i];
        return aux;
    }

    private static byte[] compress(final byte[] bytes) {
        final byte[] image = new byte[(int) Math.ceil(bytes.length/8)];
        int k = 0;
        for (int i = 0 ; i < bytes.length ;) {
            int byteValue = 0;
            for (int j = 0 ; j < 8 && i < bytes.length; j++, i++)
                byteValue = (byteValue << 1) | bytes[i];
            image[k++] = (byte) byteValue;
        }
        return image;
    }

    public static PbmImage createWhiteCircle(final int radius) {
        int width = 200;
        int height = 200;
        int yCentered = width / 2;
        int xCentered = height / 2;

        byte[] image = new byte[width * height];

        //image black
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++)
                image[i * width + j] = 1;
        }
        bresenhamAlgorithm(image, width, xCentered, yCentered, radius);
        image = compress(image);
        return new PbmImage(image, width, height);
    }

    //side must be pair
    public static PbmImage createWhiteSquare(final int side) {
        int width = 200;
        int height = 200;
        int yCentered = width / 2;
        int xCentered = height / 2;

        byte[] image = new byte[width * height];

        //image black
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++)
                image[i * width + j] = 1;
        }

        final int x1 = xCentered + side/2;
        for (int i = yCentered - side/2 ; i < yCentered + side/2 ; i++)
            image[x1 * width + i] = 0;

        final int x2 = xCentered - side/2;
        for (int i = yCentered - side/2 ; i < yCentered + side/2 ; i++)
            image[x2 * width + i] = 0;

        final int y1 = yCentered + side/2;
        for (int i = xCentered - side/2 ; i <= xCentered + side/2 ; i++)
            image[i * width + y1] = 0;

        final int y2 = yCentered - side/2;
        for (int i = xCentered - side/2 ; i < xCentered + side/2 ; i++)
            image[i * width + y2] = 0;

        image = compress(image);
        return new PbmImage(image, width, height);
    }


    private static void bresenhamAlgorithm(final byte[] image, final int width,
                                           final int x0, final int y0, final int radius) {
        int x = radius-1;
        int y = 0;
        int dx = 1;
        int dy = 1;
        int err = dx - (radius << 1);

        while (x >= y) {
            image[(x0 + x) * width + y0 + y] = 0;
            image[(x0 + y) * width + y0 + x] = 0;
            image[(x0 - y) * width + y0 + x] = 0;
            image[(x0 - x) * width + y0 + y] = 0;
            image[(x0 - x) * width + y0 - y] = 0;
            image[(x0 - y) * width + y0 - x] = 0;
            image[(x0 - y) * width + y0 - x] = 0;
            image[(x0 + y) * width + y0 - x] = 0;
            image[(x0 + x) * width + y0 - y] = 0;

            if (err <= 0) {
                y++;
                err += dy;
                dy += 2;
            }
            else if (err > 0) {
                x--;
                dx += 2;
                err += dx - (radius << 1);
            }
        }
    }
	
	@Override
	public String getPixelInfo(int i, int j) {
		return "Gray level: " + (PgmImage.binToGray((BitPixel) getPixel(j, i)).getGray() & 0xFF);
	}
	
	@Override
	public String getRegionInfo(int oi, int oj, int w, int h) {
		Color c = averageColor(oi, oj, w, h);
		return "Region average : " + c.getBlue();
	}
	
	@Override
	public ATIImage deepCopy() {
		return new PbmImage(getImage().clone(), getWidth(), getHeight());
	}
  
  @Override
  public ATIImage from(int[] array, int width, int height) {
    return new PpmImage(array, width, height);
  }
}

