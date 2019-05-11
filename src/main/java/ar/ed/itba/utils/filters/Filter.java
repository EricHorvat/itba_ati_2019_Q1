package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

public abstract class Filter {
 
	public ATIImage applyFilter(ATIImage sourceAtiImage, boolean ignoreBordersValue){
		return new PpmImage(applyFilterRaw(sourceAtiImage, ignoreBordersValue), sourceAtiImage.getWidth(), sourceAtiImage.getHeight());
	}
  
  public int[] applyFilterRaw(ATIImage sourceAtiImage, boolean ignoreBordersValue){
	  return applyFilterRaw(sourceAtiImage.toRGB(), ignoreBordersValue, sourceAtiImage.getWidth(),
      sourceAtiImage.getHeight());
  }
	
  public abstract int[] applyFilterRaw(int[] sourceAtiRGBArray, boolean ignoreBordersValue, int width, int height);
	
}
