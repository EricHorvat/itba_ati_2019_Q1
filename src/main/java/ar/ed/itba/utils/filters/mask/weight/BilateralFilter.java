package ar.ed.itba.utils.filters.mask.weight;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.Pair;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import java.util.HashMap;
import java.util.Map;

public class BilateralFilter extends WeightMaskFilter {

  private final int sigmaR;

  public BilateralFilter(final int maskSide, final int sigmaR) {
    super(maskSide);
    this.sigmaR = sigmaR;
  }

  @Override
  //fixed mask (not variable, gaussian)
  protected double[][] generateMask() {
    //sigma = center
    int center = maskSide / 2;
    Map<Pair,Double> values = new HashMap<>();
    double[][] mask = new double[maskSide][];
    for (int i = -center; i < center+1; i++) {
      double[] column = new double[maskSide];
      for (int j = -center; j < center+1; j++) {
        Pair p = new Pair(Math.abs(i), Math.abs(j));
        if (values.containsKey(p)){
          column[j+center] = values.get(p);
        } else {
          double value = getGaussianValue(i,j, center);
          values.put(p, value);
          column[j+center] = value;
        }
      }
      mask[i+center]=column;
    }
    return mask;
  }

  protected double[][] generateVariableMask(final int[] sourceRGBArray, final int width, final int x) {
    //sigma = center
    int center = maskSide / 2;
    double[][] mask = new double[maskSide][];
    for (int i = -center; i < center+1; i++) {
      double[] column = new double[maskSide];
      for (int j = -center; j < center+1; j++) {
        column[j + center] = getIntensityDifference(sourceRGBArray, x, x + (i * width + j) * 3);
      }
      mask[i+center]=column;
    }
    return mask;
  }

  @Override
  public int[] applyFilterRaw(ATIImage sourceAtiImage) {
  
    mask = mask == null?generateMask():mask;
    int maskCenter = maskSide /2;
  
    int imageWidth = sourceAtiImage.getWidth();
    int imageHeight = sourceAtiImage.getHeight();
    int[] sourceRGBArray = sourceAtiImage.toRGB();
    int[] finalRGBArray = new int[sourceRGBArray.length];
  
    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageHeight; j++) {
        int indexRed = (i * imageWidth + j) * 3;
        int indexGreen = indexRed + 1;
        int indexBlue = indexGreen + 1;
        if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1) {
          finalRGBArray[indexRed] = sourceRGBArray[indexRed];
          finalRGBArray[indexGreen] = sourceRGBArray[indexGreen];
          finalRGBArray[indexBlue] = sourceRGBArray[indexBlue];
        }else{
          final double[][] variableMaskRed = generateVariableMask(sourceRGBArray, sourceAtiImage.getWidth(), indexRed);
          final double[][] variableMaskGreen = generateVariableMask(sourceRGBArray, sourceAtiImage.getWidth(), indexGreen);
          final double[][] variableMaskBlue = generateVariableMask(sourceRGBArray, sourceAtiImage.getWidth(), indexBlue);
          double sumRed = 0, sumGreen = 0, sumBlue = 0;
          for (int k = -maskCenter; k < maskCenter + 1; k++) {
            for (int l = -maskCenter; l < maskCenter + 1; l++) {
              int deltaIndex = (k * imageWidth + l) * 3;
              sumRed += sourceRGBArray[indexRed + deltaIndex] * mask[k+maskCenter][l+maskCenter] * variableMaskRed[k+maskCenter][l+maskCenter];
              sumGreen += sourceRGBArray[indexGreen + deltaIndex] * mask[k+maskCenter][l+maskCenter] * variableMaskGreen[k+maskCenter][l+maskCenter];
              sumBlue += sourceRGBArray[indexBlue + deltaIndex] * mask[k+maskCenter][l+maskCenter] * variableMaskBlue[k+maskCenter][l+maskCenter];
            }
          }
          finalRGBArray[indexRed] = (int)(sumRed/normalizationScalar(variableMaskRed));
          finalRGBArray[indexGreen] = (int)(sumGreen/normalizationScalar(variableMaskGreen));
          finalRGBArray[indexBlue] = (int)(sumBlue/normalizationScalar(variableMaskBlue));
        }
      }
    }
    return finalRGBArray;
  }

  private double getGaussianValue(double i, double j, double sigmaS){
    return Math.pow(Math.E,-1 * (i*i + j*j)/(2 * sigmaS * sigmaS));
  }

  private double getIntensityDifference(final int[] sourceRGBArray, final int x, final int x1) {
    return Math.pow(Math.E, -1 * Math.pow(Math.abs(sourceRGBArray[x] - sourceRGBArray[x1]),2)/ (2 * Math.pow(sigmaR, 2)));
  }

  private double normalizationScalar(final double[][] variableMask) {
    double normalizationScalar = 0;
    //square matrix. fixed mask size = variable mask size
    for (int i = 0 ; i < variableMask.length ; i++) {
      for (int j = 0 ; j < variableMask.length ; j++)
        normalizationScalar += variableMask[i][j] * mask[i][j];
    }
    return normalizationScalar;
  }

  @Override
  protected int maskDivisor() {
    return 1;
  }
}
