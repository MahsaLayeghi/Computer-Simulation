import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class DrawCanvas extends JPanel {

    public ArrayList<Drawable> items;
    public Image img;
    public DrawCanvas(){
        items = new ArrayList<>();
    }
    // Override paintComponent to perform your own painting
    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);     // paint parent's background
        g.drawImage(img,this.getX(),this.getY(),null);
        setBackground(Color.yellow);  // set background color for this JPanel

    /*    for (Drawable item : items) {
            item.draw(g);
        }
*/
        for (int i = 0; i < items.size() ; i++) {
            items.get(i).draw(g);
        }

    }
}