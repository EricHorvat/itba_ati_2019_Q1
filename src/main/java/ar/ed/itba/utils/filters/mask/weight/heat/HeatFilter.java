package ar.ed.itba.utils.filters.mask.weight.heat;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import static ar.ed.itba.utils.ImageUtils.indexRGB;

public abstract class HeatFilter extends WeightMaskFilter {
  
  private final double delta;
  
  public HeatFilter(double delta) {
    super(3);
    this.delta = delta;
  }
  
  
  @Override
  //fixed mask (not variable, gaussian)
  protected double[][] generateMask() {
    return new double[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
  }
    
    /*
     0 1 0    0 c 0       0           /DnIij*cnij 0
     1 1 1    c 4 c       /DoIij*coij 1           /DeIij*ceij
     0 1 0    0 c 0       0           /DsIij*csij 0
    
     */
  
  protected double[][] generateVariableMask(final int[] sourceRGBArray, final int width, final int x) {
    //sigma = center
    int center = maskSide / 2;
    double[][] mask = new double[maskSide][];
    for (int i = -center; i < center + 1; i++) {
      double[] column = new double[maskSide];
      for (int j = -center; j < center + 1; j++) {
        column[j + center] = getVariableValue(sourceRGBArray, x, i, j, width);
      }
      mask[i + center] = column;
    }
    return mask;
  }
  
  private double getVariableValue(final int[] sourceRGBArray, final int x, final int i, final int j,
                                  final int width) {
    if (Math.abs(i) + Math.abs(j) == 1) {
      double DIij = sourceRGBArray[x + indexRGB(i, j, width)] - sourceRGBArray[x];
      return delta * DIij * getCij(DIij);
    } else if (i == 0 && j == 0) {
      return 1;
    }
    return 0;
  }
  
  protected abstract double getCij(double dIij);
  
  @Override
  public int[] applyFilterRaw(ATIImage sourceAtiImage) {
    
    mask = generateMask();
    int maskCenter = maskSide / 2;
    int imageWidth = sourceAtiImage.getWidth();
    int imageHeight = sourceAtiImage.getHeight();
    int[] sourceRGBArray = sourceAtiImage.toRGB();
    int[] finalRGBArray = new int[sourceRGBArray.length];
    
    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageHeight; j++) {
        int indexRed = indexRGB(i,j,imageWidth);
        int indexGreen = indexRed + 1;
        int indexBlue = indexGreen + 1;
        if (i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter - 1) {
          finalRGBArray[indexRed] = sourceRGBArray[indexRed];
          finalRGBArray[indexGreen] = sourceRGBArray[indexGreen];
          finalRGBArray[indexBlue] = sourceRGBArray[indexBlue];
        } else {
          final double[][] variableMaskRed = generateVariableMask(sourceRGBArray, sourceAtiImage.getWidth(), indexRed);
          final double[][] variableMaskGreen = generateVariableMask(sourceRGBArray, sourceAtiImage.getWidth(), indexGreen);
          final double[][] variableMaskBlue = generateVariableMask(sourceRGBArray, sourceAtiImage.getWidth(), indexBlue);
          double sumRed = 0, sumGreen = 0, sumBlue = 0;
          for (int k = -maskCenter; k < maskCenter + 1; k++) {
            for (int l = -maskCenter; l < maskCenter + 1; l++) {
              int deltaIndex = (k * imageWidth + l) * 3;
              sumRed += sourceRGBArray[indexRed + deltaIndex] * mask[k + maskCenter][l + maskCenter] * variableMaskRed[k + maskCenter][l + maskCenter];
              sumGreen += sourceRGBArray[indexGreen + deltaIndex] * mask[k + maskCenter][l + maskCenter] * variableMaskGreen[k + maskCenter][l + maskCenter];
              sumBlue += sourceRGBArray[indexBlue + deltaIndex] * mask[k + maskCenter][l + maskCenter] * variableMaskBlue[k + maskCenter][l + maskCenter];
            }
          }
          
          /* HERE DO OLD + DELTA*/
          finalRGBArray[indexRed] = sourceRGBArray[indexRed] + (int) (sumRed);
          finalRGBArray[indexBlue] = sourceRGBArray[indexBlue] + (int) (sumBlue);
          finalRGBArray[indexGreen] = sourceRGBArray[indexGreen] + (int) (sumGreen);
        }
      }
    }
    return finalRGBArray;
  }
  
  @Override
  protected int maskDivisor() {
    return 1;
  }
}
