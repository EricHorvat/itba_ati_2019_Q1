package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.FrameFactory;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.*;

public class ActiveContourVideoButtonListener implements ActionListener {
  
  private final JTextField framesField;
  
  public ActiveContourVideoButtonListener(JTextField framesField) {
    this.framesField = framesField;
  }
  
  public String getTitle() {
    return "Active Contour";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    
    ActiveContourFilter.getInstance();
    String fPath = EditableImageFrame.instance().getAtiImage().getFilePath();
    String ext = fPath.substring(fPath.length() - 4);
    fPath = fPath.substring(0,fPath.length() - 5);
    int lastFrame = Integer.parseInt(framesField.getText());
    List<Long> times = new ArrayList<>();
    
    for (int i = 1; i <= lastFrame; i++) {
      ATIImage image = new ImageOpener().open(fPath + i + ext);
      long millis = System.currentTimeMillis();
      ActiveContourFilter.getInstance().setImage(image);
      ActiveContourFilter.getInstance().apply();
      times.add(System.currentTimeMillis() - millis);
      ATIImage resultImage = ActiveContourFilter.getInstance().getImage();
  
      FrameFactory.fixedImageFrame(getTitle() + i, resultImage).buildAndShow();
    }
  
    times.forEach((time)-> System.out.println(time + " " +((time * 1000) < (1 / 30.0) )));
    
    
  }
}
