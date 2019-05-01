package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.*;

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
	
	protected abstract MaskFilter getPreFilter(PrefilterOrientation orientation);
	
	@Override
	public int[] applyFilterRaw(ATIImage sourceAtiImage, boolean ignoreBordersValue) {
		MaskFilter xFilter = getPreFilter(X);
		MaskFilter yFilter = getPreFilter(Y);
		int[] xImage = xFilter.applyFilterRaw(sourceAtiImage, ignoreBordersValue);
		int[] yImage = yFilter.applyFilterRaw(sourceAtiImage, ignoreBordersValue);
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
        xImage = xFilter.applyFilterRaw(sourceAtiImage, true);
        yImage = yFilter.applyFilterRaw(sourceAtiImage, true);
				finalImage = mod2(xImage,yImage, width, height);
				break;
			case MAX:
				finalImage = max(xImage,yImage, width, height);
				break;
			case MIN:
			default:
				finalImage = min(xImage,yImage, width, height);
				break;
      case G135:
        finalImage = getPreFilter(G135).applyFilterRaw(sourceAtiImage, ignoreBordersValue);
        break;
      case G45:
        finalImage = getPreFilter(G45).applyFilterRaw(sourceAtiImage, ignoreBordersValue);
		}
		return finalImage;
	}
	
}
