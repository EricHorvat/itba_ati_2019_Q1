package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

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

public class ActiveContourFilterButtonListener extends FilterButtonListener {
  
  private final JTextField nField;
  
  public ActiveContourFilterButtonListener(JTextField nField) {
    this.nField = nField;
  }

  @Override
  public Filter getFilter() {
    
    int N = Integer.parseInt(nField.getText());

    return new ActiveContourFilter(N);
  }

  @Override
  public String getTitle() {
    return "Active Contour";
  }
  
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
  
  }
}