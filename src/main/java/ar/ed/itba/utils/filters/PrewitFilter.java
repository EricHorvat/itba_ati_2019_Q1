package ar.ed.itba.utils.filters;

import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.file.image.PpmImage;
import ar.ed.itba.utils.ImageUtils;

import static ar.ed.itba.utils.ImageUtils.*;

public class PrewitFilter extends MaskFilter {
	
	final private PrewitType type;
	
	public PrewitFilter(PrewitType type) {
		super(3);
		this.type = type;
	}
	
	@Override
	protected double[][] generateMask() {
		return new double[0][];
	}
	
	@Override
	public ATIImage applyFilter(ATIImage sourceAtiImage) {
		return new PpmImage(applyFilterRaw(sourceAtiImage),sourceAtiImage.getWidth(),sourceAtiImage.getHeight());
	}
	
	@Override
	public int[] applyFilterRaw(ATIImage sourceAtiImage) {
		MaskFilter xFilter = new PrewitPreFilter(PrewitPreFilter.Orientation.X);
		MaskFilter yFilter = new PrewitPreFilter(PrewitPreFilter.Orientation.Y);
		int[] xImage = xFilter.applyFilterRaw(sourceAtiImage);
		int[] yImage = yFilter.applyFilterRaw(sourceAtiImage);
		int[] finalImage;
		int width = sourceAtiImage.getWidth();
		int height = sourceAtiImage.getHeight();
		switch (type){
			case AVG:
				finalImage = avg(xImage,yImage, width, height);
				break;
			case MAX:
				finalImage = max(xImage,yImage, width, height);
				break;
			case MIN:
			default:
				finalImage = min(xImage,yImage, width, height);
				break;
		}
		return normalize(finalImage);
	}
	
	public enum PrewitType{
		MAX, MIN, AVG
	}
}
