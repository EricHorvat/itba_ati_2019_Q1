package ar.ed.itba.utils.filters.mask.gradient.prefilter;

import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.X;

public class TP2P5APreFilter extends BasicPreFilter {
 
	static private double[][] xMask = {{-1,1,1},{-1,-2,1},{-1,1,1}};
	static private double[][] yMask = {{-1,-1,-1},{1,-2,1},{1,1,1}};
  static private double[][] g45Mask = {{-1,-1,1},{-1,-2,1},{1,1,1}};
  static private double[][] g135Mask = {{-1,-1,-1},{1,-2,-1},{1,1,1}};
  
  public TP2P5APreFilter(PrefilterOrientation orientation) {
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
