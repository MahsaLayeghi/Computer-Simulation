import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class ReportCanvas extends JPanel {
    public ArrayList<Map.Entry<String,String>> report;
    private int height;
    private int width;

    public  ReportCanvas(int w, int h ){
        height = h;
        width = w;
        report = new ArrayList<>();
    }
    @Override
    public void paint(Graphics graphics){
        super.paintComponent(graphics);     // paint parent's background
        setBackground(Color.LIGHT_GRAY);  // set background color for this JPanel

        graphics.setFont(new Font("Monospaced", Font.PLAIN, 12));
        int row = height/(report.size()+2);
        for (int i=0; i< report.size(); i++) {
            Map.Entry item = report.get(i);
            graphics.setColor(Color.black);

            graphics.drawString(item.getKey() + " :", 15, (i+1)*row);
            graphics.setColor(Color.blue);
            graphics.drawString(item.getValue().toString(), width/2+65 , (i+1)*row);
        }
    }
}
