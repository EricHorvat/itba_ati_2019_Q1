package ar.ed.itba.ui.frames.interfaces;

import ar.ed.itba.ui.listeners.button.finall.menu.FinalMenuButtonListener;
import ar.ed.itba.ui.listeners.button.main.EditGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.main.FileGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.main.FiltersGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.main.GenerateGeneralMenuButtonListener;

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
	private JButton editButton;
  private JButton finalButton;
  
  public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public MainInterface(){
		detailPanel.setVisible(false);
		paramPanel.setVisible(false);
	}
	
	public void setListeners(){
		fileButton.addActionListener(new FileGeneralMenuButtonListener());
		editButton.addActionListener(new EditGeneralMenuButtonListener());
		generateOptionsButton.addActionListener(new GenerateGeneralMenuButtonListener());
    filterButton.addActionListener(new FiltersGeneralMenuButtonListener());
    finalButton.addActionListener(new FinalMenuButtonListener());
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
