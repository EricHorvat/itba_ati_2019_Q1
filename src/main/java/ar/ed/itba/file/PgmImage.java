package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.*;


public class PgmImage extends PortableImage {

    public PgmImage(final String filePath) throws IOException {
        super(filePath);
    }

    public PgmImage(final byte[] image, final int width, final int height) {
        super(image, width, height, BufferedImage.TYPE_BYTE_GRAY);
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
    public BufferedImage view() {
        return getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        return new GrayPixel(getImage()[i * getWidth() + j]);
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        getImage()[i * getWidth() + j] = ((GrayPixel) pixel).getGray();
    }

    public static PgmImage createGrayDowngrade(final int width, final int height) {
        final byte[] image = new byte[width * height];
        final int colors = 256;
        final int cols = (int) Math.ceil((float) width / colors);
        int currentColor = 255;

        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < height ; j++)
                image[i * width + j] = (byte) currentColor;
            if (i % cols == 0 && i != 0)
                currentColor--;
        }
        return new PgmImage(image, width, height);
    }
}
