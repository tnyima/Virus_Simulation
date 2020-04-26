import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsObject;

import java.awt.*;

public class VirusHost extends Ellipse implements Person{


    public boolean infected = true;
    private Color color = Color.RED;
    public boolean recovered = false;
    private final int RADIUS = 20;
    private final double SPEED = 10;
    double dX = SPEED;
    double dY= SPEED;
    private double currentX, currentY;
    private CanvasWindow canvas;


    public VirusHost(CanvasWindow canvas) {
        super(canvas.getWidth() * .5, canvas.getHeight() * .5, 20,20);
        super.setFillColor(color);
        this.currentX = getX();
        this.currentY = getY();
        this.canvas = canvas;
    }


    public void updateHealthStatus() {

    }

    public void moveRandomly() {
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
}
