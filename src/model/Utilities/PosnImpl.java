package model.Utilities;

import model.Utilities.Posn;

public class PosnImpl implements Posn {
  private final int x;
  private final int y;

  public PosnImpl(int x, int y) {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("X and Y cannot be negative");
    }
    this.x = x;
    this.y = y;
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
