package ar.ed.itba.utils.filters.advanced;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.utils.Pair;
import ar.ed.itba.utils.filters.Filter;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
  private int theta0 = 0;
  private double theta1 = 0.5;
  private Set<Pair> In = new HashSet<>();
  private Set<Pair> Lin = new HashSet<>();
  private Set<Pair> Lout = new HashSet<>();
  private int[][] fi;
  
  private boolean outerSet = false;
  private boolean innerSet = false;
  private Pair inPFrom;
  private Pair inPTo;
  private Pair outPFrom;
  private Pair outPTo;
  
  private ATIImage sourceATIImage;
  
  private ActiveContourFilter() {}
  
  public int[] applyRaw(){
    int[] ans = sourceATIImage.toRGB();
    int w = sourceATIImage.getWidth();
    Iterator<Pair> lOutIt = Lout.iterator();
    Set<Pair> toAdd = new HashSet<>();
    while (lOutIt.hasNext()){
      Pair p = lOutIt.next();
      if(fd(ans[indexRGB(p.getX(),p.getY(),w)]) > 0){
        Lin.add(p);
        fi[p.getX()][p.getY()] = -1;
        lOutIt.remove();
        List<Pair> areOuters = neighbours(p).stream().filter(n -> getFi(n) > 1).collect(Collectors.toList());
        toAdd.addAll(areOuters);
        areOuters.forEach(o -> fi[o.getX()][o.getY()] = 1);
      }
    }
    Lout.addAll(toAdd);
    Iterator<Pair> lInIt = Lin.iterator();
    while (lInIt.hasNext()){
      Pair p = lInIt.next();
      long outersNeighbours = neighbours(p).stream().filter(n -> getFi(n) == 1).count();
      if(outersNeighbours == 0){
        lInIt.remove();
        fi[p.getX()][p.getY()] = -3;
      }
    }
    toAdd = new HashSet<>();
    lInIt = Lin.iterator();
    while (lInIt.hasNext()){
      Pair p = lInIt.next();
      if(fd(ans[indexRGB(p.getX(),p.getY(),w)]) < 0){
        Lout.add(p);
        fi[p.getX()][p.getY()] = 1;
        lInIt.remove();
        List<Pair> areInners = neighbours(p).stream().filter(n -> getFi(n) < 1).collect(Collectors.toList());
        toAdd.addAll(areInners);
        areInners.forEach(o -> fi[o.getX()][o.getY()] = -1);
      }
    }
    Lin.addAll(toAdd);
    lOutIt = Lout.iterator();
    while (lOutIt.hasNext()){
      Pair p = lOutIt.next();
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
  
  public List<Pair> neighbours(Pair p){
    ArrayList<Pair> ans = new ArrayList<>();
    ans.add(new Pair(p.getX(),p.getY() + 1));
    ans.add(new Pair(p.getX(),p.getY() - 1));
    ans.add(new Pair(p.getX() + 1,p.getY()));
    ans.add(new Pair(p.getX() - 1,p.getY()));
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
      inPTo.getX() - inPFrom.getX(),inPTo.getY() - inPFrom.getY()).getRed();
    theta1 = sourceAtiImage.averageColor(outPFrom.getX(), outPFrom.getY(),
      outPTo.getX() - outPFrom.getX(),outPTo.getY() - outPFrom.getY()).getRed() + 0.5;
    fillFi();
    setLs();
  }
  
  private void setLs(){
    for (int x = inPFrom.getX(); x <= inPTo.getX(); x++) {
      Lin.add(new Pair(x,inPFrom.getY()));
      Lin.add(new Pair(x,inPTo.getY()));
      Lout.add(new Pair(x,inPFrom.getY()-1));
      Lout.add(new Pair(x,inPTo.getY()+1));
      
      fi[x][inPFrom.getY()] = -1;
      fi[x][inPTo.getY()] = -1;
      fi[x][inPFrom.getY() - 1] = 1;
      fi[x][inPTo.getY() + 1] = 1;
      
    }
    
    for (int y = inPFrom.getY(); y <= inPTo.getY(); y++) {
      Lin.add(new Pair(inPFrom.getX(),y));
      Lin.add(new Pair(inPTo.getX(),y));
      Lout.add(new Pair(inPFrom.getX()-1,y));
      Lout.add(new Pair(inPTo.getX()+1,y));
  
      fi[inPFrom.getX()][y] = -1;
      fi[inPTo.getX()][y] = -1;
      fi[inPFrom.getX() - 1][y] = 1;
      fi[inPTo.getX() + 1][y] = 1;
    }
  
    for (int x = inPFrom.getX() + 1; x < inPTo.getX(); x++) {
      for (int y = inPFrom.getY() + 1; y < inPTo.getY(); y++) {
        In.add(new Pair(x,y));
  
        fi[x][y] = -3;
        fi[x][y] = -3;
      }
    }
    
  }
  
  public void setInPairs(Pair inP1, Pair inP2) {
    this.inPFrom = new Pair(Math.min(inP1.getX(), inP2.getX()), Math.min(inP1.getY(),inP2.getY()));;
    this.inPTo = new Pair(Math.max(inP1.getX(), inP2.getX()), Math.max(inP1.getY(),inP2.getY()));;
    this.innerSet = true;
  }
  
  public void setOutPairs(Pair outP1, Pair outP2) {
    this.outPFrom = new Pair(Math.min(outP1.getX(), outP2.getX()), Math.min(outP1.getY(),outP2.getY()));;
    this.outPTo = new Pair(Math.max(outP1.getX(), outP2.getX()), Math.max(outP1.getY(),outP2.getY()));;
    this.outerSet = true;
  }
  
  public double fd(int x){
    return Math.log(Math.abs(theta0 - x)/Math.abs(theta1 - x));
  }
  
  public int getFi(Pair p) {
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
