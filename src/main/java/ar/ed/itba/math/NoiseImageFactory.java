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
	
	public static double[] rayleighNoiseImage(int width, int height, double percentage, double phi, int defaultValue){
		Map<String, Double> params = new HashMap<>();
		params.put(RayleighNoiseGenerator.PHI,phi);
		return imageGenerator(RayleighNoiseGenerator.getInstance(), width, height, percentage, params, defaultValue);
	}
	
	public static double[] exponentialNoiseImage(int width, int height, double percentage, double lambda, int defaultValue){
		Map<String, Double> params = new HashMap<>();
		params.put(ExponentialNoiseGenerator.LAMBDA,lambda);
		return imageGenerator(ExponentialNoiseGenerator.getInstance(), width, height, percentage, params,defaultValue);
	}
	
	public static double[] gaussianNoiseImage(int width, int height, double percentage, double sigma, double mu, int defaultValue){
		Map<String, Double> params = new HashMap<>();
		params.put(GaussianNoiseGenerator.SIGMA, sigma);
		params.put(GaussianNoiseGenerator.MU, mu);
		return imageGenerator(GaussianNoiseGenerator.getInstance(), width, height, percentage, params,defaultValue);
	}
	
	private static double[] imageGenerator(NoiseGenerator noiseGenerator, int width, int height, double percentage, Map params, int defaultValue){
		double[] imageRGB = new double[width*height*3];
		List<Pair> pairs = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				pairs.add(new Pair(i,j));
				imageRGB[(i * width + j) * 3] = defaultValue;
				imageRGB[(i * width + j) * 3+1] = defaultValue;
				imageRGB[(i * width + j) * 3+2] = defaultValue;
			}
		}
		List<Pair> pairList = IntStream.range(0, width * height).mapToObj(pairs::get).collect(Collectors.toList());
		Collections.shuffle(pairList, random);
		int toBeChosen = (int)(percentage*width*height);
		pairList.subList(0,toBeChosen).forEach(p -> copyValue(imageRGB, p.getX(), p.getY(), width, noiseGenerator.getNextValue(params)));
		return imageRGB;
	}
	
	private static void copyValue(double[] image, int x, int y, int width, double value){
		int index = (x * width + y) * 3;
		image[index] = value;
		image[index + 1] = value;
		image[index + 2] = value;
	}
}
