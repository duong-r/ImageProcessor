package model;

import java.util.ArrayList;

/**
 * Represents operations offered by an image processor. One object of the model represents one
 * instance of the image processor.
 */
public interface ImageProcessorModel {

  /**
   * Load a given file from device storage and name it a given name. Also adds to loaded images.
   *
   * @param filename Path to file (relative to current folder).
   * @param name     The name of the image.
   */
  void loadImage(String filename, String name);

  /**
   * Save an image to the device storage, given a file of {@code filename} does not already exist.
   *
   * @param filename What the file will be named in device storage.
   * @param name     The name of the image to save.
   */
  void saveImage(String filename, String name);

  /**
   * Gets the current loaded image for this image processor.
   *
   * @return The loaded image for this image processor.
   */
  ArrayList<ImageImpl> getLoadedImages();

  /**
   * Gets an image given the name of desired image.
   *
   * @param name The name of the image to get.
   * @return The image.
   */
  ImageImpl getImage(String name);

  /**
   * Creates a new image representing the red component of the given image and adds it to the loaded
   * images of this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void getRedComponent(String name, String newImageName);

  /**
   * Creates a new image representing the green component of the given image and adds it to the
   * loaded images of this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void getGreenComponent(String name, String newImageName);

  /**
   * Creates a new image representing the blue component of the given image and adds it to the
   * loaded images of this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void getBlueComponent(String name, String newImageName);

  /**
   * Creates a new image representing the value of the given image and adds it to the loaded images
   * of this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void valueOf(String name, String newImageName);

  /**
   * Creates a new image representing the intensity of the given image and adds it to the loaded
   * images of this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void intensityOf(String name, String newImageName);

  /**
   * Creates a new image representing the luma of the given image and adds it to the loaded images
   * of this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void lumaOf(String name, String newImageName);

  void greyscaleOf(String name, String newImageName);

  void sepiaOf(String name, String newImageName);

  /**
   * Creates a new image of the given image flipped vertically and adds it to the loaded images of
   * this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void flipVertically(String name, String newImageName);

  /**
   * Creates a new image of the given image flipped horizontally and adds it to the loaded images of
   * this processor under the given new name.
   *
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void flipHorizontally(String name, String newImageName);

  /**
   * Creates a new image of the given image brighter by {@code value} values and adds it to the
   * loaded images of this processor under the given new name.
   *
   * @param value        How much brighter the new image is than the original image.
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void brighten(int value, String name, String newImageName);

  /**
   * Creates a new image of the given image darker by {@code value} values and adds it to the loaded
   * images of this processor under the given new name.
   *
   * @param value        How much darker the new image is than the original image.
   * @param name         name of the image to be modified.
   * @param newImageName name of the new image that is created.
   */
  void darken(int value, String name, String newImageName);

  /**
   * Creates a new image from the given image that is blurred.
   *
   * @param name         The name of the image to be modified.
   * @param newImageName The name of the new image that is created.
   */
  void blur(String name, String newImageName);

  /**
   * Creates a new image from the given image this is sharpened.
   *
   * @param name         The name of the image to be modified.
   * @param newImageName The name of the image that is created.
   */
  void sharpen(String name, String newImageName);

  /**
   * Creates a mosaic image created from an image with a given image name and number of unique seeds.
   *
   * @param numberOfSeeds The number of unique seeds that will form the image.
   * @param name          The name of the image to be modified.
   * @param newImageName  The name of the image that is created.
   */
  void mosaic(int numberOfSeeds, String name, String newImageName);

  Image getLastImage();
}
