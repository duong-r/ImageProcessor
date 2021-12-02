import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import model.ImageProcessorModelImpl;
import model.ImageUtil;
import model.ImageImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ImageProcessorModelImpl.
 */
public class ImageProcessorModelImplTest {

  ImageImpl pain;
  ImageProcessorModelImpl processor;

  @Before
  public void setUp() {
    pain = ImageUtil.loadPPM("res/pain.ppm", "pain");
    processor = new ImageProcessorModelImpl();
    processor.loadImage("res/pain.ppm", "pain");
  }

  ImageProcessorModelImpl examplePPMImageProcessor = new ImageProcessorModelImpl();

  @Test
  public void testGetLoadedImages() {
    assertEquals(examplePPMImageProcessor.getLoadedImages(), new ArrayList<ImageImpl>());
  }

  @Test
  public void testLoadImage() {
    this.examplePPMImageProcessor.loadImage("res/pain.ppm", "pain");
    assertEquals(this.examplePPMImageProcessor.getLoadedImages().size(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadImageNoImageFound() {
    this.examplePPMImageProcessor.loadImage("res/koala.ppm", "koala");
  }

  @Test
  public void testGetImage() {
    this.examplePPMImageProcessor.loadImage("res/pain.ppm", "pain");
    ImageImpl pain = this.examplePPMImageProcessor.getImage("pain");
    assertEquals(pain.getHeight(), 372);
    assertEquals(pain.getWidth(), 500);
    assertEquals(pain.getPixelAt(0, 0).getRedValue(),
        48);
    assertEquals(pain.getPixelAt(0, 0).getGreenValue(),
        23);
    assertEquals(pain.getPixelAt(0, 0).getBlueValue(),
        3);
    assertEquals(pain.getPixelAt(371, 499).getRedValue(),
        102);
    assertEquals(pain.getPixelAt(371, 499).getGreenValue(),
        52);
    assertEquals(pain.getPixelAt(371, 499).getBlueValue(),
        19);

  }

  @Test
  public void testLumaOf() {
    this.examplePPMImageProcessor.loadImage("res/pain.ppm", "pain");
    this.examplePPMImageProcessor.lumaOf("pain", "luma-of-pain");
    assertEquals(this.examplePPMImageProcessor.getLoadedImages().size(), 2);
  }
}
