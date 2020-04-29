import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Simulation {

    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH =  800;


    private ManagePersons managePersons;

    private List<Person> allPersons;



    public Simulation(){

        canvas = new CanvasWindow("Virus Simulation",WINDOW_WIDTH,WINDOW_HEIGHT);

        managePersons = new ManagePersons(canvas);
        Scanner scan = new Scanner(System.in);
        System.out.println("What is your sample size");
       allPersons = managePersons.generate(scan.nextInt());


    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();

    }

    private void run(){

        while (true){
            for(Person person: allPersons) {
                person.moveRandomly();
                managePersons.checkHealthStatus(person);
                managePersons.checkInfectedCollision(person);
            }
            canvas.draw();
            canvas.pause(10);
        }
    }

}
