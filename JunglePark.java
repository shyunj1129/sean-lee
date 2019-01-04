////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title: JunglePark.java
//Files: CS 300
//Course: LEC 003 Fall, 2018
//
//Author: Sanghyun lee	
//Email: lee866@wisc.edu
//Lecturer's Name: Mouna Kacem
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name: Quming Wang
//Partner Email: qwang357@wisc.edu
//Partner Lecturer's Name: GARY DAHL
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__X_ Write-up states that pair programming is allowed for this assignment.
//__X_ We have both read and understand the course Pair Programming Policy.
//__X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully
//acknowledge and credit those sources of help here. Instructors and TAs do
//not need to be credited here, but tutors, friends, relatives, room mates,
//strangers, and others do. If you received no outside help from either type
//of source, then please explicitly indicate NONE.
//
//Persons: NONE.
//Online Sources: NONE.
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
import java.util.Random;

public class JunglePark {
  // PApplet object that represents the graphic interface of the JunglePark application
  private static PApplet processing;

  // PImage object that represents the background image
  private static PImage backgroundImage;

  // Array storing the current tigers present in the Jungle Park
  private static Tiger[] tigers;

  // Generator of random numbers
  private static Random randGen;

  /**
   * Defines the initial environment properties of the application
   * 
   * @param processingObj represents a reference to the graphical interface of the application
   */
  public static void setup(PApplet processingObj) {
    // initializes the processing field to the one passed into the input argument parameter
    processing = processingObj;

    // initializes and load the image of the background
    backgroundImage = processing.loadImage("images/background.png");

    tigers = new Tiger[8]; // Creates the eight tigers array
    randGen = new Random(); // creates a Random object and store its reference in randGen
  }

  /**
   * draw the application display window and updates its content with respect to any change or any
   * event that affects its appearance.
   */
  public static void update() {
    // Set the color used for the background of the Processing window
    processing.background(245, 255, 250); // Mint cream color

    // Draw the background image at the center of the screen
    processing.image(backgroundImage, processing.width / 2, processing.height / 2);

    // uses a for loop to store a tiger in each index location
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        tigers[i].draw(); // where i is the index of the created tiger in the tigers array.
      }
    }
  }

  /**
   * Defines the tiger's position and the mouse whether over the tiger area or not.
   * 
   * @param tiger represents the array storing the current tigers present in the Jungle Park
   * @return return true if the mouse is not over the image of the Tiger object passed to it as
   *         input parameter, and false otherwise.
   */
  public static boolean isMouseOver(Tiger tiger) {
    if (processing.mouseX > tiger.getPositionX() - (tiger.getImage().width / 2)
        && processing.mouseX < tiger.getPositionX() + tiger.getImage().width / 2
        && processing.mouseY > tiger.getPositionY() - tiger.getImage().height / 2
        && processing.mouseY < tiger.getPositionY() + (tiger.getImage().height / 2)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Utility class will automatically call mouseDown() whenever the mouse button is pressed down on
   * the tiger and holding the mouse to drag the tiger inside the background window.
   */
  public static void mouseDown() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        if (isMouseOver(tigers[i])) {
          tigers[i].setDragging(true); // true that means mouse button is pressed down
        }

      }
    }
  }

  /**
   * mouseUp() is called from Utility class and will be called once when that mouse button is later
   * released
   */
  public static void mouseUp() {
    for (int i = 0; i < tigers.length; i++) {
      if (tigers[i] != null) {
        if (isMouseOver(tigers[i])) {
          tigers[i].setDragging(false); // false that means mouse button is released
        }
      }
    }
  }

  /**
   * Uses the key field within the processing project. Pressing 't' or 'T' once to add a tiger into
   * the JunglePark(up to eight tiger); Pressing 'r' or 'R' while the mouse at a tiger which will be
   * removed from the JunglePark.
   */
  public static void keyPressed() {
    if (processing.key == 't' || processing.key == 'T') {
      for (int i = 0; i < tigers.length; i++) {
        if (tigers[i] == null) {
          tigers[i] = new Tiger(processing, (float) randGen.nextInt(processing.width),
              (float) randGen.nextInt(processing.height));
          break; // Using break instruction to make sure every T-key pressing only adding one tiger
        }
      }
    }
    
    if (processing.key == 'r' || processing.key == 'R') {
      for (int j = 0; j < tigers.length; j++) {
        if (tigers[j] != null) {
          if (isMouseOver(tigers[j])) {
            tigers[j] = null;
          }
        }
      }
    }
  }

  /**
   * The startApplication() method is from the provided Utility class and called from the main()
   * method, provided in the CS300JunglePark jar library, creates the main window for the
   * application, and then repeatedly updates its appearance and checks for user input. It also
   * checks if specific callback methods have been defined in the JunglePark class.
   */
  public static void main(String[] args) {

    Utility.startApplication(); // The provided Utility class
  }

}
