import comp127graphics.CanvasWindow;

public class Simulation {

    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH =  800;
    private Susceptible susceptible;
    private VirusHost virusHost;
    private RecoveredHost recoveredHost;

    public Simulation(){

        canvas = new CanvasWindow("Virus Simulation",WINDOW_WIDTH,WINDOW_HEIGHT);

        susceptible = new Susceptible(canvas);
        canvas.add(susceptible);

        virusHost = new VirusHost(canvas);
        canvas.add(virusHost);

        recoveredHost = new RecoveredHost(canvas);
        canvas.add(recoveredHost);

    }

    public static void main(String[] args) {
        new Simulation();

    }
}
