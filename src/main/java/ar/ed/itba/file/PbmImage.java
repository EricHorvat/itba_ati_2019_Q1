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
    protected Header generateHeader() throws Exception {
        return new Header(MagicNumber.P4.getMagicNumber(), getWidth(), getHeight());
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

