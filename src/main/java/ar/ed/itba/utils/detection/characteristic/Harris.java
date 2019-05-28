package ar.ed.itba.utils.detection.characteristic;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.filters.mask.weight.GaussianFilter;
import ar.ed.itba.utils.threshold.GlobalThreshold;
import ar.ed.itba.utils.threshold.OtsuThreshold;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.detection.characteristic.HarrisHelper.MaskHelper.getIx;
import static ar.ed.itba.utils.detection.characteristic.HarrisHelper.MaskHelper.getIy;


public class Harris  {
  
  public ATIImage applyFilter(ATIImage sourceRGBImage, boolean ignoreBordersValue, double tolerance) {
    
    int[] sourceRGBArray = sourceRGBImage.toRGB();
    int imageWidth = sourceRGBImage.getWidth(), imageHeight = sourceRGBImage.getHeight();
  
    int[] Ix = getIx(sourceRGBArray, imageWidth, imageHeight);
    int[] Iy = getIy(sourceRGBArray, imageWidth, imageHeight);
  
    int[] Ix2 = ImageUtils.pow(Ix,2,imageWidth,imageHeight,false);
    int[] Iy2 = ImageUtils.pow(Iy,2,imageWidth,imageHeight,false);
    
    GaussianFilter g = new GaussianFilter(7);
    g.setSigma(2);
    
    int[] Ix2G = g.applyFilterRaw(Ix2,true,imageWidth,imageHeight);
    int[] Iy2G = g.applyFilterRaw(Iy2,true,imageWidth,imageHeight);
  
    int[] Ixy = ImageUtils.multiply(Ix, Iy, imageWidth, imageHeight);
    int[] IxyG = g.applyFilterRaw(Ixy,true,imageWidth,imageHeight);
    
    int[] ans2 = HarrisHelper.getCim(Ix2G,Iy2G,IxyG,imageWidth,imageHeight, tolerance);
  
    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageHeight; j++) {
        int index = indexRGB(i,j,imageWidth);
        if (ans2[index] == 255) {
          sourceRGBArray[red(index)] = 255;
          sourceRGBArray[green(index)] = 0;
          sourceRGBArray[blue(index)] = 0;
        }
      }
    }
    
    ATIImage ans = new PpmImage(sourceRGBArray, imageWidth, imageHeight);
    
    //ImageUtils.threshold(ans,threshold);
    
    return ans;
  }
  
}
