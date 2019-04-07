package ar.ed.itba.utils.filters.mask.weight;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

public abstract class WeightMaskFilter extends MaskFilter {
	
	protected final int maskDivisor;
	
	protected abstract int maskDivisor();
	
	public WeightMaskFilter(int maskSide) {
		super(maskSide);
		this.maskDivisor = maskDivisor();
	}
	
	@Override
	public int[] applyFilterRaw(ATIImage sourceAtiImage) {
		
		mask = generateMask();
		int maskCenter = maskSide/2;
		

		int imageWidth = sourceAtiImage.getWidth();
		int imageHeight = sourceAtiImage.getHeight();
		int[] sourceRGBArray = sourceAtiImage.toRGB();
		int[] finalRGBArray = new int[sourceRGBArray.length];
		
		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				int indexRed = (i * imageWidth + j) * 3;
				int indexGreen = indexRed + 1;
				int indexBlue = indexGreen + 1;
				if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1){
					finalRGBArray[indexRed] = sourceRGBArray[indexRed];
					finalRGBArray[indexGreen] = sourceRGBArray[indexGreen];
					finalRGBArray[indexBlue] = sourceRGBArray[indexBlue];
				}else{
					double sumRed = 0, sumGreen = 0, sumBlue = 0;
					for (int k = -maskCenter; k < maskCenter + 1; k++) {
						for (int l = -maskCenter; l < maskCenter + 1; l++) {
							int deltaIndex = (k * imageWidth + l) * 3;
							sumRed += sourceRGBArray[indexRed + deltaIndex] * mask[k+maskCenter][l+maskCenter];
							sumGreen += sourceRGBArray[indexGreen + deltaIndex] * mask[k+maskCenter][l+maskCenter];
							sumBlue += sourceRGBArray[indexBlue + deltaIndex] * mask[k+maskCenter][l+maskCenter];
						}
					}
					finalRGBArray[indexRed] = (int)(sumRed/maskDivisor);
					finalRGBArray[indexGreen] = (int)(sumGreen/maskDivisor);
					finalRGBArray[indexBlue] = (int)(sumBlue/maskDivisor);
				}
			}
		}
		return finalRGBArray;
	}
}
