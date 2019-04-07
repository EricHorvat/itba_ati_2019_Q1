package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.GradientFilterType;
import ar.ed.itba.utils.filters.KirshFilter;
import ar.ed.itba.utils.filters.MaskFilter;

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
