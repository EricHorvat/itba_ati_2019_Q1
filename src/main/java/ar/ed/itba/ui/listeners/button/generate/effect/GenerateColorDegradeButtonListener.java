package ar.ed.itba.ui.listeners.button.generate.effect;

import ar.ed.itba.file.image.PgmImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.file.pixel.RGBPixel;
import ar.ed.itba.ui.frames.EditableImageFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateColorDegradeButtonListener implements ActionListener {
	
	final JTextField color1Field;
	final JTextField color2Field;
	final JTextField widthField;
	final JTextField heightField;
	
	public GenerateColorDegradeButtonListener(JTextField color1Field, JTextField color2Field, JTextField widthField, JTextField heightField) {
		this.color1Field= color1Field;
		this.color2Field= color2Field;
		this.widthField= widthField;
		this.heightField= heightField;
	}
	
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		Color c1 = new Color(Integer.parseInt(color1Field.getText(),16));
		Color c2 = new Color(Integer.parseInt(color2Field.getText(),16));
		RGBPixel rgbPixel1 = new RGBPixel((byte) c1.getRed(),(byte) c1.getBlue(),(byte) c1.getGreen());
		RGBPixel rgbPixel2 = new RGBPixel((byte) c2.getRed(),(byte) c2.getBlue(),(byte) c2.getGreen());
		PpmImage image = PpmImage.createColorDowngrade(rgbPixel1,rgbPixel2,Integer.parseInt(widthField.getText()), Integer.parseInt(heightField.getText()));
		
		EditableImageFrame inputImageFrame = EditableImageFrame.instance();
		inputImageFrame.setAtiImage(image);
		inputImageFrame.buildAndShow();
	}
}
