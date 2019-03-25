package ar.ed.itba.utils.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class WeightMaskFilter extends MaskFilter{
	
	protected final int maskDivisor;
	
	protected abstract int maskDivisor();
	
	public WeightMaskFilter(int maskSide) {
		super(maskSide);
		this.maskDivisor = maskDivisor();
	}
	
	public BufferedImage applyFilter(BufferedImage sourceBi){
		int center = maskSide/2;
		BufferedImage finalBi = new BufferedImage(sourceBi.getWidth(), sourceBi.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		for (int i = 0; i < sourceBi.getWidth(); i++) {
			for (int j = 0; j < sourceBi.getHeight(); j++) {
				if ( i < center || j < center || i > sourceBi.getWidth() - center - 1 || j > sourceBi.getHeight() - center -1){
					finalBi.setRGB(i,j,sourceBi.getRGB(i,j));
				}else{
					double sum = 0;
					for (int k = 0; k < maskSide; k++) {
						for (int l = 0; l < maskSide; l++) {
							sum += new Color(sourceBi.getRGB(i+k-center, j+l-center)).getBlue() * mask[k][l];
						}
					}
					int v = (int)(sum/maskDivisor);
					finalBi.setRGB(i,j,new Color(v,v,v).getRGB());
				}
			}
		}
		return finalBi;
	}
	
}
