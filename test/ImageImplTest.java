import static org.junit.Assert.assertEquals;

import model.ImageImpl;
import model.ImageProcessorModelImpl;
import model.ImageUtil;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for ImageImpl.
 */
public class ImageImplTest {

  ImageImpl pain;
  ImageProcessorModelImpl processor;

  @Before
  public void setUp() {
    pain = ImageUtil.loadPPM("res/pain.ppm", "pain");
    processor = new ImageProcessorModelImpl();
    processor.loadImage("res/pain.ppm", "pain");
  }

  @Test
  public void ImageImplConstructorTest() {
    assertEquals(new ImageImpl(1, 1, "dot").getHeight(), 1);
    assertEquals(new ImageImpl(1, 1, "dot").getWidth(), 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageImplConstructorBadNameTest() {
    new ImageImpl(1, 1, null);
  }

  @Test
  public void ImageImplGetHeightTest() {
    assertEquals(this.pain.getHeight(), 372);
  }

  @Test
  public void ImageImplGetWidthTest() {
    assertEquals(this.pain.getWidth(), 500);
  }

  @Test
  public void ImageImplGetPixelAtTest() {
    assertEquals(this.pain.getPixelAt(0, 0).getRedValue(), 48);
    assertEquals(this.pain.getPixelAt(0, 0).getGreenValue(), 23);
    assertEquals(this.pain.getPixelAt(0, 0).getBlueValue(), 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageImplGetPixelAtOutOfBoundsNegHTest() {
    this.pain.getPixelAt(-1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageImplGetPixelAtOutOfBoundsNegWTest() {
    this.pain.getPixelAt(0, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageImplGetPixelAtOutOfBoundsHighHTest() {
    this.pain.getPixelAt(372, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void ImageImplGetPixelAtOutOfBoundsHighWTest() {
    this.pain.getPixelAt(0, 500);
  }

  @Test
  public void ImageImplGetRedComponentTest() {
    ImageImpl redPain = this.pain.getRedComponent("redPain");
    assertEquals(redPain.getPixelAt(0, 0).getRedValue(), 48);
    assertEquals(redPain.getPixelAt(0, 0).getGreenValue(), 48);
    assertEquals(redPain.getPixelAt(0, 0).getBlueValue(), 48);
  }

  @Test
  public void ImageImplGetGreenComponentTest() {
    ImageImpl greenPain = this.pain.getGreenComponent("greenPain");
    assertEquals(greenPain.getPixelAt(0, 0).getRedValue(), 23);
    assertEquals(greenPain.getPixelAt(0, 0).getGreenValue(), 23);
    assertEquals(greenPain.getPixelAt(0, 0).getBlueValue(), 23);
  }

  @Test
  public void ImageImplGetBlueComponentTest() {
    ImageImpl bluePain = this.pain.getBlueComponent("bluePain");
    assertEquals(bluePain.getPixelAt(0, 0).getRedValue(), 3);
    assertEquals(bluePain.getPixelAt(0, 0).getGreenValue(), 3);
    assertEquals(bluePain.getPixelAt(0, 0).getBlueValue(), 3);
  }

  @Test
  public void ImageImplGetValueTest() {
    ImageImpl valueOfPain = this.pain.getValue("valueOfPain");
    assertEquals(valueOfPain.getPixelAt(0, 0).getRedValue(), 48);
    assertEquals(valueOfPain.getPixelAt(0, 0).getGreenValue(), 48);
    assertEquals(valueOfPain.getPixelAt(0, 0).getBlueValue(), 48);
  }

  @Test
  public void ImageImplGetIntensityTest() {
    ImageImpl intensePain = this.pain.getIntensity("intensePain");
    assertEquals(intensePain.getPixelAt(0, 0).getRedValue(), 25);
    assertEquals(intensePain.getPixelAt(0, 0).getGreenValue(), 25);
    assertEquals(intensePain.getPixelAt(0, 0).getBlueValue(), 25);
  }

  @Test
  public void ImageImplGetLumaTest() {
    ImageImpl painLuma = this.pain.getLuma("painLuma");
    assertEquals(painLuma.getPixelAt(0, 0).getRedValue(), 27);
    assertEquals(painLuma.getPixelAt(0, 0).getGreenValue(), 27);
    assertEquals(painLuma.getPixelAt(0, 0).getBlueValue(), 27);
  }

  @Test
  public void ImageImplFlipVertically() {
    ImageImpl flippedPain = this.pain.flipVertically("flippedPain");
    assertEquals(flippedPain.getPixelAt(0, 0).getRedValue(),
        this.pain.getPixelAt(this.pain.getHeight() - 1, 0).getRedValue());
    assertEquals(flippedPain.getPixelAt(0, 0).getGreenValue(),
        this.pain.getPixelAt(this.pain.getHeight() - 1, 0).getGreenValue());
    assertEquals(flippedPain.getPixelAt(0, 0).getBlueValue(),
        this.pain.getPixelAt(this.pain.getHeight() - 1, 0).getBlueValue());
  }

  @Test
  public void ImageImplFlipHorizontally() {
    ImageImpl mirroredPain = this.pain.flipHorizontally("mirroredPain");
    assertEquals(mirroredPain.getPixelAt(0, 0).getRedValue(),
        this.pain.getPixelAt(0, this.pain.getWidth() - 1).getRedValue());
    assertEquals(mirroredPain.getPixelAt(0, 0).getGreenValue(),
        this.pain.getPixelAt(0, this.pain.getWidth() - 1).getGreenValue());
    assertEquals(mirroredPain.getPixelAt(0, 0).getBlueValue(),
        this.pain.getPixelAt(0, this.pain.getWidth() - 1).getBlueValue());
  }

  @Test
  public void ImageImplBrighten() {
    ImageImpl cheerUp = this.pain.brighten(220, "cheerUp");
    assertEquals(cheerUp.getPixelAt(0, 0).getRedValue(),
        255);
    assertEquals(cheerUp.getPixelAt(0, 0).getGreenValue(),
        this.pain.getPixelAt(0, 0).getGreenValue() + 220);
    assertEquals(cheerUp.getPixelAt(0, 0).getBlueValue(),
        this.pain.getPixelAt(0, 0).getBlueValue() + 220);
  }

  @Test
  public void ImageImplDarken() {
    ImageImpl crestfallen = this.pain.darken(20, "crestfallen");
    assertEquals(crestfallen.getPixelAt(0, 0).getRedValue(),
        this.pain.getPixelAt(0, 0).getRedValue() - 20);
    assertEquals(crestfallen.getPixelAt(0, 0).getGreenValue(),
        this.pain.getPixelAt(0, 0).getGreenValue() - 20);
    assertEquals(crestfallen.getPixelAt(0, 0).getBlueValue(),
        0);
  }

  @Test
  public void ImageImplGetGreyscale() {
    ImageImpl painNoir = this.pain.getGreyscale("painGreyscale");
    assertEquals(painNoir.getPixelAt(0, 0).getRedValue(), 27);
    assertEquals(painNoir.getPixelAt(0, 0).getGreenValue(), 27);
    assertEquals(painNoir.getPixelAt(0, 0).getBlueValue(), 27);
  }

  @Test
  public void ImageImplGetSepia() {
    ImageImpl painVintage = this.pain.getSepia("painVintage");
    assertEquals(painVintage.getPixelAt(0, 0).getRedValue(), 37);
    assertEquals(painVintage.getPixelAt(0, 0).getGreenValue(), 33);
    assertEquals(painVintage.getPixelAt(0, 0).getBlueValue(), 26);
  }

  @Test
  public void ImageImplBlur() {
    ImageImpl blurredPain = this.pain.blur("blurredPain");
    assertEquals(blurredPain.getPixelAt(0, 0).getRedValue(), 27);
    assertEquals(blurredPain.getPixelAt(0, 0).getGreenValue(), 13);
    assertEquals(blurredPain.getPixelAt(0, 0).getBlueValue(), 1);
  }

  @Test
  public void ImageImplSharpen() {
    ImageImpl sharpPain = this.pain.sharpen("sharpPain");
    assertEquals(sharpPain.getPixelAt(0, 0).getRedValue(), 55);
    assertEquals(sharpPain.getPixelAt(0, 0).getGreenValue(), 27);
    assertEquals(sharpPain.getPixelAt(0, 0).getBlueValue(), 4);
  }

  @Test
  public void ImageImplMosaic() {
    ImageImpl mosaicPain = this.pain.mosaic(100,"mosaicPain");
    assertEquals(mosaicPain.getPixelAt(0, 0).getRedValue(), 43);
    assertEquals(mosaicPain.getPixelAt(0, 0).getGreenValue(), 115);
    assertEquals(mosaicPain.getPixelAt(0, 0).getBlueValue(), 4);
  }

}
