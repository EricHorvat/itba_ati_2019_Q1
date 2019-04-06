package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.DerivedFilterType;
import ar.ed.itba.utils.filters.MaskFilter;
import ar.ed.itba.utils.filters.PrewitFilter;

public class PrewitFilterButtonListener extends MaskFilterButtonListener {
	
	private final DerivedFilterType type;
	
	public PrewitFilterButtonListener(DerivedFilterType type) {
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
