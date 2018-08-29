import javax.swing.*;
import java.awt.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;
import java.awt.image.BufferedImage;


public class Main {


    public static int cpuCount ;
    public static int queueCount ;

    public static ArrayList<CPU> cpus ;
    public static ArrayList<Queue> queues;

    public static EventList FEL;

    public static double systemTime;
    public static int numOfDepartures;

    public static VariateGenerator interarrivalGenerator ;
    public static VariateGenerator serviceTimeGenerator;
    public static VariateGenerator priorityGenerator;
    public static double [] arguments = new double[22];
    public static String interArrivalDistribution ;
    public static String serviceTimeDistribution ;
    public static String [] distributions = new String[2];


    public static Scene scene;

    public static ArrayList< Map.Entry <String , String> > report;

    public static void main(String[] args) {

/*
        JFrame frame = new JFrame();
        frame.setLayout(null);

        frame.setBounds(200,100,450,450 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.yellow);

      //  Image newImage = yourImage.getScaledInstance(newWidth, newHeight, Image.SCALE_DEFAULT);

        ImagePanel imagePanel = new ImagePanel("/Users/niushaalavi/Desktop/cpu.png", 50,50);
        imagePanel.setBackground(Color.pink);
        imagePanel.setBounds(0,0,100,100);
        frame.add(imagePanel);
        frame.repaint();
        frame.setVisible(true);
*/

   //     BufferedImage myPicture = ImageIO.read(new File("path-to-file"));
    //    JLabel picLabel = new JLabel(new ImageIcon(myPicture));

    StartMenu.menu( arguments , distributions);
       // start();



    }


    public static void start (){

        initialization();
        simulation();
    }


    public static void simulation() {
        Thread updateThread = new Thread() {
            @Override
            public void run() {
                while (systemTime < 100) {
                    Process process = FEL.poll();
                    goForward(process.getTime());

                    if ( process.getState() ==  PState.arrival )
                        arrival(process);
                    else
                        departure(process);
                }
            }
        };
        updateThread.start();
    }


    public static void arrival(Process process)

    {

        int queueIndex = process.getPriority();
        queues.get(queueIndex).enqueue(process);

        scene.addProcess(process.getId(),queueIndex);
        int cpuIndex = checkCPUs();
        if (cpuIndex > -1)
        {
            process.setServer(cpuIndex);
            queues.get(queueIndex).dequeue();
            scheduleDeparture(process);
        }

        //schedule next arrival

        Process nextProcess = new Process(priorityGenerator.nextInt(), interarrivalGenerator.nextDouble() + systemTime);
        FEL.add(nextProcess);


    }

    public static void scheduleDeparture(Process process)
    {

        double serviceTime = serviceTimeGenerator.nextDouble();
        process.setState(PState.departure);
        process.setTime(systemTime + serviceTime);

        int cpuIndex = process.getServer();
        cpus.get(cpuIndex).setBusy(true);
      //  System.out.println(cpuIndex + " " + systemTime);

        scene.serve(process.getId(),process.getPriority(),cpuIndex);
        FEL.add(process);


    }

    public static void departure(Process process) {

        int cpuIndex = process.getServer();
        cpus.get(cpuIndex).setBusy(false);

        int queueIndex = checkQueues();


        scene.leave(process.getId(),cpuIndex);

        if (queueIndex > -1) {
            Process nextProcess = (Process) queues.get(queueIndex).dequeue();
            nextProcess.setServer(cpuIndex);
            scheduleDeparture(nextProcess);
        }


        numOfDepartures++;

    }


    public static void goForward(double time)  {
        while (systemTime < time) {
            if (!Scene.PAUSE) {
                systemTime = Math.round((systemTime + 0.05) * 100d) / 100d;
                updateTotalResponseTimes();
                reportGenerator();
                scene.setReport(report);
            }
                try {
                    Thread.sleep(Scene.UPDATE_INTERVAL);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //    System.out.println(systemTime);

        }

    }

    public static void updateTotalResponseTimes() {
        for (int i = 0; i < cpuCount; i++) {
            if (cpus.get(i).isBusy() == true)
                cpus.get(i).setTotalResponseTime( Math.round((cpus.get(i).getTotalResponseTime() + 0.01) * 100d) / 100d);


        }
    }

    public static int checkQueues() {
        for (int i = 0; i < queueCount; i++) {
            if (queues.get(i).size() > 0)
                return i;
        }
        return -1;
    }

    public static int checkCPUs() {
        for (int i = 0; i < cpuCount; i++) {
            if (cpus.get(i).isBusy() == false)
                return i;
        }
        return -1;
    }





    public static void initialization() {

        systemTime = 0;
        numOfDepartures = 0;

        cpuCount = (int)arguments[20];
        queueCount = (int) arguments[21];


        interArrivalDistribution = distributions[0];
        serviceTimeDistribution = distributions[1];


        cpus = new ArrayList<>(cpuCount);
        queues = new ArrayList<>(queueCount);

        for (int i = 0; i < cpuCount; i++) {
            cpus.add(i, new CPU());
        }

        for (int i = 0; i < queueCount; i++) {
            queues.add(i, new Queue());
        }

        FEL = new EventList();



        scene = new Scene(cpuCount,queueCount);

        report = new ArrayList<>();
        report.add(new AbstractMap.SimpleEntry<>("System Time" , Double.toString(systemTime)));
        report.add(new AbstractMap.SimpleEntry<>("Number Of Departures" , Integer.toString(numOfDepartures)));

        for (int i = 1 ; i < cpuCount+1 ; i++)
            report.add(new AbstractMap.SimpleEntry<>("Total Response Time of CPU "+ i , Double.toString(cpus.get(i-1).getTotalResponseTime())));

        for (int i = 1; i < queueCount+1 ; i++)
            report.add(new AbstractMap.SimpleEntry<>("Max Length Of Queue "+ i , Integer.toString(queues.get(i-1).getMaxLength())));



        priorityGenerator = new DiscreteUniformGenerator(0, queueCount - 1);

      //  interarrivalGenerator = new ContinuousUniformGenerator(2,5);
     //   serviceTimeGenerator = new ContinuousUniformGenerator(5,8);

        System.out.println(cpuCount);
        System.out.println(queueCount);
        System.out.println(interArrivalDistribution);
        System.out.println(serviceTimeDistribution);
        System.out.println(arguments[0] + " "+ arguments[1]);


        switch (interArrivalDistribution)
        {
            case "Uniform" :
                interarrivalGenerator = new ContinuousUniformGenerator(arguments[0], arguments[1]);
                break;
            case "Erlang" :
                interarrivalGenerator = new ErlangGenerator((int)arguments[2], arguments[3]);
                break;
            case "Exponential" :
                interarrivalGenerator = new ExponentialGenerator(arguments[4]);
                break;
            case "Normal" :
                interarrivalGenerator = new NormalGenerator(arguments[5] , arguments[6]);
                break;
            case "Weibull" :
                interarrivalGenerator = new WeibullGenerator(arguments[7] , arguments[8] , arguments[9]);

        }
        switch (serviceTimeDistribution)
        {
            case "Uniform" :
                serviceTimeGenerator = new ContinuousUniformGenerator(arguments[10], arguments[11]);
                break;
            case "Erlang" :
                serviceTimeGenerator = new ErlangGenerator((int)arguments[12], arguments[13]);
                break;
            case "Exponential" :
                serviceTimeGenerator = new ExponentialGenerator(arguments[14]);
                break;
            case "Normal" :
                serviceTimeGenerator = new NormalGenerator(arguments[15] , arguments[16]);
                break;
            case "Weibull" :
                serviceTimeGenerator = new WeibullGenerator(arguments[17] , arguments[18] , arguments[19]);

        }
        int start = 0;
        for (int i = 0; i < cpuCount; i++) {
            start += interarrivalGenerator.nextDouble();
            Process initialProcess = new Process(priorityGenerator.nextInt(), start);
            FEL.add(initialProcess);
        }

    }


    public static void reportGenerator (){

        report.get(0).setValue(Double.toString(systemTime));
        report.get(1).setValue(Integer.toString(numOfDepartures));

        for (int i = 2 ; i < cpuCount +2 ; i++) {
            report.get(i).setValue(Double.toString(cpus.get(i-2).getTotalResponseTime()));
        }

        for (int i = cpuCount+2 ; i < cpuCount+queueCount+2 ; i++) {
            report.get(i).setValue(Integer.toString(queues.get(i-cpuCount-2).getMaxLength()));

        }

        /*

        System.out.println("TWO SERVER TWO QUEUE SYSTEM SIMULATION");
        System.out.println("\tNUMBER OF CARS SERVED " + numOfDepartures);


        for (int i = 0; i < cpuCount ; i++) {
            System.out.println("\t  UTILIZATION " + cpus.get(i).totalResponseTime / systemTime);
        }

        for (int i = 0; i < queueCount ; i++) {

            System.out.println("\t  MAX QUEUE LENGTH " + queues.get(i).getMaxLength() );
        }

*/
    }




}