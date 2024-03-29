import org.junit.Test;

import model.utils.PosnImpl;

import static org.junit.Assert.assertEquals;

/**
 * Class utilized for testing the Posn class.
 */
public class PosnImplTest {
  PosnImpl examplePosn = new PosnImpl(50, 50);

  @Test
  public void seedConstructorTest() {
    assertEquals(new PosnImpl(50, 50).getX(), 50);
    assertEquals(new PosnImpl(50, 50).getY(), 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void seedConstructorTestNegX() {
    new PosnImpl(50, -50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void seedConstructorTestNegY() {
    new PosnImpl(-50, 50);
  }

  @Test
  public void seedGetXTest() {
    assertEquals(this.examplePosn.getX(), 50);
  }

  @Test
  public void seedGetYTest() {
    assertEquals(this.examplePosn.getY(), 50);
  }
}
