package ar.ed.itba.ui.components;

import javax.swing.*;

public class DialogFactory {
	public static void promptError(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}
}
