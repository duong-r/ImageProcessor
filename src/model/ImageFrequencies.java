package model;

import java.util.Map;

/**
 * Represents the pixel component frequencies of an image.
 */
public interface ImageFrequencies {

  /**
   * Returns the frequencies of the red components of an image.
   *
   * @return the frequencies of the red components of an image
   */
  Map<Integer, Integer> getRedFrequency();

  /**
   * Returns the frequencies of the green components of an image.
   *
   * @return the frequencies of the green components of an image
   */
  Map<Integer, Integer> getGreenFrequency();

  /**
   * Returns the frequencies of the blue components of an image.
   *
   * @return the frequencies of the blue components of an image
   */
  Map<Integer, Integer> getBlueFrequency();

  /**
   * Returns the frequencies of the intensity components of an image.
   *
   * @return the frequencies of the intensity components of an image
   */
  Map<Integer, Integer> getIntensityFrequency();
}
