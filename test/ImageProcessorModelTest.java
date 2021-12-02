
import model.ImageImpl;
import model.ImageProcessorModelImpl;
import model.ImageUtil;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for ImageProcessorModel.
 */
public class ImageProcessorModelTest {

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
  public void testGetRedComponent() {
    processor.getRedComponent("pain", "pain2");
    assertEquals(66, processor.getImage(
        "pain2").getPixelAt(24, 124).getRedValue());
    assertEquals(66, processor.getImage(
        "pain2").getPixelAt(24, 124).getGreenValue());
    assertEquals(66, processor.getImage(
        "pain2").getPixelAt(24, 124).getBlueValue());
  }

  @Test
  public void testGetGreenComponent() {
    processor.getGreenComponent("pain", "pain2");
    assertEquals(52, processor.getImage(
        "pain2").getPixelAt(24, 124).getGreenValue());
    assertEquals(52, processor.getImage(
        "pain2").getPixelAt(24, 124).getRedValue());
    assertEquals(52, processor.getImage(
        "pain2").getPixelAt(24, 124).getBlueValue());
  }

  @Test
  public void testGetBlueComponent() {
    processor.getBlueComponent("pain", "pain2");
    assertEquals(43, processor.getImage(
        "pain2").getPixelAt(24, 124).getBlueValue());
    assertEquals(43, processor.getImage(
        "pain2").getPixelAt(24, 124).getRedValue());
    assertEquals(43, processor.getImage(
        "pain2").getPixelAt(24, 124).getGreenValue());
  }

  @Test
  public void testValueOf() {
    processor.valueOf("pain", "pain2");
    assertEquals(66, processor.getImage(
        "pain2").getPixelAt(24, 124).getRedValue());
    assertEquals(66, processor.getImage(
        "pain2").getPixelAt(24, 124).getGreenValue());
    assertEquals(66, processor.getImage(
        "pain2").getPixelAt(24, 124).getBlueValue());
  }

  @Test
  public void testIntensityOf() {
    processor.intensityOf("pain", "pain2");
    assertEquals(54, processor.getImage(
        "pain2").getPixelAt(24, 124).getRedValue());
    assertEquals(54, processor.getImage(
        "pain2").getPixelAt(24, 124).getGreenValue());
    assertEquals(54, processor.getImage(
        "pain2").getPixelAt(24, 124).getBlueValue());
  }
}