package ar.ed.itba.utils.filters.mask.advanced;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation;
import ar.ed.itba.utils.filters.mask.gradient.SobelFilter;

import java.util.List;

import static ar.ed.itba.utils.ImageUtils.*;
import static ar.ed.itba.utils.ImageUtils.blue;
import static ar.ed.itba.utils.filters.mask.gradient.PrefilterOrientation.*;

/*package*/ class CannyHelper {
  
  /*package*/ static class AngleHelper {
  
    private final static double PIDIV8 = Math.PI / 8;
    private final static double PI3DIV8 = 3 * Math.PI / 8;
    private final static double PI5DIV8 = 5 * Math.PI / 8;
    private final static double PI7DIV8 = 7 * Math.PI / 8;
  
    /*package*/ static PrefilterOrientation[] getGradientAngles(int[] valueArray, int imageWidth, int imageHeight) {
    
      SobelFilter xSobelFilter = new SobelFilter(GradientFilterType.HOR);
      SobelFilter ySobelFilter = new SobelFilter(GradientFilterType.VER);
      int[] gxArray = xSobelFilter.applyFilterRaw(valueArray, false, imageWidth, imageHeight);
      int[] gyArray = ySobelFilter.applyFilterRaw(valueArray, false, imageWidth, imageHeight);
    
      PrefilterOrientation[] angleResult = new PrefilterOrientation[gxArray.length];
    
      for (int i = 0; i < gxArray.length; i++) {
        double angle = Math.atan2(gyArray[i], gxArray[i]);
        if (gxArray[i] == 0) {
          angle = Math.PI / 2;
        }
        angleResult[i] = getAngle(angle);
        System.out.println(Math.toDegrees(angle) + " " + angleResult[i].name());
      }
      return angleResult;
    }
  
    private static PrefilterOrientation getAngle(double angle) {
      if (angle < 0) {
        angle += Math.PI;
      }
    
      if (0 <= angle && angle <= PIDIV8) {
        return X;
      } else if (PIDIV8 < angle && angle <= PI3DIV8) {
        return G45;
      } else if (PI3DIV8 < angle && angle <= PI5DIV8) {
        return Y;
      } else if (PI5DIV8 < angle && angle <= PI7DIV8) {
        return G135;
      } else if (PI7DIV8 < angle && angle <= Math.PI) {
        return X;
      }
      throw new RuntimeException();
    }
  }

  /*package*/ static class BooleanHelper{
    
    private static boolean controlIsMax(int[] RGBArray, final int i, final int delta1, final int delta2) {
      //System.out.println(RGBArray[i] + " " + RGBArray[i + delta1] + " " + RGBArray[i + delta2]);
      return Math.max(RGBArray[i], Math.max(RGBArray[i + delta1], RGBArray[i + delta2])) == RGBArray[i] && RGBArray[i] != 0;
    }
    
    /*package*/ static Boolean[] nonMaximumSuppresion(int[] sobelValues, int maskCenter /*sigma*/, int imageWidth, int imageHeight, PrefilterOrientation[] anglesArray) {
      int delta1;
      int delta2;
      Boolean[] isBorder = new Boolean[sobelValues.length];
      for (int i = 0; i < imageWidth; i++) {
        for (int j = 0; j < imageHeight; j++) {
          int indexRGB = indexRGB(i, j, imageWidth);
          if (i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter - 1) {
            isBorder[red(indexRGB)] = false;
            isBorder[green(indexRGB)] = false;
            isBorder[blue(indexRGB)] = false;
          } else {
            PrefilterOrientation prefilterOrientation = anglesArray[red(indexRGB)];
            switch (prefilterOrientation) {
              case G45:
                delta1 = indexRGB(1, 1, imageWidth);
                delta2 = indexRGB(-1, -1, imageWidth);
                break;
              case G135:
                delta1 = indexRGB(1, -1, imageWidth);
                delta2 = indexRGB(-1, 1, imageWidth);
                break;
              case X:
                delta1 = indexRGB(-1, 0, imageWidth);
                delta2 = indexRGB(1, 0, imageWidth);
                break;
              case Y:
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
  
    /*package*/ static Boolean[] hysteresisThreshold (Boolean[] originalArray, int[] sobelArray, int imageWidth, int imageHeight, int maskCenter, int t1, int t2) {
      Boolean[] isBorder = new Boolean[originalArray.length];
    
      for (int i = 0; i < imageWidth; i++) {
        for (int j = 0; j < imageHeight; j++) {
          int indexRGB = indexRGB(i, j, imageWidth);
          if (i < maskCenter || j < maskCenter || i > imageWidth - maskCenter - 1 || j > imageHeight - maskCenter - 1) {
            isBorder[red(indexRGB)] = false;
            isBorder[green(indexRGB)] = false;
            isBorder[blue(indexRGB)] = false;
          } else {
            int sobelValue = sobelArray[red(indexRGB)];
            if (sobelValue < t1) {
              isBorder[red(indexRGB)] = false;
            } else if (sobelValue > t2) {
              isBorder[red(indexRGB)] = true;
            } else {
              isBorder[red(indexRGB)] =
                originalArray[red(indexRGB) + indexRGB(-1, 0, imageWidth)]
                  || originalArray[red(indexRGB) + indexRGB(1, 0, imageWidth)]
                  || originalArray[red(indexRGB) + indexRGB(0, 1, imageWidth)]
                  || originalArray[red(indexRGB) + indexRGB(0, -1, imageWidth)];
            }
            isBorder[red(indexRGB)] = isBorder[red(indexRGB)] && originalArray[red(indexRGB)];
            isBorder[green(indexRGB)] = isBorder[red(indexRGB)] && originalArray[green(indexRGB)];
            isBorder[blue(indexRGB)] = isBorder[red(indexRGB)] && originalArray[blue(indexRGB)];
          }
        }
      }
      return isBorder;
    }
  }
  
  /*package*/ static class DebugHelper{
  
    static int[] intList = {3, 7, 11};
  
    /*package*/ static void showInt(List<int[]> imageList, int imageWidth, int imageHeight, String maskName){
      imageList.forEach(a -> FrameFactory.fixedImageFrame(maskName +" result " + intList[imageList.indexOf(a)], new PpmImage(a, imageWidth, imageHeight)).buildAndShow());
    }
  
    /*package*/ static void showOri(List<PrefilterOrientation[]> imageList, int imageWidth, int imageHeight, String maskName){
      imageList.forEach(a -> FrameFactory.fixedImageFrame(maskName +" result " + intList[imageList.indexOf(a)], new PpmImage(toRGBArray(a), imageWidth, imageHeight)).buildAndShow());
    }
  
    /*package*/ static void showBoolean(List<Boolean[]> imageList, int imageWidth, int imageHeight, String maskName){
      imageList.forEach(a -> FrameFactory.fixedImageFrame(maskName +" result " + intList[imageList.indexOf(a)], new PpmImage(toRGBArray(a), imageWidth, imageHeight)).buildAndShow());
    }
    
    private static int[] toRGBArray(PrefilterOrientation[] angleImage) {
      int[] ansArr = new int[angleImage.length];
      for (int i = 0; i < angleImage.length; i++) {
        int ans = 0;
        switch (angleImage[i]) {
          case G45:
            ans = i%3 == 0? 255 : 0;
            break;
          case Y:
            ans = i%3 == 1? 255 : 0;
            break;
          case G135:
            ans = i%3 == 2? 255 : 0;
        }
        ansArr[i] = ans;
      }
      return ansArr;
    }
  
    /*package*/ static int[] toRGBArray(Boolean[] booleanImage) {
      int[] ansArr = new int[booleanImage.length];
      for (int i = 0; i < booleanImage.length; i++) {
        ansArr[i] = booleanImage[i] ? 255 : 0;
      }
      return ansArr;
    }
  }
}
