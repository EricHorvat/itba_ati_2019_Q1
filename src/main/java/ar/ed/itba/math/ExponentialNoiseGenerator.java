package ar.ed.itba.math;

import ar.ed.itba.utils.ProgramRandom;

import java.util.Map;

public class ExponentialNoiseGenerator extends NoiseGenerator{
	
	public final static String LAMBDA = "LAMBDA";
	private static ExponentialNoiseGenerator instance;
	
	public ExponentialNoiseGenerator() {
		super();
	}
	
	public static ExponentialNoiseGenerator getInstance() {
		if(instance == null){
			instance = new ExponentialNoiseGenerator();
		}
		return instance;
	}
	
	@Override
	public double getNextValue(Map<String, ? extends Number> params) {
		Double lambda = (Double) params.get(LAMBDA);
		double x = ProgramRandom.random().nextDouble();
		return -(1.0 / lambda) * Math.log(x);
		
	}
}
