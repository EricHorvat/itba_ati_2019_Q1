package ar.ed.itba.ui.listeners.button.filter.menu.tp2;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

import javax.swing.*;

public class AnisotropicFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public AnisotropicFilterMenuButtonListener() {
    options.add(new JLabel("Delta"));
    JTextField deltaField = new JTextField();
    options.add(deltaField);
    options.add(new JLabel("Sigma"));
    JTextField sigmaField = new JTextField();
    options.add(sigmaField);
    options.add(MenuOptionButtonFactory.anisotropicLeclercFilterMenuOptionButton(deltaField, sigmaField));
    options.add(MenuOptionButtonFactory.anisotropicLorentzFilterMenuOptionButton(deltaField, sigmaField));
  }
}
