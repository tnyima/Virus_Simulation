import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;

import java.util.ArrayList;
import java.util.List;


public class managePersons {

    private CanvasWindow canvas;
    private List<Susceptible> allPersons = new ArrayList<>();

    public managePersons(CanvasWindow canvas){
        this.canvas = canvas;

    }

    public List<Susceptible> generate(int numPeople){
        for (int i = 0; i <= numPeople; i++ ){
            Susceptible person = new Susceptible(canvas);
            allPersons.add(person);
            canvas.add(person);

        }
        return allPersons;
    }
}
