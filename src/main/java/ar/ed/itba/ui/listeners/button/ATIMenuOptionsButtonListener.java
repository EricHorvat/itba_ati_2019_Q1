package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.ui.frames.MainFrame;

public abstract class ATIMenuOptionsButtonListener extends ATIGeneralMenuButtonListener {
	
	public ATIMenuOptionsButtonListener() {
		super(MainFrame.instance().getParamPanel());
	}
}
