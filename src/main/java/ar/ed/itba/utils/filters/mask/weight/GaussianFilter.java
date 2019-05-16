package ar.ed.itba.utils.filters.mask.weight;

import ar.ed.itba.utils.Pair;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import java.util.HashMap;
import java.util.Map;

public class GaussianFilter extends WeightMaskFilter {
	
	public GaussianFilter(int maskSide) {
		super(maskSide);
	}
	
	@Override
	protected double[][] generateMask() {
		//sigma = center
		int center = maskSide / 2;
		Map<Pair,Double> values = new HashMap<>();
		double[][] mask = new double[maskSide][];
		double sum = 0;
		for (int i = -center; i < center+1; i++) {
			double[] column = new double[maskSide];
			for (int j = -center; j < center+1; j++) {
				Pair p = new Pair(Math.abs(i), Math.abs(j));
				if (values.containsKey(p)){
					column[j+center] = values.get(p);
				} else {
					double value = getGaussianValue(i,j,center);
					values.put(p, value);
					column[j+center] = value;
				}
				sum += column[j+center];
			}
			mask[i+center]=column;
		}
		return mask;
	}
	
	@Override
	protected int maskDivisor() {
		return 1;
	}
	
	private double getGaussianValue(double i, double j, double sigma){
		return (1/(2*Math.PI*sigma*sigma)) * Math.pow(Math.E,(i*i+j*j)/(-sigma*sigma));
	}
}
