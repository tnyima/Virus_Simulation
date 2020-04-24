import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;

import java.awt.*;


public class managePersons {

    private CanvasWindow canvas;
    private GraphicsGroup graphicsGroup;

    public managePersons(CanvasWindow canvas){
        this.canvas = canvas;

    }

    public GraphicsGroup generate(int numPeople){
        for (int i = 0; i <= numPeople; i++ ){
            Susceptible person = new Susceptible(canvas);
            canvas.add(person);
            graphicsGroup.add(person);
        }
        return graphicsGroup;
    }
}
