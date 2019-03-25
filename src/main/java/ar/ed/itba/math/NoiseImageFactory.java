package ar.ed.itba.math;

import ar.ed.itba.utils.Pair;

import java.awt.image.BufferedImage;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NoiseImageFactory {

	private static Random random = new Random(System.currentTimeMillis());
	
	private NoiseImageFactory() { }
	
	public static BufferedImage rayleighNoiseImage(int width, int height, double percentage, double phi){
		Map<String, Double> params = new HashMap<>();
		params.put(RayleighNoiseGenerator.PHI,phi);
		return imageGenerator(RayleighNoiseGenerator.getInstance(), width, height, percentage, params);
	}
	
	public static BufferedImage exponentialNoiseImage(int width, int height, double percentage, double lambda){
		Map<String, Double> params = new HashMap<>();
		params.put(ExponentialNoiseGenerator.LAMBDA,lambda);
		return imageGenerator(ExponentialNoiseGenerator.getInstance(), width, height, percentage, params);
	}
	
	public static BufferedImage gaussianNoiseImage(int width, int height, double percentage, double sigma, double mu){
		Map<String, Double> params = new HashMap<>();
		params.put(GaussianNoiseGenerator.SIGMA, sigma);
		params.put(GaussianNoiseGenerator.MU, mu);
		return imageGenerator(GaussianNoiseGenerator.getInstance(), width, height, percentage, params);
	}
	
	private static BufferedImage imageGenerator(NoiseGenerator noiseGenerator, int width, int height, double percentage, Map params){
		BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_BYTE_GRAY);
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pairs.add(new Pair(i,j));
				//bi.setRGB(i,j,0xFFFFFF);
			}
		}
		List<Pair> pairList = (List<Pair>)(IntStream.range(0, width * height).mapToObj(pairs::get).collect(Collectors.toList()));
		Collections.shuffle(pairList, random);
		int toBeChosen = (int)(percentage*width*height);
		pairList.subList(0,toBeChosen).forEach(p -> bi.setRGB(p.getX(), p.getY(),(int)noiseGenerator.getNextValue(params)));
		return bi;
	}
}
