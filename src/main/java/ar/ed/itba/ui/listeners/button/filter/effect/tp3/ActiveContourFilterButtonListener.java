package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.listeners.button.filter.effect.FilterButtonListener;
import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.Filter;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.advanced.SusanFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.IntStream;

public class ActiveContourFilterButtonListener implements ActionListener {
  
  private final JTextField nField;
  
  public ActiveContourFilterButtonListener(JTextField nField) {
    this.nField = nField;
  }
  
  public String getTitle() {
    return "Active Contour";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    
    ActiveContourFilter.getInstance();

    int N = Integer.parseInt(nField.getText());
  
    for (int i = 0; i < N - 1; i++) {
      ActiveContourFilter.getInstance().apply();
    }
    
    ATIImage resultImage = ActiveContourFilter.getInstance().apply();
    
    FrameFactory.fixedImageFrame(getTitle(), resultImage).buildAndShow();
  }
}
