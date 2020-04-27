import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.util.Random;

import java.awt.*;


public class Susceptible extends Ellipse implements Person{

  private Color color = Color.BLACK;
  private final double RADIUS = 20;
  private final double SPEED = 5;
  public boolean infected = false;
  public boolean recovered = false;


  private CanvasWindow canvas;

  private Random ran = new Random();
  private double currentX, currentY;
  double dX = SPEED;
  double dY= SPEED;
  double randomPointX;
  double randomPointY;
  double delta;


    public Susceptible(CanvasWindow canvas){

     super(canvas.getWidth() * .4 , canvas.getHeight() * .5, 20,20);
     super.setFillColor(color);
     super.setPosition(canvas.getWidth() * ran.nextDouble(), canvas.getHeight() * ran.nextDouble());
     this.currentX = getX();
     this.currentY = getY();
     this.canvas = canvas;

    }
    public void updateHealthStatus() {

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


    public boolean detectCollision(VirusHost virusHost) {

        double leftX = currentX - 20;
        double rightX = currentX + 20;
        double bottomY = currentY + 20;
        double topY = currentY - 20;

        GraphicsObject topLeftCorner = canvas.getElementAt(leftX, topY);
        GraphicsObject topRightCorner = canvas.getElementAt(rightX, topY);
        GraphicsObject bottomLeftCorner = canvas.getElementAt(leftX, bottomY);
        GraphicsObject bottomRightCorner = canvas.getElementAt(rightX, bottomY);

        if (topRightCorner == virusHost) {
            return true;
        }
        if (topLeftCorner == virusHost) {
            return true;
        }
        if (bottomLeftCorner == virusHost) {

            return true;
        }
        if (bottomRightCorner == virusHost) {
            return true;
        }
        return false;
    }

    public void changeColor(){
       super.setFillColor(Color.RED);
    }

}


