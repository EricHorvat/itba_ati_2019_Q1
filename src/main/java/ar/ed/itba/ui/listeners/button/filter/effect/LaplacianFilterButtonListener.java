package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.laplacian.LaplacianFilter;

import javax.swing.*;

public class LaplacianFilterButtonListener extends MaskFilterButtonListener {
	
	private boolean pendientControl;
	private JTextField thresholdField;
	
	public LaplacianFilterButtonListener(boolean pendientControl) {
		super(null);
		this.pendientControl = pendientControl;
	}
	
	public LaplacianFilterButtonListener(boolean pendientControl, JTextField thresholdField) {
		this(pendientControl);
		this.thresholdField = thresholdField;
	}
	
	@Override
	public MaskFilter getFilter() {
		Integer threshold = null;
		if (thresholdField != null){
			threshold = Integer.parseInt(thresholdField.getText());
		}
		return new LaplacianFilter(pendientControl, threshold);
	}
	
	@Override
	public String getName() {
		return "Laplacian 3x3";
	}
}
