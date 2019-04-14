package ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.SobelFilter;

public class SobelFilterButtonListener extends MaskFilterButtonListener {
	
	private final GradientFilterType type;
	
	public SobelFilterButtonListener(GradientFilterType type) {
		super(null);
		this.type = type;
	}
	
	@Override
	public MaskFilter getFilter() {
		return new SobelFilter(type);
	}
	
	@Override
	public String getName() {
		return "Sobel 3x3 " + type.name();
	}
}
