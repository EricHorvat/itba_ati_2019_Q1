package ar.ed.itba.ui.listeners.button.filter.effect.tp2;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.BilateralFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.AnisotropicFilter;

import javax.swing.*;

public class AnisotropicFilterButtonListener extends MaskFilterButtonListener {
    // TODO PARAMS protected JTextField gammaRField;

    public AnisotropicFilterButtonListener(JTextField maskSideField) {
        super(maskSideField);
    }

    @Override
    public MaskFilter getFilter() {
        int maskSide = Integer.parseInt(maskSideField.getText());

        return new AnisotropicFilter(maskSide);
    }

    @Override
    public String getName() {
        return "Anisotropic Filter";
    }
}
