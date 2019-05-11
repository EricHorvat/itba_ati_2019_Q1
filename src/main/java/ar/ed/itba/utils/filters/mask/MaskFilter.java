package ar.ed.itba.utils.filters.mask;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.Filter;

import java.awt.image.BufferedImage;

public abstract class MaskFilter extends Filter {
	
	protected double[][] mask;
	protected final int maskSide;
	
	protected abstract double[][] generateMask();
	
	public MaskFilter(int maskSide) {
		this.maskSide = maskSide;
	}
	
}
