package ar.ed.itba.math;

import ar.ed.itba.utils.ProgramRandom;

import java.util.Map;

public class RayleighNoiseGenerator extends NoiseGenerator{
	
	public final static String PHI = "PHI";
	private static RayleighNoiseGenerator instance;
	
	private RayleighNoiseGenerator() {
		super();
	}
	
	public static RayleighNoiseGenerator getInstance() {
		if (instance == null){
			instance = new RayleighNoiseGenerator();
		}
		return instance;
	}
	
	@Override
	public double getNextValue(Map<String, ? extends Number> params) {
		Double phi = (Double) params.get(PHI);
		double x = ProgramRandom.random().nextDouble();
		return phi * Math.sqrt(-2 * Math.log(1-x));
	}
}
