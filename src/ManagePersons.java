import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

public class ManagePersons {

    private CanvasWindow canvas;
    private List<Person> allPersons = new ArrayList<>();



    public ManagePersons(CanvasWindow canvas){
        this.canvas = canvas;
    }

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
       if (!person.infected){
           for (Person secondPerson  : allPersons){
               if (person.detectCollision() == secondPerson){
                 if (secondPerson.infected){
                     person.makeInfected();
                 }
               }

           }
       }

    }
}


