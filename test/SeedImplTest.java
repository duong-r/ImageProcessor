import org.junit.Test;

import java.util.ArrayList;
import model.utils.SeedImpl;

import static org.junit.Assert.assertEquals;

/**
 * Class utilized for testing the Seed class.
 */
public class SeedImplTest {

  SeedImpl exampleSeed = new SeedImpl(50, 50);

  @Test
  public void seedConstructorTest() {
    assertEquals(new SeedImpl(50, 50).getX(), 50);
    assertEquals(new SeedImpl(50, 50).getY(), 50);
    assertEquals(new SeedImpl(50, 50).getCluster(), new ArrayList<>());
  }

  @Test(expected = IllegalArgumentException.class)
  public void seedConstructorTestNegX() {
    new SeedImpl(-50, 50);
  }

  @Test(expected = IllegalArgumentException.class)
  public void seedConstructorTestNegY() {
    new SeedImpl(50, -50);
  }

  @Test
  public void seedGetXTest() {
    assertEquals(this.exampleSeed.getX(), 50);
  }

  @Test
  public void seedGetYTest() {
    assertEquals(this.exampleSeed.getY(), 50);
  }

  @Test
  public void seedGetClusterTest() {
    assertEquals(this.exampleSeed.getCluster(), new ArrayList<>());
  }

  @Test
  public void seedAddPosnTest() {
    assertEquals(this.exampleSeed.getCluster().size(), 0);
    this.exampleSeed.addPosn(11, 11);
    assertEquals(this.exampleSeed.getCluster().size(), 1);
  }

  @Test
  public void seedAdd2PosnsTest() {
    assertEquals(this.exampleSeed.getCluster().size(), 0);
    this.exampleSeed.addPosn(11, 11);
    assertEquals(this.exampleSeed.getCluster().size(), 1);
    this.exampleSeed.addPosn(34, 11);
    assertEquals(this.exampleSeed.getCluster().size(), 2);
  }

  @Test
  public void seedFindEuclideanTest() {
    assertEquals(this.exampleSeed.findEuclidean(3, 11), 61.07372593840988,
            0.000001);

  }


}
