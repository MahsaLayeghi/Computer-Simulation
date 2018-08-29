import javafx.util.Pair;

import java.awt.*;
import java.util.*;
import java.util.Queue;

public class GProcess extends Entity implements Drawable {
    public int id;
    private int type;
    private Point velocity;
    private Point final_dest;
    private Queue<Map.Entry<Point,Integer>> destinations;
    public static Image img;


    public GProcess(int id, int type, int x, int y, int size){
        super(x, y, size);
        this.id = id;
        this.type =type;
        this.velocity = new Point(0,0);
        this.final_dest = new Point(x,y);
        destinations = new LinkedList<>();
        setColorbyType();
    }

    public void setColorbyType(){
        int r = 0;
        if(type % 2 == 0) r = 255;
        int g = 0;
        if( (type % 6) > 0 && (type % 6) < 4 ) g = 255;
        int b = 0;
        if ((type % 6) > 2) b = 255;
        this.color = new Color(r,g,b);
        if(type == -1)
            this.color = new Color(0,0,0);
    }

    public Point getAfterPosition(){
        return new Point(final_dest.x + (int)(1.25*size), final_dest.y);
    }

    public void move(){
        if ( !destinations.isEmpty()) {
            Map.Entry<Point,Integer> d = destinations.peek();
            if(d.getValue() == 1){
                pos.setLocation(d.getKey());
                destinations.remove();
            }else {
                velocity.setLocation((d.getKey().x - pos.x)/d.getValue(),(d.getKey().y - pos.y)/d.getValue());
                pos.translate(velocity.x, velocity.y);
                d.setValue(d.getValue()-1);
            }
        }
    }

    public void addDest(Entity dest){
        destinations.add(new AbstractMap.SimpleEntry<>(dest.getAfterPosition(),animation_steps));
        final_dest = dest.getAfterPosition();
    }

    public void addDest(Point p){
        destinations.add(new AbstractMap.SimpleEntry<>(p,animation_steps));
        final_dest = p;
    }

    public void goForward(){
            addDest(new Point(final_dest.x - (int) (size * 1.25), final_dest.y));
    }

    public void draw(Graphics graphics){

        graphics.setColor(color);
        graphics.fillRect(pos.x ,(int)(pos.y +0.1*size) ,(int)(0.9 * size), (int)(0.8 * size));
        graphics.drawImage(img,pos.x,pos.y,null);
    }
}
