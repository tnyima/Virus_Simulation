import comp127graphics.CanvasWindow;
import comp127graphics.Point;
import java.util.List;
import java.util.Scanner;


import org.jfree.chart.*; // you have to add Jfree to your library to run have the graph method work
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;


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
    private int recoveryTime;
    private int infectiousPeriod;
    private long elapsedTime = 0;
    private DefaultCategoryDataset dataset;
    private long startTime, endTime;

    public Simulation() {
        canvas = new CanvasWindow("Virus Simulation", WINDOW_WIDTH, WINDOW_HEIGHT);
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
     *
     * @param args
     */
    public static void main(String[] args) {
        Simulation simulation = new Simulation();
        simulation.run();
    }


    /**
     * This method runs the program.
     */
    private void run() {
        startTime = System.nanoTime() / 1000000000;
        while (!endCondition()) {
            move();
            endTime = System.nanoTime() / 1000000000;
            elapsedTime = (endTime - startTime);
        }
    }

    public void addToDataSet(int numPersons, long time, String stringName) {
        dataset = new DefaultCategoryDataset();
        dataset.addValue(numPersons, stringName, String.valueOf(time));
    }

    public void graph() {
        JFreeChart lineChart = ChartFactory.createLineChart("Virus Simulation",
                "Time", "Number of People", dataset, PlotOrientation.VERTICAL,
                false, false, false);
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        ApplicationFrame frame = new ApplicationFrame("Virus Simulation");
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    public void move() {
        for (Person person : allPersons) {
            Point canvasCenter = new Point(canvas.getWidth() / 2.0, canvas.getHeight() / 2.0);
            person.moveRandomly(canvasCenter);
            managePersons.checkHealthStatus(person, elapsedTime, endTime);
            managePersons.checkInfectedCollision(person, transmissionRate, elapsedTime);
        }
        canvas.draw();
        canvas.pause(10);
    }

    public boolean endCondition() {
        return allPersons.stream().noneMatch(person -> person.infected);
    }
}


