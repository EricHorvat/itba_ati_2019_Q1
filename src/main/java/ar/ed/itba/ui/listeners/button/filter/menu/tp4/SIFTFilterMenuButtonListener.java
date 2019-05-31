package ar.ed.itba.ui.listeners.button.filter.menu.tp4;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class SIFTFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
  
  public SIFTFilterMenuButtonListener() {
    options.add(new JLabel("Image 1"));
    JTextField image1Field = new JTextField();
    options.add(image1Field);
    options.add(MenuOptionButtonFactory.setImageSIFTFilterMenuOptionButton(0, image1Field));
    options.add(new JLabel("Image 2"));
    JTextField image2Field = new JTextField();
    options.add(image2Field);
    options.add(MenuOptionButtonFactory.setImageSIFTFilterMenuOptionButton(1, image2Field));
    options.add(new JLabel("Matching Distance"));
    JTextField matchingDistanceField = new JTextField();
    options.add(matchingDistanceField);
    options.add(new JLabel("Matching Percentage"));
    JTextField matchingPercentageField = new JTextField();
    options.add(matchingPercentageField);
    JCheckBox statusCheckBox = new JCheckBox("Show result");
    options.add(statusCheckBox);
    options.add(MenuOptionButtonFactory.SIFTMenuOptionButton(matchingDistanceField, matchingPercentageField, statusCheckBox));
  }
}
