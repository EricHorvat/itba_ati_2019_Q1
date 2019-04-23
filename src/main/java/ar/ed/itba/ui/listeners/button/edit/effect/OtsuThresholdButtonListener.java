package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.CheckUIUtils;
import ar.ed.itba.utils.threshold.OtsuThreshold;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OtsuThresholdButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (CheckUIUtils.checkEditableImageVisible()) {
            EditableImageFrame editableImageFrame = EditableImageFrame.instance();
            OtsuThreshold.threshold(editableImageFrame.getAtiImage());
            editableImageFrame.buildAndShow();
        }
    }
}
