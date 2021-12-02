import controller.ImageProcessorController;
import controller.ImageProcessorControllerImpl;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import model.ImageProcessorModel;
import model.ImageProcessorModelImpl;
import view.ImageProcessorGraphicalView;
import view.ImageProcessorView;

/**
 * The entry point for the Image Processor.
 */
public final class ImageProcessor {

  /**
   * Creates a ImageProcessor controller, model, and view (if necessary) and runs the image
   * processor.
   *
   * @param args given command line arguments
   */
  public static void main(String[] args) {
    ImageProcessorModel model;
    ImageProcessorView view;
    ImageProcessorController controller;
    FileReader scriptFile;
    if (args.length > 0) {
      if (args[0].equals("-file")) {
        try {
          scriptFile = new FileReader(args[1]);
        } catch (FileNotFoundException e) {
          throw new IllegalArgumentException("Could not find file.");
        }
        model = new ImageProcessorModelImpl();
        controller = new ImageProcessorControllerImpl(model, scriptFile);
        controller.runImageProcessor();
      } else if (args[0].equals("-text")) {
        model = new ImageProcessorModelImpl();
        controller = new ImageProcessorControllerImpl(model, new InputStreamReader(System.in));
        controller.runImageProcessor();
      } else {
        throw new IllegalArgumentException("Invalid command line argument.");
      }
    } else {
      model = new ImageProcessorModelImpl();
      view = new ImageProcessorGraphicalView();
      controller = new ImageProcessorControllerImpl(model, view);
    }
  }
}
