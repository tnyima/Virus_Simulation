import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ManagePersons {

    private CanvasWindow canvas;
    private List<Person> allPersons = new ArrayList<>();
    private List<Susceptible> susceptibleList = new ArrayList<>();
    private List<VirusHost> virusHostList = new ArrayList<>();

    public ManagePersons(CanvasWindow canvas){
        this.canvas = canvas;
    }

    public List<Person> generate(int numPeople){
        VirusHost  virusHost = new VirusHost(canvas);
        allPersons.add(virusHost);
        virusHostList.add(virusHost);
        canvas.add(virusHost);

        for (int i = 0; i <= numPeople; i++ ){
            Susceptible susPerson = new Susceptible(canvas);
            allPersons.add(susPerson);
            susceptibleList.add(susPerson);
            canvas.add(susPerson);
        }
        return allPersons;
    }

    public void virusCollision(){
        for(Susceptible person: susceptibleList){
            for(VirusHost virusHost : virusHostList){
                if(person.detectCollision(virusHost)){
                    VirusHost newVirusHost = new VirusHost(canvas);
                    newVirusHost.setPosition(person.getPosition());
                    allPersons.add(newVirusHost);
                    canvas.add(newVirusHost);
                    canvas.remove(person);
//                    person.changeColor();

                }
            }
        }

    }





}
