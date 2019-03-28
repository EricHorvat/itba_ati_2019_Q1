package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.utils.filters.MaskFilter;
import ar.ed.itba.utils.filters.MedianFilter;
import ar.ed.itba.utils.filters.PonderatedMedianFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PonderedMedianFilterButtonListener extends MaskFilterButtonListener{
	
	public PonderedMedianFilterButtonListener() {
		super(null);
	}
	
	@Override
	public MaskFilter getFilter() {
		return new PonderatedMedianFilter();
	}
	
	@Override
	public String getName() {
		return "PMedian";
	}
}
