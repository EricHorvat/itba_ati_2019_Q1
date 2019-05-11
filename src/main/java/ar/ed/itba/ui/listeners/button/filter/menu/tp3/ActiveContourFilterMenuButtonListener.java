package ar.ed.itba.ui.listeners.button.filter.menu.tp3;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ActiveContourFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public ActiveContourFilterMenuButtonListener() {
    options.add(new JLabel("Iterations"));
    JTextField nField = new JTextField();
    options.add(nField);
    options.add(MenuOptionButtonFactory.setOuterActiveContourFilterMenuOptionButton());
    options.add(MenuOptionButtonFactory.setInnerActiveContourFilterMenuOptionButton());
    options.add(MenuOptionButtonFactory.activeContourFilterMenuOptionButton(nField));
  }
}
