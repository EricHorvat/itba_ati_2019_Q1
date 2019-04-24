package ar.ed.itba.ui.listeners.button.filter.menu.tp2;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

import javax.swing.*;

public class IsotropicFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public IsotropicFilterMenuButtonListener() {
    options.add(new JLabel("Delta"));
    JTextField deltaField = new JTextField();
    options.add(deltaField);
    options.add(MenuOptionButtonFactory.isotropicFilterMenuOptionButton(deltaField));
  }
}
