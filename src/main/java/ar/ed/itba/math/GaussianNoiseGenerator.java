package ar.ed.itba.math;

import ar.ed.itba.utils.ProgramRandom;

import java.util.Map;

public class GaussianNoiseGenerator extends NoiseGenerator{
	
	public final static String SIGMA = "SIGMA";
	public final static String MU = "MU";
	private static GaussianNoiseGenerator instance;
	
	public GaussianNoiseGenerator() {
		super();
	}
	
	public static GaussianNoiseGenerator getInstance() {
		if(instance == null){
			instance = new GaussianNoiseGenerator();
		}
		return instance;
	}
	
	@Override
	public double getNextValue(Map<String, ? extends Number> params) {
		Double sigma = (Double) params.get(SIGMA);
		Double mu = (Double) params.get(MU);
		double x1 = ProgramRandom.random().nextDouble();
		double x2 = ProgramRandom.random().nextDouble();
		double ans = (Math.sqrt(-2 * Math.log(x1))*Math.cos(2*Math.PI*x2))*sigma + mu;
		System.out.println(ans);
		return ans;
	}
}
