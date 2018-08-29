

import java.awt.*;       // Using AWT's Graphics and Color
import java.awt.event.*; // Using AWT event classes and listener interfaces
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.*;    // Using Swing's components and containers


/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Scene extends JFrame {
    // Define constants
    public static final int WIDTH  = 1200;
    public static final int HEIGHT = 700;
    public static int UPDATE_INTERVAL = 50; //milliseconds
    public static boolean PAUSE = false;
    private int process_size;
    private int can_width;
    private int can_height;
    private ArrayList<GProcess> processes = new ArrayList<>() ;
    private ArrayList<GCPU> cpus;
    private ArrayList<GQueue> queues;


    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;
    private ReportCanvas reportCanvas;

    // Constructor to set up the GUI components and event handlers
    public Scene(int cpu_num, int q_num) {

        can_width = WIDTH - 400;
        can_height = HEIGHT - 200;

        cpus = new ArrayList<>();

        int cpu_size = Math.min(50, (int)((can_height / cpu_num) * 0.8));
        int cpu_margin = (can_height - cpu_size * cpu_num) / (cpu_num + 1);

        try {
            GCPU.img = ImageIO.read(new File("/Users/niushaalavi/IdeaProjects/FinalProject/src/CPU.png")).getScaledInstance(cpu_size,cpu_size,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }


        for(int i = 0 ; i < cpu_num ; i++){
            GCPU tmp = new GCPU(10, cpu_margin + i * (cpu_size + cpu_margin), cpu_size);
            cpus.add(tmp);
        }

        process_size = (can_width - (30 + cpu_size)) / 20;
        try {
            GProcess.img = ImageIO.read(new File("/Users/niushaalavi/IdeaProjects/FinalProject/src/process.png")).getScaledInstance(process_size,process_size,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        queues = new ArrayList<>();
        int q_size = Math.min(50, (int)((can_height / q_num) * 0.8));
        int q_margin = (can_height - q_size * q_num)/(q_num + 1);
        for(int i = 0 ; i < q_num ; i++){
            queues.add(new GQueue(10 + cpu_size + 2 * process_size + 20, q_margin + i * (q_size + q_margin), q_size));
        }




        JPanel btnPanel = new JPanel(new FlowLayout());
        JButton btnLeft = new JButton("Slower");
        btnPanel.add(btnLeft);
        btnLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(UPDATE_INTERVAL < 100)
                    UPDATE_INTERVAL += 5;
                requestFocus();
            }
        });
        JButton btnRight = new JButton("Faster");
        btnPanel.add(btnRight);
        btnRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(UPDATE_INTERVAL > 10)
                    UPDATE_INTERVAL -= 5;
                requestFocus();
            }
        });
        JButton btnPause = new JButton("Pause");
        btnPanel.add(btnPause);
        btnPause.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                if(PAUSE){
                    PAUSE = false;
                    btnPause.setText("Pause");
                }else{
                    PAUSE = true;
                    btnPause.setText("Resume");
                }
                requestFocus();
            }
        });

        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(can_width, can_height));

        canvas.items.addAll(cpus);
        canvas.items.addAll(queues);
        try {
            canvas.img = ImageIO.read(new File("/Users/niushaalavi/IdeaProjects/FinalProject/src/can.png")).getScaledInstance(can_width,can_height,Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        reportCanvas = new ReportCanvas(400,can_height);
        reportCanvas.setPreferredSize(new Dimension(400, can_height));

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(canvas, BorderLayout.CENTER);
        cp.add(reportCanvas,BorderLayout.EAST);
        cp.add(btnPanel, BorderLayout.SOUTH);


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Simulation");
        setVisible(true);


        Thread updateThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if(!PAUSE) {
                        update();   // update the (x, y) position
                        repaint();  // Refresh the JFrame. Called back paintComponent()
                    }
                    try {
                        // Delay and give other thread a chance to run
                        Thread.sleep(UPDATE_INTERVAL);  // milliseconds
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        };
        updateThread.start();

    }

    public void update() {
       // for (GProcess p: processes) {
         //   p.move();
    //}

        for (int i = 0; i < processes.size() ; i++) {
                processes.get(i).move();
        }

    }

    public void addProcess(int id, int type){
        GProcess p = new GProcess(id,type,can_width - process_size,can_height/2 - process_size,process_size);
        p.addDest(queues.get(type));
        queues.get(type).addProcess(p);
        processes.add(p);
        canvas.items.add(p);
    }

    public void serve(int process_id, int queue_id, int cpu_id){
        GQueue q = queues.get(queue_id);
        GProcess p = q.dequeue();
        if (p != null){
            if(p.id != process_id)
                System.out.println("Unmatched process id in serve() !");
            GCPU c = cpus.get(cpu_id);
            p.addDest(c);
            c.busy = true;
        }

    }

    public  void leave (int process_id, int cpu_id){
        cpus.get(cpu_id).busy = false;
        int index = -1;
        for(int i=0; i<processes.size(); i++ ){
            if(processes.get(i).id == process_id){
                index = i;
                break;
            }
        }
        if(index == -1)
            System.out.println("Process already left!");
        canvas.items.remove(processes.get(index));
        processes.remove(index);

    }

    public void setReport(ArrayList<Map.Entry<String,String>> r){
        reportCanvas.report.clear();
        reportCanvas.report.addAll(r);
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */


}