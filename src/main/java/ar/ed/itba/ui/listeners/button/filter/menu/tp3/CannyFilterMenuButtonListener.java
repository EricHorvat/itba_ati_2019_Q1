package ar.ed.itba.ui.listeners.button.filter.menu.tp3;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class CannyFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public CannyFilterMenuButtonListener() {
    options.add(new JLabel("Delta"));
    JTextField deltaField = new JTextField();
    options.add(deltaField);
    options.add(new JLabel("T"));
    JTextField tField = new JTextField();
    options.add(tField);
    options.add(MenuOptionButtonFactory.cannyFilterMenuOptionButton(deltaField, tField));
  }
}
