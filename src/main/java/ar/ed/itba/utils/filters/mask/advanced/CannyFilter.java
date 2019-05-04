package ar.ed.itba.utils.filters.mask.advanced;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.gradient.SobelFilter;
import ar.ed.itba.utils.filters.mask.weight.GaussianFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.filters.mask.gradient.GradientFilterType.*;

public class CannyFilter extends MaskFilter {
  
  private final static int T = 27;
  private final static boolean absoluteMode = true;
  private final static double N = 37.0;
  private final int t1;
  private final int t2;
  
  private final static double PIDIV8 = Math.PI / 8;
  private final static double PI3DIV8 = 3 * Math.PI / 8;
  private final static double PI5DIV8 = 5 * Math.PI / 8;
  private final static double PI7DIV8 = 7 * Math.PI / 8;
  
  
  public CannyFilter(final int t1, final int t2) {
    super(7);
    this.t1 = t1;
    this.t2 = t2;
  }
  
  private static final double[][] mask = {
    {0, 0, 1, 1, 1, 0, 0},
    {0, 1, 1, 1, 1, 1, 0},
    {1, 1, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 1, 1},
    {1, 1, 1, 1, 1, 1, 1},
    {0, 1, 1, 1, 1, 1, 0},
    {0, 0, 1, 1, 1, 0, 0}
  };
  
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
        map(gaussianFilter -> gaussianFilter.applyFilterRaw(sourceRGBArray, ignoreBordersValue, imageWidth, imageHeight)).
        collect(Collectors.toList());
    
    List<int[]> sobelResultList =
      gaussianResultList.
        stream().
        map(gaussianResult -> new SobelFilter(MOD).applyFilterRaw(gaussianResult, true, imageWidth, imageHeight)).
        collect(Collectors.toList());
    
    List<GradientFilterType[]> angleResultList =
      gaussianResultList.
        stream().
        map(gaussianResultArray -> getGradientAngles(gaussianResultArray, imageWidth, imageHeight)).
        collect(Collectors.toList());
    
    List<Boolean[]> isBorderAList =
      angleResultList.
        stream().
        map(elem -> nonMaximumSuppresion(
          sobelResultList.get(angleResultList.indexOf(elem)),
          2 * angleResultList.indexOf(elem) + 1, // 0 -> 1; 1 -> 3; 2 -> 5
          imageWidth,
          imageHeight,
          elem)
        )
        .collect(Collectors.toList());
    
    List<Boolean[]> isBorderBList =
      isBorderAList.
        stream().
        map(elem -> histeresisUmbralization(elem, sobelResultList.get(isBorderAList.indexOf(elem)), imageWidth)).
        collect(Collectors.toList());
  
    Boolean[] booleans = isBorderBList.stream().reduce((elem1,elem2)-> {
      for (int i = 0; i < elem1.length; i++) {
        elem1[i] &= elem2[i];
      }
      return elem1;
    })
    .orElse(new Boolean[0]);
  
    int[] finalRGBArray = new int[booleans.length];
  
    for (int i = 0; i < booleans.length; i++) {
      finalRGBArray[i] = booleans[i]?255:0;
    }
    return finalRGBArray;
  }
  
  private GradientFilterType getAngle(double angle) {
    if (angle < 0){
      angle += Math.PI;
    }
    if (PIDIV8 < angle && angle < PI3DIV8) {
      return G45;
    } else if (PI3DIV8 < angle && angle < PI5DIV8) {
      return VER;
    } else if (PI5DIV8 < angle && angle < PI7DIV8) {
      return G135;
    }
    return HOR;
  }
  
  private boolean controlIsMax(int[] sourceRGBArray, final int i, final int delta1, final int delta2) {
    return Math.max(sourceRGBArray[i], Math.max(sourceRGBArray[i + delta1], sourceRGBArray[i + delta2])) == sourceRGBArray[i];
  }
  
  private GradientFilterType[] getGradientAngles(int[] gaussianResult, int imageWidth, int imageHeight){
    GradientFilterType[] angleResults = new GradientFilterType[gaussianResult.length];

    SobelFilter xSobelFilter = new SobelFilter(GradientFilterType.HOR);
    SobelFilter ySobelFilter = new SobelFilter(GradientFilterType.VER);
    int[] gxArray = xSobelFilter.applyFilterRaw(gaussianResult, true, imageWidth, imageHeight);
    int[] gyArray = ySobelFilter.applyFilterRaw(gaussianResult, true, imageWidth, imageHeight);
    GradientFilterType[] angleResult = new GradientFilterType[gxArray.length];
    
    for (int i = 0; i < gxArray.length; i++) {
      if (gxArray[i] == 0) {
        angleResult[i] = HOR;
      } else {
        angleResult[i] = getAngle(Math.atan2(gyArray[i], gxArray[i]));
      }
    }
    return angleResult;
  }
  
  private Boolean[] nonMaximumSuppresion(int[] sobelValues, int sigma, int imageWidth, int imageHeight, GradientFilterType[] anglesArray){
    
    int delta1;
    int delta2;
    int maskCenter = sigma;
    Boolean[] isBorder = new Boolean[sobelValues.length];
    for (int i = 0; i < imageWidth; i++) {
      for (int j = 0; j < imageHeight; j++) {
        int indexRGB = indexRGB(i, j, imageWidth);
        if ( i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter -1){
          isBorder[red(indexRGB)] = false;
          isBorder[green(indexRGB)] = false;
          isBorder[blue(indexRGB)] = false;
        }else {
          GradientFilterType gradientFilterType = anglesArray[indexRGB];
          switch (gradientFilterType) {
            case G45:
              delta1 = indexRGB(1, 1, imageWidth);
              delta2 = indexRGB(-1, -1, imageWidth);
              break;
            case G135:
              delta1 = indexRGB(1, -1, imageWidth);
              delta2 = indexRGB(-1, 1, imageWidth);
              break;
            case HOR:
              delta1 = indexRGB(-1, 0, imageWidth);
              delta2 = indexRGB(1, 0, imageWidth);
              break;
            case VER:
              delta1 = indexRGB(0, -1, imageWidth);
              delta2 = indexRGB(0, 1, imageWidth);
              break;
            default:
              isBorder[red(indexRGB)] = false;
              isBorder[green(indexRGB)] = false;
              isBorder[blue(indexRGB)] = false;
              continue;
          }
          boolean ans = controlIsMax(sobelValues, indexRGB, delta1, delta2);
          isBorder[red(indexRGB)] = ans;
          isBorder[green(indexRGB)] = ans;
          isBorder[blue(indexRGB)] = ans;
        }
      }
    }
    return isBorder;
  }
  
  private Boolean[] histeresisUmbralization(Boolean[] originalArray, int[] sobelArray, int imageWidth){
    Boolean[] isBorder = new Boolean[originalArray.length];
  
    for (int i = 0; i < originalArray.length; i++) {
      int sobelValue = sobelArray[i];
      if (sobelValue < t1) {
        isBorder[i] = false;
      } else if (sobelValue > t2) {
        isBorder[i] = true;
      } else {
        isBorder[i] =
          originalArray[i + indexRGB(-1,0, imageWidth)]
            || originalArray[i + indexRGB(1,0, imageWidth)]
            || originalArray[i + indexRGB(0,1, imageWidth)]
            || originalArray[i + indexRGB(0,-1, imageWidth)];
      }
      isBorder[i] &= originalArray[i];
    }
    return isBorder;
  }
}
