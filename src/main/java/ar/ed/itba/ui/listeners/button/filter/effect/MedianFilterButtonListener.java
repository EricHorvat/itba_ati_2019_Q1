package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.utils.filters.MaskFilter;
import ar.ed.itba.utils.filters.MediaFilter;
import ar.ed.itba.utils.filters.MedianFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedianFilterButtonListener extends MaskFilterButtonListener {
	
	public MedianFilterButtonListener(JTextField maskSideField) {
		super(maskSideField);
	}
	
	@Override
	public MaskFilter getFilter() {
		int maskSide = Integer.parseInt(maskSideField.getText());
		
		return new MedianFilter(maskSide);
	}
	
	@Override
	public String getName() {
		return "Median";
	}
}
