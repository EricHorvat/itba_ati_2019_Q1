package ar.ed.itba.utils.filters.mask.laplacian;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.Pair;
import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import java.util.HashMap;
import java.util.Map;

import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.X;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.Y;

public class LoGFilter extends LaplacianFilter {
	
	private final int sigma;
	public LoGFilter(int sigma) {
		super(true, 2 * sigma + 1);
		this.sigma = sigma;
	}
	
	@Override
	protected int maskDivisor() {
		return 1;
	}
	
	@Override
	protected double[][] generateMask() {
		int center = sigma;
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
	
	private double getGaussianValue(double i, double j, double sigma){
		return - (1/(Math.sqrt(2*Math.PI) * sigma * sigma * sigma))
			* (2 - ((i*i + j*j) / (sigma*sigma)))
			* Math.pow(Math.E,-(i*i+j*j)/(2*sigma*sigma));
	}
}
