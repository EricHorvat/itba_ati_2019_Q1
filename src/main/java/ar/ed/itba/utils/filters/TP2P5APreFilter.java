package ar.ed.itba.utils.filters;

import static ar.ed.itba.utils.filters.PrefilterOrientation.X;

public class TP2P5APreFilter extends WeightMaskFilter {
	
	private PrefilterOrientation orientation;
	
	static private double[][] xMask = {{-1,1,1},{-1,-2,1},{-1,1,1}};
	static private double[][] yMask = {{-1,-1,-1},{1,-2,1},{1,1,1}};
	
	public TP2P5APreFilter(PrefilterOrientation orientation) {
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
