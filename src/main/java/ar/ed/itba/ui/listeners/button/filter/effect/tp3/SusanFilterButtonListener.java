package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.advanced.SusanFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.IsotropicFilter;

import javax.swing.*;

public class SusanFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField epsField;
  
  public SusanFilterButtonListener(JTextField epsField) {
    super(null);
    this.epsField = epsField;
  }

  @Override
  public MaskFilter getFilter() {
    
    double epsilon = Double.parseDouble(epsField.getText());

    return new SusanFilter(epsilon);
  }

  @Override
  public String getName() {
    return "Susan Filter";
  }
}
