package ar.ed.itba;

import ar.ed.itba.ui.listeners.FileListener;
import ar.ed.itba.ui.listeners.FiltersListener;

import javax.swing.*;

public class ATIInterface {
	private JButton fileButton;
	private JButton basicOptionsButton;
	private JButton filterButton;
	private JPanel menuPanel;
	private JPanel detailPanel;
	private JPanel paramPanel;
	private JLabel detailTitle;
	private JLabel paramTitle;
	private JPanel mainPanel;
	private JLabel imageLabel;
	
	JPanel getMainPanel(){
		return mainPanel;
	}
	
	public ATIInterface(){
		setListeners();
		detailPanel.setVisible(false);
		paramPanel.setVisible(false);
	}
	
	private void setListeners(){
		fileButton.addActionListener(new FileListener(mainPanel, detailPanel));
		filterButton.addActionListener(new FiltersListener(mainPanel, detailPanel));
	}
	
}
