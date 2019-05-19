package ar.ed.itba.ui.listeners.button.filter.effect.tp3;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.ui.frames.EditableImageFrame;
import ar.ed.itba.ui.frames.VideoImageFrame;
import ar.ed.itba.utils.filters.advanced.ActiveContourFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.*;

public class ActiveContourVideoButtonListener implements ActionListener {
  
  private final JTextField framesField;
  
  public ActiveContourVideoButtonListener(JTextField framesField) {
    this.framesField = framesField;
  }
  
  public String getTitle() {
    return "Active Contour Video";
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    
    ActiveContourFilter.getInstance();
    String fPath = EditableImageFrame.instance().getAtiImage().getFilePath();
    String ext = fPath.substring(fPath.length() - 4);
    fPath = fPath.substring(0,fPath.length() - 5);
    int lastFrame = Integer.parseInt(framesField.getText());
    VideoImageFrame.instance().setTitle(getTitle());
    VideoImageFrame.instance().setAtiImage(EditableImageFrame.instance().getAtiImage());
    VideoImageFrame.instance().buildAndShow();
    
    runWorker(fPath, ext, lastFrame);
    
  }
  
  private void runWorker (String fPath, String ext, int lastFrame){
    List<Long> times = new ArrayList<>();
  
    SwingWorker<Void, ATIImage> worker = new SwingWorker<>() {
      // this is called in background thread
      @Override
      public Void doInBackground() throws Exception {
        for (int i = 1; i <= lastFrame; i++) {
          ATIImage image = new ImageOpener().open(fPath + i + ext);
          long millis = System.currentTimeMillis();
          ActiveContourFilter.getInstance().setImage(image);
          ActiveContourFilter.getInstance().apply();
          publishTime(System.currentTimeMillis() - millis);
          ATIImage resultImage = ActiveContourFilter.getInstance().getImage();
          publish(resultImage);
        }
        return null;
      }
    
      @Override
      protected void process(List<ATIImage> chunks) {
        for (ATIImage atiImage: chunks) {
          VideoImageFrame.instance().setAtiImage(atiImage);
        }
      }
    
    };
    worker.execute();
  }
  
  private void publishTime(double time){
    System.out.println(time + " " +((time * 1000) < (1 / 30.0) ));
  }
}
