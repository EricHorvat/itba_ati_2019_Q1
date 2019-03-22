package ar.ed.itba.ui.listeners.button.filter.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class MediaFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public MediaFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.mediaFilterMenuOptionButton(maskSideField));
	}
}
