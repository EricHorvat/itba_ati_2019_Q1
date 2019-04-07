package ar.ed.itba.utils.filters;

public class KirshFilter extends GradientFilter {
	
	public KirshFilter(GradientFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new KirshPreFilter(orientation);
	}
	
}
