package ar.ed.itba.utils.filters.advanced;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.utils.CoordinatePair;
import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static ar.ed.itba.utils.ImageUtils.*;

public class ActiveContourFilter {
  
  private static ActiveContourFilter instance;
  
  public static ActiveContourFilter getInstance() {
    if (instance == null){
      instance = new ActiveContourFilter();
    }
    return instance;
  }
  
  private int maxIter;
  private int iter = 0;
  private Color theta0 = new Color(0,0,0);
  private Color theta1 = new Color(0,0,0);
  private Set<CoordinatePair> Lin = new HashSet<>();
  private Set<CoordinatePair> Lout = new HashSet<>();
  private int[][] fi;
  
  private boolean outerSet = false;
  private boolean innerSet = false;
  private CoordinatePair inPFrom;
  private CoordinatePair inPTo;
  private CoordinatePair outPFrom;
  private CoordinatePair outPTo;
  
  private ATIImage sourceATIImage;
  
  private ActiveContourFilter() {}
  
  public int[] applyRaw(){
    int[] ans = sourceATIImage.toRGB();
    int w = sourceATIImage.getWidth();
    Iterator<CoordinatePair> lOutIt = Lout.iterator();
    Set<CoordinatePair> toAdd = new HashSet<>();
    while (lOutIt.hasNext()){
      CoordinatePair p = lOutIt.next();
      if(fd(indexRGB(p.getX(),p.getY(),w),ans) > 0){
        Lin.add(p);
        fi[p.getX()][p.getY()] = -1;
        lOutIt.remove();
        List<CoordinatePair> areOuters = neighbours(p).stream().filter(n -> getFi(n) > 1).collect(Collectors.toList());
        toAdd.addAll(areOuters);
        areOuters.forEach(o -> fi[o.getX()][o.getY()] = 1);
      }
    }
    Lout.addAll(toAdd);
    Iterator<CoordinatePair> lInIt = Lin.iterator();
    while (lInIt.hasNext()){
      CoordinatePair p = lInIt.next();
      long outersNeighbours = neighbours(p).stream().filter(n -> getFi(n) == 1).count();
      if(outersNeighbours == 0){
        lInIt.remove();
        fi[p.getX()][p.getY()] = -3;
      }
    }
    toAdd = new HashSet<>();
    lInIt = Lin.iterator();
    while (lInIt.hasNext()){
      CoordinatePair p = lInIt.next();
      if(fd(indexRGB(p.getX(),p.getY(),w),ans) < 0){
        Lout.add(p);
        fi[p.getX()][p.getY()] = 1;
        lInIt.remove();
        List<CoordinatePair> areInners = neighbours(p).stream().filter(n -> getFi(n) < 1).collect(Collectors.toList());
        toAdd.addAll(areInners);
        areInners.forEach(o -> fi[o.getX()][o.getY()] = -1);
      }
    }
    Lin.addAll(toAdd);
    lOutIt = Lout.iterator();
    while (lOutIt.hasNext()){
      CoordinatePair p = lOutIt.next();
      long innerNeighbours = neighbours(p).stream().filter(n -> getFi(n) == -1).count();
      if(innerNeighbours == 0){
        lOutIt.remove();
        fi[p.getX()][p.getY()] = 3;
      }
    }
    for (int i = 0; i < sourceATIImage.getWidth(); i++) {
      for (int j = 0; j < sourceATIImage.getHeight(); j++) {
        if (fi[i][j] == -1){
          int index = indexRGB(i,j,sourceATIImage.getWidth());
          ans[red(index)] = 255;
          ans[green(index)] = 0;
          ans[blue(index)] = 0;
        }
      }
    }
    return ans;
  }
  
  public List<CoordinatePair> neighbours(CoordinatePair pair){
    ArrayList<CoordinatePair> ans = new ArrayList<>();
    ans.add(new CoordinatePair(pair.getX(),pair.getY() + 1));
    ans.add(new CoordinatePair(pair.getX(),pair.getY() - 1));
    ans.add(new CoordinatePair(pair.getX() + 1,pair.getY()));
    ans.add(new CoordinatePair(pair.getX() - 1,pair.getY()));
    ans = (ArrayList<CoordinatePair>) ans.stream().filter(
      p ->
        p.getX() >= 0 && p.getX() < sourceATIImage.getWidth()
          && p.getY() >= 0 && p.getY() < sourceATIImage.getWidth())
      .collect(Collectors.toList());
    
    return ans;
  }
  
  public void set(ATIImage sourceAtiImage, int N){
    this.iter = 0;
    this.maxIter = N;
    this.sourceATIImage = sourceAtiImage;
    if (!outerSet || ! innerSet){
      DialogFactory.promptError("Regions not set");
    }
    theta0 = sourceAtiImage.averageColor(inPFrom.getX(), inPFrom.getY(),
      inPTo.getX() - inPFrom.getX(),inPTo.getY() - inPFrom.getY());
    theta1 = sourceAtiImage.averageColor(outPFrom.getX(), outPFrom.getY(),
      outPTo.getX() - outPFrom.getX(),outPTo.getY() - outPFrom.getY());
    Lin = new HashSet<>();
    Lout = new HashSet<>();
    fillFi();
    setLs();
  }
  
  private void setLs(){
    for (int x = inPFrom.getX(); x <= inPTo.getX(); x++) {
      Lin.add(new CoordinatePair(x,inPFrom.getY()));
      Lin.add(new CoordinatePair(x,inPTo.getY()));
      Lout.add(new CoordinatePair(x,inPFrom.getY()-1));
      Lout.add(new CoordinatePair(x,inPTo.getY()+1));
      
      fi[x][inPFrom.getY()] = -1;
      fi[x][inPTo.getY()] = -1;
      fi[x][inPFrom.getY() - 1] = 1;
      fi[x][inPTo.getY() + 1] = 1;
      
    }
    
    for (int y = inPFrom.getY(); y <= inPTo.getY(); y++) {
      Lin.add(new CoordinatePair(inPFrom.getX(),y));
      Lin.add(new CoordinatePair(inPTo.getX(),y));
      Lout.add(new CoordinatePair(inPFrom.getX()-1,y));
      Lout.add(new CoordinatePair(inPTo.getX()+1,y));
  
      fi[inPFrom.getX()][y] = -1;
      fi[inPTo.getX()][y] = -1;
      fi[inPFrom.getX() - 1][y] = 1;
      fi[inPTo.getX() + 1][y] = 1;
    }
  
    for (int x = inPFrom.getX() + 1; x < inPTo.getX(); x++) {
      for (int y = inPFrom.getY() + 1; y < inPTo.getY(); y++) {
        //In.add(new CoordinatePair(x,y));
  
        fi[x][y] = -3;
        fi[x][y] = -3;
      }
    }
    
  }
  
  public void setInPairs(CoordinatePair inP1, CoordinatePair inP2) {
    this.inPFrom = new CoordinatePair(Math.min(inP1.getX(), inP2.getX()), Math.min(inP1.getY(),inP2.getY()));;
    this.inPTo = new CoordinatePair(Math.max(inP1.getX(), inP2.getX()), Math.max(inP1.getY(),inP2.getY()));;
    this.innerSet = true;
  }
  
  public void setOutPairs(CoordinatePair outP1, CoordinatePair outP2) {
    this.outPFrom = new CoordinatePair(Math.min(outP1.getX(), outP2.getX()), Math.min(outP1.getY(),outP2.getY()));;
    this.outPTo = new CoordinatePair(Math.max(outP1.getX(), outP2.getX()), Math.max(outP1.getY(),outP2.getY()));;
    this.outerSet = true;
  }
  
  public double fd(int indexRGB, int[] image){
    double dif0 = Math.pow(image[red(indexRGB)] - theta0.getRed(),2) +
      Math.pow(image[green(indexRGB)] - theta0.getGreen(),2) +
      Math.pow(image[blue(indexRGB)] - theta0.getBlue(),2);
    double dif1 = Math.pow(image[red(indexRGB)] - theta1.getRed(),2) +
      Math.pow(image[green(indexRGB)] - theta1.getGreen(),2) +
      Math.pow(image[blue(indexRGB)] - theta1.getBlue(),2);
    return Math.log(Math.abs(dif0)/Math.abs(dif1));
  }
  
  public int getFi(CoordinatePair p) {
    return getFi(p.getX(), p.getY());
  }
  
  public int getFi(int x, int y) {
    return fi[x][y];
  }
  
  private void fillFi() {
    fi = new int[sourceATIImage.getWidth()][sourceATIImage.getHeight()];
    for (int x = 0; x < sourceATIImage.getWidth(); x++) {
      for (int y = 0; y < sourceATIImage.getHeight(); y++) {
        fi[x][y] = 3;
      }
    }
  }
  
  public ATIImage apply(){
    return new PpmImage(applyRaw(), sourceATIImage.getWidth(), sourceATIImage.getHeight());
  }
  
}
