import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

import java.awt.*;

public class Susceptible extends Ellipse implements Person{

  private Color color = Color.BLACK;
   public boolean infected = false;
   public boolean recovered = false;
   private final int RADIUS = 20;

    public Susceptible(CanvasWindow canvas){
     super(canvas.getWidth() * .4, canvas.getHeight() * .5, 20,20);
     super.setFillColor(color);

    }
    public void updateHealthStatus() {

    }

    public void moveRandomly() {

    }


    public void detectCollision(CanvasWindow canvas) {

    }
}