package ar.ed.itba.utils.finall;

import org.opencv.features2d.KeyPoint;

import java.util.Comparator;
import java.util.Objects;

public class KeyPointComparator implements Comparator<KeyPoint> {
  private boolean moreRight;
  private boolean moreDown;
  
  private KeyPointComparator(boolean moreRight, boolean moreDown) {
    this.moreRight = moreRight;
    this.moreDown = moreDown;
  }
  
  public static KeyPointComparator ULComparator(){
    return new KeyPointComparator (false,false);
  }
  
  public static KeyPointComparator DLComparator(){
    return new KeyPointComparator (false,true);
  }
  
  public static KeyPointComparator URComparator(){
    return new KeyPointComparator (true,false);
  }
  
  public static KeyPointComparator DRComparator(){
    return new KeyPointComparator (true,true);
  }
  
  
  @Override
  public int compare(KeyPoint keyPoint, KeyPoint t1) {
    double v;
    if(!moreDown){
      if(!moreRight){
        // MORE UL, so less distance to origin
        v = Math.signum(distanceToOrigin(t1) - distanceToOrigin(keyPoint));
      } else {
        // MORE R
        v = keyPoint.pt.x - t1.pt.x;
      }
    } else {
      if (!moreRight) {
        // MORE D
        v = keyPoint.pt.y - t1.pt.y;
      } else {
        // MORE DR, so more distance to origin
        v = Math.signum(distanceToOrigin(keyPoint) - distanceToOrigin(t1));
      }
    }
    return (int) v;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    KeyPointComparator that = (KeyPointComparator) o;
    return moreRight == that.moreRight &&
      moreDown == that.moreDown;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(moreRight, moreDown);
  }
  
  private double distanceToOrigin(KeyPoint k){
    return Math.sqrt(Math.pow(k.pt.x,2)+Math.pow(k.pt.y,2));
    
  }
}

