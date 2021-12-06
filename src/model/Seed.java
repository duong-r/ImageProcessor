package model;

import java.util.List;

/**
 *
 */
public interface Seed {

  /**
   *
   * @return
   */
  int getX();

  /**
   *
   * @return
   */
  int getY();

  /**
   *
   * @return
   */
  List<Posn> getCluster();

  /**
   *
   * @param row
   * @param col
   */
  void addPosn(int row, int col);

  /**
   *
   * @param row
   * @param col
   * @return
   */
  Double findEuclidean(int row, int col);

}

