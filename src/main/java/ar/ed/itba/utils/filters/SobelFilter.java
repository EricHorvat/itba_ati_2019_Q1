package ar.ed.itba.utils.filters;

public class SobelFilter extends DerivedFilter {
	
	public SobelFilter(DerivedFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new SobelPreFilter(orientation);
	}
	
}
