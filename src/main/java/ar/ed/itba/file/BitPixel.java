package ar.ed.itba.file;

public class BitPixel extends Pixel {
    private byte bit;

    public BitPixel(byte bit) {
        this.bit = bit;
    }

    public byte getBit() {
        return bit;
    }
}
