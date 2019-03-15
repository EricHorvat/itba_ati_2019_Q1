package ar.ed.itba.file;

public class GrayPixel extends Pixel {
    private final byte gray;

    public GrayPixel(byte gray) {
        this.gray = gray;
    }

    public byte getGray() {
        return gray;
    }
}
