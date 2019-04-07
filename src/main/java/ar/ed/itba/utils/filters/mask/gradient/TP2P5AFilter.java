package ar.ed.itba.utils.filters.mask.gradient;

import ar.ed.itba.utils.filters.mask.MaskFilter;

public class TP2P5AFilter extends GradientFilter {
	
	public TP2P5AFilter(GradientFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new TP2P5APreFilter(orientation);
	}
	
}
