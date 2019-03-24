package ar.ed.itba.utils;

import java.util.Random;

public final class ProgramRandom {
	private ProgramRandom() {
	}
	
	private static Random random = new Random(System.currentTimeMillis());
	
	public static Random random(){return random;}
}
