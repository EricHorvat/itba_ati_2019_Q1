package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.ui.components.DialogFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
	
	private String directoryOutput = "./output/";
	
	@Override
	public void save(String fileName) throws Exception {
		
		try (FileOutputStream fs = new FileOutputStream(directoryOutput + fileName + ".raw")) {
			fs.write(getImage());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try (FileOutputStream fs = new FileOutputStream(directoryOutput + fileName + ".txt")) {
			String s = ""+ getWidth() + ","+ getHeight();
			fs.write(s.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public int[] toRGB() {
		return PgmImage.toRGB(this);
	}

	@Override
	public void negative() {
		PgmImage.negative(this);
	}
	
	
	@Override
	public ATIImage deepCopy() {
		return new PgmImage(getImage().clone(), getWidth(), getHeight());
		/*TODO HEADER, OUTPUT & bytecount*/
	}
}
