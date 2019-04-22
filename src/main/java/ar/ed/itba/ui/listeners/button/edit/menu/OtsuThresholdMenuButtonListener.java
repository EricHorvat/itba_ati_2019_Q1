package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class OtsuThresholdMenuButtonListener extends ATIMenuOptionsButtonListener {
    public OtsuThresholdMenuButtonListener() {
        options.add(MenuOptionButtonFactory.otsuThresholdMenuOptionButton());
    }
}
