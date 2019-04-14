package ar.ed.itba.ui.listeners.button.filter.effect.tp2;

import ar.ed.itba.ui.listeners.button.filter.effect.MaskFilterButtonListener;
import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.heat.IsotropicFilter;

import javax.swing.*;

public class IsotropicFilterButtonListener extends MaskFilterButtonListener {
    // TODO PARAMS protected JTextField gammaRField;

    public IsotropicFilterButtonListener(JTextField maskSideField) {
        super(maskSideField);
    }

    @Override
    public MaskFilter getFilter() {
        int maskSide = Integer.parseInt(maskSideField.getText());

        return new IsotropicFilter(maskSide);
    }

    @Override
    public String getName() {
        return "Anisotropic Filter";
    }
}
