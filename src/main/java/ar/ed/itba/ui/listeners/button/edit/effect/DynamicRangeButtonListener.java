package ar.ed.itba.ui.listeners.button.edit.effect;

import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.utils.ImageUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicRangeButtonListener implements ActionListener {
	public DynamicRangeButtonListener() {
	}
	
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		EditableImageFrame editableImageFrame = EditableImageFrame.instance();
		final int[] rgbImage = editableImageFrame.getAtiImage().toRGB();
		ImageUtils.dynamicRangeCompression(rgbImage);
		editableImageFrame.setAtiImage(new PpmImage(rgbImage, editableImageFrame.getAtiImage().getWidth(),
				editableImageFrame.getAtiImage().getHeight()));
		editableImageFrame.buildAndShow();
	}
}
