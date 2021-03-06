package ar.ed.itba.utils.filters.mask.advanced;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.gradient.SobelFilter;
import ar.ed.itba.utils.filters.mask.weight.GaussianFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ar.ed.itba.utils.filters.mask.advanced.CannyHelper.AngleHelper.getGradientAngles;
import static ar.ed.itba.utils.filters.mask.advanced.CannyHelper.BooleanHelper.*;
import static ar.ed.itba.utils.filters.mask.advanced.CannyHelper.DebugHelper.*;
import static ar.ed.itba.utils.filters.mask.gradient.GradientFilterType.MOD;

public class CannyFilter extends MaskFilter {
  
  private final int t1;
  private final int t2;
  
  public CannyFilter(final int t1, final int t2) {
    super(7);
    this.t1 = t1;
    this.t2 = t2;
  }
  
  @Override
  protected double[][] generateMask() {
    return mask;
  }
  
  @Override
  public int[] applyFilterRaw(int[] sourceRGBArray, boolean ignoreBordersValue, int imageWidth, int imageHeight) {
    
    List<GaussianFilter> gaussianFilters = new ArrayList<>();
    gaussianFilters.add(new GaussianFilter(3)); // sigma = 1 ->  mask side 3
    gaussianFilters.add(new GaussianFilter(7)); // sigma = 3 ->  mask side 7
    gaussianFilters.add(new GaussianFilter(11));// sigma = 5 ->  mask side 11
    
    List<int[]> gaussianResultList =
      gaussianFilters.
        stream().
        map(gaussianFilter -> gaussianFilter.applyFilterRaw(sourceRGBArray, true, imageWidth, imageHeight)).
        map(gaussianFilter -> sourceRGBArray)
        .collect(Collectors.toList());
    
    List<int[]> magnitudeResultList =
      gaussianResultList.
        stream().
        map(gaussianResult -> new SobelFilter(MOD, true).applyFilterRaw(gaussianResult, true, imageWidth, imageHeight)).
        collect(Collectors.toList());

    List<PrefilterOrientation[]> angleResultList =
      gaussianResultList.
      //magnitudeResultList.
        stream().
        map(magnitudeResultArray -> getGradientAngles(magnitudeResultArray, imageWidth, imageHeight)).
        collect(Collectors.toList());

    List<Boolean[]> isBorderAList =
      angleResultList.
        stream().
        map(elem -> nonMaximumSuppresion(
          magnitudeResultList.get(angleResultList.indexOf(elem)),
          2 * angleResultList.indexOf(elem) + 1, // 0 -> 1; 1 -> 3; 2 -> 5
          imageWidth,
          imageHeight,
          elem)
        )
        .collect(Collectors.toList());
  
    List<Boolean[]> isBorderBList =
      isBorderAList.
        stream().
        map(elem -> hysteresisThreshold(
          elem,
          magnitudeResultList.get(isBorderAList.indexOf(elem)),
          imageWidth,
          imageHeight,
          2 * isBorderAList.indexOf(elem) + 1, // 0 -> 1; 1 -> 3; 2 -> 5
          t1,
          t2
        )).
        collect(Collectors.toList());
  
    Boolean[] booleans = isBorderBList.stream().reduce((elem1, elem2) -> {
      for (int i = 0; i < elem1.length; i++) {
        elem1[i] &= elem2[i];
      }
      return elem1;
    })
      .orElse(new Boolean[0]);
  
    return toRGBArray(booleans);
  }
  
}
