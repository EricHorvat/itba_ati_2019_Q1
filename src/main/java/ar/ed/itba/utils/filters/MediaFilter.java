package ar.ed.itba.utils.filters;

public class MediaFilter extends MaskFilter {
	
	public MediaFilter(int maskSide) {
		super(maskSide);
	}
	
	@Override
	protected int maskDivisor() {
		return (maskSide * maskSide);
	}
	
	@Override
	protected int[][] generateMask() {
		int[][] mask = new int[maskSide][];
		for (int i = 0; i < maskSide; i++) {
			int[] column = new int[maskSide];
			for (int j = 0; j < maskSide; j++) {
				column[j] = 1;
			}
			mask[i]=column;
		}
		return mask;
	}
}
