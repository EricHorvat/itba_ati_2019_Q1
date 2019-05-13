package ar.ed.itba.math;

import ar.ed.itba.utils.CoordinatePair;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ar.ed.itba.utils.ImageUtils.*;

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
		double[] imageRGB = new double[lengthRGB(width,height)];
		List<CoordinatePair> coordinatePairs = new ArrayList<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				coordinatePairs.add(new CoordinatePair(i,j));
				int indexRGB = indexRGB(i,j, width);
				imageRGB[red(indexRGB)] = defaultValue;
				imageRGB[green(indexRGB)] = defaultValue;
				imageRGB[blue(indexRGB)] = defaultValue;
			}
		}
		List<CoordinatePair> coordinatePairList = IntStream.range(0, width * height).mapToObj(coordinatePairs::get).collect(Collectors.toList());
		Collections.shuffle(coordinatePairList, random);
		int toBeChosen = (int)(percentage*width*height);
		coordinatePairList.subList(0,toBeChosen).forEach(p -> copyValue(imageRGB, p.getX(), p.getY(), width, noiseGenerator.getNextValue(params)));
		return imageRGB;
	}
	
	private static void copyValue(double[] image, int x, int y, int width, double value){
		int index = indexRGB(x,y,width);
		image[red(index)] = value;
		image[green(index)] = value;
		image[blue(index)] = value;
	}
}
