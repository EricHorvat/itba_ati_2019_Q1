package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.listeners.button.menu.FileMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.FiltersMenuButtonListener;

import javax.swing.*;

public class MainInterface {
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
	
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public MainInterface(){
		setListeners();
		detailPanel.setVisible(false);
		paramPanel.setVisible(false);
	}
	
	private void setListeners(){
		fileButton.addActionListener(new FileMenuButtonListener(mainPanel, detailPanel));
		filterButton.addActionListener(new FiltersMenuButtonListener(mainPanel, detailPanel));
	}
	
}
