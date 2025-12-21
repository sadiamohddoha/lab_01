1. CirclePlotter.java

a. The main method is located in the CirclePlotter class.

b. The circles are drawn in the panel class.

c. The class the circles are drawn on is a JPanel.

d. The panel implements the MouseListener interface.

e. The method that draws the circle is the paintComponent method.

f. The size of the circle is determined by the radius value in the drawing code.

g. The variable that stores circle information is a list that holds the circle objects.

h. The function that actually draws the circles is paintComponent.

i. The methods implemented for the interface are mouseClicked, mousePressed, mouseReleased, mouseEntered, and mouseExited.

j. The mouseClicked method responds to mouse clicks and causes the circle to be drawn.

2. SquarePlotter.java

a. The program is similar to CirclePlotter but allows squares to move when dragged.
b. The program implements MouseListener and MouseMotionListener.
c. The overridden methods include mousePressed, mouseReleased, mouseDragged, and mouseClicked.
d. The mouseDragged method allows squares to move.
e. The mouseReleased method stops the square from moving when the mouse button is released.
f. The insideExisting flag checks if the mouse is inside a square. When false, no square moves. When true, the selected square moves.
g. MouseDragged and mouseMoved belong to MouseMotionListener.

3. SquarePlotterAdvanced.java

a. The program allows resizing and rotating squares.
b. These features are handled by mouse drag logic and key or mouse checks in the code.
c. New mouse-related logic is added, but the same interfaces are still used.

4. HelloJavaFX.java

a. The program prints text in a window.
b. The text stays centered when the window is resized.
c. Packages from javafx.application, javafx.scene, and javafx.stage are imported.
d. The superclass is Application.
e. The start method is overridden.
f. One line sets the text shown in the window.
g. Another line sets the text in the menu bar.
h. If line 16 is commented out, the program will not start correctly.

5. VideoPlayer.java

a. Yes, the program uses JavaFX.
b. It imports JavaFX media and UI packages.
c. Some packages such as java.io are not part of JavaFX.
d. A FileChooser allows the user to select a video.
e. The MediaPlayer object plays the video.
f. The main method calls launch, which then calls start.

6. 3D and 4D Scatter Plot Programs

a. Mouse scroll zooms and mouse dragging rotates the scene.
b. The 3D data file has three columns representing x, y, and z values.
c. Each row represents one point in space.
d. The 4D data includes time as an extra column.
e. A list stores all point objects.
f. The video is created by capturing frames over time as the animation runs.

7. Database Programming

a. The programs use java.sql packages.
b. The connection includes a JDBC protocol, localhost hostname, port number, database name, username, and password.
c. A SELECT SQL command is used.
d. A ResultSet stores the data returned from the database.
e. If the connection fails, the error handling code runs and prints an error.

8. Network Programming

a. The server prints messages showing connections and messages sent. Clients print received messages.
b. Yes, all clients receive messages.
c. The programs import java.net and java.io packages.
d. The programs communicate using a socket on a specific port.
e. Socket objects handle communication.
f. The server method sends messages to all clients.
g. The client listens for messages using an input stream.

9. Games and Networked Games

The game programs were run successfully. Changes were tested to better understand how the programs work, including player movement and interaction.
