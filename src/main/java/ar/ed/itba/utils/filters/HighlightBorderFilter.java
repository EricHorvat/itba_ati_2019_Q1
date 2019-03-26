package ar.ed.itba.utils.filters;

public class HighlightBorderFilter extends WeightMaskFilter {
	
	public HighlightBorderFilter(int maskSide) {
		super(maskSide);
	}
	
	@Override
	protected int maskDivisor() {
		return (maskSide * maskSide);
	}
	
	@Override
	protected double[][] generateMask() {
		int maskDivisor = maskDivisor();
		int center = maskSide / 2;
		double[][] mask = new double[maskSide][];
		for (int i = 0; i < maskSide; i++) {
			double[] column = new double[maskSide];
			for (int j = 0; j < maskSide; j++) {
				if (i == center && j == center){
					column[j] = maskDivisor;
				} else {
					column[j] = -1;
				}
			}
			mask[i]=column;
		}
		return mask;
	}
}
