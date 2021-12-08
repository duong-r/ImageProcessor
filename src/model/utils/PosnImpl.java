package model.utils;

/**
 * An implementation of the Posn class.
 */
public class PosnImpl implements Posn {
  private final int y;
  private final int x;

  /**
   * Constructing a Posn.
   * @param y y value
   * @param x x value
   */
  public PosnImpl(int y, int x) {
    if (y < 0 || x < 0) {
      throw new IllegalArgumentException("X and Y cannot be negative");
    }
    this.y = y;
    this.x = x;
  }

  @Override
  public int getX() {
    return x;
  }

  @Override
  public int getY() {
    return y;
  }
}
