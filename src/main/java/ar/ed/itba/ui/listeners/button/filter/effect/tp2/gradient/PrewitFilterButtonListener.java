package ar.ed.itba.ui.listeners.button.filter.effect.tp2.gradient;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.gradient.GradientFilterType;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.gradient.PrewitFilter;

public class PrewitFilterButtonListener extends MaskFilterButtonListener {
	
	private final GradientFilterType type;
	
	public PrewitFilterButtonListener(GradientFilterType type) {
		super(null);
		this.type = type;
	}
	
	@Override
	public MaskFilter getFilter() {
		return new PrewitFilter(type);
	}
	
	@Override
	public String getName() {
		return "Prewit 3x3 " + type.name();
	}
}
