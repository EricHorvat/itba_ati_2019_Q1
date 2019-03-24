package ar.ed.itba.utils.filters;

public class HighlightBorderFilter extends MaskFilter {
	
	public HighlightBorderFilter(int maskSide) {
		super(maskSide);
	}
	
	@Override
	protected int maskDivisor() {
		return (maskSide * maskSide);
	}
	
	@Override
	protected int[][] generateMask() {
		int center = maskSide / 2 + 1;
		int[][] mask = new int[maskSide][];
		for (int i = 0; i < maskSide; i++) {
			int[] column = new int[maskSide];
			for (int j = 0; j < maskSide; j++) {
				if (i == center && j == center){
					column[j] = maskDivisor - 1;
				} else {
					column[j] = -1;
				}
			}
			mask[i]=column;
		}
		return mask;
	}
}
