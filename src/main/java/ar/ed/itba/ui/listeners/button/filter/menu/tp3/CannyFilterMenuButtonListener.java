package ar.ed.itba.ui.listeners.button.filter.menu.tp3;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class CannyFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public CannyFilterMenuButtonListener() {
    options.add(new JLabel("T1"));
    JTextField t1Field = new JTextField();
    options.add(t1Field);
    options.add(new JLabel("T2"));
    JTextField t2Field = new JTextField();
    options.add(t2Field);
    options.add(MenuOptionButtonFactory.cannyFilterMenuOptionButton(t1Field, t2Field));
  }
}
