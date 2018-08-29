
import java.awt.Graphics;
        import java.awt.image.BufferedImage;
        import java.awt.Image;
        import java.io.File;
        import java.io.IOException;
        import java.util.logging.Level;
        import java.util.logging.Logger;
        import javax.imageio.ImageIO;
        import javax.swing.JPanel;


public class ImagePanel extends JPanel{

    private BufferedImage image;
    private Image scaledImage;

    public ImagePanel(String s ,int  newWidth, int newHeight) {
        try {
            image = ImageIO.read(new File(s));
            scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(scaledImage, 0, 0, this); // see javadoc for more info on the parameters
    }

}