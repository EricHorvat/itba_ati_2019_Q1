package ar.ed.itba.utils.filters.mask.laplacian;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.ImageUtils;
import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.X;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.Y;

public class LaplacianFilter extends WeightMaskFilter {
	
	private boolean pendientControl;
	
	public LaplacianFilter(boolean pendientControl, int maskSide) {
		super(maskSide);
		this.pendientControl = pendientControl;
	}
	
	public LaplacianFilter(boolean pendientControl) {
		this(pendientControl,3);
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
	public int[] applyFilterRaw(ATIImage sourceAtiImage) {
		int[] prevResult = super.applyFilterRaw(sourceAtiImage);
		int[] ans = new int[sourceAtiImage.getWidth() * sourceAtiImage.getHeight() * 3];
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
		int index = ImageUtils.index(i,j,width);
		int next_index = orientation == Y ? ImageUtils.index(i,j+1,width) : ImageUtils.index(i+1,j,width);
		int next_next_index = orientation == Y ? ImageUtils.index(i,j+2,width) : ImageUtils.index(i+2,j,width);
		int control = prevResult[index] * prevResult[next_index];
		if(control == 0){
			control = prevResult[index] * prevResult[next_next_index];
			if(control < 0){
				int value = getValue(prevResult[index],prevResult[next_next_index]);
				int value1 = Math.max(value, ans[index]);
				ans[index] = value1;
				ans[index + 1] = value1;
				ans[index + 2] = value1;
				int value2 = Math.max(value, ans[next_index]);
				ans[next_index] = value2;
				ans[next_index + 1] = value2;
				ans[next_index + 2] = value2;
			}
		}else if(control < 0){
			int value = getValue(prevResult[index],prevResult[next_index]);
			int value1 = Math.max(value, ans[index]);
			ans[index] = value1;
			ans[index + 1] = value1;
			ans[index + 2] = value1;
		}
	}
	
	private int getValue(int a, int b){
		if(pendientControl){
			return Math.abs(a-b);
		}else{
			return 255;
		}
	}
}