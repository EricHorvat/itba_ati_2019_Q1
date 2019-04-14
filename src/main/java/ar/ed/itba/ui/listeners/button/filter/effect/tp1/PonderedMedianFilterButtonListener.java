package ar.ed.itba.ui.listeners.button.filter.effect.tp1;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.median.PonderatedMedianFilter;

public class PonderedMedianFilterButtonListener extends MaskFilterButtonListener {
	
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
