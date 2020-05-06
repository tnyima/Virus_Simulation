import comp127graphics.CanvasWindow;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/***
 * This class manages the people. It generates people for our simulation and checks the health status and whether
 * the people in the simulation had collided with an infected person.
 */
public class ManagePersons{
    private CanvasWindow canvas;
    private List<Person> allPersons = new ArrayList<>();
    private Random ran = new Random();
    public ManagePersons(CanvasWindow canvas){
        this.canvas = canvas;

    }

    /** Takes in a number and generates the sample population based of that number*/
    public List<Person> generate(int numPeople, long time, long infectiousPeriod){
        for (int i = 0; i < numPeople; i++ ){
            Person person = new Person(canvas);
            person.setRecoveredTime(time);
            person.setInfectiousPeriod(infectiousPeriod);
            allPersons.add(person);
            canvas.add(person);
        }
        allPersons.get(0).makeInfected(time);
        return allPersons;
    }

    /** Checks if a persons collided with a person who is infected and changes
     *  their infected state and color if they do */
    public void checkInfectedCollision(Person person, double transmissionRate, long time){
        if (!person.infected && !person.recovered){
            for (Person secondPerson  : allPersons){
                if (person.detectCollision() == secondPerson && secondPerson.infected){
                        double chance = ran.nextDouble();
                        if (secondPerson.infectiousPeriod > time && chance < transmissionRate) {
                            person.makeInfected(time);
                        }
                }
            }

        }
    }


    /** Updates the recovery time of a person, and change their status to recovered when their recovery
     * time reaches zero
     * @return*/
    public void checkHealthStatus(Person person, long currentTime, long startTime) {
         if(person.infected) {
             long timeWhenRecovered = person.infectedTime + (person.recoveryTime);
             if (timeWhenRecovered == currentTime) {
                 person.makeRecovered();
             }
         }
    }

}


