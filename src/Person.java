import comp127graphics.CanvasWindow;

public interface Person{

    public boolean infected = false;

    public boolean recovered = false;

    public void updateHealthStatus();

    public void moveRandomly();

    public boolean detectCollision();

}
