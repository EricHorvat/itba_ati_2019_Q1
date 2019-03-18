package ar.ed.itba.file.image;

public enum MagicNumber {
    P1("P1", false, ".pbm"),
    P2("P2", false, ".pgm"),
    P3("P3", false, ".ppm"),
    P4("P4", true, ".pbm"),
    P5("P5", true, ".pgm"),
    P6("P6", true, ".ppm");

    private final String magicNumber;
    private final boolean binary;
    private final String extension;

    MagicNumber(final String magicNumber, final boolean binary, final String extension) {
        this.magicNumber = magicNumber;
        this.binary = binary;
        this.extension = extension;
    }

    public boolean isBinary() {
        return binary;
    }

    public static MagicNumber magicNumberFrom(final String s) throws Exception {
        for (final MagicNumber m : values()) {
            if (m.magicNumber.equals(s))
                return m;
        }
        throw new Exception();
    }

    public String getMagicNumber() {
        return magicNumber;
    }

    public static MagicNumber getBinaryMagicNumber(final MagicNumber magicNumber) throws Exception {
        if (magicNumber.isBinary())
            return magicNumber;
        else {
            switch (magicNumber) {
                case P1:
                    return P4;
                case P2:
                    return P5;
                case P3:
                    return P6;
            }
        }
        throw new Exception();
    }

    public String getExtension() {
        return extension;
    }
}
