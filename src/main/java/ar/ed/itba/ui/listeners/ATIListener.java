package ar.ed.itba.ui.listeners;

import ar.ed.itba.App;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public abstract class ATIListener implements ActionListener {
	/*package */ abstract List<? extends JComponent> getOptions();
	private JPanel mainPanel;
	private JPanel targetPanel;
	
	ATIListener(JPanel mainPanel, JPanel targetPanel){
		this.mainPanel = mainPanel;
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
		App.pack();
	
	}
}
