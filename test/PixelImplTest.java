import static org.junit.Assert.assertEquals;

import model.PixelImpl;
import org.junit.Test;

/**
 * Tests for PixelImpl.
 */
public class PixelImplTest {

  PixelImpl examplePixel = new PixelImpl(34, 120, 200);

  @Test
  public void pixelConstructorTest() {
    assertEquals(new PixelImpl(50, 50, 50).getRedValue(), 50);
    assertEquals(new PixelImpl(50, 0, 50).getGreenValue(), 0);
    assertEquals(new PixelImpl(50, 50, 50).getBlueValue(), 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelConstructorTestNegRedValue() {
    new PixelImpl(-50, 50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelConstructorTestNegGreenValue() {
    new PixelImpl(50, -50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelConstructorTestNegBlueValue() {
    new PixelImpl(50, 50, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelConstructorTestHighRedValue() {
    new PixelImpl(300, 50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelConstructorTestHighGreenValue() {
    new PixelImpl(50, 256, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelConstructorTestHighBlueValue() {
    new PixelImpl(50, 50, 260);
  }

  @Test
  public void pixelGetRedValueTest() {
    assertEquals(this.examplePixel.getRedValue(), 34);
  }

  @Test
  public void pixelGetGreenValueTest() {
    assertEquals(this.examplePixel.getGreenValue(), 120);
  }

  @Test
  public void pixelGetBlueValueTest() {
    assertEquals(this.examplePixel.getBlueValue(), 200);
  }

  @Test
  public void pixelGetRedComponentTest() {
    assertEquals(this.examplePixel.getRedComponent().getRedValue(), 34);
    assertEquals(this.examplePixel.getRedComponent().getGreenValue(), 34);
    assertEquals(this.examplePixel.getRedComponent().getGreenValue(), 34);
  }

  @Test
  public void pixelGetGreenComponentTest() {
    assertEquals(this.examplePixel.getGreenComponent().getRedValue(), 120);
    assertEquals(this.examplePixel.getGreenComponent().getGreenValue(), 120);
    assertEquals(this.examplePixel.getGreenComponent().getBlueValue(), 120);
  }

  @Test
  public void pixelGetBlueComponentTest() {
    assertEquals(this.examplePixel.getBlueComponent().getRedValue(), 200);
    assertEquals(this.examplePixel.getBlueComponent().getGreenValue(), 200);
    assertEquals(this.examplePixel.getBlueComponent().getBlueValue(), 200);
  }

  @Test
  public void pixelBrightenTest() {
    PixelImpl exampleBrightened = this.examplePixel.brighten(50);
    assertEquals(exampleBrightened.getRedValue(), 84);
    assertEquals(exampleBrightened.getGreenValue(), 170);
    assertEquals(exampleBrightened.getBlueValue(), 250);
  }

  @Test
  public void pixelBrightenOver255Test() {
    PixelImpl exampleBrightened = this.examplePixel.brighten(60);
    assertEquals(exampleBrightened.getRedValue(), 94);
    assertEquals(exampleBrightened.getGreenValue(), 180);
    assertEquals(exampleBrightened.getBlueValue(), 255);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelBrightenNegNumTest() {
    this.examplePixel.brighten(-60);
  }

  @Test
  public void pixelDarkenTest() {
    PixelImpl exampleDarkened = this.examplePixel.darken(30);
    assertEquals(exampleDarkened.getRedValue(), 4);
    assertEquals(exampleDarkened.getGreenValue(), 90);
    assertEquals(exampleDarkened.getBlueValue(), 170);
  }

  @Test
  public void pixelDarkenUnder0Test() {
    PixelImpl exampleDarkened = this.examplePixel.darken(60);
    assertEquals(exampleDarkened.getRedValue(), 0);
    assertEquals(exampleDarkened.getGreenValue(), 60);
    assertEquals(exampleDarkened.getBlueValue(), 140);
  }

  @Test(expected = IllegalArgumentException.class)
  public void pixelDarkenNegNumTest() {
    this.examplePixel.darken(-60);
  }

  @Test
  public void pixelGetValueTest() {
    assertEquals(this.examplePixel.getValue(), 200);
  }

  @Test
  public void pixelValuePixelTest() {
    assertEquals(this.examplePixel.valuePixel().getRedValue(), 200);
    assertEquals(this.examplePixel.valuePixel().getGreenValue(), 200);
    assertEquals(this.examplePixel.valuePixel().getBlueValue(), 200);
  }

  @Test
  public void pixelGetIntensityTest() {
    assertEquals(this.examplePixel.getIntensity(), 118);
  }

  @Test
  public void pixelIntensityPixelTest() {
    assertEquals(this.examplePixel.intensityPixel().getRedValue(), 118);
    assertEquals(this.examplePixel.intensityPixel().getGreenValue(), 118);
    assertEquals(this.examplePixel.intensityPixel().getBlueValue(), 118);
  }

  @Test
  public void pixelGetLuma() {
    assertEquals(this.examplePixel.getLuma(), 107);
  }

  @Test
  public void pixelLumaPixelTest() {
    assertEquals(this.examplePixel.lumaPixel().getRedValue(), 107);
    assertEquals(this.examplePixel.lumaPixel().getGreenValue(), 107);
    assertEquals(this.examplePixel.lumaPixel().getBlueValue(), 107);
  }

  @Test
  public void pixelApplyColorTransformationTest() {
    double[][] sampleColorTransformation =
        {{0.5, 0.5, 0.5},
            {0.5, 0.5, 0.5},
            {0.5, 0.5, 0.5}};
    assertEquals(
        this.examplePixel.applyColorTransformation(sampleColorTransformation).getRedValue(), 177);
    assertEquals(
        this.examplePixel.applyColorTransformation(sampleColorTransformation).getGreenValue(), 177);
    assertEquals(
        this.examplePixel.applyColorTransformation(sampleColorTransformation).getBlueValue(), 177);
  }
}
