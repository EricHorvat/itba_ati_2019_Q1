package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class WeightMaskFilter extends MaskFilter{
	
	protected final int maskDivisor;
	
	protected abstract int maskDivisor();
	
	public WeightMaskFilter(int maskSide) {
		super(maskSide);
		this.maskDivisor = maskDivisor();
	}
	
	public ATIImage applyFilter(ATIImage sourceAtiImage){
		int maskCenter = maskSide/2;
		
		int imageWidth = sourceAtiImage.getWidth();
		int imageHeight = sourceAtiImage.getHeight();
		int[] sourceRGBArray = sourceAtiImage.toRGB();
		int[] finalRGBArray = new int[sourceRGBArray.length];
		
		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1){
					finalRGBArray[(i * imageWidth + j) * 3] = sourceRGBArray[(i * imageWidth + j) * 3];
					finalRGBArray[(i * imageWidth + j) * 3 + 1] = sourceRGBArray[(i * imageWidth + j) * 3 + 1];
					finalRGBArray[(i * imageWidth + j) * 3 + 2] = sourceRGBArray[(i * imageWidth + j) * 3 + 2];
				}else{
					double sumRed = 0, sumGreen = 0, sumBlue = 0;
					for (int k = 0; k < maskSide; k++) {
						for (int l = 0; l < maskSide; l++) {
							sumRed += sourceRGBArray[(i * imageWidth + j) * 3] * mask[k][l];
							sumGreen += sourceRGBArray[(i * imageWidth + j) * 3 + 1] * mask[k][l];
							sumBlue += sourceRGBArray[(i * imageWidth + j) * 3 + 2] * mask[k][l];
						}
					}
					if(j == 129){
						j = 129;
					}
					finalRGBArray[(i * imageWidth + j) * 3] = (int)(sumRed/maskDivisor);
					finalRGBArray[(i * imageWidth + j) * 3 + 1] = (int)(sumGreen/maskDivisor);
					finalRGBArray[(i * imageWidth + j) * 3 + 2] = (int)(sumBlue/maskDivisor);
					
				}
			}
		}
		
		return new PpmImage(finalRGBArray, imageWidth, imageHeight);
	}
	
}
