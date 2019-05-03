package ar.ed.itba.utils.filters.mask.advanced;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.ImageUtils.blue;

public class SusanFilter extends MaskFilter {
  
  private final double epsilon;
  
  public SusanFilter(double epsilon) {
    super(7);
    this.epsilon = epsilon;
  }
  
  private static final double[][] mask = {
    {0,0,1,1,1,0,0},
    {0,1,1,1,1,1,0},
    {1,1,1,1,1,1,1},
    {1,1,1,1,1,1,1},
    {1,1,1,1,1,1,1},
    {0,1,1,1,1,1,0},
    {0,0,1,1,1,0,0}
  };
  
  @Override
  protected double[][] generateMask() {
    return mask;
  }
  
  @Override
  public int[] applyFilterRaw(ATIImage sourceAtiImage, boolean ignoreBordersValue) {
  
    int maskCenter = maskSide /2;
    
    int imageWidth = sourceAtiImage.getWidth();
    int imageHeight = sourceAtiImage.getHeight();
    int[] sourceRGBArray = sourceAtiImage.toRGB();
    int[] finalRGBArray = new int[sourceRGBArray.length];
  
  
    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageHeight; j++) {
        int indexRGB = indexRGB(i, j, imageWidth);
        if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1) {
          finalRGBArray[red(indexRGB)] = ignoreBordersValue ? 0 : sourceRGBArray[red(indexRGB)];
          finalRGBArray[green(indexRGB)] = ignoreBordersValue ? 0 : sourceRGBArray[green(indexRGB)];
          finalRGBArray[blue(indexRGB)] = ignoreBordersValue ? 0: sourceRGBArray[blue(indexRGB)];
        }else{
          int sum = 0;
          for (int k = -maskCenter; k < maskCenter + 1; k++) {
            for (int l = -maskCenter; l < maskCenter + 1; l++) {
              int deltaIndex = indexRGB(k, l, imageWidth);
              sum += isNearValue(sourceRGBArray[red(indexRGB) + deltaIndex], sourceRGBArray[indexRGB]) ? mask[k+maskCenter][l+maskCenter] : 0;
            }
          }
          double sumRelative = sum / N;
          if ( sumRelative > (0.5 - epsilon) && sumRelative < (0.5 + epsilon)){
            finalRGBArray[red(indexRGB)] = 255;
            finalRGBArray[green(indexRGB)] = 0;
            finalRGBArray[blue(indexRGB)] = 0;
          } else if (sumRelative > (0.75 - epsilon) && sumRelative < (0.75 + epsilon)) {
            finalRGBArray[red(indexRGB)] = 0;
            finalRGBArray[green(indexRGB)] = 255;
            finalRGBArray[blue(indexRGB)] = 255;
          } else {
            finalRGBArray[red(indexRGB)] = sourceRGBArray[red(indexRGB)];
            finalRGBArray[green(indexRGB)] = sourceRGBArray[green(indexRGB)];
            finalRGBArray[blue(indexRGB)] = sourceRGBArray[blue(indexRGB)];
          }
        }
      }
    }
    return finalRGBArray;
  }
  
  
  private final static int T = 27;
  private final static double N = 37.0;
  
  private boolean isNearValue(int v1, int v2){
    return Math.abs(v1-v2) > T;
  }
}
