package model;


import java.util.ArrayList;

/**
 * Represents an Image. One {@code Image} object represents one image. Images have names.
 */
public interface Image {

  /**
   * Gets the height of the image.
   *
   * @return The number of pixels that make up the height.
   */
  int getHeight();

  /**
   * Gets the width of the image.
   *
   * @return The number of pixels that make up the width.
   */
  int getWidth();

  /**
   * Gets the name of this image.
   *
   * @return The name of the image.
   */
  String getName();

  /**
   * Gets the pixel located in the given row and column.
   *
   * @param row The row the pixel is in.
   * @param col The column the pixel is in.
   * @return The desired Pixel.
   */
  Pixel getPixelAt(int row, int col);

  /**
   * Gets the 2d array of pixels that represents this image.
   *
   * @return The 2d array of pixels.
   */
  Pixel[][] getImage();

  /**
   * Creates a new image from this image that represents the red component of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image getRedComponent(String newImageName);

  /**
   * Creates a new image from this image that represents the green component of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image getGreenComponent(String newImageName);

  /**
   * Creates a new image from this image that represents the blue component of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image getBlueComponent(String newImageName);

  /**
   * Creates a new image from this image that represents the value of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image getValue(String newImageName);

  /**
   * Creates a new image from this image that represents the intensity of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image getIntensity(String newImageName);

  /**
   * Creates a new image from this image that represents the luma of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image getLuma(String newImageName);

  Image getGreyscale(String newImageName);

  Image getSepia(String newImageName);

  /**
   * Creates a new image from the vertical flip of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image flipVertically(String newImageName);

  /**
   * Creates a new image from the horizontal flip of this image.
   *
   * @param newImageName The name of the new image.
   * @return The new image.
   */
  Image flipHorizontally(String newImageName);

  /**
   * Brighten this image by a given increment.
   *
   * @param value        How much to brighten this image by.
   * @param newImageName The name of the new image.
   * @return A new image that is {@code value} brighter than this image.
   */
  Image brighten(int value, String newImageName);

  /**
   * Darken this image by a given increment.
   *
   * @param value        How much to darken this image by.
   * @param newImageName The name of the new image.
   * @return A new image that is {@code value} darker than this image.
   */
  Image darken(int value, String newImageName);

  /**
   * Blur this image.
   *
   * @param newImageName The name of the new image.
   * @return A new image that is blurred.
   */
  Image blur(String newImageName);

  /**
   * Sharpen this image.
   *
   * @param newImageName The name of the new image.
   * @return A new image that is blurred.
   */
  Image sharpen(String newImageName);

  ArrayList<SeedImpl> getSeeds(int numberOfSeeds);
}
