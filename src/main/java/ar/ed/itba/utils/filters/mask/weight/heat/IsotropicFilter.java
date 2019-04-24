package ar.ed.itba.utils.filters.mask.weight.heat;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.Pair;
import ar.ed.itba.utils.filters.mask.weight.WeightMaskFilter;

import java.util.HashMap;
import java.util.Map;

public class IsotropicFilter extends HeatFilter {
  
  public IsotropicFilter(double delta) {
    super(delta);
  }
  
  @Override
  protected double getCij(double dIij) {
    return 1;
  }
}
