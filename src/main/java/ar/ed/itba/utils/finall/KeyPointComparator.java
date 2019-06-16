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
  
  @Override
  public int compare(KeyPoint keyPoint, KeyPoint t1) {
    return Double.compare((moreDown?-1:1)*(t1.pt.y - keyPoint.pt.y), (moreRight?-1:1)*(t1.pt.x - keyPoint.pt.x));
  }
  /*
   *      These example is UL, so the above equation is
   *      Double.compare((t1.pt.y - keyPoint.pt.y), (t1.pt.x - keyPoint.pt.x));
   *      So its moving origin to keyPoint, and see if t1 is above x = y (x <= y)
   *      More over: UR above -x = y (-x <= y)
   *      More over: DL below x = y (x <= -y)
   *      More over: DR below -x = y (-x <= -y ==> x >= y)
   *
   *                               .;;.                     ':llc.               .l0Ol.
   *                              .kMMx.                   'xXWMMN:              .kMMx.           .:xx;
   *         .cxd;                .kMMx.                .oKWMMMMWkxx:.           .kMMx.         .;kNMNd.
   *         '0MWo                .kMMx.              .,oNWKkXMMWXXMWKo,         .kMMx.       .;kNMNk;
   *         '0MWo                .kMMx.              .:l'  'OMMNl' .:xd'        .kMMx.     .;kNMNk;
   *         ,0MWl                .kMMx.                    .kMMN:               .kMMx.   .;kNMNk;
   *         ,KMNl                .kMMx.                    .kMMN:               .kMMx.  ;kNMNk;.
   *         ;KMNc                .kMMx.                    .kWMN:               .kMMk';kNMNk;.
   *         ;KMNc                .kMMx.                    .xWMN:               .kMMX0NMNk;.
   *         :XMXc                .kMMx.                    .xWMN:               .kMMMMNk:.
   *         :XMX:                .kMMk.                    .xWMN:               :KMMNO:.
   *         cXMX:                .kMMk.                    .dWMN:             ,xNMNO:.
   *         cXMK;                .kMMk.                    .dWMN:           ,xNMNO:.
   *         cNMK;                .kMMk. MMMW               .dWMN:         ,xXMNO:.
   *         lNMK,                .kMMk. MMMW               .oWMN:       ,xXMWO:.
   *         lNM0,                .kMMk.       .:l,. ...'.   oWMN:     ,dXMWO:.
   *         oWM0'                .kMMk.      .lXMN0O0XXXo.  oNMN:   'dXWWOc.
   *         oWM0'                .kMMk.       .kMMMMMWN0c.  lNMX; 'dXMW0c.
   *         dWMO.                .kMMk.       .dMMWWWWWKo.  lNMXddXMW0c.                          .,.
   *        .dWMO.                .kMMx.        ,loc;oKNWWKd;oNMWWWW0c.                           ;0NKo.
   *        .xMMO.                .kMMk.              .,l0WMNXWMMW0c.                             'dXMWKo
   *        .xMMO'       .........,OMMO,.......'''',,,,,;lOWM    KdccccccccllllllooooooddddddxxxxxkOXMMMW
   *     .lxOXMMN0kOOOO000000KKKKKXWMMWNXXXNNNNNNNNNNNWNWWWWM    MWWMMMMMMMMMMMWWWWWWWWNNNNNNNNNNXXXXNWMM
   *     .kXNWMMWXXKKKKKK00000OOOO0NMMXOkkxxxxxxdddddx0NWMMWKKWMWklcccccc::::::;;;;;;,,,,,''''''....;xNMW
   *      ..;0MWO,............    .kMMk.            .oXWWW0l.cXMN:                                 .xNMXd
   *        .kMWx.                .kMMx.          .oKWWNKl.  ;XMN:                                  ;dx,
   *        .OMWd.                .kMMx.        .oKWWKl;.    ;XMNc
   *        .OMWd.                .kMMk.      .lKWWKl.       ;XMNc
   *        '0MWo.                .kMMx.    .lKWWKl.         ,KMNc
   *        '0MNo.                 ,cc,   .lKWWKo.           ,KMNl
   *        ,0MNl                       .lKWWKo.             ,KMWl
   *        ,KMNl                     .l0WWKo.               ,0MWo
   *        ,KMNl                   .l0WWKo.                 '0MWo
   *        ;KMNc                 .l0WWKo'                   '0MWo.
   *        ;XMXc               .c0WWXo'                     'OMWd.
   *        :XMX:             .c0WWXd'                       .OMWd.
   *        :NMX:           .c0WWXd'                         .OMWd.
   *        cNMX;         .c0WMXd'                           .kMWx.
   *        cNMX;       .c0WMXd'                             .kMWx.
   *        :KXO'     .cOWMXd'                               .kMWx.
   *         ...    .cOWMXd'                                 .kMWk.
   *              .:OWMXx,                                   .xMMk.
   *            .:OWMXx,                                     .xMMk.
   *          .:OWMXx,                                       .xMMO.
   *        .:ONMXx,                                         .dMMO.
   *      .:ONMNx,                                            dWM0'
   *    .:ONMNx,                                              dWM0'
   *   'kNMNx,                                                oWM0'
   *   .lOx,                                                  :OOd.
   *    .'.                                                    ..
   */
}

