import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.ImageImpl;
import model.ImageUtil;


/**
 * Tests for ImageUtil.
 */
public class ImageUtilTest {

  /**
   * Tests for loading and saving a file as a BMP or other, but the PPM.
   */
  @Test
  public void loadAndSaveNotPPM() {
    ImageImpl loaded = ImageUtil.loadNotPPM("res//pain.bmp", "pain");
    ImageUtil.saveNotPPM("res//pain2.bmp", loaded, "bmp");
    boolean check = true;
    for (int x = 0; x < loaded.getHeight(); x++) {
      for (int y = 0; y < loaded.getWidth(); y++) {
        check &= loaded.getPixelAt(x, y).getRedValue() == loaded.getPixelAt(x, y).getRedValue()
            && loaded.getPixelAt(x, y).getGreenValue() == loaded.getPixelAt(x, y).getGreenValue()
            && loaded.getPixelAt(x, y).getBlueValue() == loaded.getPixelAt(x, y).getBlueValue();
      }
    }
    assertTrue(check);
  }

  /**
   * Tests for loading and saving the image file as a PPM.
   */
  @Test
  public void loadAndSavePPM() {
    ImageImpl loaded = ImageUtil.loadPPM("res//pain.ppm", "pain");
    ImageUtil.savePPM("res//pain2.ppm", loaded);
    boolean check = true;
    for (int x = 0; x < loaded.getHeight(); x++) {
      for (int y = 0; y < loaded.getWidth(); y++) {
        check &= loaded.getPixelAt(x, y).getRedValue() == loaded.getPixelAt(x, y).getRedValue()
            && loaded.getPixelAt(x, y).getGreenValue() == loaded.getPixelAt(x, y).getGreenValue()
            && loaded.getPixelAt(x, y).getBlueValue() == loaded.getPixelAt(x, y).getBlueValue();
      }
    }
    assertTrue(check);
  }

  /**
   * Tests for the int clamp when given a negative number to make sure it returns as 0.
   */
  @Test
  public void clampNeg() {
    assertEquals(0, ImageUtil.clamp(-25));
  }

  /**
   * Tests the lower limit for the int clamp when given zero to make sure it returns as 0.
   */
  @Test
  public void clampZero() {
    assertEquals(0, ImageUtil.clamp(0));
  }

  /**
   * Tests for the int clamp when given a number between 0 and 255.
   */
  @Test
  public void clampNorm() {
    assertEquals(25, ImageUtil.clamp(25));
  }

  /**
   * Tests the upper limit for the int clamp when given 255 to make sure it returns as 255.
   */
  @Test
  public void clamp255() {
    assertEquals(255, ImageUtil.clamp(255));
  }

  /**
   * Tests for the int clamp when given a positive number above the upper limit to make sure it
   * returns as 255.
   */
  @Test
  public void clampTooHigh() {
    assertEquals(255, ImageUtil.clamp(256));
  }

  /**
   * Tests for the double clamp when given a negative number to make sure it returns as 0.0.
   */
  @Test
  public void testNegClamp() {
    assertEquals(0.0, ImageUtil.clamp(-25.0),
        0.00001);
  }

  /**
   * Tests the lower limit for the double clamp when given zero to make sure it returns as 0.0.
   */
  @Test
  public void testClampZero() {
    assertEquals(0.0, ImageUtil.clamp(0.0),
        0.00001);
  }

  /**
   * Tests for the double clamp when given a number between 0.0 and 255.0.
   */
  @Test
  public void testClampNorm() {
    assertEquals(25.0, ImageUtil.clamp(25.0),
        0.00001);
  }

  /**
   * Tests the upper limit for the double clamp when given 255.0 to make sure it returns as 255.0.
   */
  @Test
  public void testClamp255() {
    assertEquals(255.0, ImageUtil.clamp(255.0),
        0.00001);
  }

  /**
   * Tests for the double clamp when given a positive number above the upper limit to make sure it
   * returns as 255.0.
   */
  @Test
  public void testClampTooHigh() {
    assertEquals(255.0, ImageUtil.clamp(256.0),
        0.00001);
  }
}