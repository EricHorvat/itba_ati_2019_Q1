package ar.ed.itba.utils.filters.mask.median;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import java.util.ArrayList;
import java.util.List;

public abstract class MedianMaskFilter extends MaskFilter {
	
	public MedianMaskFilter(int maskSide) {
		super(maskSide);
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
					List<Integer> redList = new ArrayList<>();
					List<Integer> greenList = new ArrayList<>();
					List<Integer> blueList = new ArrayList<>();
					
					for (int k = -maskCenter; k < maskCenter + 1; k++) {
						for (int l = -maskCenter; l < maskCenter + 1; l++) {
							int deltaIndex = (k * imageWidth + l) * 3;
							for (int m = 0; m < mask[k+maskCenter][l+maskCenter]; m++) {
								redList.add(sourceRGBArray[indexRed + deltaIndex]);
								greenList.add(sourceRGBArray[indexGreen + deltaIndex]);
								blueList.add(sourceRGBArray[indexBlue + deltaIndex]);
								
							}
						}
					}
					redList.sort(Integer::compareTo);
					greenList.sort(Integer::compareTo);
					blueList.sort(Integer::compareTo);
					if(redList.size() % 2 == 1) {
						finalRGBArray[indexRed] = redList.get(redList.size() / 2);
						finalRGBArray[indexGreen] = greenList.get(greenList.size() / 2);
						finalRGBArray[indexBlue] = blueList.get(blueList.size() / 2);
					}else{
						finalRGBArray[indexRed] = (int)((redList.get(redList.size() / 2) + redList.get((redList.size() / 2) -1))/2.0);
						finalRGBArray[indexGreen] = (int)((greenList.get(greenList.size() / 2) + greenList.get((greenList.size() / 2) -1))/2.0);
						finalRGBArray[indexBlue] = (int)((blueList.get(blueList.size() / 2) + greenList.get((blueList.size() / 2) -1))/2.0);
					}
				}
			}
		}
		return finalRGBArray;
	}
}
