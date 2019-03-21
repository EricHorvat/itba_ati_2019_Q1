package ar.ed.itba.utils;

import ar.ed.itba.ui.components.DialogFactory;
import ar.ed.itba.ui.frames.EditableImageFrame;

public final class CheckUtils {
	
	private CheckUtils(){}
	
	public static boolean checkEditableImageVisible(){
		boolean isVisible = EditableImageFrame.instance().isVisible();
		if (!isVisible) {
			DialogFactory.promptError("Not active editable image");
		}
		return isVisible;
	}
	
	
}