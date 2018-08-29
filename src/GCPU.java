import java.awt.*;

public class GCPU extends Entity implements Drawable {
    boolean busy;
    int animation_index;
    public static Image img;

    public GCPU(int x, int y, int size){
        super( x, y, size);
        busy = false;
        color = new Color(255,255,255);
        animation_index = 0;
    }

    public void draw(Graphics graphics){
        graphics.drawImage(img,pos.x,pos.y,null);
        graphics.setColor(Color.CYAN);
        if(busy){
            graphics.setFont(new Font("Monospaced", Font.PLAIN, 12));
            graphics.drawString(((Integer)((animation_index)%2)).toString(), pos.x, pos.y-10);
            graphics.drawString(((Integer)((animation_index/3)%2)).toString(), pos.x+10, pos.y-10);
            graphics.drawString(((Integer)((animation_index/4)%2)).toString(), pos.x+20, pos.y-10);
            animation_index++;
        }
    }

    public Point getAfterPosition(){
        return new Point(pos.x + (int)(size * 1.25)  , pos.y);
    }
}
