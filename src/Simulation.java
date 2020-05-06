import comp127graphics.CanvasWindow;
import comp127graphics.Point;
import java.util.List;
import java.util.Scanner;


/**
 * This class runs the simulation.
 */
public class Simulation {
    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH =  800;
    private ManagePersons managePersons;
    private List<Person> allPersons;
    private double transmissionRate;
    private int recoveryTime;
    private int infectiousPeriod;

    public Simulation(){
        canvas = new CanvasWindow("Virus Simulation",WINDOW_WIDTH,WINDOW_HEIGHT);
        managePersons = new ManagePersons(canvas);

        System.out.println("This is a Virus Simulation.");
        System.out.println(" ============================== ");
        Scanner numPopulation = new Scanner(System.in);
        System.out.println("What is the sample size?: ");
        int sampleSize = numPopulation.nextInt();

        Scanner transRate = new Scanner(System.in);
        System.out.println("Select an infectious rate within the range of 0 to 1: ");
        transmissionRate = transRate.nextDouble();

        Scanner recovered = new Scanner(System.in);
        System.out.println("Select the recovery time: ");
        recoveryTime = recovered.nextInt();

        Scanner infectPeriod = new Scanner(System.in);
        System.out.println("Select the infectious Period of the Virus: ");
        infectiousPeriod = infectPeriod.nextInt();

        allPersons = managePersons.generate(sampleSize, recoveryTime, infectiousPeriod);
    }

    /**
     * This is the main method that runs the simulation.
     * @param args
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();
    }

    /**
     * This method runs the program.
     */
    private void run(){
        while (true){
            for(Person person: allPersons) {
                Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
                person.moveRandomly(canvasCenter);
                managePersons.checkHealthStatus(person);
                managePersons.checkInfectedCollision(person, transmissionRate);
            }
            canvas.draw();
            canvas.pause(10);
        }
    }
}
