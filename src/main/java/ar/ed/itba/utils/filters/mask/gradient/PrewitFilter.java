package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.utils.filters.mask.MaskFilter;

public class PrewitFilter extends GradientFilter {
	
	public PrewitFilter(GradientFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new PrewitPreFilter(orientation);
	}
	
}
