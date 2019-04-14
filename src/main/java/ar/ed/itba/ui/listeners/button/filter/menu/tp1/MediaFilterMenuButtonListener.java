package ar.ed.itba.ui.listeners.button.filter.menu.tp1;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

import javax.swing.*;

public class MediaFilterMenuButtonListener extends MaskFilterMenuButtonListener {
	
	public MediaFilterMenuButtonListener() {
		options.add(MenuOptionButtonFactory.mediaFilterMenuOptionButton(maskSideField));
	}
}
