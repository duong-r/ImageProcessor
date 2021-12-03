package model.Utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Utilities.Posn;
import model.Utilities.PosnImpl;

public class SeedImpl implements Seed{
  private final int x;
  private final int y;
  private List<Posn> cluster = new ArrayList<>();

  public SeedImpl(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  @Override
  public List<Posn> getCluster() {
    return this.cluster;
  }

  public void addPosn(int row, int col) {
    this.cluster.add(new PosnImpl(row, col));
  }

  public Double findEuclidean(int row, int col) {
    return Math.sqrt(((this.x - row) * (this.x - row)) + ((this.y - col) * (this.y - col)));
  }


  @Override
  public boolean equals(Object o) {
    if(o == this) {
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
//  Pixels are assigned to seeds (a randomly placed row col coord) by distance. Once associated with a seed, must differentiate associated pixels from non associated.
//
//        For
//
//        Assuming were placing the seeds (row, col) in a list,
//
//        Iterate through all pixels (double for loop)
//
//        Apply pixel to each seed using root((x2 - x2)^2 + (y2 - y1)^2)) to store in min value


