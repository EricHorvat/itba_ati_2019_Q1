package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PpmImage extends PortableImage {

    public PpmImage(final String filePath) throws IOException {
        super(filePath);
    }

    public BufferedImage open(final String filePath) {
        return super.open(filePath, BufferedImage.TYPE_3BYTE_BGR);
    }

    @Override
    protected byte[] parseBinary(String filePath) throws IOException {
        return super.parseBinary(filePath);
    }

    @Override
    protected byte[] parseAscii(String filePath, Header header) throws IOException {
        return super.parseAscii(filePath, header, RGB);

    }

    @Override
    public BufferedImage view() {
        swapRB(getImage());
        return getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        return new RGBPixel(getImage()[(i * getWidth() + j) * 3], getImage()[(i * getWidth() + j) * 3 + 1],
                getImage()[(i * getWidth() + j) * 3 + 2]);
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        getImage()[(i * getWidth() + j) * 3] = ((RGBPixel) pixel).getRed();
        getImage()[(i * getWidth() + j) * 3 + 1] = ((RGBPixel) pixel).getBlue();
        getImage()[(i * getWidth() + j) * 3 + 2] = ((RGBPixel) pixel).getGreen();
    }

    private void swapRB(final byte[] array) {
        //swap red coordinate with blue
        for (int i = 0, j = 2 ; j < array.length ; i += 3, j = i + 2) {
            byte aux = array[i];
            array[i] = array[j];
            array[j] = aux;
        }
    }
}
