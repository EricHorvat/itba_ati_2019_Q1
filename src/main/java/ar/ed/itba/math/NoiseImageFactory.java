package ar.ed.itba.math;

import ar.ed.itba.utils.Pair;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Stream;

public class NoiseImageFactory {

	private static Random random = new Random(System.currentTimeMillis());
	
	private NoiseImageFactory() { }
	
	public static BufferedImage rayleighNoiseImage(int width, int height, double percentage, double xi){
		Map<String, Double> params = new HashMap<>();
		params.put(RayleighNoiseGenerator.XI,xi);
		return imageGenerator(RayleighNoiseGenerator.getInstance(), width, height, percentage, params);
	}
	
	private static BufferedImage imageGenerator(NoiseGenerator noiseGenerator, int width, int height, double percentage, Map params){
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pairs.add(new Pair(i,j));
			}
		}
		int toBeChosen = (int)(percentage*width*height);
		Stream<Pair> pairStream = random.ints(toBeChosen,0,width*height).mapToObj(pairs::get);
		pairStream.forEach(p -> bi.setRGB(p.getX(), p.getY(),(int)noiseGenerator.getNextValue(params)));
		return bi;
	}
}
