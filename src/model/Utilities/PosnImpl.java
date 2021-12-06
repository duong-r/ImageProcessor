package model.Utilities;

import model.Utilities.Posn;

public class PosnImpl implements Posn {
  private final int y;
  private final int x;

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
