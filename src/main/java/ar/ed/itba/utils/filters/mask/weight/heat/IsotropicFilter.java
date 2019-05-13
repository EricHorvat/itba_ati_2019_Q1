package ar.ed.itba.utils.filters.mask.weight.heat;

public class IsotropicFilter extends HeatFilter {
  
  public IsotropicFilter(double delta, int t) {
    super(delta, t);
  }
  
  @Override
  protected double getCij(double dIij) {
    return 1;
  }
  
  @Override
  protected boolean hasToNormalize() {
    return true;
  }
}
