
import controller.ImageProcessorControllerImpl;
import java.awt.event.ActionEvent;
import model.ImageImpl;
import model.ImageProcessorModel;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;

import model.ImageUtil;
import model.ImageProcessorModelImpl;
import view.ImageProcessorGraphicalView;
import view.ImageProcessorView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for ImageProcessControllerImpl.
 */
public class ImageProcessorControllerImplTest {

  ImageImpl pain;
  ImageProcessorModelImpl processor;

  @Before
  public void setUp() {
    pain = ImageUtil.loadPPM("res//pain.ppm", "pain");
    processor = new ImageProcessorModelImpl();
    processor.loadImage("res//pain.ppm", "pain");
  }

  @Test
  public void testSaveImage() {
    processor.saveImage("res//painSave.ppm", "pain");
    ImageImpl pain2 = ImageUtil.loadPPM("res//painSave.ppm", "pain2");
    boolean check = true;
    for (int x = 0; x < pain.getHeight(); x++) {
      for (int y = 0; y < pain.getWidth(); y++) {
        check &= pain.getPixelAt(x, y).getRedValue() == pain2.getPixelAt(x, y).getRedValue()
            && pain.getPixelAt(x, y).getGreenValue() == pain2.getPixelAt(x, y).getGreenValue()
            && pain.getPixelAt(x, y).getBlueValue() == pain2.getPixelAt(x, y).getBlueValue();
      }
    }
    assertTrue(check);
  }

  @Test
  public void runImageProcessorRed() {
    Readable rd = new StringReader("load res//pain.ppm pain \n red-component pain painRed");
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    ImageProcessorControllerImpl c = new ImageProcessorControllerImpl(model, rd);
    c.runImageProcessor();
    assertEquals(66, model.getImage(
        "painRed").getPixelAt(24, 124).getRedValue());
    assertEquals(66, model.getImage(
        "painRed").getPixelAt(24, 124).getGreenValue());
    assertEquals(66, model.getImage(
        "painRed").getPixelAt(24, 124).getBlueValue());
  }

  @Test
  public void runImageProcessorGreen() {
    Readable rd = new StringReader("load res//pain.ppm pain \n "
        + "green-component pain painGreen");
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    ImageProcessorControllerImpl c = new ImageProcessorControllerImpl(model, rd);
    c.runImageProcessor();
    assertEquals(52, model.getImage(
        "painGreen").getPixelAt(24, 124).getGreenValue());
    assertEquals(52, model.getImage(
        "painGreen").getPixelAt(24, 124).getRedValue());
    assertEquals(52, model.getImage(
        "painGreen").getPixelAt(24, 124).getBlueValue());
  }

  @Test
  public void runImageProcessorBlue() {
    Readable rd = new StringReader("load res//pain.ppm pain \n "
        + "blue-component pain painBlue");
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    ImageProcessorControllerImpl c = new ImageProcessorControllerImpl(model, rd);
    c.runImageProcessor();
    assertEquals(43, model.getImage(
        "painBlue").getPixelAt(24, 124).getBlueValue());
    assertEquals(43, model.getImage(
        "painBlue").getPixelAt(24, 124).getRedValue());
    assertEquals(43, model.getImage(
        "painBlue").getPixelAt(24, 124).getGreenValue());
  }

  @Test
  public void runImageProcessorValue() {
    Readable rd = new StringReader("load res//pain.ppm pain \n "
        + "value pain painValue");
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    ImageProcessorControllerImpl c = new ImageProcessorControllerImpl(model, rd);
    c.runImageProcessor();
    assertEquals(66, model.getImage(
        "painValue").getPixelAt(24, 124).getRedValue());
    assertEquals(66, model.getImage(
        "painValue").getPixelAt(24, 124).getGreenValue());
    assertEquals(66, model.getImage(
        "painValue").getPixelAt(24, 124).getBlueValue());
  }

  @Test
  public void runImageProcessorIntensity() {
    Readable rd = new StringReader("load res//pain.ppm pain \n "
        + "intensity pain painIntense");
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    ImageProcessorControllerImpl c = new ImageProcessorControllerImpl(model, rd);
    c.runImageProcessor();
    assertEquals(54, model.getImage(
        "painIntense").getPixelAt(24, 124).getRedValue());
    assertEquals(54, model.getImage(
        "painIntense").getPixelAt(24, 124).getGreenValue());
    assertEquals(54, model.getImage(
        "painIntense").getPixelAt(24, 124).getBlueValue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void runImageProcessorInvalidScript() {
    Readable rd = new StringReader("bolster res//pain.ppm pain \n "
        + "intensity pain painIntense");
    ImageProcessorModelImpl model = new ImageProcessorModelImpl();
    ImageProcessorControllerImpl c = new ImageProcessorControllerImpl(model, rd);
    c.runImageProcessor();
  }

  @Test
  public void actionPerformedTest() {
    ImageProcessorModel model = new ImageProcessorModelImpl();
    ImageProcessorView view = new ImageProcessorGraphicalView();
    ImageProcessorControllerImpl controller = new ImageProcessorControllerImpl(model, view);
    model.loadImage("res/pain.ppm", "pain");

    controller.actionPerformed(new ActionEvent(view, 1, "Red-Component"));
    assertEquals(66, model.getImage(
        "pain-red-component").getPixelAt(24, 124).getRedValue());
    assertEquals(66, model.getImage(
        "pain-red-component").getPixelAt(24, 124).getGreenValue());
    assertEquals(66, model.getImage(
        "pain-red-component").getPixelAt(24, 124).getBlueValue());
  }
}