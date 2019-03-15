package ar.ed.itba.file;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public abstract class PortableImage {
    private static final int HEADERS_MAX = 4;
    private static final int HEADERS_BITMAP = 3;
    private static final int MAGIC_NUMBER_INDEX = 0;
    private static final int WIDTH_INDEX = 1;
    private static final int HEIGHT_INDEX = 2;
    private static final int MAX_COLOR_INDEX = 3;

    private String directoryOutput = "./output/";

    protected static final int GRAY = 1;
    protected static final int BIN = 1;
    protected static final int RGB = 3;

    private final BufferedImage bufferedImage;
    private Header header;
    private int byteCount = 0;

    public PortableImage(final String filePath) throws IOException {
        bufferedImage = open(filePath);
    }

    protected abstract BufferedImage open(final String filePath) throws IOException;

    protected BufferedImage open(final String filePath, final int imageType) {
        try (DataInputStream ds = new DataInputStream(new FileInputStream(filePath))) {
            if (imageType == BufferedImage.TYPE_BYTE_BINARY)
                header = readHeader(ds, HEADERS_BITMAP);
            else
                header = readHeader(ds, HEADERS_MAX);
            return byte2Buffered(header.getMagicNumber().isBinary()? parseBinary(filePath):parseAscii(filePath, header),
                    header.getWidth(), header.getHeight(), imageType);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Header readHeader(final DataInputStream ds, final int headerCount) throws Exception {
        final List<String> header = new LinkedList<>();
        final StringBuilder s = new StringBuilder();
        boolean skipLine = false;
        while (header.size() < headerCount) {
            char c = (char) ds.readUnsignedByte();
            if (skipLine) {
                if (c == '\n')
                    skipLine = false;
            }
            else if (!isWhitespace(c)) {
                if (c == '#')
                    skipLine = true;
                else
                    s.append(c);
            }
            else if (s.length() != 0) {
                header.add(s.toString());
                s.setLength(0);
            }
            byteCount++;
        }
        if (headerCount == HEADERS_MAX)
            return new Header(header.get(MAGIC_NUMBER_INDEX), Integer.parseInt(header.get(WIDTH_INDEX)),
                    Integer.parseInt(header.get(HEIGHT_INDEX)), Integer.parseInt(header.get(MAX_COLOR_INDEX)));
        else
            return new Header(header.get(MAGIC_NUMBER_INDEX), Integer.parseInt(header.get(WIDTH_INDEX)),
                    Integer.parseInt(header.get(HEIGHT_INDEX)));
    }

    public int getByteCount() {
        return byteCount;
    }

    protected static BufferedImage byte2Buffered(byte[] pixels, int width, int height, final int imageType) throws IllegalArgumentException {
        BufferedImage image = new BufferedImage(width, height, imageType);
        byte[] imgData = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
        System.arraycopy(pixels, 0, imgData, 0, pixels.length);
        return image;
    }

    protected byte[] parseBinary(final String filePath) throws IOException {
        final BufferedInputStream bs = new BufferedInputStream(new FileInputStream(filePath));
        bs.skip(getByteCount());
        final byte[] imageArray = bs.readAllBytes();
        bs.close();
        return imageArray;
    }

    protected byte[] parseAscii(final String filePath, final Header header, final int bytesPerPixel) throws IOException {
        DataInputStream dis = new DataInputStream(new FileInputStream(filePath));
        dis.skip(getByteCount());
        final int size = header.getWidth() * header.getHeight() * bytesPerPixel;
        final byte[] imageArray = new byte[size];
        int currentValue = 0;
        boolean numberFound = false;
        int i = 0;
        while (i < size) {
            char c = (char) dis.readUnsignedByte();
            if (isDigit(c)) {
                currentValue = currentValue * 10 + Character.getNumericValue(c);
                numberFound = true;
            } else if (numberFound) {
                imageArray[i++] = (byte) currentValue;
                currentValue = 0;
                numberFound = false;
            }
        }
        return imageArray;
    }

    protected abstract byte[] parseAscii(final String filePath, final Header header) throws IOException;

    protected BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public byte[] getImage() {
        return ((DataBufferByte)bufferedImage.getRaster().getDataBuffer()).getData();
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public void save(final String fileName) {
        try (FileOutputStream fs = new FileOutputStream(directoryOutput + fileName +
                header.getMagicNumber().getExtension())) {
            fs.write(header.toString().getBytes());
            fs.write(getImage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract BufferedImage view();

    public abstract Pixel getPixel(final int i, final int j);

    public abstract void setPixel(final int i, final int j, final Pixel pixel);
}

