package ar.ed.itba.ui.listeners.button.filter.effect.tp2;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.IsotropicFilter;

import javax.swing.*;

public class IsotropicFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField deltaField;
  private final JTextField tField;
  
  public IsotropicFilterButtonListener(JTextField deltaField, JTextField tField) {
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
