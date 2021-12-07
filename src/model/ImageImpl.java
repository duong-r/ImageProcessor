package model;


import java.util.ArrayList;
import java.util.Random;

import model.Utilities.Posn;
import model.Utilities.Seed;
import model.Utilities.SeedImpl;

/**
 * Represents a ImageImpl. An image is an array of pixels with a name, height, and width.
 */
public class ImageImpl implements Image {

  private final String name;
  protected final Pixel[][] image;
  private final int height;
  private final int width;

  /**
   * Constructs a ImageImpl.
   *
   * @param height The number of pixels that make up the height (rows) of the image.
   * @param width  The number of pixels that make up the width (columns) of the image.
   * @param name   The name of the image, for identification.
   * @throws IllegalArgumentException if any of the parameters are null.
   */
  public ImageImpl(int height, int width, String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name cannot be null.");
    }
    this.name = name;
    this.height = height;
    this.width = width;
    this.image = new Pixel[height][width];
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public Pixel[][] getImage() {
    return this.image;
  }

  @Override
  public Pixel getPixelAt(int row, int col) {
    if (row >= this.height || row < 0) {
      throw new IllegalArgumentException("Invalid row.");
    }
    if (col >= this.width || col < 0) {
      throw new IllegalArgumentException("Invalid column.");
    }
    return this.image[row][col];
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public ImageImpl getRedComponent(String newImageName) {
    ImageImpl redImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        redImage.image[h][w] = this.image[h][w].getRedComponent();
      }
    }
    return redImage;
  }

  @Override
  public ImageImpl getGreenComponent(String newImageName) {
    ImageImpl greenImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        greenImage.image[h][w] = this.image[h][w].getGreenComponent();
      }
    }
    return greenImage;
  }

  @Override
  public ImageImpl getBlueComponent(String newImageName) {
    ImageImpl blueImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        blueImage.image[h][w] = this.image[h][w].getBlueComponent();
      }
    }
    return blueImage;
  }

  @Override
  public ImageImpl getValue(String newImageName) {
    ImageImpl valueImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        valueImage.image[h][w] = this.image[h][w].valuePixel();
      }
    }
    return valueImage;
  }

  @Override
  public ImageImpl getIntensity(String newImageName) {
    ImageImpl intensityImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        intensityImage.image[h][w] = this.image[h][w].intensityPixel();
      }
    }
    return intensityImage;
  }

  @Override
  public ImageImpl getLuma(String newImageName) {
    ImageImpl lumaImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        lumaImage.image[h][w] = this.image[h][w].lumaPixel();
      }
    }
    return lumaImage;
  }

  @Override
  public ImageImpl getGreyscale(String newImageName) {
    double[][] greyScaleTransformation =
        {{0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};
    ImageImpl greyscaleImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        greyscaleImage.image[h][w] = this.image[h][w]
            .applyColorTransformation(greyScaleTransformation);
      }
    }
    return greyscaleImage;
  }

  @Override
  public ImageImpl getSepia(String newImageName) {
    double[][] sepiaTransformation =
        {{0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};
    ImageImpl sepiaImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        sepiaImage.image[h][w] = this.image[h][w].applyColorTransformation(sepiaTransformation);
      }
    }
    return sepiaImage;
  }

  @Override
  public ImageImpl flipVertically(String newImageName) {
    ImageImpl flippedImage = new ImageImpl(this.height, this.width, newImageName);
    int h2 = 0;
    for (int h = this.height - 1; h >= 0; h--) {
      int w2 = 0;
      for (int w = 0; w < this.width; w++) {
        flippedImage.image[h2][w2] = this.image[h][w];
        w2++;
      }
      h2++;
    }
    return flippedImage;
  }

  @Override
  public ImageImpl flipHorizontally(String newImageName) {
    ImageImpl flippedImage = new ImageImpl(this.height, this.width, newImageName);
    int h2 = 0;
    for (int h = 0; h < this.height; h++) {
      int w2 = 0;
      for (int w = this.width - 1; w >= 0; w--) {
        flippedImage.image[h2][w2] = this.image[h][w];
        w2++;
      }
      h2++;
    }
    return flippedImage;
  }

  @Override
  public ImageImpl brighten(int value, String newImageName) {
    ImageImpl brightenedImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        brightenedImage.image[h][w] = this.image[h][w].brighten(value);
      }
    }
    return brightenedImage;
  }

  @Override
  public ImageImpl darken(int value, String newImageName) {
    ImageImpl darkenedImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        darkenedImage.image[h][w] = this.image[h][w].darken(value);
      }
    }
    return darkenedImage;
  }

  @Override
  public ImageImpl blur(String newImageName) {
    double[][] blurFilter =
        {{0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};
    ImageImpl blurredImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        double[][] redChannel = this.getRedChannel(this, h, w, 3, 3);
        double[][] greenChannel = this.getGreenChannel(this, h, w, 3, 3);
        double[][] blueChannel = this.getBlueChannel(this, h, w, 3, 3);
        int blurredRed = (int) Math.round(this.applyFilter(redChannel, blurFilter));
        int blurredGreen = (int) Math.round(this.applyFilter(greenChannel, blurFilter));
        int blurredBlue = (int) Math.round(this.applyFilter(blueChannel, blurFilter));
        blurredImage.image[h][w] = new PixelImpl(blurredRed, blurredGreen, blurredBlue);
      }
    }
    return blurredImage;
  }

  @Override
  public ImageImpl sharpen(String newImageName) {
    double[][] sharpenFilter =
        {{-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};
    ImageImpl sharpenedImage = new ImageImpl(this.height, this.width, newImageName);
    for (int h = 0; h < this.height; h++) {
      for (int w = 0; w < this.width; w++) {
        double[][] redChannel = this.getRedChannel(this, h, w, 5, 5);
        double[][] greenChannel = this.getGreenChannel(this, h, w, 5, 5);
        double[][] blueChannel = this.getBlueChannel(this, h, w, 5, 5);
        int sharpRed = ImageUtil
            .clamp((int) Math.round(this.applyFilter(redChannel, sharpenFilter)));
        int sharpGreen = ImageUtil
            .clamp((int) Math.round(this.applyFilter(greenChannel, sharpenFilter)));
        int sharpBlue = ImageUtil
            .clamp((int) Math.round(this.applyFilter(blueChannel, sharpenFilter)));
        sharpenedImage.image[h][w] = new PixelImpl(sharpRed, sharpGreen, sharpBlue);
      }
    }
    return sharpenedImage;
  }

  @Override
  public ImageImpl mosaic(int numberOfSeeds, String newImageName) {
    ImageImpl mosaicImage = new ImageImpl(this.height, this.width, newImageName);
    ArrayList<SeedImpl> seeds = this.getSeeds(numberOfSeeds);

    //iterate through every pixel
    for(int row = 0; row < height; row ++) {
      for(int col = 0; col < width; col++) {
        int index = 0;
        Double euclideanDist = Double.MAX_VALUE;

        for(int i = 0; i < seeds.size(); i++) {
          Double euclidean = seeds.get(i).findEuclidean(row, col);
          if(euclidean < euclideanDist) {
            index = i;
            euclideanDist = euclidean;
          }
        }
      // Associate the pixel at row col to the seed at point SeedRow and SeedCol;
        seeds.get(index).addPosn(row, col); //helper method written later
      }
    }
    for (SeedImpl seed : seeds) {
      for (Posn p : seed.getCluster()) {
        Pixel seedPixel = image[seed.getY()][seed.getX()];
        mosaicImage.image[p.getY()][p.getX()] = new PixelImpl(seedPixel.getRedValue(),
                seedPixel.getGreenValue(), seedPixel.getBlueValue());
      }
    }
    return mosaicImage;
  }


  /**
   * Applies a given filter to a given channel.
   *
   * @param channel The channel the filter is to be applied to.
   * @param filter  The filter to apply.
   * @return The result of applying the filter to the channel.
   */
  private double applyFilter(double[][] channel, double[][] filter) {
    if (channel.length != filter.length || channel[0].length != filter[0].length) {
      throw new IllegalArgumentException("Channel and Filter must be the same size (dimensions).");
    }
    int numRows = channel[0].length;
    int numCols = channel.length;
    double sum = 0;
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        sum += channel[r][c] * filter[r][c];
      }
    }
    return sum;
  }

  /**
   * Gets red channel for the pixel at (pRow, pCol) for the given image, in the given dimensions.
   *
   * @param image The image the pixel is a part of.
   * @param pRow  The row of the pixel.
   * @param pCol  The column of the pixel.
   * @param h     The height (number of rows) the channel should be.
   * @param w     The width (number of columns) the channel should be.
   * @return The red channel for a particular pixel.
   */
  private double[][] getRedChannel(Image image, int pRow, int pCol, int h, int w) {
    double[][] redChannel = new double[h][w];
    for (int r = 0; r < h; r++) {
      for (int c = 0; c < w; c++) {
        try {
          redChannel[r][c] = image.getPixelAt(pRow - ((h - 1) / 2) + r,
              pCol - ((w - 1) / 2) + c).getRedValue();
        } catch (IllegalArgumentException oob) {
          redChannel[r][c] = 0;
        }
      }
    }
    return redChannel;
  }

  /**
   * Gets green channel for the pixel at (pRow, pCol) for the given image, in the given dimensions.
   *
   * @param image The image the pixel is a part of.
   * @param pRow  The row of the pixel.
   * @param pCol  The column of the pixel.
   * @param h     The height (number of rows) the channel should be.
   * @param w     The width (number of columns) the channel should be.
   * @return The red channel for a particular pixel.
   */
  private double[][] getGreenChannel(Image image, int pRow, int pCol, int h, int w) {
    double[][] greenChannel = new double[h][w];
    for (int r = 0; r < h; r++) {
      for (int c = 0; c < w; c++) {
        try {
          greenChannel[r][c] = image.getPixelAt(pRow - ((h - 1) / 2) + r,
              pCol - ((w - 1) / 2) + c).getGreenValue();
        } catch (IllegalArgumentException oob) {
          greenChannel[r][c] = 0;
        }
      }
    }
    return greenChannel;
  }

  /**
   * Gets blue channel for the pixel at (pRow, pCol) for the given image, in the given dimensions.
   *
   * @param image The image the pixel is a part of.
   * @param pRow  The row of the pixel.
   * @param pCol  The column of the pixel.
   * @param h     The height (number of rows) the channel should be.
   * @param w     The width (number of columns) the channel should be.
   * @return The red channel for a particular pixel.
   */
  private double[][] getBlueChannel(Image image, int pRow, int pCol, int h, int w) {
    double[][] blueChannel = new double[h][w];
    for (int r = 0; r < h; r++) {
      for (int c = 0; c < w; c++) {
        try {
          blueChannel[r][c] = image.getPixelAt(pRow - ((h - 1) / 2) + r,
              pCol - ((w - 1) / 2) + c).getBlueValue();
        } catch (IllegalArgumentException oob) {
          blueChannel[r][c] = 0;
        }
      }
    }
    return blueChannel;
  }

  public ArrayList<SeedImpl> getSeeds(int numberOfSeeds) {
    if (numberOfSeeds > width * height || numberOfSeeds < 0) {
      throw new IllegalArgumentException("The given number of seeds is invalid");
    }

    Random rand = new Random();
    ArrayList<SeedImpl> seeds = new ArrayList<>();

    for (int i = 0; i < numberOfSeeds; i++) {
      SeedImpl seed = new SeedImpl(rand.nextInt(height), rand.nextInt(width));

      while(seeds.contains(seed)) {
       seed = new SeedImpl(rand.nextInt(height), rand.nextInt(width));
      }
      seeds.add(new SeedImpl(rand.nextInt(height), rand.nextInt(width)));

    }
    return seeds;
  }
}

