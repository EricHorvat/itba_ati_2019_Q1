package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.GradientFilterType;
import ar.ed.itba.utils.filters.MaskFilter;
import ar.ed.itba.utils.filters.TP2P5AFilter;

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
