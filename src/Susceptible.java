import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.awt.geom.Rectangle2D;
import java.util.Random;

import java.awt.*;
import java.util.stream.DoubleStream;

public class Susceptible extends Ellipse implements Person{

  private Color color = Color.BLACK;
  private final double RADIUS = 20;
  private final double SPEED = 10;
  public boolean infected = false;
  public boolean recovered = false;
  private Random random;
  private CanvasWindow canvas;
  private Point.Double location;
  private Random ran = new Random();
  private double currentX, currentY;
  double dX = SPEED;
  double dY= SPEED;


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
        currentY += dY;
        currentX += dX;
        super.setCenter(currentX, currentY);
        if (currentX < 0 || currentX > canvas.getWidth()) {
            dX = -dX;

        }
        if (currentY < 0 || currentY > canvas.getHeight()) {
            dY = -dY;

        }

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

}


