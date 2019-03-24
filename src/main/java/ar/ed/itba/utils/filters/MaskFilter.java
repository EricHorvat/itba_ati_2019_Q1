package ar.ed.itba.utils.filters;

import java.awt.image.BufferedImage;

public abstract class MaskFilter {
	
	private final int[][] mask;
	protected final int maskSide;
	protected final int maskDivisor;
	
	protected abstract int[][] generateMask();
	protected abstract int maskDivisor();
	
	public MaskFilter(int maskSide) {
		this.maskSide = maskSide;
		this.maskDivisor = maskDivisor();
		this.mask = generateMask();
	}
	
	public BufferedImage applyFilter(BufferedImage sourceBi){
		int centerDistance = maskSide/2;
		int center = centerDistance + 1;
		BufferedImage finalBi = new BufferedImage(sourceBi.getWidth(), sourceBi.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
		for (int i = 0; i < sourceBi.getWidth(); i++) {
			for (int j = 0; j < sourceBi.getHeight(); j++) {
				if ( i < centerDistance || j < centerDistance || i > sourceBi.getWidth() - center || j > sourceBi.getHeight() - center){
					finalBi.setRGB(i,j,sourceBi.getRGB(i,j));
				}else{
					int sum = 0;
					for (int k = 0; k < maskSide; k++) {
						for (int l = 0; l < maskSide; l++) {
							sum += sourceBi.getRGB(i+k-centerDistance, j+l-centerDistance) * mask[k][l];
						}
					}
					finalBi.setRGB(i,j,sum/maskDivisor);
				}
			}
		}
		return finalBi;
	}
	
}
