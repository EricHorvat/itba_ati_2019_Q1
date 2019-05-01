package ar.ed.itba.utils.filters.mask;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import java.awt.image.BufferedImage;

public abstract class MaskFilter {
	
	protected double[][] mask;
	protected final int maskSide;
	
	protected abstract double[][] generateMask();
	
	public MaskFilter(int maskSide) {
		this.maskSide = maskSide;
	}
	
	public ATIImage applyFilter(ATIImage sourceAtiImage, boolean ignoreBordersValue){
		return new PpmImage(applyFilterRaw(sourceAtiImage, ignoreBordersValue), sourceAtiImage.getWidth(), sourceAtiImage.getHeight());
	}
	
	public abstract int[] applyFilterRaw(ATIImage sourceAtiImage, boolean ignoreBordersValue);
	
}
