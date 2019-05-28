package ar.ed.itba.utils.detection.characteristic;

import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.gradient.SobelFilter;

import java.util.Arrays;

/*package*/ class HarrisHelper {
  
  private final static double k = 0.04;
  private final static double eps = 0.04;
  
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
    double[] pixels = new double[ImageUtils.lengthRGB(imageWidth, imageHeight)];
    int[] ans = new int[ImageUtils.lengthRGB(imageWidth, imageHeight)];
    for (int i = 0; i < pixels.length; i++) {
      pixels[i] = getCim1(Ix2[i], Iy2[i], Ixy[i]);
    }
    double max = Arrays.stream(pixels).max().orElse(0);
    for (int i = 0; i < pixels.length; i++) {
      ans[i] = pixels[i] > max * tolerance ? 255 : 0;
    }
    return ans;
  }
  
  private static double getCim1(int Ix2, int Iy2,int Ixy){
    return (Ix2 * Iy2 - Ixy * Ixy) - (k * Math.pow(Ix2 + Iy2, 2));
  }
  
  private static double getCim2(int Ix2, int Iy2,int Ixy){
    return (Ix2 * Iy2 - Ixy * Ixy) / (eps + Ix2 + Iy2);
  }
  
  private static double getCim3(int Ix2, int Iy2,int Ixy){
    return (Ix2 * Iy2 - Ixy * Ixy * Ixy * Ixy) - (k * Math.pow(Ix2 + Iy2, 2));
  }
  
}
