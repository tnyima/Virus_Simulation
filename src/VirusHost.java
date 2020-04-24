import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

import java.awt.*;

public class VirusHost extends Ellipse implements Person{



    public boolean infected = true;
    private Color color = Color.RED;
    public boolean recovered = false;
    private final int RADIUS = 20;

    public VirusHost(CanvasWindow canvas) {
        super(canvas.getWidth() * .5, canvas.getHeight() * .5, 20,20);
        super.setFillColor(color);
    }


    public void updateHealthStatus() {

    }

    public void moveRandomly() {

    }

    @Override
    public boolean detectCollision() {
        return false;
    }

    public boolean detectCollision(CanvasWindow canvas) {
        return false;

    }
}
