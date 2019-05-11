package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.Pair;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetInnerActiveContourFilterButtonListener implements ActionListener {
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    EditableImageFrame e = EditableImageFrame.instance();
    Pair p1 = new Pair(e.getRegion().getX(), e.getRegion().getY());
    Pair p2 = new Pair(e.getRegion().getX() + e.getRegion().getW(), e.getRegion().getY() + e.getRegion().getH());
    
    ActiveContourFilter.getInstance().setInPairs(p1.reverse(),p2.reverse());
    ActiveContourFilter.getInstance().set(e.getAtiImage(),100);
    /*TODO CHANGE N*/
  }
}
