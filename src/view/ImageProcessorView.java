package view;

import java.awt.Image;
import java.awt.event.ActionListener;

/**
 * Represents the view for an Image Processor. One object represents one view.
 */
public interface ImageProcessorView {

  /**
   * Sets the listener of this view.
   *
   * @param listener The listener.
   */
  void setListener(ActionListener listener);

  /**
   * Sets the current image visible in the Image Processor.
   *
   * @param image The BufferedImage the user should currently see.
   */
  void setImage(Image image);

  /**
   * Sets the appropriate histogram for the current image.
   *
   * @param image The corresponding histogram the user should see along with the image.
   */
  void setHistogram(Image image);

  /**
   * Makes the view visible.
   */
  void display();

}
