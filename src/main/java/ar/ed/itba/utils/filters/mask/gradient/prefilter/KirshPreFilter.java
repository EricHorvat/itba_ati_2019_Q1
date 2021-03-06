package ar.ed.itba.utils.filters.mask.gradient.prefilter;

import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.X;

public class KirshPreFilter extends BasicPreFilter {
	
	static private double[][] xMask = {{-3,-3,5},{-3,0,5},{-3,-3,5}};
  static private double[][] yMask = {{-3,-3,-3},{-3,0,-3},{5,5,5}};
  static private double[][] g45Mask = {{-3,-3,-3},{-3,0,5},{-3,5,5}};
  static private double[][] g135Mask = {{-3,-3,-3},{5,0,-3},{5,5,-3}};
  
  public KirshPreFilter(PrefilterOrientation orientation) {
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
