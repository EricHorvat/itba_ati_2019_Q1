package ar.ed.itba.ui.listeners.button.filter.effect.tp2;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.BilateralFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicG;

import javax.swing.*;

import static ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicG.LECLERC;

public class AnisotropicFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField deltaField;
  private final JTextField sigmaField;
  private final JTextField tField;
  private final AnisotropicG g;
  
  public AnisotropicFilterButtonListener(JTextField deltaField, JTextField sigmaField, JTextField tField, AnisotropicG g) {
    super(null);
  
    this.deltaField = deltaField;
    this.sigmaField = sigmaField;
    this.tField = tField;
    this.g = g == null ? LECLERC : g;
  }

  @Override
  public MaskFilter getFilter() {
  
    double delta = Double.parseDouble(deltaField.getText());
    double sigma = Double.parseDouble(sigmaField.getText());
    int t = Integer.parseInt(tField.getText());
    
    return new AnisotropicFilter(g, t, delta, sigma);
  }

  @Override
  public String getName() {
    return "Anisotropic " + g.name() + " Filter";
  }
}
