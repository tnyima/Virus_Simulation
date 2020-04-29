import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;
import comp127graphics.Rectangle;

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
    public boolean getInfected(){
        return infected = true;
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


    public GraphicsObject detectCollision() {
        comp127graphics.Point top = new comp127graphics.Point(currentX + RADIUS, currentY);
        comp127graphics.Point right = new comp127graphics.Point(currentX + (2 * RADIUS), currentY + RADIUS);
        comp127graphics.Point left = new comp127graphics.Point(currentX, currentY + RADIUS);
        comp127graphics.Point bottom = new comp127graphics.Point(currentX + RADIUS, currentY + (2 * RADIUS));

        if (canvas.getElementAt(top) instanceof Ellipse) { //top
            System.out.println("Top was hit.");
            return canvas.getElementAt(top);

        }
        else if (canvas.getElementAt(right) instanceof Ellipse) { //right
            System.out.println("Right was hit.");
            return canvas.getElementAt(right);
        }
        else if(canvas.getElementAt(left) instanceof comp127graphics.Rectangle) { //left
            System.out.println("Left was hit.");
            return canvas.getElementAt(left);
        }
        else if(canvas.getElementAt(bottom) instanceof Rectangle) { //bottom
            System.out.println("Bottom was hit.");
            return canvas.getElementAt(bottom);
        }
        else{
            return null;
        }
    }

    private void changeColor(Color color){
       super.setFillColor(color);
    }

    public void makeInfected(){
        this.infected = true;
        changeColor(Color.RED);
    }



}


