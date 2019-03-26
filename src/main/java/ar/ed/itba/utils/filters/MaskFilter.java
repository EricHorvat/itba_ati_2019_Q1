package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;

import java.awt.image.BufferedImage;

public abstract class MaskFilter {
	
	protected final double[][] mask;
	protected final int maskSide;
	
	protected abstract double[][] generateMask();
	
	public MaskFilter(int maskSide) {
		this.maskSide = maskSide;
		this.mask = generateMask();
	}
	
	public abstract ATIImage applyFilter(ATIImage sourceAtiImage);
	
}
