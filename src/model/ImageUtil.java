package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import javax.imageio.ImageIO;


/**
 * This class contains utility methods to load a PPM image from file and save a PPM image to a
 * file.
 */
public class ImageUtil {

  /**
   * Loads an image from this devices storage, if the format is supported, and creates an ImageImpl
   * object to represent it.
   *
   * @param filename The name of the image to be loaded from the device.
   * @param name     The name the ImageImpl created from the loaded image.
   * @return The ImageImpl object that represents the loaded image.
   */
  public static ImageImpl loadNotPPM(String filename, String name) {
    BufferedImage loadedImage;
    try {
      loadedImage = ImageIO.read(new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not read file " + filename + ".");
    }
    ImageImpl image = new ImageImpl(loadedImage.getHeight(), loadedImage.getWidth(), name);
    for (int h = 0; h < loadedImage.getHeight(); h++) {
      for (int w = 0; w < loadedImage.getWidth(); w++) {
        int currPixel = loadedImage.getRGB(w, h);
        Color currColor = new Color(currPixel, true);
        int r = currColor.getRed();
        int g = currColor.getGreen();
        int b = currColor.getBlue();
        image.image[h][w] = new PixelImpl(r, g, b);
      }
    }
    return image;
  }

  /**
   * Saves an ImageImpl object as a support file type to a given destination on this device's
   * storage.
   *
   * @param filename The name of the file to be created.
   * @param image    The name of the image to save.
   * @param fileType The type of file that will be created.
   */
  public static void saveNotPPM(String filename, Image image, String fileType) {
    BufferedImage toSave = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    for (int h = 0; h < image.getHeight(); h++) {
      for (int w = 0; w < image.getWidth(); w++) {
        Pixel currPixelImage = image.getPixelAt(h, w);
        Color imageColor = new Color(currPixelImage.getRedValue(), currPixelImage.getGreenValue(),
            currPixelImage.getBlueValue());
        toSave.setRGB(w, h, imageColor.getRGB());
      }
    }
    try {
      ImageIO.write(toSave, fileType, new File(filename));
    } catch (IOException e) {
      throw new IllegalArgumentException("Could not save file " + filename + ".");
    }
  }

  /**
   * Converts an Image object to a BufferedImage object.
   *
   * @param image The image to convert.
   * @return A BufferedImage from the rgb values of the given Image.
   */
  public static BufferedImage convertToBufferedImage(Image image) {
    BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),
        BufferedImage.TYPE_INT_RGB);
    for (int h = 0; h < image.getHeight(); h++) {
      for (int w = 0; w < image.getWidth(); w++) {
        Pixel currPixelImage = image.getPixelAt(h, w);
        Color imageColor = new Color(currPixelImage.getRedValue(), currPixelImage.getGreenValue(),
            currPixelImage.getBlueValue());
        newImage.setRGB(w, h, imageColor.getRGB());
      }
    }
    return newImage;
  }

  /**
   * Loads a PPM file from device storage, given a destination path as a string and represents it as
   * a ImageImpl.
   *
   * @param filename The file destination path relative to this folder.
   * @param name     The name of the ImageImpl
   * @return The loaded PPM file as a ImageImpl.
   */
  public static ImageImpl loadPPM(String filename, String name) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }

    StringBuilder builder = fileWithoutComments(sc);

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    sc.nextInt();

    ImageImpl image = new ImageImpl(height, width, name);
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        image.image[i][j] = new PixelImpl(r, g, b);
      }
    }
    return image;
  }

  /**
   * Saves a ImageImpl as a PPM file to the given destination on this device's storage.
   *
   * @param filename The name of the PPM file created.
   * @param image    The name of the ImageImpl to be saved.
   */
  public static void savePPM(String filename, ImageImpl image) {
    PrintWriter out;
    try {
      out = new PrintWriter(new FileOutputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    String token = "P3 \n";
    String heightAndWidth = "" + image.getWidth() + " " + image.getHeight() + "\n";
    String maxValue = 255 + "\n";
    StringBuilder restOfFile = new StringBuilder();
    restOfFile.append(token);
    restOfFile.append(heightAndWidth);
    restOfFile.append(maxValue);
    for (int h = 0; h < image.getHeight(); h++) {
      for (int w = 0; w < image.getWidth(); w++) {
        int r = image.getPixelAt(h, w).getRedValue();
        int g = image.getPixelAt(h, w).getGreenValue();
        int b = image.getPixelAt(h, w).getBlueValue();
        restOfFile.append(r).append("\n").append(g).append("\n").append(b).append("\n");
      }
    }
    out.append(restOfFile.toString());
    out.close();
  }

  /**
   * Creates a StringBuilder from a PPM file without the comments.
   *
   * @param sc The file
   * @return The StringBuilder of with the relevant contents from the PPM file.
   */
  public static StringBuilder fileWithoutComments(Scanner sc) {
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    return builder;
  }

  /**
   * Clamps the value of a given (int) value within the bounds of [0, 255].
   *
   * @param value The value to be clamped.
   * @return The clamped value.
   */
  public static int clamp(int value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }

  /**
   * Clamps the value of a given (double) value within the bounds of [0, 255].
   *
   * @param value The value to be clamped.
   * @return The clamped value.
   */
  public static double clamp(double value) {
    if (value < 0) {
      value = 0;
    } else if (value > 255) {
      value = 255;
    }
    return value;
  }

}

