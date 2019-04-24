package ar.ed.itba.ui.listeners.button.filter.effect.tp2;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.IsotropicFilter;

import javax.swing.*;

public class IsotropicFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField deltaField;
  
  public IsotropicFilterButtonListener(JTextField deltaField) {
        super(null);
        this.deltaField = deltaField;
    }

  @Override
  public MaskFilter getFilter() {
    
    double delta = Double.parseDouble(deltaField.getText());

    return new IsotropicFilter(delta);
  }

  @Override
  public String getName() {
    return "Anisotropic Filter";
  }
}
