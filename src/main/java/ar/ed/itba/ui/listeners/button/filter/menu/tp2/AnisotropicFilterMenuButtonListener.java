package ar.ed.itba.ui.listeners.button.filter.menu.tp2;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

import javax.swing.*;

public class AnisotropicFilterMenuButtonListener extends MaskFilterMenuButtonListener {
    //TODO PARAMS JTextField sigmaR = new JTextField();

    public AnisotropicFilterMenuButtonListener() {
        //options.add(new JLabel("Sigma R"));
        //options.add(sigmaR);
        options.add(MenuOptionButtonFactory.anisotropicFilterMenuOptionButton(maskSideField));//, sigmaR));
    }
}
