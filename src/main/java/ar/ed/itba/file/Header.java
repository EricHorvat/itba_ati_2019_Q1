package ar.ed.itba.file;

import java.util.Optional;

public class Header {
    private final MagicNumber magicNumber;
    private final int width;
    private final int height;
    private final Optional<Integer> maxColors;

    public Header(final String magicNumber, final int width, final int height) throws Exception {
        this.magicNumber = MagicNumber.magicNumberFrom(magicNumber);
        this.width = width;
        this.height = height;
        this.maxColors = Optional.empty();
    }

    public Header(final String magicNumber, final int width, final int height, final int maxColors) throws Exception {
        this.magicNumber = MagicNumber.magicNumberFrom(magicNumber);
        this.width = width;
        this.height = height;
        this.maxColors = Optional.of(maxColors);
    }

    public MagicNumber getMagicNumber() {
        return magicNumber;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMaxColors() {
        return maxColors.get();
    }

    public String toString() {
        try {
            final StringBuilder sb = new StringBuilder();
            sb.append(MagicNumber.getBinaryMagicNumber(magicNumber).getMagicNumber()).append("\n")
              .append(width).append("\t").append(height).append("\n");
            if (maxColors.isPresent())
                sb.append(maxColors).append("\n");
            return sb.toString();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
