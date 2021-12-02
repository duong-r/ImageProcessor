package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Represents a GUI for an Image Processor. Allows thee user to load and view, save, and manipulate
 * images through buttons and panels.
 */
public class ImageProcessorGraphicalView extends JFrame implements ImageProcessorView {

  private final JButton loadButton;
  private final JButton redButton;
  private final JButton greenButton;
  private final JButton blueButton;
  private final JButton horizFlipButton;
  private final JButton vertFlipButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton valueButton;
  private final JButton lumaButton;
  private final JButton greyscaleButton;
  private final JButton sepiaButton;
  private final JButton intensityButton;
  private final JButton blurButton;
  private final JButton sharpenButton;
  private final JButton mosaicButton;
  private final JButton saveButton;
  private final JLabel imageLabel;
  private final JLabel histogramLabel;


  /**
   * The constructor for the ImageProcessorGraphicalView.
   */
  public ImageProcessorGraphicalView() {
    super();
    this.setTitle("Image Processor");
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane mainScrollPane = new JScrollPane(mainPanel);
    add(mainScrollPane);

    JPanel imagePanel = new JPanel();
    imagePanel
        .setBorder(BorderFactory.createTitledBorder("Current Image (and matching histogram)"));
    imagePanel.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(imagePanel);

    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Operations"));
    dialogBoxesPanel.setLayout(new GridLayout(4, 4));
    mainPanel.add(dialogBoxesPanel);

    loadButton = new JButton("Load an Image");
    loadButton.setActionCommand("Load image");
    dialogBoxesPanel.add(loadButton);

    redButton = new JButton("Red-Component");
    redButton.setActionCommand("Red-Component");
    dialogBoxesPanel.add(redButton);

    greenButton = new JButton("Green-Component");
    greenButton.setActionCommand("Green-Component");
    dialogBoxesPanel.add(greenButton);

    blueButton = new JButton("Blue-Component");
    blueButton.setActionCommand("Blue-Component");
    dialogBoxesPanel.add(blueButton);

    horizFlipButton = new JButton("Flip Horizontally");
    horizFlipButton.setActionCommand("Flip Horizontally");
    dialogBoxesPanel.add(horizFlipButton);

    vertFlipButton = new JButton("Flip Vertically");
    vertFlipButton.setActionCommand("Flip Vertically");
    dialogBoxesPanel.add(vertFlipButton);

    brightenButton = new JButton("Brighten");
    brightenButton.setActionCommand("Brighten");
    dialogBoxesPanel.add(brightenButton);

    darkenButton = new JButton("Darken");
    darkenButton.setActionCommand("Darken");
    dialogBoxesPanel.add(darkenButton);

    valueButton = new JButton("Value");
    valueButton.setActionCommand("Value");
    dialogBoxesPanel.add(valueButton);

    lumaButton = new JButton("Luma");
    lumaButton.setActionCommand("Luma");
    dialogBoxesPanel.add(lumaButton);

    intensityButton = new JButton("Intensity");
    intensityButton.setActionCommand("Intensity");
    dialogBoxesPanel.add(intensityButton);

    greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setActionCommand("Greyscale");
    dialogBoxesPanel.add(greyscaleButton);

    sepiaButton = new JButton("Sepia");
    sepiaButton.setActionCommand("Sepia");
    dialogBoxesPanel.add(sepiaButton);

    blurButton = new JButton("Blur");
    blurButton.setActionCommand("Blur");
    dialogBoxesPanel.add(blurButton);

    sharpenButton = new JButton("Sharpen");
    sharpenButton.setActionCommand("Sharpen");
    dialogBoxesPanel.add(sharpenButton);

    mosaicButton = new JButton("Mosaic");
    mosaicButton.setActionCommand("Mosaic");
    dialogBoxesPanel.add(mosaicButton);

    saveButton = new JButton("Save an Image");
    saveButton.setActionCommand("Save");
    dialogBoxesPanel.add(saveButton);

    imageLabel = new JLabel();
    JScrollPane imageScrollPane = new JScrollPane(imageLabel);
    imageScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(imageScrollPane);

    histogramLabel = new JLabel();
    JScrollPane histogramScrollPane = new JScrollPane(histogramLabel);
    histogramScrollPane.setPreferredSize(new Dimension(100, 600));
    imagePanel.add(histogramScrollPane);
  }

  @Override
  public void setListener(ActionListener listener) {
    loadButton.addActionListener(listener);
    redButton.addActionListener(listener);
    greenButton.addActionListener(listener);
    blueButton.addActionListener(listener);
    valueButton.addActionListener(listener);
    horizFlipButton.addActionListener(listener);
    vertFlipButton.addActionListener(listener);
    intensityButton.addActionListener(listener);
    lumaButton.addActionListener(listener);
    blurButton.addActionListener(listener);
    sharpenButton.addActionListener(listener);
    greyscaleButton.addActionListener(listener);
    sepiaButton.addActionListener(listener);
    brightenButton.addActionListener(listener);
    darkenButton.addActionListener(listener);
    mosaicButton.addActionListener(listener);
    saveButton.addActionListener(listener);
  }

  @Override
  public void setImage(Image image) {
    imageLabel.setIcon(new ImageIcon(image));
  }

  @Override
  public void setHistogram(Image image) {
    histogramLabel.setIcon(new ImageIcon(image));
  }

  @Override
  public void display() {
    this.setVisible(true);
  }
}
