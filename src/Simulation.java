import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

public class Simulation {

    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH =  800;
    private Susceptible susceptible;
    private VirusHost virusHost;
    private RecoveredHost recoveredHost;
    private ManagePersons managePersons;
    private List<Person> allPersons;
    private List<VirusHost> virusHostList;
    private List<Susceptible> susceptibleList;


    public Simulation(){

        canvas = new CanvasWindow("Virus Simulation",WINDOW_WIDTH,WINDOW_HEIGHT);

        susceptible = new Susceptible(canvas);
        susceptibleList = new ArrayList<>();


        virusHost = new VirusHost(canvas);
        virusHostList = new ArrayList<>();


        recoveredHost = new RecoveredHost(canvas);

        managePersons = new ManagePersons(canvas);

       allPersons = managePersons.generate(2);


    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();

    }

    private void run(){

        while (true){
            for(Person person: allPersons) {
                person.moveRandomly();
//                    managePersons.virusCollision();
            }
            canvas.draw();
            canvas.pause(10);
        }
    }

}
