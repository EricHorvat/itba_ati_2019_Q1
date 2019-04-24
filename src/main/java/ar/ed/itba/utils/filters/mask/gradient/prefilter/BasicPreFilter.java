package ar.ed.itba.utils.filters.mask.gradient.prefilter;

import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

public abstract class BasicPreFilter extends WeightMaskFilter {
	
	private PrefilterOrientation orientation;
  
  protected abstract double[][] getXMask();
  protected abstract double[][] getYMask();
  protected abstract double[][] getG45Mask();
  protected abstract double[][] getG135Mask();
	
	public BasicPreFilter(PrefilterOrientation orientation) {
		super(3);
		this.orientation = orientation;
	}
	
	@Override
	protected int maskDivisor() {
		return (maskSide * maskSide);
	}
	
	@Override
	protected double[][] generateMask() {
	  switch (orientation){
      case X:
        return getXMask();
      case Y:
        return getYMask();
      case G45:
        return getG45Mask();
      case G135:
      default:
        return getG135Mask();
    }
	}
	
}
