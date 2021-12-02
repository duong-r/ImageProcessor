package model;

import java.util.ArrayList;
import java.util.List;

public class SeedImpl {
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

  public void addPosn(int row, int col) {
    this.cluster.add(new Posn(row, col));
  }

  public Double findEuclidean(int row, int col) {
    return Math.sqrt(((this.x - row) * (this.x - row)) + ((this.y - col) * (this.y - col)));
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


