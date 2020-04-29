import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.util.Random;

import java.awt.*;


/** This class is responsible for creating a peron*/
public class Person extends Ellipse{


  private final double SPEED = 5;

  public boolean infected = false;
  public boolean recovered = false;


  private Color color = Color.BLACK;
  private CanvasWindow canvas;

  private Random ran = new Random();

  public int recoveryTime = 1000;
  private double radius;


    public Person(CanvasWindow canvas){

     super(canvas.getWidth() * .4 , canvas.getHeight() * .5, 20,20);
     super.setFillColor(color);
     super.setPosition(canvas.getWidth() * ran.nextDouble(), canvas.getHeight() * ran.nextDouble());
     this.canvas = canvas;
     radius = this.getWidth() /2;

    }

    /** Moves persons randomly on screen my creating points on the screen that they move to */
    public void moveRandomly(){

        double randomPointX = canvas.getWidth() * ran.nextDouble();
        double randomPointY = canvas.getHeight() * ran.nextDouble();

        double distX = randomPointX - getX();
        double distY = randomPointY - getY();
        double totalDist = Math.hypot(distX,distY);

        // Sets new goal
        double distToGoal = Math.hypot(
                randomPointX - getX(),
                randomPointY - getY());
        if (distToGoal < 40 * 40 || ran.nextDouble() < 0.5 / 10){
            distX = (canvas.getWidth() * ran.nextDouble())  - getX();
            distY = (canvas.getHeight() * ran.nextDouble())  - getY();
        }

        this.moveBy((distX * SPEED / totalDist) * 0.5,
                (distY * SPEED / totalDist) * 0.5);


    }
    /** Returns graphic objects when coming in contact with persons*/
    public Object detectCollision() {

        double leftX = getX() - radius;
        double rightX = getX() + radius;
        double bottomY = getY() + radius;
        double topY = getY() - radius;

        GraphicsObject topLeftCorner = canvas.getElementAt(leftX, topY);
        GraphicsObject topRightCorner = canvas.getElementAt(rightX, topY);
        GraphicsObject bottomLeftCorner = canvas.getElementAt(leftX, bottomY);
        GraphicsObject bottomRightCorner = canvas.getElementAt(rightX, bottomY);

        if (topRightCorner != null) {
            return topRightCorner;
        }
        if (topLeftCorner != null) {
            return topLeftCorner;
        }
        if (bottomLeftCorner != null) {
            return bottomLeftCorner;
        }
        if (bottomRightCorner != null) {
            return bottomRightCorner;
        }
        return null;
    }
    /** Changes the color of the person*/
    private void changeColor(Color color){
       super.setFillColor(color);
    }
    /** Changes the infected state to true of the person and changes their color to red */
    public void makeInfected(){
        this.infected = true;
        changeColor(Color.RED);
    }
    /** Changes the infected state to false and recovered to true of the person and changes their color to green */
    public void makeRecovered(){
        this.recovered = true;
        this.infected = false;
        changeColor(Color.GREEN);
    }





}


