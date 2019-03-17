package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PpmImage extends PortableImage {

    public PpmImage(final String filePath) throws IOException {
        super(filePath);
    }

    public PpmImage(final int width, final int height) {
        super(width, height, BufferedImage.TYPE_3BYTE_BGR);
    }

    public PpmImage(byte[] image, int width, int height) {
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
        return new PpmImage(swapRB(getImage()), getWidth(), getHeight()).getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        return new RGBPixel(getImage()[(i * getWidth() + j) * 3], getImage()[(i * getWidth() + j) * 3 + 1],
                getImage()[(i * getWidth() + j) * 3 + 2]);
    }

    public PgmImage getRedChannel() {
        final byte[] aux = new byte[getWidth() * getHeight()];
        final byte[] image = getImage();
        for (int i = 0; i < getHeight() ; i++) {
            for (int j = 0 ; j < getWidth() ; j++)
                aux[i * getWidth() + j] = image[(i * getWidth() + j) * 3];
        }
        return new PgmImage(aux, getWidth(), getHeight());
    }

    public PgmImage getGreenChannel() {
        final byte[] aux = new byte[getWidth() * getHeight()];
        final byte[] image = getImage();
        for (int i = 0; i < getHeight() ; i++) {
            for (int j = 0 ; j < getWidth() ; j++)
                aux[i * getWidth() + j] = image[(i * getWidth() + j) * 3 + 1];
        }
        return new PgmImage(aux, getWidth(), getHeight());
    }

    public PgmImage getBlueChannel() {
        final byte[] aux = new byte[getWidth() * getHeight()];
        final byte[] image = getImage();
        for (int i = 0; i < getHeight() ; i++) {
            for (int j = 0 ; j < getWidth() ; j++)
                aux[i * getWidth() + j] = image[(i * getWidth() + j) * 3 + 2];
        }
        return new PgmImage(aux, getWidth(), getHeight());
    }

    public PgmImage[] toHSV() {
        final PpmImage hsv = new PpmImage(getWidth(), getHeight());
        for (int i = 0 ; i < getHeight() ; i++) {
            for (int j = 0 ; j < getWidth() ; j++) {
                final RGBPixel rgbPixel = toHSV((RGBPixel) getPixel(i, j));
                hsv.setPixel(i, j, rgbPixel);
            }
        }
        return new PgmImage[] {hsv.getRedChannel(), hsv.getGreenChannel(), hsv.getBlueChannel()};
    }

    private RGBPixel toHSV(final RGBPixel rgbPixel) {
        final int h_max = 360;
        final int s_max = 100;
        final int v_max = 100;

        final int r = rgbPixel.getRed() & 0xFF;
        final int g = rgbPixel.getGreen() & 0xFF;
        final int b = rgbPixel.getBlue() & 0xFF;
        double h;
        double s;
        double v;

        int max = Math.max(Math.max(r,g),b);
        int min = Math.min(Math.max(r,g),b);
        v = max;
        double delta = max-min;
        s = (max != 0) ? (delta/max) : 0.0;
        if(s == 0)
            h = 0;
        else {
            if (r == max)
                h = (g-b) / delta;
            else if (g == max)
                h = 2.0 + (b-r) / delta;
            else
                h = 4.0 + (r-g) / delta;
            h = h * 60.0;
            if (h < 0.0)
                h = h + 360.0;
        }
        return new RGBPixel((byte) ((int)  (h / h_max * 255)), (byte) ((int) (s / s_max * 255)),
                (byte) ((int) (v / v_max * 255)));
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        getImage()[(i * getWidth() + j) * 3] = ((RGBPixel) pixel).getRed();
        getImage()[(i * getWidth() + j) * 3 + 1] = ((RGBPixel) pixel).getGreen();
        getImage()[(i * getWidth() + j) * 3 + 2] = ((RGBPixel) pixel).getBlue();
    }

    private byte[] swapRB(final byte[] image) {
        //swap red coordinate with blue
        final byte[] aux = new byte[image.length];
        for (int i = 0, j = 2 ; j < image.length ; i += 3, j = i + 2) {
            byte aux2 = image[i];
            aux[i] = image[j];
            aux[i+1] = image[i+1];
            aux[j] = aux2;
        }
        return aux;
    }

    public static PpmImage createColorDowngrade(final RGBPixel color1, final RGBPixel color2,
                                                final int width, final int height) {
        final PpmImage image = new PpmImage(width, height);
        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++) {
                final RGBPixel aux = colorBetween(color1, color2, (double) (i * width + j) / (width * height) * 100.0);
                image.setPixel(i, j, aux);
            }
        }
        return image;
    }

    private static RGBPixel colorBetween(final RGBPixel color1, final RGBPixel color2, double percent) {
        return new RGBPixel(
                transitionColor(color1.getRed(), color2.getRed(), percent),
                transitionColor(color1.getGreen(), color2.getGreen(), percent),
                transitionColor(color1.getBlue(), color2.getBlue(), percent));
    }

    private static byte transitionColor(final byte color1, final byte color2, double percent) {
        int unsignedC1 = color1 & 0xFF;
        int unsignedC2 = color2 & 0xFF;
        int c1 = (int) (unsignedC1 * percent);
        int c2 = (int) (unsignedC2 * (100 - percent));
        double ans =  (c1 + c2)/100.0;
        return (byte) ((int) Math.round(ans));
    }

    @Override
    protected Header generateHeader() throws Exception {
        return new Header(MagicNumber.P6.getMagicNumber(), getWidth(), getHeight(), 255);
    }

}
