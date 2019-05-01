package ar.ed.itba.utils.filters.mask.laplacian;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.X;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.Y;

public class LaplacianFilter extends WeightMaskFilter {
	
	private boolean pendientControl;
	private Integer threshold;
	
	public LaplacianFilter(boolean pendientControl, Integer threshold, int maskSide) {
		super(maskSide);
		this.pendientControl = pendientControl;
		this.threshold = threshold;
	}
	
	public LaplacianFilter(boolean pendientControl, Integer threshold) {
		this(pendientControl,threshold, 3);
	}
	
	@Override
	protected int maskDivisor() {
		return 1;
	}
	
	@Override
	protected double[][] generateMask() {
		return new double[][]{{0,1,0},{1,-4,1},{0,1,0}};
	}
	
	@Override
	public int[] applyFilterRaw(ATIImage sourceAtiImage, boolean ignoreBordersValue) {
		int[] prevResult = super.applyFilterRaw(sourceAtiImage, ignoreBordersValue);
		int[] ans = new int[lengthRGB(sourceAtiImage.getWidth(), sourceAtiImage.getHeight())];
		int width = sourceAtiImage.getWidth();
		for (int i = mask.length / 2; i < sourceAtiImage.getWidth() - mask.length / 2 - 1; i++) {
			for (int j = mask.length / 2; j < sourceAtiImage.getHeight() - mask.length / 2 - 1; j++) {
				// (i,j) * (i,j + 1) < 0 || (i,j) * (i + 1,j) < 0
				control(X,prevResult,i,j,width,ans);
				control(Y,prevResult,i,j,width,ans);
			}
		}
		return ans;
	}
	
	private void control(PrefilterOrientation orientation, int[] prevResult, int i, int j, int width, int[] ans){
		int index = indexRGB(i,j,width);
		int next_index = orientation == Y ? indexRGB(i,j+1,width) : indexRGB(i+1,j,width);
		int next_next_index = orientation == Y ? indexRGB(i,j+2,width) : indexRGB(i+2,j,width);
		int zeroCrossControl = prevResult[index] * prevResult[next_index];
		if(zeroCrossControl == 0){
			zeroCrossControl = prevResult[index] * prevResult[next_next_index];
			if(zeroCrossControl < 0){
				int value = getValue(prevResult[index],prevResult[next_next_index]);
				int value1 = Math.max(value, ans[index]);
				ans[red(index)] = value1;
				ans[green(index)] = value1;
				ans[blue(index)] = value1;
				int value2 = Math.max(value, ans[next_index]);
				ans[red(next_index)] = value2;
				ans[green(next_index)] = value2;
				ans[blue(next_index)] = value2;
			}
		}else if(zeroCrossControl < 0){
			int value = getValue(prevResult[index],prevResult[next_index]);
			int value1 = Math.max(value, ans[index]);
      ans[red(index)] = value1;
      ans[green(index)] = value1;
      ans[blue(index)] = value1;
    }
	}
	
	private int getValue(int a, int b){
		if(pendientControl){
			int value = Math.abs(a-b);
			if(threshold != null){
				return value >= threshold ? 255 : 0;
			}
			return value;
		}else{
			return 255;
		}
	}
}
