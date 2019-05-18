package ar.ed.itba.ui.listeners.button.filter.menu.tp4;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class HarrisMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public HarrisMenuButtonListener() {
    options.add(new JLabel("Threshold"));
    JTextField thresholdField = new JTextField();
    options.add(thresholdField);
    options.add(MenuOptionButtonFactory.harrisMenuOptionButton(thresholdField));
  }
}
