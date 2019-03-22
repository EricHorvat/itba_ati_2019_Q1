package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.ui.frames.MainFrame;

public abstract class ATIMenuButtonListener extends ATIGeneralMenuButtonListener {
	
	public ATIMenuButtonListener() {
		super(MainFrame.instance().getDetailPanel());
	}
}
