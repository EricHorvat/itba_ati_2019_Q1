package ar.ed.itba.utils.filters;

public class PrewitFilter extends DerivedFilter {
	
	public PrewitFilter(DerivedFilterType type) {
		super(type);
	}
	
	@Override
	protected MaskFilter getPreFilter(PrefilterOrientation orientation) {
		return new PrewitPreFilter(orientation);
	}
	
}
