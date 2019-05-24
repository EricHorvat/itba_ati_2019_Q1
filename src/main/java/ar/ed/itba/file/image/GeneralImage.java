package ar.ed.itba.file.image;

import ar.ed.itba.file.pixel.Pixel;
import ar.ed.itba.file.pixel.RGBPixel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.*;

import static ar.ed.itba.utils.ImageUtils.*;

public class GeneralImage extends ATIImage {
  
  public GeneralImage(String filePath) throws IOException {
    super(filePath, ImageMode.COLOR);
  }
  
  public GeneralImage(byte[] image, int width, int height) {
    super(byte2Buffered(image, width, height, BufferedImage.TYPE_3BYTE_BGR), ImageMode.COLOR);
  }
  
  @Override
  protected BufferedImage open(String filePath) throws IOException {
    BufferedImage bufferedImage = ImageIO.read(new File(filePath));
    return new GeneralImage(swapRB(((DataBufferByte) bufferedImage.getRaster().getDataBuffer()).getData()), bufferedImage.getWidth(),
            bufferedImage.getHeight()).getBufferedImage();
  }

  private byte[] swapRB(final byte[] image) {
    //swap red coordinate with blue
    final byte[] aux = new byte[image.length];
    for (int i = 0, j = 2 ; j < image.length ; i += 3, j = i + 2) {
      byte aux2 = image[i];
      aux[i] = image[j];
      aux[i+1] = image[i+1];
      aux[j] = aux2;
    }
    return aux;
  }

  @Override
  public BufferedImage view() {
    return new GeneralImage(swapRB(getImage()), getWidth(), getHeight()).getBufferedImage();
  }
  
  @Override
  public Pixel getPixel(int i, int j) {
    int indexRGB = indexRGB(i,j,getWidth());
    return new RGBPixel(getImage()[red(indexRGB)], getImage()[green(indexRGB)],
      getImage()[blue(indexRGB)]);
  }
  
  
  @Override
  public void setPixel(final int i, final int j, final Pixel pixel) {
    int indexRGB = indexRGB(i,j,getWidth());
    getImage()[red(indexRGB)] = ((RGBPixel) pixel).getRed();
    getImage()[green(indexRGB)] = ((RGBPixel) pixel).getGreen();
    getImage()[blue(indexRGB)] = ((RGBPixel) pixel).getBlue();
  }
  
  @Override
  public String getPixelInfo(int i, int j) {
    final RGBPixel rgb = (RGBPixel) getPixel(j, i);
    return "Gray level: R" + (rgb.getRed() & 0xFF) + " G" + (rgb.getGreen() & 0xFF) + " B" + (rgb.getBlue() & 0xFF);
  }
  
  
  @Override
  public String getRegionInfo(int oi, int oj, int w, int h) {
    Color c = averageColor(oi, oj, w, h);
    return "Region average : R" + c.getRed() + " G" + c.getGreen() + " B" + c.getBlue();
  }
  
  @Override
  public void save(String fileName) throws Exception {
  }
  
  @Override
  public int[] toRGB() {
    final byte[] image = getImage();
    final int[] aux = new int[image.length];
    for (int i = 0 ; i < image.length ; i++)
      aux[i] = image[i] & 0xFF;
    return aux;
  }
  private static final int MAX_COLOR = 255;
  
  @Override
  public void negative() {
    for (int i = 0; i < getHeight(); i++) {
      for (int j = 0; j < getWidth(); j++) {
        final RGBPixel pixel = (RGBPixel) getPixel(i, j);
        setPixel(i, j, new RGBPixel((byte) (-(pixel.getRed() & 0xFF) + MAX_COLOR),
          (byte) (-(pixel.getGreen() & 0xFF) + MAX_COLOR), (byte) (-(pixel.getBlue() & 0xFF) + MAX_COLOR)));
      }
    }
  }
  
  @Override
  public ATIImage deepCopy() {
    return new GeneralImage(getImage().clone(), getWidth(), getHeight());
  }
}
