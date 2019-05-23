package ar.ed.itba.file;

import ar.ed.itba.file.image.*;
import ar.ed.itba.ui.components.DialogFactory;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

public /*abstract*/ class ImageOpener {
	
	public ATIImage open(String filePath){
		try {
			if(filePath.length() <= 4){
				DialogFactory.promptError("Incompatible file extension" + filePath);
				return null;
			}
			if(! new File(filePath).exists()){
				DialogFactory.promptError("File doesn't exist" + filePath);
				return null;
			}
			ATIImage image;
			switch (filePath.substring(filePath.length()-4).toLowerCase()){
				case ".raw":
					image = new RawImage(filePath);
					break;
				case ".pgm":
					image = new PgmImage(filePath);
					break;
				case ".pbm":
					image = new PbmImage(filePath);
					break;
				case ".ppm":
					image = new PpmImage(filePath);
					break;
        case "jpeg":
          if (filePath.charAt(filePath.length()-5) != '.')
            return null;
        case ".jpg":
        case ".png":
          image = new GeneralImage(filePath);
          break;
				default:
					DialogFactory.promptError("Incompatible file extension" + filePath);
					return null;
			}
			return image;
		} catch (IOException e){
			DialogFactory.promptError("Can't open file");
		}
		
		return null;
	}
}