package ar.ed.itba.ui.listeners.button.finall.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class FinalMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public FinalMenuButtonListener() {
    JCheckBox useOnlyHarrisCheckBox = new JCheckBox("Run only Harris");
    options.add(useOnlyHarrisCheckBox);
    JCheckBox ignoreNeighbours = new JCheckBox("Ignore Neighbours");
    options.add(ignoreNeighbours);
    options.add(new JLabel("From photo"));
    JTextField fromTextField = new JTextField();
    options.add(fromTextField);
    options.add(new JLabel("To photo"));
    JTextField toTextField = new JTextField();
    options.add(toTextField);
    options.add(new JLabel("From delta"));
    JTextField fromDTextField = new JTextField();
    options.add(fromDTextField);
    options.add(new JLabel("To delta"));
    JTextField toDTextField = new JTextField();
    options.add(toDTextField);
    options.add(MenuOptionButtonFactory.finalMenuOptionButton(
      ignoreNeighbours,
      useOnlyHarrisCheckBox,
      fromTextField,
      toTextField,
      fromDTextField,
      toDTextField));
	}
	
}
