package ar.ed.itba.ui.listeners.button.filter.menu.tp2;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

import javax.swing.*;

public class BilateralFilterMenuButtonListener extends MaskFilterMenuButtonListener {
    JTextField sigmaR = new JTextField();

    public BilateralFilterMenuButtonListener () {
        options.add(new JLabel("Sigma R"));
        options.add(sigmaR);
        options.add(MenuOptionButtonFactory.bilateralFilterMenuOptionButton(maskSideField, sigmaR));
    }
}
