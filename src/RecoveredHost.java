import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;

import java.awt.*;

public class RecoveredHost extends Ellipse implements Person {

    private final int RADIUS = 20;
    private Color color = Color.GREEN;
    public boolean infected = false;
    public boolean recovered = true;


    public RecoveredHost(CanvasWindow canvas){
        super(canvas.getWidth() * .6, canvas.getHeight() * .5, 20,20);
        super.setFillColor(color);


    }

    @Override
    public void updateHealthStatus() {

    }

    @Override
    public void moveRandomly() {

    }

    @Override
    public boolean detectCollision() {
        return false;
    }


}
