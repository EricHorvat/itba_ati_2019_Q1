package ar.ed.itba.ui.listeners.button.filter.effect.tp2;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.BilateralFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicG;

import javax.swing.*;

import static ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicG.LECLERC;

public class AnisotropicFilterButtonListener extends MaskFilterButtonListener {
  
  private final JTextField deltaSideField;
  private final JTextField sigmaSideField;
  private final AnisotropicG g;
  
  public AnisotropicFilterButtonListener(JTextField deltaSideField, JTextField sigmaSideField, AnisotropicG g) {
    super(null);
  
    this.deltaSideField = deltaSideField;
    this.sigmaSideField = sigmaSideField;
    this.g = g == null ? LECLERC : g;
  }

  @Override
  public MaskFilter getFilter() {
  
    double delta = Double.parseDouble(deltaSideField.getText());
    double sigma = Double.parseDouble(sigmaSideField.getText());
    
    return new AnisotropicFilter(g, delta, sigma);
  }

  @Override
  public String getName() {
    return "Anisotropic " + g.name() + " Filter";
  }
}
