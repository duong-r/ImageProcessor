package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a class that draws a graph of the RGB and intensity values' frequencies for each value
 * from 0 to 255. The lines representing the frequencies are colored to match the value they
 * represent, leaving black for intensity. All the lines are overlapping on a single graph and the
 * highest values are clamped to 255.
 */
public class Histogram {

  private final Map<Integer, Integer> redMap;
  private final Map<Integer, Integer> greenMap;
  private final Map<Integer, Integer> blueMap;
  private final Map<Integer, Integer> intensityMap;

  /**
   * Creates a DrawHistogram that takes in an ImageModel that cannot be null. From the ImageModel,
   * we get the RGB and intensity frequencies from 0-255.
   *
   * @param delegate an ImageModel of the image being worked on
   */
  public Histogram(Image delegate) {
    Objects.requireNonNull(delegate);
    redMap = new ImageFrequenciesImp(delegate).getRedFrequency();
    greenMap = new ImageFrequenciesImp(delegate).getGreenFrequency();
    blueMap = new ImageFrequenciesImp(delegate).getBlueFrequency();
    intensityMap = new ImageFrequenciesImp(delegate).getIntensityFrequency();
  }

  /**
   * Creates a buffered image of the red, green, blue, and intensity frequencies visualized as lines
   * of color in a graph.
   *
   * @return the buffered image
   */
  public BufferedImage drawHistogram() {
    BufferedImage buff = new BufferedImage(256, 256, BufferedImage.TYPE_INT_RGB);

    Graphics2D g = buff.createGraphics();
    g.setBackground(Color.WHITE);
    g.fillRect(0, 0, 256, 256);
    BasicStroke b = new BasicStroke(2);
    g.setStroke(b);

    // red frequencies
    for (int x = 0; x < 255; x++) {
      int y1 = 256 - (int) Math.min(255, 0.01 * Math.pow(redMap.get(x), 2));
      int y2 = 256 - (int) Math.min(255, 0.01 * Math.pow(redMap.get(x + 1), 2));
      g.setColor(Color.red);
      g.drawLine(x, y1, x + 1, y2);
    }

    // green frequencies
    for (int x = 0; x < 255; x++) {
      int y1 = 256 - (int) Math.min(255, 0.01 * Math.pow(greenMap.get(x), 2));
      int y2 = 256 - (int) Math.min(255, 0.01 * Math.pow(greenMap.get(x + 1), 2));
      g.setColor(Color.green);
      g.drawLine(x, y1, x + 1, y2);
    }

    // blue frequencies
    for (int x = 0; x < 255; x++) {
      int y1 = 256 - (int) Math.min(255, 0.01 * Math.pow(blueMap.get(x), 2));
      int y2 = 256 - (int) Math.min(255, 0.01 * Math.pow(blueMap.get(x + 1), 2));
      g.setColor(Color.blue);
      g.drawLine(x, y1, x + 1, y2);
    }

    // intensity frequencies
    for (int x = 0; x < 255; x++) {
      int y1 = 256 - (int) Math.min(255, 0.01 * Math.pow(intensityMap.get(x), 2));
      int y2 = 256 - (int) Math.min(255, 0.01 * Math.pow(intensityMap.get(x + 1), 2));
      g.setColor(Color.BLACK);
      g.drawLine(x, y1, x + 1, y2);
    }

    return buff;
  }
}
