package ar.ed.itba.ui.listeners.button.menu;

import ar.ed.itba.ui.frames.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class ATIMenuButtonListener implements ActionListener {
	private JPanel targetPanel;
	
	public abstract List<? extends JComponent> getOptions();
	
	public ATIMenuButtonListener(JPanel targetPanel){
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
