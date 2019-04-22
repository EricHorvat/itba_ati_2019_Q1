package ar.ed.itba.ui.listeners.button.edit.menu;

import ar.ed.itba.ui.components.MenuOptionButtonFactory;
import ar.ed.itba.ui.listeners.button.ATIMenuOptionsButtonListener;

public class GlobalThresholdMenuButtonListener extends ATIMenuOptionsButtonListener {
    public GlobalThresholdMenuButtonListener() {
        options.add(MenuOptionButtonFactory.globalThresholdMenuOptionButton());
    }
}
