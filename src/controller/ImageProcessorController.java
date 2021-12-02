package controller;

/**
 * Represents the operations offered by the controller for the Image Processor.
 */
public interface ImageProcessorController {

  /**
   * Runs the image processor.
   *
   * @throws IllegalArgumentException if given an invalid command.
   */
  void runImageProcessor();

}
