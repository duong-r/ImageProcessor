package model.utils;

import java.util.List;

/**
 * Representing a seed in an image.
 */
public interface Seed {

  /**
   * Getting the x value of the seed.
   * @return x val
   */
  int getX();

  /**
   * Getting the y value of the seed.
   * @return y val
   */
  int getY();

  /**
   * Getting the cluster of a seed.
   * @return list of coordinates
   */
  List<Posn> getCluster();

  /**
   * Adding a coordinate to the list.
   * @param row x val
   * @param col y val
   */
  void addPosn(int row, int col);

  /**
   * Finding the distance between two points.
   * @param row x val
   * @param col y val
   * @return the euclidean distance
   */
  Double findEuclidean(int row, int col);

}

