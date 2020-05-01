import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManagePersons {

    private CanvasWindow canvas;
    private List<Person> allPersons = new ArrayList<>();
    private double transmissionRate = 0.5;
    private Random ran = new Random();




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
        if (!person.infected && !person.recovered){
            for (Person secondPerson  : allPersons){
                if (person.detectCollision() == secondPerson){
                    if (secondPerson.infected){
                        double chance = ran.nextDouble();
                        System.out.println(secondPerson.infectiousPeriod);
                        if (secondPerson.infectiousPeriod > 0 && chance < 0.1 )
                            person.makeInfected();
                    }
                }

            }
        }

    }
    /** Updates the recovery time of a person, and change their status to recovered when their recovery
     * time reaches zero
     * @return*/
    public void checkHealthStatus(Person person) {
        if (person.infected) {
            int like = person.recoveryTime--;
            if (!(person.infectiousPeriod <= 0)) {
                person.infectiousPeriod--;
            }
        }
        if (person.recoveryTime == 0) {
            person.makeRecovered();
        }

    }

//    /***
//     * This generates the sample population.
//     * @param numPeople
//     * @return
//     */
//
//    public List<Person> generate(int numPeople){
//        for (int i = 0; i < numPeople; i++ ){
//            Person person = new Person(canvas);
//            allPersons.add(person);
//            canvas.add(person);
//        }
//        allPersons.get(0).makeInfected();
//
//        return allPersons;
//    }
//
//    /** Checks if a persons collided with a person who is infected and changes
//     *  their infected state and color if they do */
//    public void checkInfectedCollision(Person person){
//        if (person.getInfected()){
//            for (Person secondPerson : allPersons){
//                if (secondPerson.detectCollision() == person){
//                    secondPerson.makeInfected();
//                    System.out.println("Hello");
//                }
//            }
//
//        }
//
//    }
}


