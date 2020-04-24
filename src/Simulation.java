import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;

import java.sql.PseudoColumnUsage;
import java.util.List;

public class Simulation {

    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH =  800;
    private Susceptible susceptible;
    private VirusHost virusHost;
    private RecoveredHost recoveredHost;
    private managePersons managePersons;
    private List<Susceptible> allPersons;

    public Simulation(){

        canvas = new CanvasWindow("Virus Simulation",WINDOW_WIDTH,WINDOW_HEIGHT);

        susceptible = new Susceptible(canvas);

        virusHost = new VirusHost(canvas);

        recoveredHost = new RecoveredHost(canvas);

        managePersons = new managePersons(canvas);

       allPersons = managePersons.generate(10);




    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();

    }

    private void run(){
        canvas.animate(() -> { for(Susceptible person: allPersons){
            person.movePeople();
        }
        });
    }

}
