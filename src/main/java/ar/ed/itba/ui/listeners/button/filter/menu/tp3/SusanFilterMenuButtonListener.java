package ar.ed.itba.ui.listeners.button.filter.menu.tp3;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class SusanFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public SusanFilterMenuButtonListener() {
    options.add(new JLabel("Epsilon"));
    JTextField epsField = new JTextField();
    options.add(epsField);
    options.add(MenuOptionButtonFactory.susanFilterMenuOptionButton(epsField));
  }
}
