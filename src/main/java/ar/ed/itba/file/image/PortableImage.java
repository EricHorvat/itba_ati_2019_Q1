package ar.ed.itba.file.image;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.lang.Character.isDigit;
import static java.lang.Character.isWhitespace;

public abstract class PortableImage extends ATIImage {
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

    private Optional<Header> header;
    private int byteCount = 0;

    public PortableImage(final String filePath, final ImageMode imageMode) throws IOException {
        super(filePath, imageMode);
    }

    public PortableImage(final int width, final int height, final int imgType, final ImageMode imageMode) {
        super(new BufferedImage(width, height, imgType), imageMode);
    }

    public PortableImage(final byte[] image, final int width, final int height, final int imgType, final ImageMode imageMode) {
        super(byte2Buffered(image, width, height, imgType), imageMode);
    }
    
    protected BufferedImage open(final String filePath, final int imageType) {
        try (DataInputStream ds = new DataInputStream(new FileInputStream(filePath))) {
            if (imageType == BufferedImage.TYPE_BYTE_BINARY)
                header = Optional.of(readHeader(ds, HEADERS_BITMAP));
            else
                header = Optional.of(readHeader(ds, HEADERS_MAX));
            Header header = this.header.get();
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
    
    @Override
    public void save(final String fileName) throws Exception {
        if (!header.isPresent())
            this.header = Optional.of(generateHeader());
        try (FileOutputStream fs = new FileOutputStream(directoryOutput + fileName +
                header.get().getMagicNumber().getExtension())) {
            fs.write(header.get().toString().getBytes());
            fs.write(getImage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract Header generateHeader() throws Exception;

}
