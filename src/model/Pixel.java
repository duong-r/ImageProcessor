package model;

/**
 * Represent a pixel in an Image. The Pixel has a red, green, and blue value.
 */
public interface Pixel {

  /**
   * Gets the red value of the pixel.
   *
   * @return the red value of the pixel as an int.
   */
  int getRedValue();

  /**
   * Gets the green value of the pixel.
   *
   * @return The green value of the pixel as an int.
   */
  int getGreenValue();

  /**
   * Gets the blue value of the pixel.
   *
   * @return The blue value of the pixel as an int.
   */
  int getBlueValue();

  /**
   * Identifies the red component of this pixel and returns it.
   *
   * @return A new greyscale pixel that represents the red value of this pixel.
   */
  Pixel getRedComponent();

  /**
   * Identifies the green component of this pixel and returns it.
   *
   * @return A new greyscale pixel that represents the green value of this pixel.
   */
  Pixel getGreenComponent();

  /**
   * Identifies the green component of this pixel and returns it.
   *
   * @return A new greyscale pixel that represents the blue value of this pixel.
   */
  Pixel getBlueComponent();

  /**
   * Brightens the image by a given increment. If any of the resulted rgb values would be over 255,
   * set to 255.
   *
   * @param value How much to brighten the pixel by.
   * @return A new pixel than is {@code value} brighter than this pixel.
   */
  Pixel brighten(int value);

  /**
   * Darkens the image by a given increment. If any of the resulted rgb values would be under 0, set
   * to 0.
   *
   * @param value How much to darken the pixel by.
   * @return A new pixel than is {@code value} darker than this pixel.
   */
  Pixel darken(int value);

  /**
   * Get the value of this pixel. Value is the greatest rgb value of this pixel.
   *
   * @return The value as an int.
   */
  int getValue();

  /**
   * Identifies the value of this pixel and creates a greyscale representation of it.
   *
   * @return The value as a pixel.
   */
  Pixel valuePixel();

  /**
   * Get the rounded intensity of this pixel. Intensity is the average of the rgb values of this
   * pixel.
   *
   * @return The rounded intensity as an int.
   */
  int getIntensity();

  /**
   * Identifies the intensity of this pixel and creates a greyscale representation of it.
   *
   * @return The intensity as a pixel.
   */
  Pixel intensityPixel();

  /**
   * Get the rounded luma of this pixel. Luma is 0.2126r + 0.7152g + 0.0722b.
   *
   * @return The rounded luma as an int.
   */
  int getLuma();

  /**
   * Identifies the luma of this pixel and creates a greyscale representation of it.
   *
   * @return The luma as a pixel.
   */
  Pixel lumaPixel();

  /**
   * Applies a given color transformation to this pixel.
   *
   * @param colorTransformation The color transformation to be applied.
   * @return A new pixel with the color transformation applied.
   */
  Pixel applyColorTransformation(double[][] colorTransformation);

}
