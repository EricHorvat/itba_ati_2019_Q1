package ar.ed.itba.ui.components;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.pixel.Pixel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayHistogramPanel extends JPanel {
	
	private final int[] bins;
	private final ATIImage image;
	
	public GrayHistogramPanel(ATIImage atiImage) {
		image = atiImage;
		bins = analyse();
		repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		for (int i = 0; i < 256; i++) {
			//System.out.println("bin[" + i + "]===" + bins[i]);
			g.drawLine(200 + i, 300, 200 + i, 300 - (bins[i])/1000);
		}
	}
	
	private int[] analyse(){
		BufferedImage bi = this.image.view();
		int[] sbins = new int[256];
		
		for (int x = 0; x < bi.getWidth(); x++) {
			for (int y = 0; y < bi.getHeight(); y++) {
				Color c = new Color(bi.getRGB(x,y));
				sbins[c.getRed()]++;
			}
		}
		return sbins;
	}
	
	

}
