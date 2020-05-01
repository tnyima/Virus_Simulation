import comp127graphics.CanvasWindow;
import comp127graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;


public class Simulation {

    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH =  800;




    private ManagePersons managePersons;

    private List<Person> allPersons;
    private int time;



    public Simulation(){

        canvas = new CanvasWindow("Virus Simulation",WINDOW_WIDTH,WINDOW_HEIGHT);



        managePersons = new ManagePersons(canvas);
        System.out.println("This is a Virus Simulation.");
        System.out.println(" ============================== ");
        Scanner numPopulation = new Scanner(System.in);
        System.out.println("What is the sample size?: ");
        int sampleSize = numPopulation.nextInt();

//        Scanner recoveryTime = new Scanner(System.in);
//        System.out.println("What should the recovery time be? : ");
//        time = recoveryTime.nextInt();


       allPersons = managePersons.generate(sampleSize);


    }

    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();

    }

    private void run(){

        while (true){
            for(Person person: allPersons) {
                Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
                person.moveRandomly(canvasCenter);
                managePersons.checkHealthStatus(person);
                managePersons.checkInfectedCollision(person);
            }
            canvas.draw();
            canvas.pause(10);
        }
    }

}
