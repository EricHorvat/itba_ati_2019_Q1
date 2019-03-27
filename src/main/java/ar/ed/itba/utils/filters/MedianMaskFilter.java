package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MedianMaskFilter extends MaskFilter{
	
	public MedianMaskFilter(int maskSide) {
		super(maskSide);
	}
	
	public ATIImage applyFilter(ATIImage sourceAtiImage){
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
							System.out.println(mask[k+maskCenter][l+maskCenter]);
						}
					}
					redList.sort(Integer::compareTo);
					greenList.sort(Integer::compareTo);
					blueList.sort(Integer::compareTo);
					finalRGBArray[indexRed] = redList.get(redList.size()/2);
					finalRGBArray[indexGreen] = greenList.get(greenList.size()/2);
					finalRGBArray[indexBlue] = blueList.get(blueList.size()/2);
				}
			}
		}
		
		return new PpmImage(finalRGBArray, imageWidth, imageHeight);
	}
	//4,6
	
}