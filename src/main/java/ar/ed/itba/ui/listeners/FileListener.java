package ar.ed.itba.ui.listeners;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import ar.ed.itba.file.ImageOpener;

public class FileListener extends ATIListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	List<JButton> getOptions() {
		return options;
	}
	
	public FileListener(JPanel mainPanel, JPanel targetPanel) {
		super(mainPanel, targetPanel);
		options.add(openFileButton());
		options.add(aButton("a"));
		options.add(aButton("b"));
		options.add(aButton("c"));
	}
	
	private static JButton aButton( String text){
		JButton button = new JButton(text);
		button.addActionListener(actionEvent -> {
			button.setText(button.getText()+text);
		});
		
		return button;
	}
	
	private static JButton openFileButton(){
		JButton button = new JButton("Open file");
		button.addActionListener(actionEvent -> {
			ImageOpener imageOpener = new ImageOpener();
			BufferedImage image = imageOpener.open();
			JFrame frame = new JFrame("Image");
			frame.getContentPane().setLayout(new FlowLayout());
			frame.getContentPane().add(new JLabel(new ImageIcon(image)));
			frame.pack();
			frame.setVisible(true);
		});
		return button;
	}
}
