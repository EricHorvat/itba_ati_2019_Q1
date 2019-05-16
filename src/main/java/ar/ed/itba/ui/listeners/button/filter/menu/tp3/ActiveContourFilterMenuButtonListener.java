package ar.ed.itba.ui.listeners.button.filter.menu.tp3;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class ActiveContourFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public ActiveContourFilterMenuButtonListener() {
    options.add(MenuOptionButtonFactory.setInnerActiveContourFilterMenuOptionButton());
    options.add(MenuOptionButtonFactory.setOuterActiveContourFilterMenuOptionButton());
    options.add(new JLabel("Max iterations"));
    JTextField nMaxField = new JTextField();
    options.add(nMaxField);
    options.add(MenuOptionButtonFactory.setParamsActiveContourFilterMenuOptionButton(nMaxField));
    options.add(new JLabel("Iterations"));
    JTextField nField = new JTextField();
    options.add(nField);
    options.add(MenuOptionButtonFactory.activeContourFilterMenuOptionButton(nField));
  }
}
