package ar.ed.itba.utils.filters.mask.gradient.prefilter;

import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.X;

public class KirshPreFilter extends WeightMaskFilter {
	
	private PrefilterOrientation orientation;
	
	static private double[][] xMask = {{-3,-3,5},{-3,0,5},{-3,-3,5}};
	static private double[][] yMask = {{-3,-3,-3},{-3,0,-3},{5,5,5}};
	
	public KirshPreFilter(PrefilterOrientation orientation) {
		super(3);
		this.orientation = orientation;
	}
	
	@Override
	protected int maskDivisor() {
		return (maskSide * maskSide);
	}
	
	@Override
	protected double[][] generateMask() {
		return orientation == X? xMask:yMask;
	}
	
}
