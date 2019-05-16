package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateHoughCirclesMenuButtonListener extends ATIMenuOptionsButtonListener {

	public GenerateHoughCirclesMenuButtonListener() {
        options.add(new JLabel("fromA"));
        JTextField fromAParam = new JTextField();
        options.add(fromAParam);
        options.add(new JLabel("toA"));
        JTextField toAParam = new JTextField();
        options.add(toAParam);
        options.add(new JLabel("aIntervals"));
        JTextField aIntervals = new JTextField();
        options.add(aIntervals);
        options.add(new JLabel("fromB"));
        JTextField fromBParam = new JTextField();
        options.add(fromBParam);
        options.add(new JLabel("toB"));
        JTextField toBParam = new JTextField();
        options.add(toBParam);
        options.add(new JLabel("bIntervals"));
        JTextField bIntervals = new JTextField();
        options.add(bIntervals);
        options.add(new JLabel("fromR"));
        JTextField fromRParam = new JTextField();
        options.add(fromRParam);
        options.add(new JLabel("toR"));
        JTextField toRParam = new JTextField();
        options.add(toRParam);
        options.add(new JLabel("rIntervals"));
        JTextField rIntervals = new JTextField();
        options.add(rIntervals);
        options.add(new JLabel("circumferenceCount"));
        JTextField circumferenceCount = new JTextField();
        options.add(circumferenceCount);
        options.add(MenuOptionButtonFactory.generateHoughCirclesMenuOptionButton(circumferenceCount, fromAParam, toAParam, aIntervals, fromBParam,
                toBParam, bIntervals, fromRParam, toRParam, rIntervals));
	}
	
}
