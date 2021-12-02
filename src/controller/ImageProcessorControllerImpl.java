package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Image;
import model.ImageProcessorModel;
import model.ImageUtil;
import model.Histogram;
import view.ImageProcessorView;

/**
 * Represents the controller for an image processor. Transmits commands and arguments to the model.
 */
public class ImageProcessorControllerImpl implements ImageProcessorController, ActionListener {

  private final ImageProcessorModel model;
  private final Readable input;
  private final ImageProcessorView view;
  private boolean processorQuit = false;

  /**
   * Constructor for the controller of the image processor without GUI.
   *
   * @param model The model of the image processor.
   * @param input The input for the image processor.
   * @throws IllegalArgumentException if the model or input are null.
   */
  public ImageProcessorControllerImpl(ImageProcessorModel model, Readable input) {
    if (model == null || input == null) {
      throw new IllegalArgumentException("Arguments can not be null.");
    }
    this.model = model;
    this.input = input;
    this.view = null;
  }

  /**
   * Constructor for the controller of the image processor with GUI.
   *
   * @param model The model of the image processor.
   * @param view  The view for the image processor.
   */
  public ImageProcessorControllerImpl(ImageProcessorModel model, ImageProcessorView view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Arguments can not be null.");
    }
    this.model = model;
    this.input = null;
    this.view = view;
    view.setListener(this);
    view.display();
  }

  @Override
  public void runImageProcessor() {
    Scanner in = new Scanner(this.input);
    while (!this.processorQuit && in.hasNext()) {
      switch (in.next()) {
        case "load":
          this.model.loadImage(in.next(), in.next());
          break;
        case "save":
          this.model.saveImage(in.next(), in.next());
          break;
        case "red-component":
          this.model.getRedComponent(in.next(), in.next());
          break;
        case "green-component":
          this.model.getGreenComponent(in.next(), in.next());
          break;
        case "blue-component":
          this.model.getBlueComponent(in.next(), in.next());
          break;
        case "horizontal-flip":
          this.model.flipHorizontally(in.next(), in.next());
          break;
        case "vertical-flip":
          this.model.flipVertically(in.next(), in.next());
          break;
        case "brighten":
          this.model.brighten(Integer.parseInt(in.next()), in.next(), in.next());
          break;
        case "darken":
          this.model.darken(Integer.parseInt(in.next()), in.next(), in.next());
          break;
        case "value":
          this.model.valueOf(in.next(), in.next());
          break;
        case "luma":
          this.model.lumaOf(in.next(), in.next());
          break;
        case "greyscale":
          this.model.greyscaleOf(in.next(), in.next());
          break;
        case "sepia":
          this.model.sepiaOf(in.next(), in.next());
          break;
        case "intensity":
          this.model.intensityOf(in.next(), in.next());
          break;
        case "blur":
          this.model.blur(in.next(), in.next());
          break;
        case "sharpen":
          this.model.sharpen(in.next(), in.next());
          break;
        case "q":
        case "Q":
          this.processorQuit = true;
          in.close();
          break;
        //Case: "h" "H":
        //Manual?
        default:
          throw new IllegalArgumentException("Invalid command: " + in.next());
      }
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    Image currImage;
    StringBuilder imageName;
    BufferedImage image;
    Histogram histogram;
    switch (e.getActionCommand()) {
      case "Load image": {
        final JFileChooser imageChooser = new JFileChooser(".");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PNG, BMP, and PPM Images", "jpg", "png", "bmp", "ppm");
        imageChooser.setFileFilter(filter);
        int returnValue = imageChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
          File f = imageChooser.getSelectedFile();
          String imagePath = f.getAbsolutePath();
          imageName = new StringBuilder(f.getName());
          this.model.loadImage(imagePath, imageName.toString());
          currImage = this.model.getImage(imageName.toString());
          image = ImageUtil.convertToBufferedImage(currImage);
          this.view.setImage(image);
          histogram = new Histogram(currImage);
          this.view.setHistogram(histogram.drawHistogram());
        }
      }
      break;
      case "Red-Component":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-red-component");
        this.model.getRedComponent(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Green-Component":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-green-component");
        this.model.getGreenComponent(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Blue-Component":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-blue-component");
        this.model.getBlueComponent(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Flip Horizontally":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-horizontal-flip");
        this.model.flipHorizontally(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Flip Vertically":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-vertical-flip");
        this.model.flipVertically(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Value":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-value");
        this.model.valueOf(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Luma":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-luma");
        this.model.lumaOf(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Intensity":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-intensity");
        this.model.intensityOf(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Greyscale":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-greyscale");
        this.model.greyscaleOf(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Sepia":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-sepia");
        this.model.sepiaOf(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Blur":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-blurred");
        this.model.blur(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Sharpen":
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-sharpened");
        this.model.sharpen(currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Brighten": {
        String value = JOptionPane.showInputDialog("Enter a positive value.");
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-brightened-by-" + value);
        this.model.brighten(Integer.parseInt(value), currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
      }
      break;
      case "Darken":
        String value = JOptionPane.showInputDialog("Enter a positive value.");
        currImage = this.model.getLastImage();
        imageName = new StringBuilder(currImage.getName() + "-darkened-by-" + value);
        this.model.darken(Integer.parseInt(value), currImage.getName(), imageName.toString());
        image = ImageUtil.convertToBufferedImage(this.model.getImage(imageName.toString()));
        this.view.setImage(image);
        histogram = new Histogram(this.model.getImage(imageName.toString()));
        this.view.setHistogram(histogram.drawHistogram());
        break;
      case "Save":
        currImage = this.model.getLastImage();
        String saveName = JOptionPane.showInputDialog("Save as (include extension)");
        this.model.saveImage(saveName, currImage.getName());
        break;
      default:
        throw new IllegalArgumentException("Action not recognized.");
    }
  }
}
