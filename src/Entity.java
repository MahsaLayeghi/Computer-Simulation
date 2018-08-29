import java.awt.*;

abstract public class Entity implements Drawable{

    protected Point pos;
    protected int size;
    protected Color color;
    protected static int animation_steps = 10;


    public Entity(int x, int y, int size){
        this.pos = new Point(x,y);
        this.size = size;
    }

    abstract public Point getAfterPosition();

    public static void setAnimationSteps(int s){
        Entity.animation_steps = s;
    }


    abstract public void draw(Graphics g);

}
