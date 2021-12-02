Design Overview:
Images are loaded using ImageUtil methods and represented as ImageImpls, regardless of file type.
This is done by extracting rgb data from the BufferedImage objects created from reading files using
the ImageIO class and creating PixelImpl objects from the data.
An ImageImpl is an object with a String name, 2d array of Pixels, height (number of rows), and 
width (number of columns).
The Image interface support several operations to manipulate the Pixels of this Image, the name 
of the image is for identifying specific images.
The ImageProcessorModelImpl class is simply an array list of ImageImpl that can be worked on.
Loaded image are added to this list and any image that is created from manipulating an
already loaded image is also added.
The ImageProcessorController interface supports one method to run the ImageProcessor.
The implementation of this class, ImageProcessorControllerImpl, has two constructors: one that 
supports and GUI and one that reads input. They both take a model, the former takes a view and
the latter takers a Readable.
The ImageProcessorControllerImpl evaluates the input for specific supported commands and passes the 
relevent arguments to the relevent model methods.
Files are saved through ImageUtil, using the PixelImpl rgb data to create BufferedImage objects and
ImageIO class to write to files.
The ImageProcessorView interface supports methods for displaying the GUI and setting a listener for the view. 
The implementation of ImageProcessorView, ImageProcessorGraphicalView, also extends JFrame and creates 
a window with 16 buttons that signal to the listener which operation to perform.
The Histogram class is responsble for storing the frequencies, using ImageFrequencies, and creating a 
BufferedImage of the histogram as a line graph.
The ImageFrequencies interface has some methods that are for the purpose of mapping rgb values to their
frequencies.
The implementation of this interface, ImageFrequenciesImp, contains a delegate Image (ours) Object
to get the frequencies from.

Design Changes:
- Added ImageProcessorView view field to ImageProcessorControllerImpl
	To support a GUI.
- Added another constructor to ImageProcessorControllerImpl
	To support a GUI.
- ImageProcessorControllerImpl implements ActionListener
	To listen to view buttons.

Image Source: The Ren and Stimpy Show
Sample Script File: res/sample-script.txt