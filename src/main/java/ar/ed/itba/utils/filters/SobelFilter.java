package ar.ed.itba.utils.filters;

public class SobelFilter extends GradientFilter {
	
	public SobelFilter(GradientFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new SobelPreFilter(orientation);
	}
	
}
