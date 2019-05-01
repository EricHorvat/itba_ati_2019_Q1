package ar.ed.itba.utils.filters.mask.weight.heat;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import java.util.*;

import static ar.ed.itba.utils.ImageUtils.*;

public abstract class HeatFilter extends WeightMaskFilter {
  
  private final double delta;
  private final double finalT;
  
  public HeatFilter(double delta, int finalT) {
    super(3);
    this.delta = delta;
    this.finalT = finalT;
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
  
    mask = mask == null?generateMask():mask;
    int maskCenter = maskSide / 2;
    int imageWidth = sourceAtiImage.getWidth();
    int imageHeight = sourceAtiImage.getHeight();
    int[] sourceRGBArray = sourceAtiImage.toRGB();
    Deque<int[]> imageArrayList = new ArrayDeque<>();
    imageArrayList.add(sourceRGBArray);
    for (int it = 0; it < finalT; it++){
      int[] appliedResult = basicLoop(imageArrayList.getLast(), imageWidth, imageHeight, maskCenter);
      imageArrayList.add(appliedResult);
    }
    
    return imageArrayList.getLast();
  }
  
  private int[] basicLoop(int[] sourceRGBArray, int imageWidth, int imageHeight, int maskCenter){
    int[] finalRGBArray = new int[sourceRGBArray.length];
  
    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageHeight; j++) {
        int indexRGB = indexRGB(i,j,imageWidth);
        if (i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter - 1) {
          finalRGBArray[red(indexRGB)] = sourceRGBArray[red(indexRGB)];
          finalRGBArray[green(indexRGB)] = sourceRGBArray[green(indexRGB)];
          finalRGBArray[blue(indexRGB)] = sourceRGBArray[blue(indexRGB)];
        } else {
          final double[][] variableMaskRed = generateVariableMask(sourceRGBArray, imageWidth, red(indexRGB));
          final double[][] variableMaskGreen = generateVariableMask(sourceRGBArray, imageWidth, green(indexRGB));
          final double[][] variableMaskBlue = generateVariableMask(sourceRGBArray, imageWidth, blue(indexRGB));
          double sumRed = 0, sumGreen = 0, sumBlue = 0;
          for (int k = -maskCenter; k < maskCenter + 1; k++) {
            for (int l = -maskCenter; l < maskCenter + 1; l++) {
              int deltaIndex = indexRGB(k,l,imageWidth);
              sumRed += sourceRGBArray[red(indexRGB) + deltaIndex] * mask[k + maskCenter][l + maskCenter] * variableMaskRed[k + maskCenter][l + maskCenter];
              sumGreen += sourceRGBArray[green(indexRGB) + deltaIndex] * mask[k + maskCenter][l + maskCenter] * variableMaskGreen[k + maskCenter][l + maskCenter];
              sumBlue += sourceRGBArray[blue(indexRGB) + deltaIndex] * mask[k + maskCenter][l + maskCenter] * variableMaskBlue[k + maskCenter][l + maskCenter];
            }
          }
        
          /* HERE DO OLD + DELTA*/
          finalRGBArray[red(indexRGB)] = sourceRGBArray[red(indexRGB)] + (int) (sumRed);
          finalRGBArray[green(indexRGB)] = sourceRGBArray[green(indexRGB)] + (int) (sumGreen);
          finalRGBArray[blue(indexRGB)] = sourceRGBArray[blue(indexRGB)] + (int) (sumBlue);
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
