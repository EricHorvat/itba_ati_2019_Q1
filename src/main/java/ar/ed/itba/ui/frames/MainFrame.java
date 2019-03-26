package ar.ed.itba.ui.frames;

import ar.ed.itba.ui.frames.interfaces.MainInterface;

import javax.swing.*;

public class MainFrame extends ATIFrame {
	private static MainFrame instance;
	private MainInterface anInterface = new MainInterface();
	
	private MainFrame(){
		super("ATI GUI");
	}
	
	public static MainFrame instance() {
		if (instance == null) {
			instance = new MainFrame();
			instance.anInterface.setListeners();
		}
		return instance;
	}
	
	@Override
	protected JPanel getMainPanel() {
		return anInterface.getMainPanel();
	}
	
	public JPanel getDetailPanel(){
		return anInterface.getDetailPanel();
	}
	
	public JPanel getParamPanel(){
		return anInterface.getParamPanel();
	}
}
