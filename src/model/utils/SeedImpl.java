package model.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representing an implementation of a Seed.
 */
public class SeedImpl implements Seed {
  private final int x;
  private final int y;
  private final List<model.utils.Posn> cluster = new ArrayList<>();

  /**
   * Constructing a SeedImpl object.
   * @param y y val
   * @param x x val
   */
  public SeedImpl(int y, int x) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("X and Y cannot be negative");
    }
    this.y = y;
    this.x = x;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public List<Posn> getCluster() {
    return this.cluster;
  }

  @Override
  public void addPosn(int row, int col) {
    this.cluster.add(new PosnImpl(row, col));
  }

  @Override
  public Double findEuclidean(int row, int col) {
    return Math.sqrt(((this.y - row) * (this.y - row)) + ((this.x - col) * (this.x - col)));
  }


  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (! (o instanceof SeedImpl)) {
      return false;
    }

    SeedImpl that = (SeedImpl) o;

    return this.x == that.x
            && this.y == that.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}



