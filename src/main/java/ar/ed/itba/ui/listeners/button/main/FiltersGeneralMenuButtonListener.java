package ar.ed.itba.ui.listeners.button.main;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.file.pixel.RGBPixel;
import ar.ed.itba.ui.components.MenuButtonFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.listeners.button.ATIGeneralMenuButtonListener;
import ar.ed.itba.ui.listeners.button.ATIMenuButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FiltersGeneralMenuButtonListener extends ATIMenuButtonListener {
	
	public FiltersGeneralMenuButtonListener() {
		options.add(MenuButtonFactory.mediaFilterMenuButton());
		options.add(MenuButtonFactory.medianFilterMenuButton());
		options.add(MenuButtonFactory.ponderedMedianFilterMenuButton());
		options.add(MenuButtonFactory.gaussianFilterMenuButton());
		options.add(MenuButtonFactory.highlightBorderFilterMenuButton());
		options.add(MenuButtonFactory.prewitFilterMenuButton());
		options.add(MenuButtonFactory.sobelFilterMenuButton());
	}
	
}
