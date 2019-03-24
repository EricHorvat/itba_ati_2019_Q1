package ar.ed.itba.math;

import ar.ed.itba.utils.ProgramRandom;

import java.util.Map;

public class ExponentialNoiseGenerator extends NoiseGenerator{
	
	public final static String LAMBDA = "LAMBDA";
	
	public ExponentialNoiseGenerator() {
		super();
	}
	
	@Override
	public double getNextValue(Map<String, ? extends Number> params) {
		Double lambda = (Double) params.get(LAMBDA);
		return (int) Math.ceil((1.0 / lambda) * Math.log(ProgramRandom.random().nextDouble()));
		
	}
}
