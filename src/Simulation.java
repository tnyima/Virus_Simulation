import comp127graphics.CanvasWindow;
import comp127graphics.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// You need to add the org.knowm.xchart:xchart:3.6.1 library using maven so that these imports work.
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;



/**
 * This class runs the simulation.
 */
public class Simulation {

    private CanvasWindow canvas;
    private final static int WINDOW_HEIGHT = 800;
    private final static int WINDOW_WIDTH = 800;

    private ManagePersons managePersons;
    private List<Person> allPersons;
    private double transmissionRate;

    private long elapsedTime = 0;


    SwingWrapper<XYChart> sw;
    private List<Number> timeList = new ArrayList<>(),
            infectedNumList = new ArrayList<>(),
            recoveredNumList = new ArrayList<>();
    List<Person> allInfected;
    List<Person> allRecovered;

    private XYChart lineChart;


    /** Initializes canvas, uses scanner to ask user for transmission rate, infectious period, recovery time
     * and the sample size, which is used to generate person objects.  */
    public Simulation() {
        canvas = new CanvasWindow("Virus Simulation", WINDOW_WIDTH, WINDOW_HEIGHT);
        managePersons = new ManagePersons(canvas);

        System.out.println("This is a Virus Simulation.");
        System.out.println(" ============================== ");
        Scanner numPopulation = new Scanner(System.in);
        System.out.println("What is the sample size?: ");
        int sampleSize = numPopulation.nextInt();

        Scanner transRate = new Scanner(System.in);

        // Checks if user input is within the accepted range
        do{
            System.out.println("Enter a transmission rate between 0 and 1");
            transmissionRate = transRate.nextDouble();
        } while (transmissionRate > 1);

        Scanner recovered = new Scanner(System.in);
        System.out.println("Select the recovery time: ");
        int recoveryTime = recovered.nextInt();

        Scanner infectPeriod = new Scanner(System.in);
        System.out.println("Select the infectious Period of the Virus: ");
        int infectiousPeriod = infectPeriod.nextInt();

        // Generates person objects and stores them in the allPersons list.
        allPersons = managePersons.generate(sampleSize, recoveryTime, infectiousPeriod);
    }

    /** This is the main method that runs the simulation. */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();
    }


    /**
     * This method runs the program. It has a while loop that runs as long as the end condition is not met.
     * Keeps track of time within program using nanoTime.
     */
    private void run() {
        setUpLists();
        createGraph();
        long startTime = System.nanoTime() / 1000000000;
        while (!endCondition()) {
            updatePerson();
            long endTime = System.nanoTime() / 1000000000;
            elapsedTime = (endTime - startTime);
            setUpLists();
            updateGraph();
        }
    }

    /** It initializes the line chart and displays it on screen */
    public void createGraph() {
        lineChart = QuickChart.getChart("Infected people over time", "Time", "People",
                "infected",timeList, infectedNumList);
        lineChart.addSeries("recovered", timeList, recoveredNumList);
        sw = new SwingWrapper<XYChart>(lineChart);
        sw.displayChart();
    }

    /** It creates the lists necessary to create the graphs in the createGraph() method.*/
    public void setUpLists(){
        allInfected = allPersons.stream()
                .filter(person -> person.infected)
                .collect(Collectors.toList());

        allRecovered = allPersons.stream()
                .filter((person -> person.recovered))
                .collect(Collectors.toList());

        recoveredNumList.add(allRecovered.size());
        infectedNumList.add(allInfected.size());
        timeList.add(elapsedTime);
    }

    /** Updates the data of the line chart and updates the line chart so that the changes are displayed */
    public void updateGraph(){
        lineChart.updateXYSeries("infected",timeList,infectedNumList, null);
        lineChart.updateXYSeries("recovered", timeList, recoveredNumList, null);
        sw.repaintChart();
    }

    /** Updates the position of each person object randomly so that they move. Also, checks for
     * collision and health status */
    public void updatePerson() {
        for (Person person : allPersons) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            person.moveRandomly(canvasCenter);
            managePersons.checkHealthStatus(person, elapsedTime);
            managePersons.checkInfectedCollision(person, transmissionRate, elapsedTime);
        }
        canvas.draw();
        canvas.pause(10);
    }

    /** Checks if there are no more infected persons and returns result in boolean*/
    public boolean endCondition() {
        return allPersons.stream().noneMatch(person -> person.infected);
    }
}



