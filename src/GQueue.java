import java.awt.*;
import java.util.ArrayList;

public class GQueue extends Entity implements  Drawable{

    private ArrayList<GProcess> processes;
    private static int MAX_VISIBLE = 10;

    public GQueue(int x, int y, int size){
        super( x, y, size);
        processes = new ArrayList<>();
        this.color = new Color(128,128,128);
    }

    public void draw(Graphics graphics){
        graphics.setColor(color);
        graphics.fillRect(pos.x,pos.y,2,size);
        graphics.setFont(new Font("Monospaced", Font.PLAIN, 12));
        graphics.drawString(((Integer)processes.size()).toString(), pos.x, pos.y-10);
        if(processes.size() > MAX_VISIBLE){
            Point after = getAfterPosition();
            graphics.drawString("...", after.x+50, after.y + size/2);
        }
    }

    public void addProcess(GProcess p){
        processes.add(p);
    }

    public Point getAfterPosition(){
        if (processes.isEmpty())
            return new Point(pos.x + 10  , pos.y);
        if(processes.size() >= MAX_VISIBLE)
            return processes.get(MAX_VISIBLE-2).getAfterPosition();
        return processes.get(processes.size()-1).getAfterPosition();
    }

    public GProcess dequeue(){
        if(!processes.isEmpty()){
            GProcess p =  processes.remove(0);
            int last = Math.min(processes.size(),MAX_VISIBLE-1);
            for (int i=0; i < last; i++ ) {
                processes.get(i).goForward();
            }
            return p;
        }else
            System.out.println("Request for dequeue an empty queue !");
        return null;
    }
}
