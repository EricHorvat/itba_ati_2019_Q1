package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PbmImage extends PortableImage {

    public PbmImage(final String filePath) throws IOException {
        super(filePath);
    }

    public PbmImage(final byte[] image, final int width, final int height) {
        super(image, width, height, BufferedImage.TYPE_BYTE_BINARY);
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
    public BufferedImage view() {
        negateImage(getImage());
        return getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        final int position = i * getWidth() + j;
        final int arrayPosition = (int) Math.ceil(position/8);
        final int offset = position % 8;
        final byte pixelByte = getImage()[arrayPosition];
        return new BitPixel((byte) ((pixelByte >> offset) & 1));
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        final int position = i * getWidth() + j;
        final int arrayPosition = (int) Math.ceil(position/8);
        final int offset = position % 8;
        final byte pixelByte = getImage()[arrayPosition];
        if (((BitPixel) pixel).getBit() == (byte) 1)
            getImage()[arrayPosition] = (byte) (pixelByte | (1 << offset));
        else
            getImage()[arrayPosition] = (byte) (pixelByte & ~(1 << offset));
    }

    private void negateImage(final byte[] aux) {
        for (int i = 0 ; i < aux.length ; i++)
            aux[i] = (byte) ~aux[i];
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

        byte[] image = new byte[200 * 200];

        //image black
        for (int i = 0 ; i < 200 ; i++) {
            for (int j = 0 ; j < 200 ; j++)
                image[i * 200 + j] = 1;
        }
        bresenhamAlgorithm(image, 200, 100, 100, radius);
        image = compress(image);
        return new PbmImage(image, 200, 200);
    }

    private static void bresenhamAlgorithm(final byte[] image, final int width,
                                    final int x0, final int y0, final int radius) {
        int x = radius-1;
        int y = 0;
        int dx = 1;
        int dy = 1;
        int err = dx - (radius << 1);

        while (x >= y)
        {
            image[(x0 + x) * width + y0 + y] = 0;
            image[(x0 + y) * width + y0 + x] = 0;
            image[(x0 - y) * width + y0 + x] = 0;
            image[(x0 - x) * width + y0 + y] = 0;
            image[(x0 - x) * width + y0 - y] = 0;
            image[(x0 - y) * width + y0 - x] = 0;
            image[(x0 - y) * width + y0 - x] = 0;
            image[(x0 + y) * width + y0 - x] = 0;
            image[(x0 + x) * width + y0 - y] = 0;


            if (err <= 0)
            {
                y++;
                err += dy;
                dy += 2;
            }

            else if (err > 0)
            {
                x--;
                dx += 2;
                err += dx - (radius << 1);
            }
        }
    }
}
