package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.filters.PrefilterOrientation.X;
import static ar.ed.itba.utils.filters.PrefilterOrientation.Y;

public abstract class GradientFilter extends MaskFilter {
	
	final private GradientFilterType type;
	
	public GradientFilter(GradientFilterType type) {
		super(3);
		this.type = type;
	}
	
	@Override
	protected double[][] generateMask() {
		return new double[0][];
	}
	
	@Override
	public ATIImage applyFilter(ATIImage sourceAtiImage) {
		return new PpmImage(applyFilterRaw(sourceAtiImage),sourceAtiImage.getWidth(),sourceAtiImage.getHeight());
	}
	
	protected abstract MaskFilter getPreFilter(PrefilterOrientation orientation);
	
	@Override
	public int[] applyFilterRaw(ATIImage sourceAtiImage) {
		MaskFilter xFilter = getPreFilter(X);
		MaskFilter yFilter = getPreFilter(Y);
		int[] xImage = xFilter.applyFilterRaw(sourceAtiImage);
		int[] yImage = yFilter.applyFilterRaw(sourceAtiImage);
		int[] finalImage;
		int width = sourceAtiImage.getWidth();
		int height = sourceAtiImage.getHeight();
		switch (type){
			case HOR:
				finalImage = xImage;
				break;
			case VER:
				finalImage = yImage;
				break;
			case AVG:
				finalImage = avg(xImage,yImage, width, height);
				break;
			case MOD:
				finalImage = mod2(xImage,yImage, width, height);
				break;
			case MAX:
				finalImage = max(xImage,yImage, width, height);
				break;
			case MIN:
			default:
				finalImage = min(xImage,yImage, width, height);
				break;
		}
		return normalize(finalImage);
	}
	
}
