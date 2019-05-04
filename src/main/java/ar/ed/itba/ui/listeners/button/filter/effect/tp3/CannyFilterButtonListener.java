package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.advanced.CannyFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.IsotropicFilter;

import javax.swing.*;

public class CannyFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField t1Field;
  private final JTextField t2Field;
  
  public CannyFilterButtonListener(JTextField t1Field, JTextField t2Field) {
    super(null);
    this.t1Field = t1Field;
    this.t2Field = t2Field;
  }

  @Override
  public MaskFilter getFilter() {
  
    int t1 = Integer.parseInt(t1Field.getText());
    int t2 = Integer.parseInt(t2Field.getText());

    return new CannyFilter(t1, t2);
  }

  @Override
  public String getName() {
    return "Isotropic Filter";
  }
}
