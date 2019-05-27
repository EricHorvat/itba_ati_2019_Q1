package ar.ed.itba.ui.listeners.button.filter.menu.tp4;

import ar.ed.itba.file.ImageOpener;
import ar.ed.itba.file.image.ATIImage;
import ar.ed.itba.utils.detection.SIFT;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SIFTOpenerListener implements ActionListener {
  
  private final int index;
  private final JTextField filePathField;
  
  public SIFTOpenerListener(final int index, final JTextField filePathField) {
    this.index = index;
    this.filePathField = filePathField;
  }
  
  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    ImageOpener imageOpener = new ImageOpener();
    ATIImage atiImage = imageOpener.open(filePathField.getText());
    SIFT.getInstance().setImage(index, atiImage);
  }
}
