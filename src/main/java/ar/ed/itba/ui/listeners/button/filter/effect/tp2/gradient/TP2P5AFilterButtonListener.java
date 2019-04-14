package ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.TP2P5AFilter;

public class TP2P5AFilterButtonListener extends MaskFilterButtonListener {
	
	private final GradientFilterType type;
	
	public TP2P5AFilterButtonListener(GradientFilterType type) {
		super(null);
		this.type = type;
	}
	
	@Override
	public MaskFilter getFilter() {
		return new TP2P5AFilter(type);
	}
	
	@Override
	public String getName() {
		return "TP2 5A 3x3 " + type.name();
	}
}
