package ar.ed.itba.ui.listeners.button.menu.main;

import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.ui.frames.ImageFrame;
import ar.ed.itba.ui.frames.interfaces.FixedImageInterface;
import ar.ed.itba.ui.listeners.button.menu.ATIMenuButtonListener;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FiltersMenuButtonListener extends ATIMenuButtonListener {
	
	private final List<JButton> options = new ArrayList<>();
	
	@Override
	public List<JButton> getOptions() {
		return options;
	}
	
	public FiltersMenuButtonListener(JPanel targetPanel) {
		super(targetPanel);
		options.add(aButton("Filter a"));
		options.add(aButton("Filter b"));
	}
	
	private static JButton aButton( String text){
		JButton button = new JButton(text);
		button.addActionListener(actionEvent -> {
			
			BufferedImage image = new BufferedImage(250,250,BufferedImage.TYPE_3BYTE_BGR);
			/*TODO REMOVE*/
			Random random = new Random();
			for(int count = 0; count < random.nextInt(60000); count++){
				image.setRGB(random.nextInt(250), random.nextInt(250), random.nextInt(0xFFFFFF));
			}
			//FrameFactory.fixedImageFrame("originalImage", image).buildAndShow();
			/*REMOVE*/
		});
		return button;
	}
}
