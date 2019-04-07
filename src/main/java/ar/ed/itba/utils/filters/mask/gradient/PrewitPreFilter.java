package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

public class PrewitPreFilter extends WeightMaskFilter {
	
	private PrefilterOrientation orientation;
	
	public PrewitPreFilter(PrefilterOrientation orientation) {
		super(3);
		this.orientation = orientation;
	}
	
	@Override
	protected int maskDivisor() {
		return (maskSide * maskSide);
	}
	
	@Override
	protected double[][] generateMask() {
		double[][] mask = new double[maskSide][];
		for (int i = 0; i < maskSide; i++) {
			double[] column = new double[maskSide];
			for (int j = 0; j < maskSide; j++) {
				column[j] = getValue(i,j);
			}
			mask[i]=column;
		}
		return mask;
	}
	
	private int getValue(int i, int j){
		int value;
		switch (orientation){
			case X:
				value = i==0?-1:i==1?0:1;
				break;
			case Y:
			default:
				value = j==0?-1:j==1?0:1;
				break;
		}
		return value;
	}
	
}
