package ar.ed.itba.ui.listeners.button;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PbmImage;
import ar.ed.itba.file.pixel.GrayPixel;
import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.file.pixel.RGBPixel;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.interfaces.EditableImageInterface;
import ar.ed.itba.ui.listeners.mouse.EditableImageMouseAdapter;
import ar.ed.itba.utils.CheckUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static ar.ed.itba.file.image.ATIImage.ImageMode.GRAY;

public class EditPixelButtonListener implements ActionListener {
	
	final JTextField colorField;
	
	public EditPixelButtonListener(JTextField colorField) {
		this.colorField = colorField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if(CheckUtils.checkEditableImageVisible()){
			
			Pixel pixel;
			
			EditableImageFrame inputImageFrame = EditableImageFrame.instance();
			if(inputImageFrame.getAtiImage().getMode() == GRAY){
				int grayLevel = Integer.parseInt(colorField.getText(),16);
				pixel = new GrayPixel((byte) grayLevel);
			}else{
				Color c1 = new Color(Integer.parseInt(colorField.getText(),16));
				pixel = new RGBPixel((byte) c1.getRed(),(byte) c1.getBlue(),(byte) c1.getGreen());
			}
			
			
			EditableImageMouseAdapter mouseAdapter = ((EditableImageInterface)inputImageFrame.getInterface()).getEditableMouseAdapter();
			//for (int x = 0; x < 256; x++)
			//	inputImageFrame.getAtiImage().setPixel(x,mouseAdapter.getOriginY(),pixel);
			inputImageFrame.getAtiImage().setPixel(mouseAdapter.getOriginX(),mouseAdapter.getOriginY(),pixel);
			inputImageFrame.pack();
		}
	}
}
