import comp127graphics.*;
import comp127graphics.Point;
import java.util.Random;
import java.awt.*;

/**
 * This class creates a person using Ellipse. It also has methods that checks for collisions and allows the person to
 * move around the canvas.
 */
public class Person extends Ellipse{
    private static final double
            WIGGLINESS = 0.2,
            WANDER_FROM_CENTER = 60000;
    public boolean infected = false;
    public boolean recovered = false;
    private Color color = Color.BLACK;
    private CanvasWindow canvas;
    public long recoveryTime;
    public long infectiousPeriod;
    private double direction;
    private Random ran = new Random();
    public double currentX, currentY;
    private double radius;

    public Person(CanvasWindow canvas){
        super(canvas.getWidth() * .4 , canvas.getHeight() * .5, 20,20);
        super.setFillColor(color);
        super.setPosition(canvas.getWidth() * ran.nextDouble(), canvas.getHeight() * ran.nextDouble());
        this.currentX = getX();
        this.currentY = getY();
        this.canvas = canvas;
        radius = this.getWidth() /2;

    }

    /** This allows the person to move freely around the canvas. The movement was credited to the
     * cell absorption lab. */
    public void moveRandomly(Point centerOfGravity){
        this.moveBy(Math.cos(direction), Math.sin(direction));
        double distToCenter = this.getCenter().distance(centerOfGravity);
        double angleToCenter = centerOfGravity.subtract(this.getCenter()).angle();
        double turnTowardCenter = normalizeRadians(angleToCenter - direction);
        direction = normalizeRadians(
                direction
                        + (Math.random() - 0.5) * WIGGLINESS
                        + turnTowardCenter * Math.tanh(distToCenter / WANDER_FROM_CENTER));
    }

    /**
     * This method helps our method moveRandomly method.
     * @param theta
     * @return
     */
    private static double normalizeRadians(double theta) {
        double pi2 = Math.PI * 2;
        return ((theta + Math.PI) % pi2 + pi2) % pi2 - Math.PI;
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

    /**
     * this method sets the recovered time
     * @param time
     */
    public void setRecoveredTime(long time){
        this.recoveryTime = time;
    }

    /**
     * This method sets the infectious period.
     * @param infectiousPeriod
     */
    public void setInfectiousPeriod(long infectiousPeriod){
        this.infectiousPeriod = infectiousPeriod;
    }

}


