package ar.ed.itba.utils.filters.advanced;

import ar.ed.itba.utils.filters.Filter;

public class ActiveContourFilter extends Filter {
  
  private final int maxIter;
  
  public ActiveContourFilter(int N) {
    maxIter = N;
  }
  
  @Override
  public int[] applyFilterRaw(int[] sourceAtiRGBArray, boolean ignoreBordersValue, int width, int height) {
    return new int[0];
  }
}
