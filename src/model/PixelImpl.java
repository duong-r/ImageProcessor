package model;

/**
 * Represents a pixel in a PPM image. Has red, green, and blue values represented by integers.
 */
public class PixelImpl implements Pixel {

  private final int red;
  private final int green;
  private final int blue;

  /**
   * Constructor for a pixel.
   *
   * @param red   Red value represented as an int.
   * @param green Green value represented as an int.
   * @param blue  Blue value represented as an int.
   * @throws IllegalArgumentException if any of the values are less than 0 or greater than 255.
   */
  public PixelImpl(int red, int green, int blue) {
    if (red > 255 || green > 255 || blue > 255) {
      throw new IllegalArgumentException("Max value is 255.");
    }
    if (red < 0 || green < 0 || blue < 0) {
      throw new IllegalArgumentException("Min value is 0.");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }


  @Override
  public int getRedValue() {
    return this.red;
  }

  @Override
  public int getGreenValue() {
    return this.green;
  }

  @Override
  public int getBlueValue() {
    return this.blue;
  }

  @Override
  public PixelImpl getRedComponent() {
    return new PixelImpl(this.red, this.red, this.red);
  }

  @Override
  public PixelImpl getGreenComponent() {
    return new PixelImpl(this.green, this.green, this.green);
  }

  @Override
  public PixelImpl getBlueComponent() {
    return new PixelImpl(this.blue, this.blue, this.blue);
  }

  @Override
  public PixelImpl brighten(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Can not brighten with a negative integer.");
    }
    int brightenedRed = this.red + value;
    int brightenedGreen = this.green + value;
    int brightenedBlue = this.blue + value;
    brightenedRed = ImageUtil.clamp(brightenedRed);
    brightenedGreen = ImageUtil.clamp(brightenedGreen);
    brightenedBlue = ImageUtil.clamp(brightenedBlue);
    return new PixelImpl(brightenedRed, brightenedGreen, brightenedBlue);
  }

  @Override
  public PixelImpl darken(int value) {
    if (value < 0) {
      throw new IllegalArgumentException("Can not darken with a negative integer.");
    }
    int darkenedRed = this.red - value;
    int darkenedGreen = this.green - value;
    int darkenedBlue = this.blue - value;
    darkenedRed = ImageUtil.clamp(darkenedRed);
    darkenedGreen = ImageUtil.clamp(darkenedGreen);
    darkenedBlue = ImageUtil.clamp(darkenedBlue);
    return new PixelImpl(darkenedRed, darkenedGreen, darkenedBlue);
  }

  @Override
  public int getValue() {
    return Math.max(Math.max(this.red, this.green), this.blue);
  }

  @Override
  public PixelImpl valuePixel() {
    return new PixelImpl(this.getValue(), this.getValue(), this.getValue());
  }

  @Override
  public int getIntensity() {
    double intensity = ((double) this.red + (double) this.green + (double) this.blue) / 3;
    return (int) Math.round(intensity);
  }

  @Override
  public PixelImpl intensityPixel() {
    return new PixelImpl(this.getIntensity(), this.getIntensity(), this.getIntensity());
  }

  @Override
  public int getLuma() {
    double luma = 0.2126 * this.red + 0.7152 * this.green + 0.0722 * this.blue;
    return (int) Math.round(luma);
  }

  @Override
  public PixelImpl lumaPixel() {
    return new PixelImpl(this.getLuma(), this.getLuma(), this.getLuma());
  }

  @Override
  public PixelImpl applyColorTransformation(double[][] colorTransformation) {
    double newRed = 0;
    double newGreen = 0;
    double newBlue = 0;
    double[] colors = {this.red, this.green, this.blue};
    for (int r = 0; r < colorTransformation[0].length; r++) {
      for (int c = 0; c < colorTransformation.length; c++) {
        if (r == 0) {
          newRed += colors[c] * colorTransformation[r][c];
        }
        if (r == 1) {
          newGreen += colors[c] * colorTransformation[r][c];
        }
        if (r == 2) {
          newBlue += colors[c] * colorTransformation[r][c];
        }
      }
    }
    int readyRed = (int) Math.round(ImageUtil.clamp(newRed));
    int readyGreen = (int) Math.round(ImageUtil.clamp(newGreen));
    int readyBlue = (int) Math.round(ImageUtil.clamp(newBlue));
    return new PixelImpl(readyRed, readyGreen, readyBlue);
  }
}
