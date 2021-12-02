package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


/**
 * Represents the frequencies of a Pixel (in red, green, blue and intensity) occurring in an image.
 * It's methods are able to compute the frequencies by using the delegate to access the Pixel[][].
 */
public class ImageFrequenciesImp implements ImageFrequencies {

  private final Image delegate;

  /**
   * Constructs an ImageFrequenciesImp that allows for the use of a delegate.
   *
   * @param delegate a delegate of Image, allowing us to use its methods.
   */
  public ImageFrequenciesImp(Image delegate) {
    if (delegate == null) {
      throw new IllegalArgumentException("Null delegate.");
    }
    this.delegate = delegate;
  }

  // creates a map from 0 to 255 all with values of 0
  private Map<Integer, Integer> initialMap() {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < 256; i++) {
      map.put(i, 0);
    }
    return map;
  }

  // gets the frequencies of the values up to 255
  // the function gets the value of the pixel
  private Map<Integer, Integer> getFrequency(Function<Pixel, Integer> f) {
    Map<Integer, Integer> map = this.initialMap();
    for (Pixel[] pixels : delegate.getImage()) {
      for (Pixel p : pixels) {
        int value = f.apply(p);
        int frequency = map.get(value);
        map.replace(value, frequency + 1);
      }
    }
    return map;
  }

  @Override
  public Map<Integer, Integer> getRedFrequency() {
    return this.getFrequency((Pixel p) -> p.getRedValue());
  }

  @Override
  public Map<Integer, Integer> getGreenFrequency() {
    return this.getFrequency((Pixel p) -> p.getGreenValue());
  }

  @Override
  public Map<Integer, Integer> getBlueFrequency() {
    return this.getFrequency((Pixel p) -> p.getBlueValue());
  }

  @Override
  public Map<Integer, Integer> getIntensityFrequency() {
    return this.getFrequency((Pixel p) -> (int) p.getIntensity());
  }
}

