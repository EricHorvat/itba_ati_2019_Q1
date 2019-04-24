package ar.ed.itba.utils.filters.mask.gradient.prefilter;

import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

public class PrewitPreFilter extends BasicPreFilter {
  
  static private double[][] xMask = {{-1,0,1},{-1,0,1},{-1,0,1}};
  static private double[][] yMask = {{-1,-1,-1},{0,0,0},{1,1,1}};
  static private double[][] g45Mask = {{-1,-1,0},{-1,0,1},{0,1,1}};
  static private double[][] g135Mask = {{0,-1,-1},{1,0,-1},{1,1,0}};
	
	public PrewitPreFilter(PrefilterOrientation orientation) {
		super(orientation);
	}
 
	@Override
  public double[][] getXMask() {
    return xMask;
  }
  
  @Override
  public double[][] getYMask() {
    return yMask;
  }
  
  @Override
  public double[][] getG45Mask() {
    return g45Mask;
  }
  
  @Override
  public double[][] getG135Mask() {
    return g135Mask;
  }
  
}
