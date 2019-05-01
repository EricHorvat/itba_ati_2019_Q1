package ar.ed.itba.utils.filters.mask.median;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import java.util.ArrayList;
import java.util.List;

import static ar.ed.itba.utils.ImageUtils.*;

public abstract class MedianMaskFilter extends MaskFilter {
	
	public MedianMaskFilter(int maskSide) {
		super(maskSide);
	}
	
	@Override
	public int[] applyFilterRaw(ATIImage sourceAtiImage) {
		mask = mask == null?generateMask():mask;
		int maskCenter = maskSide/2;
		
		int imageWidth = sourceAtiImage.getWidth();
		int imageHeight = sourceAtiImage.getHeight();
		int[] sourceRGBArray = sourceAtiImage.toRGB();
		int[] finalRGBArray = new int[sourceRGBArray.length];
		
		for (int i = 0; i < imageWidth; i++) {
			for (int j = 0; j < imageHeight; j++) {
				int indexRGB = indexRGB(i,j,imageWidth);
				if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1){
					finalRGBArray[red(indexRGB)] = sourceRGBArray[red(indexRGB)];
					finalRGBArray[green(indexRGB)] = sourceRGBArray[green(indexRGB)];
					finalRGBArray[blue(indexRGB)] = sourceRGBArray[blue(indexRGB)];
				}else{
					List<Integer> redList = new ArrayList<>();
					List<Integer> greenList = new ArrayList<>();
					List<Integer> blueList = new ArrayList<>();
					
					for (int k = -maskCenter; k < maskCenter + 1; k++) {
						for (int l = -maskCenter; l < maskCenter + 1; l++) {
							int deltaIndex = (k * imageWidth + l) * 3;
							for (int m = 0; m < mask[k+maskCenter][l+maskCenter]; m++) {
								redList.add(sourceRGBArray[red(indexRGB) + deltaIndex]);
								greenList.add(sourceRGBArray[green(indexRGB) + deltaIndex]);
								blueList.add(sourceRGBArray[blue(indexRGB) + deltaIndex]);
								
							}
						}
					}
					redList.sort(Integer::compareTo);
					greenList.sort(Integer::compareTo);
					blueList.sort(Integer::compareTo);
					if(redList.size() % 2 == 1) {
						finalRGBArray[red(indexRGB)] = redList.get(redList.size() / 2);
						finalRGBArray[green(indexRGB)] = greenList.get(greenList.size() / 2);
						finalRGBArray[blue(indexRGB)] = blueList.get(blueList.size() / 2);
					}else{
						finalRGBArray[red(indexRGB)] = (int)((redList.get(redList.size() / 2) + redList.get((redList.size() / 2) -1))/2.0);
						finalRGBArray[green(indexRGB)] = (int)((greenList.get(greenList.size() / 2) + greenList.get((greenList.size() / 2) -1))/2.0);
						finalRGBArray[blue(indexRGB)] = (int)((blueList.get(blueList.size() / 2) + greenList.get((blueList.size() / 2) -1))/2.0);
					}
				}
			}
		}
		return finalRGBArray;
	}
}
