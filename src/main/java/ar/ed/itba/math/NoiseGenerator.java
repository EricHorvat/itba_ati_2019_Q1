package ar.ed.itba.math;

import java.util.Map;
import java.util.Random;

public abstract class NoiseGenerator {
	private Random random;
	
	/*package*/ NoiseGenerator() {
		this.random = new Random(System.currentTimeMillis());
	}
	
	public abstract double getNextValue(Map<String, ? extends Number> params);
}
