package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.listeners.button.menu.main.EditMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.main.FileMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.main.FiltersMenuButtonListener;
import ar.ed.itba.ui.listeners.button.menu.main.GenerateMenuButtonListener;

import javax.swing.*;

public class MainInterface {
	private JButton fileButton;
	private JButton generateOptionsButton;
	private JButton filterButton;
	private JPanel menuPanel;
	private JPanel detailPanel;
	private JPanel paramPanel;
	private JLabel detailTitle;
	private JLabel paramTitle;
	private JPanel mainPanel;
	private JLabel imageLabel;
	private JButton editButton;
	
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public MainInterface(){
		detailPanel.setVisible(false);
		paramPanel.setVisible(false);
	}
	
	public void setListeners(){
		fileButton.addActionListener(new FileMenuButtonListener(detailPanel));
		editButton.addActionListener(new EditMenuButtonListener(detailPanel));
		generateOptionsButton.addActionListener(new GenerateMenuButtonListener(detailPanel));
		filterButton.addActionListener(new FiltersMenuButtonListener(detailPanel));
	}
	
	public JPanel getMenuPanel() {
		return menuPanel;
	}
	
	public JPanel getDetailPanel() {
		return detailPanel;
	}
	
	public JPanel getParamPanel() {
		return paramPanel;
	}
}
