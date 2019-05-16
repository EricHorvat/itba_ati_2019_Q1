package ar.ed.itba.ui.listeners.button.generate.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

import javax.swing.*;

public class GenerateHoughLinesMenuButtonListener extends ATIMenuOptionsButtonListener {
	
	public GenerateHoughLinesMenuButtonListener() {
		options.add(new JLabel("fromTheta"));
		JTextField fromThetaParam = new JTextField();
		options.add(fromThetaParam);
		options.add(new JLabel("toTheta"));
		JTextField toThetaParam = new JTextField();
		options.add(toThetaParam);
		options.add(new JLabel("thetaIntervals"));
		JTextField thetaIntervals = new JTextField();
		options.add(thetaIntervals);
		options.add(new JLabel("fromPhi"));
		JTextField fromPhiParam = new JTextField();
		options.add(fromPhiParam);
		options.add(new JLabel("toPhi"));
		JTextField toPhiParam = new JTextField();
		options.add(toPhiParam);
		options.add(new JLabel("phiIntervals"));
		JTextField phiIntervals = new JTextField();
		options.add(phiIntervals);
		options.add(new JLabel("sinusoidalCount"));
		JTextField circumferenceCount = new JTextField();
		options.add(circumferenceCount);
		options.add(MenuOptionButtonFactory.generateHoughLinesMenuOptionButton(circumferenceCount, fromThetaParam, toThetaParam, thetaIntervals, fromPhiParam,
				toPhiParam, phiIntervals));
	}
	
}
