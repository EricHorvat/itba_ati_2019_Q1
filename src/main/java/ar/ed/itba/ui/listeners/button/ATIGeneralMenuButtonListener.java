package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public abstract class ATIGeneralMenuButtonListener implements ActionListener {
	private JPanel targetPanel;
	
	protected final List<JComponent> options = new ArrayList<>();
	
	public List<JComponent> getOptions() {
		return options;
	}
	
	public ATIGeneralMenuButtonListener(JPanel targetPanel){
		this.targetPanel = targetPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		targetPanel.setVisible(true);
		targetPanel.removeAll();
		targetPanel.setLayout(new GridLayout(getOptions().size()+2,0));
		getOptions().forEach(o -> targetPanel.add(o));
		targetPanel.revalidate();
		targetPanel.repaint();
		MainFrame.instance().pack();
	
	}
}
