package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.*;


public class PgmImage extends PortableImage {

    public PgmImage(final String filePath) throws IOException {
        super(filePath);
    }

    public PgmImage(final byte[] image, final int width, final int height) {
        super(image, width, height, BufferedImage.TYPE_BYTE_GRAY);
    }

    @Override
    public BufferedImage open(final String filePath) {
        return super.open(filePath, BufferedImage.TYPE_BYTE_GRAY);
    }

    @Override
    protected byte[] parseBinary(final String filePath) throws IOException {
        return super.parseBinary(filePath);
    }

    @Override
    protected byte[] parseAscii(final String filePath, final Header header) throws IOException {
        return super.parseAscii(filePath, header, GRAY);
    }

    @Override
    public BufferedImage view() {
        return getBufferedImage();
    }

    @Override
    public Pixel getPixel(final int i, final int j) {
        return new GrayPixel(getImage()[i * getWidth() + j]);
    }

    @Override
    public void setPixel(final int i, final int j, final Pixel pixel) {
        getImage()[i * getWidth() + j] = ((GrayPixel) pixel).getGray();
    }

    public static PgmImage createGrayDowngrade(final int width, final int height) {
        final byte[] image = new byte[width * height];
        final int colors = 256;
        final int cols = (int) Math.ceil((float) height / colors);
        int currentColor = 255;

        for (int i = 0 ; i < height ; i++) {
            for (int j = 0 ; j < width ; j++)
                image[i * width + j] = (byte) currentColor;
            if (i % cols == 0 && i != 0)
                currentColor--;
        }
        return new PgmImage(image, width, height);
    }

    @Override
    protected Header generateHeader() throws Exception {
        return new Header(MagicNumber.P5.getMagicNumber() , getWidth(), getHeight(), 255);
    }
    
    @Override
    public String getPixelInfo(int i, int j) {
        return "Gray level: " + (getBufferedImage().getRGB(i,j) & 0xFF);
    }
    
    @Override
    public String getRegionInfo(int oi, int oj, int ti, int tj) {
        if(oi > ti){
            int q = ti;
            ti = oi;
            oi = q;
        }
        if(oj > tj){
            int q = tj;
            tj = oj;
            oj = q;
        }
        Raster raster = getBufferedImage().getData(new Rectangle(oi,oj,ti-oi,tj-oj));
        return "Avg : " + getAverage(((DataBufferByte)raster.getDataBuffer()).getData());
    }
    
    private byte getAverage(byte[] byteArray){
    
        /*int len = byteArray.length;
        if (len == 2) {
            int a = byteArray[0] + byteArray[1];
            return a / 2;
        }
        
        byte a[] = Arrays.copyOfRange(byteArray, 0, len/2);
        byte b[] = Arrays.copyOfRange(byteArray, len/2, len);*/
        
        return 0;
    }
}
