package ar.ed.itba.math;

import ar.ed.itba.utils.ProgramRandom;

import java.util.Map;

public class RayleighNoiseGenerator extends NoiseGenerator{
	
	public final static String XI = "XI";
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
		Double xi = (Double) params.get(XI);
		double x = ProgramRandom.random().nextDouble();
		return xi * Math.sqrt(-2 * Math.log(1-x));
	}
}
