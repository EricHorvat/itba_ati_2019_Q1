package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;

import java.awt.image.BufferedImage;

public abstract class MaskFilter {
	
	protected double[][] mask;
	protected final int maskSide;
	
	protected abstract double[][] generateMask();
	
	public MaskFilter(int maskSide) {
		this.maskSide = maskSide;
	}
	
	public abstract ATIImage applyFilter(ATIImage sourceAtiImage);
	
	public abstract int[] applyFilterRaw(ATIImage sourceAtiImage);
	
}
