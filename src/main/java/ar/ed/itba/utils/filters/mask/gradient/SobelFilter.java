package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.prefilter.SobelPreFilter;

public class SobelFilter extends GradientFilter {
  
  public SobelFilter(GradientFilterType type, boolean normalize) {
    super(type, normalize);
  }
  public SobelFilter(GradientFilterType type) {
    super(type, true);
  }
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new SobelPreFilter(orientation);
	}
	
}
