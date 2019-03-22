package ar.ed.itba.ui.components;

import javax.swing.*;

public final class DialogFactory {
	public static void promptError(String msg){
		JOptionPane.showMessageDialog(null, msg);
	}

	public static void notImplementedDialog(){
		promptError( "Not implemented yet");
	}
}
