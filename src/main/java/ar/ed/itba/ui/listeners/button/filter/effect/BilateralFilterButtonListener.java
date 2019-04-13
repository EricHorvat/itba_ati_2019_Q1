package ar.ed.itba.ui.listeners.button.filter.effect;

import ar.ed.itba.utils.filters.mask.MaskFilter;
import ar.ed.itba.utils.filters.mask.weight.BilateralFilter;

import javax.swing.*;

public class BilateralFilterButtonListener extends MaskFilterButtonListener {
    protected JTextField gammaRField;

    public BilateralFilterButtonListener(JTextField maskSideField, JTextField gammaRField) {
        super(maskSideField);
        this.gammaRField = gammaRField;
    }

    @Override
    public MaskFilter getFilter() {
        int maskSide = Integer.parseInt(maskSideField.getText());
        int gammaR = Integer.parseInt(gammaRField.getText());

        return new BilateralFilter(maskSide, gammaR);
    }

    @Override
    public String getName() {
        return "Bilateral Filter";
    }
}
