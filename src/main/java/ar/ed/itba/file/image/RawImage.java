package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class RawImage extends ATIImage {

    public RawImage(final String filePath) throws IOException {
        super(filePath, ImageMode.GRAY);
    }

    @Override
    public BufferedImage open(final String filePath) throws IOException{
        String infoFilePath = filePath.substring(0,filePath.length()-4) + ".txt";
        File infoFile = new File(infoFilePath);
        BufferedReader br = new BufferedReader(new FileReader(infoFile));
        String[] st = br.readLine().split(",");
        int width = Integer.parseInt(st[0]);
        int height = Integer.parseInt(st[1]);
    
        byte[] imageArray = Files.readAllBytes(Paths.get(filePath));
        return byte2Buffered(imageArray, width,height, BufferedImage.TYPE_BYTE_GRAY);
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
	
	@Override
	public String getPixelInfo(int i, int j) {
		return "Gray level: " + (new Color(getBufferedImage().getRGB(i,j)).getBlue());
	}
	
	@Override
	public String getRegionInfo(int oi, int oj, int w, int h) {
		Color c = averageColor(oi, oj, w, h);
		return "Region average : " + c.getBlue();
	}
}
