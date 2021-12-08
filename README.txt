MOSAIC IMPLEMENTATION DETAILS

No modifications to the recieved code we’re made to implement the mosaic functionality,
we only added new code on top of what was given to support the new functionality.
Our mosaic model takes in a non negative, integer value from the user, along with the name of the
image to be modified, and the name of the new image. This information is passed to the model,
which then retrieves the target image from the already present hashmap of images, then uses it
to create a new mosaic using a helper function in the already present image class.

The helper mosaic method takes the number of seeds, and the new image’s name, to create a list
of seeds that are positioned on different pixels of the image, then iterates through every pixel
in “this” image to determine the closest seed to that pixel, using the added method in the seed
class that finds the Euclidean distance of two positions. The row,col pair of the pixel are stored
the a list of Posns of a Seed’s cluster, which allows us to associate the positions of nearby
pixels to seeds. A Posn is an added class that is used to store row,col positions.

One the double for loop that iterates through every pixel is completed, another double for loop
goes through every seed, and then through every cluster, in order to change the pixels at the
Posn’s stored in the seed’s cluster to the color of the pixel that the seed is located at.

Once this is complete, the helper returns the image to the model which is then added to the hashmap.

Our designEd our code to match the design of the people before us, as our model’s method passes
the related information along to the image class, which handles the creation of the image.
Once the image is created, we use the same style found in each functionality supporting
method of the model to store our image.

Adding new functionality to the controller and view was fairly trivial and simply required adding
a new case for the mosaic operation in all methods which deal with features.

***************************************************************************************************


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