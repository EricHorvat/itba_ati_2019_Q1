package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

/*package */ abstract class MaskFilterMenuButtonListener extends ATIMenuOptionsButtonListener {
	/* package */ JTextField maskSideField = new JTextField();
	
	/* package */ MaskFilterMenuButtonListener() {
		options.add(new JLabel("Mask Side"));
		options.add(maskSideField);
	}
}
