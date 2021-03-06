package ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.gradient.KirshFilter;
import ar.ed.itba.utils.filters.mask.MaskFilter;

public class KirshFilterButtonListener extends MaskFilterButtonListener {
	
	private final GradientFilterType type;
	
	public KirshFilterButtonListener(GradientFilterType type) {
		super(null);
		this.type = type;
	}
	
	@Override
	public MaskFilter getFilter() {
		return new KirshFilter(type);
	}
	
	@Override
	public String getName() {
		return "Kirsh 3x3 " + type.name();
	}
}
