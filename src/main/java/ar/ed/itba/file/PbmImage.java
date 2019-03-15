package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class PbmImage extends PortableImage {

    public PbmImage(final String filePath) throws IOException {
        super(filePath);
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
        final byte[] image = new byte[(int) Math.ceil(aux.length/8)];
        int k = 0;
        for (int i = 0 ; i < aux.length ;) {
            int byteValue = 0;
            for (int j = 0 ; j < 8 && i < aux.length; j++, i++)
                byteValue = (byteValue << 1) | aux[i];
            image[k++] = (byte) byteValue;
        }
        return image;
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
}
