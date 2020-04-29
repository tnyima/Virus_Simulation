import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.util.Random;

import java.awt.*;


public class Person extends Ellipse{

  private final double RADIUS = 20;
  private final double SPEED = 5;
  public boolean infected = false;
  public boolean recovered = false;
  private Color color = Color.BLACK;
  private CanvasWindow canvas;

  private Random ran = new Random();
  private double currentX, currentY;
  private double dX = SPEED, dY= SPEED;
  private double randomPointX, randomPointY;
  private double delta, radius;


    public Person(CanvasWindow canvas){

     super(canvas.getWidth() * .4 , canvas.getHeight() * .5, 20,20);
     super.setFillColor(color);
     super.setPosition(canvas.getWidth() * ran.nextDouble(), canvas.getHeight() * ran.nextDouble());
     this.currentX = getX();
     this.currentY = getY();
     this.canvas = canvas;
     radius = this.getWidth() /2;

    }

    public void checkHealthStatus() {

    }

    public void moveRandomly(){
        randomPointX = canvas.getWidth() * ran.nextDouble();
        randomPointY = canvas.getHeight() * ran.nextDouble();

        double distX = randomPointX - currentX;
        double distY = randomPointY - currentY;
        double totalDist = Math.hypot(distX,distY);

        this.moveBy((distX * SPEED / totalDist) * 0.5,
                (distY * SPEED / totalDist) * 0.5);


    }


    public Object detectCollision() {

        double leftX = currentX - radius;
        double rightX = currentX + radius;
        double bottomY = currentY + radius;
        double topY = currentY - radius;

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

    private void changeColor(Color color){
       super.setFillColor(color);
    }

    public void makeInfected(){
        this.infected = true;
        changeColor(Color.RED);
    }



}


