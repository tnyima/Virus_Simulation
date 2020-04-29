import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

/** This class is responsible for managing interactions between persons, the includes  */
public class ManagePersons {

    private CanvasWindow canvas;
    private List<Person> allPersons = new ArrayList<>();
    public ManagePersons(CanvasWindow canvas){
        this.canvas = canvas;
    }

    /**Generates person and adds them to the canvas and to a list */
    public List<Person> generate(int numPeople){
        for (int i = 0; i < numPeople; i++ ){
            Person person = new Person(canvas);
            allPersons.add(person);
            canvas.add(person);
        }
        allPersons.get(0).makeInfected();
        return allPersons;
    }

    /** Checks if a persons collided with a person who is infected and changes
     *  their infected state and color if they do */
    public void checkInfectedCollision(Person person){
       if (!person.infected && !person.recovered){
           for (Person secondPerson  : allPersons){
               if (person.detectCollision() == secondPerson){
                 if (secondPerson.infected){
                     person.makeInfected();
                 }
               }

           }
       }

    }
    /** Updates the recovery time of a person, and change their status to recovered when their recovery
     * time reaches zero*/
    public void checkHealthStatus(Person person) {
        if (person.infected) {
            person.recoveryTime -= 1;
        }
        if (person.recoveryTime == 0) {
            person.makeRecovered();
        }
    }
}


