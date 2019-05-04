package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.filters.mask.MaskFilter;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.*;

public abstract class GradientFilter extends MaskFilter {
  
  final private GradientFilterType type;
  final private boolean normalize;
	
	public GradientFilter(GradientFilterType type, boolean normalize) {
		super(3);
		this.type = type;
		this.normalize = normalize;
	}
	
	@Override
	protected double[][] generateMask() {
		return new double[0][];
	}
	
	protected abstract MaskFilter getPreFilter(PrefilterOrientation orientation);
	
	@Override
	public int[] applyFilterRaw(int[] sourceAtiRGBArray, boolean ignoreBordersValue, int width, int height) {
		MaskFilter xFilter = getPreFilter(X);
		MaskFilter yFilter = getPreFilter(Y);
		int[] xImage = xFilter.applyFilterRaw(sourceAtiRGBArray, ignoreBordersValue, width, height);
		int[] yImage = yFilter.applyFilterRaw(sourceAtiRGBArray, ignoreBordersValue, width, height);
		int[] finalImage;
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
        xImage = xFilter.applyFilterRaw(sourceAtiRGBArray, true, width, height);
        yImage = yFilter.applyFilterRaw(sourceAtiRGBArray, true, width, height);
				finalImage = mod2(xImage,yImage, width, height, normalize);
				break;
			case MAX:
				finalImage = max(xImage,yImage, width, height, normalize);
				break;
			case MIN:
			default:
				finalImage = min(xImage,yImage, width, height, normalize);
				break;
      case G135:
        finalImage = getPreFilter(G135).applyFilterRaw(sourceAtiRGBArray, ignoreBordersValue, width, height);
        break;
      case G45:
        finalImage = getPreFilter(G45).applyFilterRaw(sourceAtiRGBArray, ignoreBordersValue, width, height);
		}
		return finalImage;
	}
	
}
