package ar.ed.itba.utils.filters;

public class MediaFilter extends WeightMaskFilter {
	
	public MediaFilter(int maskSide) {
		super(maskSide);
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
				column[j] = 1;
			}
			mask[i]=column;
		}
		return mask;
	}
}
