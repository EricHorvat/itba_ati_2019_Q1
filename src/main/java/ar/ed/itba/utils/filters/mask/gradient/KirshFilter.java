package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.prefilter.KirshPreFilter;

public class KirshFilter extends GradientFilter {
	
	public KirshFilter(GradientFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new KirshPreFilter(orientation);
	}
	
}
