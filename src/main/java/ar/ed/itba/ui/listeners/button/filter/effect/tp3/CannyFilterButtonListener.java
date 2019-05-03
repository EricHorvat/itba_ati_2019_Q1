package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.IsotropicFilter;

import javax.swing.*;

public class CannyFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField deltaField;
  private final JTextField tField;
  
  public CannyFilterButtonListener(JTextField deltaField, JTextField tField) {
    super(null);
    this.deltaField = deltaField;
    this.tField = tField;
  }

  @Override
  public MaskFilter getFilter() {
    
    double delta = Double.parseDouble(deltaField.getText());
    int t = Integer.parseInt(tField.getText());

    return new IsotropicFilter(delta, t);
  }

  @Override
  public String getName() {
    return "Isotropic Filter";
  }
}
