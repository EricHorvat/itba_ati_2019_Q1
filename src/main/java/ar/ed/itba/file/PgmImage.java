package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.*;


public class PgmImage extends PortableImage {

    public PgmImage(final String filePath) throws IOException {
        super(filePath);
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
}
