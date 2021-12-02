package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the model for a PPM image processor. One object represents one model for the
 * processor. The image processor starts with no loaded images.
 */
public class ImageProcessorModelImpl implements ImageProcessorModel {

  private final ArrayList<ImageImpl> loadedImages = new ArrayList<>();


  @Override
  public void loadImage(String filename, String name) {
    String ext = filename.substring(filename.lastIndexOf("."));
    switch (ext) {
      case ".ppm":
        this.loadedImages.add(ImageUtil.loadPPM(filename, name));
        break;
      case ".jpg":
      case ".png":
      case ".bmp":
        this.loadedImages.add(ImageUtil.loadNotPPM(filename, name));
        break;
      default:
        throw new IllegalArgumentException("File type " + ext + " not supported.");
    }
  }

  @Override
  public ArrayList<ImageImpl> getLoadedImages() {
    return this.loadedImages;
  }

  @Override
  public ImageImpl getImage(String name) {
    for (ImageImpl image : loadedImages) {
      if (image.getName().equals(name)) {
        return image;
      }
    }
    throw new IllegalArgumentException(name + " not found.");
  }

  @Override
  public void getRedComponent(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getRedComponent(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void getGreenComponent(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getGreenComponent(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void getBlueComponent(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getBlueComponent(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void valueOf(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getValue(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void intensityOf(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getIntensity(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void lumaOf(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getLuma(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void greyscaleOf(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getGreyscale(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void sepiaOf(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.getSepia(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void flipVertically(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.flipVertically(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void flipHorizontally(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.flipHorizontally(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void brighten(int value, String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.brighten(value, newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void darken(int value, String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.darken(value, newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void blur(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.blur(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void sharpen(String name, String newImageName) {
    ImageImpl imageToModify = this.getImage(name);
    ImageImpl modifiedImage = imageToModify.sharpen(newImageName);
    loadedImages.add(modifiedImage);
  }

  @Override
  public void mosaic(String name, String newImageName, int numberOfSeeds) {
    ImageImpl image = this.getImage(name);
    List<SeedImpl> seeds = image.getSeeds(numberOfSeeds);

    for(int row = 0; row < image.getWidth(); row ++) {
      int index = 0;
      Double euclideanDist = Double.MAX_VALUE;

      for(int col = 0; col < image.getHeight(); col++) {
        for(int i = 0; i < seeds.size(); i++) {
          Double euclidean = seeds.get(i).findEuclidean(row, col);
          if(euclidean < euclideanDist) {
            index = i;
            euclideanDist = euclidean;
          }
        }
//      Associate the pixel at row col to the seed at point SeedRow and SeedCol;
        seeds.get(index).addPosn(row, col); //helper method written later
      }
    }

  }


  @Override
  public Image getLastImage() {
    return this.loadedImages.get(this.loadedImages.size() - 1);
  }

  @Override
  public void saveImage(String filename, String name) {
    String ext = filename.substring(filename.lastIndexOf(".") + 1);
    switch (ext) {
      case "ppm":
        ImageUtil.savePPM(filename, this.getImage(name));
        break;
      case "jpg":
      case "png":
      case "bmp":
        ImageUtil.saveNotPPM(filename, this.getImage(name), ext);
        break;
      default:
        throw new IllegalArgumentException("File type " + ext + " not supported.");
    }
  }

}
