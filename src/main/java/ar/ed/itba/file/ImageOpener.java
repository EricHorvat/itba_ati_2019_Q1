package ar.ed.itba.file;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public /*abstract*/ class ImageOpener {
	private final String filePath = "./resources/LENAX.RAW";
	private final String infoFilePath = "./resources/LENA.txt";
	
	public BufferedImage open(){
		try {
			byte[] imageArray = Files.readAllBytes(Paths.get(filePath));
			File infoFile = new File(infoFilePath);
			BufferedReader br = new BufferedReader(new FileReader(infoFile));
			String[] st = br.readLine().split(",");
			int width = Integer.parseInt(st[0]);
			int height = Integer.parseInt(st[1]);
			BufferedImage image = byte2Buffered(imageArray, width,height);
			return image;
		} catch (IOException e){
			/*TODO*/
		}
		return null;
	}
	
	private static BufferedImage byte2Buffered(byte[] pixels, int width, int height) throws IllegalArgumentException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
		byte[] imgData = ((DataBufferByte)image.getRaster().getDataBuffer()).getData();
		System.arraycopy(pixels, 0, imgData, 0, pixels.length);
		return image;
	}
}