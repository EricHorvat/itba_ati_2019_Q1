package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.prefilter.PrewitPreFilter;

public class PrewitFilter extends GradientFilter {
	
	public PrewitFilter(GradientFilterType type) {
		super(type, true);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new PrewitPreFilter(orientation);
	}
	
}
