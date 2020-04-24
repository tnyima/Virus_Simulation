import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

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


    }

    public boolean detectCollision() {
     return false;

    }

    public void movePeople(){
        double dX = SPEED;
        double dY= SPEED;
        currentY += dY;
        currentX += dX;

        if (currentX < 0 || currentX > canvas.getWidth()) {
            dX = -dX;
        }
        if (currentY < 0 || currentY > canvas.getHeight()) {
            dY = -dY;
        }

        super.setCenter(currentX, currentY);

    }
}


