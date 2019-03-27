package ar.ed.itba.utils.filters;

public class PonderatedMedianFilter extends MedianMaskFilter {
	
	public PonderatedMedianFilter() {
		super(3);
	}
	
	@Override
	protected double[][] generateMask() {
		double[][] mask = {{1,2,1},{2,4,2},{1,2,1}};
		/*for (int i = 0; i < maskSide; i++) {
			double[] column = new double[maskSide];
			for (int j = 0; j < maskSide; j++) {
				column[j] = 1;
			}
			mask[i]=column;
		}*/
		
		return mask;
	}
}
