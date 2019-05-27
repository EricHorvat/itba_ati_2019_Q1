package ar.ed.itba.utils.detection.characteristic;

import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.gradient.SobelFilter;

import java.util.Arrays;

/*package*/ class HarrisHelper {
  
  private final static double k = 0.04;
  
  /*package*/ static class MaskHelper{
    
    /*package*/ static int[] getIx(int[] image, int imageWidth, int imageHeight){
      SobelFilter xSobelFilter = new SobelFilter(GradientFilterType.HOR);
      return xSobelFilter.applyFilterRaw(image, false, imageWidth, imageHeight);
    }
    
    /*package*/ static int[] getIy(int[] image, int imageWidth, int imageHeight){
      SobelFilter ySobelFilter = new SobelFilter(GradientFilterType.VER);
      return ySobelFilter.applyFilterRaw(image, false, imageWidth, imageHeight);
    }
  }
  
  static int[] getCim(int[] Ix2, int[] Iy2,int[] Ixy, int imageWidth, int imageHeight, double tolerance){
     return getCim1(Ix2, Iy2, Ixy, imageWidth, imageHeight, tolerance);
  }
  
  private static int[] getCim1(int[] Ix2, int[] Iy2,int[] Ixy, int imageWidth, int imageHeight, double tolerance){
    int[] pixels = new int[ImageUtils.lengthRGB(imageWidth, imageHeight)];
    for (int i = 0; i < pixels.length; i++) {
        pixels[i] = (Ix2[i] * Iy2[i] - Ixy[i] * Ixy[i]) - (int)(k * Math.pow(Ix2[i] + Iy2[i], 2));
      }
    int max = Arrays.stream(pixels).max().orElse(0);
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = pixels[i] > max * tolerance ? 255 : 0;
    }
    return pixels;
  }
  
}
