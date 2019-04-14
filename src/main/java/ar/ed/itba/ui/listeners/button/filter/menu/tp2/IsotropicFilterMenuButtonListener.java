package ar.ed.itba.ui.listeners.button.filter.menu.tp2;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.filter.menu.MaskFilterMenuButtonListener;

public class IsotropicFilterMenuButtonListener extends MaskFilterMenuButtonListener {
    //TODO PARAMS JTextField sigmaR = new JTextField();

    public IsotropicFilterMenuButtonListener() {
        //options.add(new JLabel("Sigma R"));
        //options.add(sigmaR);
        options.add(MenuOptionButtonFactory.isotropicFilterMenuOptionButton(maskSideField));//, sigmaR));
    }
}
