package ar.ed.itba.utils.filters.mask.weight;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import static ar.ed.itba.utils.ImageUtils.*;

public abstract class WeightMaskFilter extends MaskFilter {
	
	protected final int maskDivisor;
	
	protected abstract int maskDivisor();
	
	public WeightMaskFilter(int maskSide) {
		super(maskSide);
		this.maskDivisor = maskDivisor();
	}
	
	@Override
	public int[] applyFilterRaw(int[] sourceRGBArray, boolean ignoreBordersValue, int imageWidth, int imageHeight) {
		
		mask = mask == null?generateMask():mask;
    int maskCenter = maskSide/2;
		int[] finalRGBArray = new int[sourceRGBArray.length];
		
		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				int indexRGB = indexRGB(i,j,imageWidth);
				if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1){
					finalRGBArray[red(indexRGB)] = ignoreBordersValue ? 0 : sourceRGBArray[red(indexRGB)];
					finalRGBArray[green(indexRGB)] = ignoreBordersValue ? 0 : sourceRGBArray[green(indexRGB)];
					finalRGBArray[blue(indexRGB)] = ignoreBordersValue ? 0 : sourceRGBArray[blue(indexRGB)];
				}else{
					double sumRed = 0, sumGreen = 0, sumBlue = 0;
					for (int k = -maskCenter; k < maskCenter + 1; k++) {
						for (int l = -maskCenter; l < maskCenter + 1; l++) {
							int deltaIndex = indexRGB(k,l,imageWidth);
							sumRed += sourceRGBArray[red(indexRGB) + deltaIndex] * mask[k+maskCenter][l+maskCenter];
							sumGreen += sourceRGBArray[green(indexRGB) + deltaIndex] * mask[k+maskCenter][l+maskCenter];
							sumBlue += sourceRGBArray[blue(indexRGB) + deltaIndex] * mask[k+maskCenter][l+maskCenter];
						}
					}
					finalRGBArray[red(indexRGB)] = (int)(sumRed/maskDivisor);
					finalRGBArray[green(indexRGB)] = (int)(sumGreen/maskDivisor);
					finalRGBArray[blue(indexRGB)] = (int)(sumBlue/maskDivisor);
				}
			}
		}
		return finalRGBArray;
	}
}
