package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PpmImage extends PortableImage {

    public PpmImage(final String filePath) throws IOException {
        super(filePath);
    }

    public PpmImage(final byte[] image, final int width, final int height) {
        super(image, width, height, BufferedImage.TYPE_3BYTE_BGR);
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

    public PgmImage getRedChannel() {
        final byte[] aux = new byte[getWidth() * getHeight()];
        final byte[] image = getImage();
        for (int i = 0; i < getWidth() ; i++) {
            for (int j = 0 ; j < getHeight() ; j++)
                 aux[i * getWidth() + j] = image[(i * getWidth() + j) * 3];
        }
        return new PgmImage(aux, getWidth(), getHeight());
    }

    public PgmImage getGreenChannel() {
        final byte[] aux = new byte[getWidth() * getHeight()];
        final byte[] image = getImage();
        for (int i = 0; i < getWidth() ; i++) {
            for (int j = 0 ; j < getHeight() ; j++)
                aux[i * getWidth() + j] = image[(i * getWidth() + j) * 3 + 1];
        }
        return new PgmImage(aux, getWidth(), getHeight());
    }

    public PgmImage getBlueChannel() {
        final byte[] aux = new byte[getWidth() * getHeight()];
        final byte[] image = getImage();
        for (int i = 0; i < getWidth() ; i++) {
            for (int j = 0 ; j < getHeight() ; j++)
                aux[i * getWidth() + j] = image[(i * getWidth() + j) * 3 + 2];
        }
        return new PgmImage(aux, getWidth(), getHeight());
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

    public static PpmImage createColorDowngrade(final RGBPixel color1, final RGBPixel color2,
                                                final int width, final int height) {
        final byte[] image = new byte[width * height * 3];
        for (int i = 0 ; i < width ; i++) {
            for (int j = 0 ; j < height ; j++) {
                final RGBPixel aux = colorBetween(color1, color2, (double) (i * width + j) / (width * height) * 100.0);
                image[(i * width + j) * 3] = aux.getRed();
                image[(i * width + j) * 3 + 1] = aux.getGreen();
                image[(i * width + j) * 3 + 2] = aux.getBlue();
            }
        }
        return new PpmImage(image, width, height);
    }

    private static RGBPixel colorBetween(final RGBPixel color1, final RGBPixel color2, double percent) {
        return new RGBPixel(
                transitionColor(color1.getRed(), color2.getRed(), percent),
                transitionColor(color1.getGreen(), color2.getGreen(), percent),
                transitionColor(color1.getBlue(), color2.getBlue(), percent));
    }

    private static byte transitionColor(final byte color1, final byte color2, double percent) {
        int unsignedC1 = (int) (color1) & 0xFF;
        int unsignedC2 = (int) (color2) & 0xFF;
        int c1 = (int) (unsignedC1 * percent);
        int c2 = (int) (unsignedC2 * (100 - percent));
        double ans =  (c1 + c2)/100.0;
        return (byte) ((int) Math.round(ans));
    }

}
