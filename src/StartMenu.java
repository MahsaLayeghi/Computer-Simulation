
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;

public class StartMenu {


  public static   JComboBox queueComboBox ;
  public static   JComboBox cpuComboBox;
  public static   JComboBox interArrivalComboBox;
  public static   JComboBox serviceTimeComboBox;

  public static   JFormattedTextField iaText;
  public static   JFormattedTextField  ibText;
  public static   JFormattedTextField  ikText;
  public static   JFormattedTextField  itetaText;
  public static   JFormattedTextField  ilambdaText;
  public static   JFormattedTextField  iaverageText;
  public static   JFormattedTextField  ivariansText;
  public static   JFormattedTextField  ialphaText;
  public static   JFormattedTextField  ibetaText;
  public static   JFormattedTextField  ivText;

    public static   JFormattedTextField saText;
    public static   JFormattedTextField  sbText;
    public static   JFormattedTextField  skText;
    public static   JFormattedTextField  stetaText;
    public static   JFormattedTextField  slambdaText;
    public static   JFormattedTextField  saverageText;
    public static   JFormattedTextField  svariansText;
    public static   JFormattedTextField  salphaText;
    public static   JFormattedTextField  sbetaText;
    public static   JFormattedTextField  svText;



    public static void menu ( double [] array , String [] strings)
    {

        JFrame frame = new JFrame("Menu");
        frame.setLayout(null);

        frame.setBounds(200,100,450,450 );
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.lightGray);
        frame.setVisible(true);


        Font headingFont = new Font("Arial",Font.BOLD,20);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);

        JLabel heading = new JLabel();
        heading.setBounds(250,5,200,40);
        heading.setText("<html><font><u><b>Menu</b></u></html>");
        heading.setFont(headingFont);


        JPanel counts = new JPanel();
        counts.setBounds(0,300,270,150);
        counts.setBackground(Color.lightGray);

        JLabel queueCount_lbl = new JLabel("Number of Queues : ");
        queueCount_lbl.setBounds(10,10,150,30);


        JLabel cpuCount_lbl = new JLabel("Number of CPUs : ");
        cpuCount_lbl.setBounds(10, 50 ,150,30);

        Integer[] numbers = new Integer[] {1,2,3,4,5,6,7,8,9,10};

        queueComboBox = new JComboBox(numbers);
        queueComboBox.setCursor(cursor);
        queueComboBox.setBounds(150,12,100,30);
        queueComboBox.doLayout();


        cpuComboBox = new JComboBox(numbers);
        cpuComboBox.setCursor(cursor);
        cpuComboBox.setBounds(150,52,100,30);
        cpuComboBox.doLayout();



        JPanel distributions = new JPanel();
        distributions.setBounds(0,0,450,300);


        JPanel choosingPanel = new JPanel();
        choosingPanel.setBounds(0,0,250,300);
        choosingPanel.setBackground(Color.lightGray);



        JPanel interArrivalArguments = new JPanel();
        interArrivalArguments.setBounds(250,0,200,150);
        interArrivalArguments.setBackground(Color.lightGray);

        JPanel serviceTimeArguments = new JPanel();
        serviceTimeArguments.setBounds(250,150,200,150);
        serviceTimeArguments.setBackground(Color.lightGray);



        JLabel interArrival_lbl = new JLabel("InterArrival : ");
        interArrival_lbl.setBounds(10,10,100,30);


        JLabel serviceTime_lbl = new JLabel("Service Time : ");
        serviceTime_lbl.setBounds(10, 160 ,100,30);



        String[] dist = new String[] { "Uniform" , "Erlang" , "Exponential" , "Normal" , "Weibull" };

        interArrivalComboBox = new JComboBox(dist);
        interArrivalComboBox.setCursor(cursor);
        interArrivalComboBox.setBounds(100,12,150,30);
        interArrivalComboBox.doLayout();


        serviceTimeComboBox = new JComboBox(dist);
        serviceTimeComboBox.setCursor(cursor);
        serviceTimeComboBox.setBounds(100,162,150,30);
        serviceTimeComboBox.doLayout();



        MaskFormatter doubleFormatter = null;
        try {
            doubleFormatter = new MaskFormatter("###.##");
        } catch (java.text.ParseException exc) {
            System.err.println("doubleFormatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        doubleFormatter.setPlaceholderCharacter('0');


        MaskFormatter intFormatter = null;
        try {
            intFormatter = new MaskFormatter("###");
        } catch (java.text.ParseException exc) {
            System.err.println("doubleFormatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        intFormatter.setPlaceholderCharacter('0');

        saText = new JFormattedTextField(doubleFormatter);
        saText.setBounds(65,10,100,30);


        sbText = new JFormattedTextField(doubleFormatter);
        sbText.setBounds(65,50,100,30);

        skText = new JFormattedTextField(intFormatter);
        skText.setBounds(65,10,100,30);


        stetaText = new JFormattedTextField(doubleFormatter);
        stetaText.setBounds(65,50,100,30);

        slambdaText = new JFormattedTextField(doubleFormatter);
        slambdaText.setBounds(65,10,100,30);


        saverageText = new JFormattedTextField(doubleFormatter);
        saverageText.setBounds(65,10,100,30);

        svariansText = new JFormattedTextField(doubleFormatter);
        svariansText.setBounds(65,50,100,30);


        salphaText = new JFormattedTextField(doubleFormatter);
        salphaText.setBounds(65,10,100,30);


        sbetaText = new JFormattedTextField(doubleFormatter);
        sbetaText.setBounds(65,50,100,30);
        svText = new JFormattedTextField(doubleFormatter);
        svText.setBounds(65,90,100,30);




        iaText = new JFormattedTextField(doubleFormatter);
        iaText.setBounds(65,10,100,30);


        ibText = new JFormattedTextField(doubleFormatter);
        ibText.setBounds(65,50,100,30);

        ikText = new JFormattedTextField(intFormatter);
        ikText.setBounds(65,10,100,30);


        itetaText = new JFormattedTextField(doubleFormatter);
        itetaText.setBounds(65,50,100,30);

        ilambdaText = new JFormattedTextField(doubleFormatter);
        ilambdaText.setBounds(65,10,100,30);


        iaverageText = new JFormattedTextField(doubleFormatter);
        iaverageText.setBounds(65,10,100,30);

        ivariansText = new JFormattedTextField(doubleFormatter);
        ivariansText.setBounds(65,50,100,30);


        ialphaText = new JFormattedTextField(doubleFormatter);
        ialphaText.setBounds(65,10,100,30);


        ibetaText = new JFormattedTextField(doubleFormatter);
        ibetaText.setBounds(65,50,100,30);
        ivText = new JFormattedTextField(doubleFormatter);
        ivText.setBounds(65,90,100,30);


        choosing(interArrivalArguments,interArrivalComboBox , iaText , ibText , ikText ,
                itetaText , ilambdaText , iaverageText , ivariansText ,
                ialphaText , ibetaText , ivText );
        interArrivalComboBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                choosing(interArrivalArguments,interArrivalComboBox , iaText , ibText , ikText ,
                          itetaText , ilambdaText , iaverageText , ivariansText ,
                          ialphaText , ibetaText , ivText );
            }
        });

        choosing(serviceTimeArguments,serviceTimeComboBox , saText , sbText , skText ,
                stetaText , slambdaText , saverageText , svariansText ,
                salphaText , sbetaText , svText );
        serviceTimeComboBox.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                choosing(serviceTimeArguments,serviceTimeComboBox , saText , sbText , skText ,
                        stetaText , slambdaText , saverageText , svariansText ,
                        salphaText , sbetaText , svText );
            }
        });


        JPanel startPanel = new JPanel();
        startPanel.setBounds(270,300,180,150);
        startPanel.setBackground(Color.lightGray);
        JButton start = new JButton("Start Simulation");
        start.setBounds(0,20,150,50);
        start.setBackground(Color.lightGray);
        start.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                fillArray( array ,strings);

           //     frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                frame.dispose();
                Main.start();

            }
        });

        startPanel.add(start);
        counts.add(queueCount_lbl);
        counts.add(cpuCount_lbl);
        counts.add(cpuComboBox);
        counts.add(queueComboBox);


        choosingPanel.add(interArrival_lbl);
        choosingPanel.add(serviceTime_lbl);
        choosingPanel.add(interArrivalComboBox);
        choosingPanel.add(serviceTimeComboBox);

        distributions.add(choosingPanel);
        distributions.add(interArrivalArguments);
        distributions.add(serviceTimeArguments);


        frame.add(counts);
      //  frame.add(heading);
        frame.add(distributions);
        frame.add(startPanel);
        frame.repaint();



    }


    public static void choosing( JPanel arguments , JComboBox box ,JFormattedTextField aText ,
                                 JFormattedTextField bText , JFormattedTextField kText , JFormattedTextField tetaText ,
                                 JFormattedTextField lambdaText , JFormattedTextField averageText , JFormattedTextField variansText ,
                                 JFormattedTextField  alphaText ,JFormattedTextField betaText , JFormattedTextField vText  )
    {

        arguments.removeAll();
        arguments.repaint();


        if (box.getSelectedItem() == "Uniform")
        {

            JLabel a = new JLabel(" a ");
            a.setBounds(10,10,100,30);




            JLabel b = new JLabel(" b ");
            b.setBounds(10,50,100,30);


            arguments.add(a);
            arguments.add(aText);
            arguments.add(b);
            arguments.add(bText);

        }

        else if (box.getSelectedItem() == "Erlang") {

            JLabel k = new JLabel(" k ");
            k.setBounds(10,10,100,30);

            JLabel teta = new JLabel(" teta ");
            teta.setBounds(10,50,100,30);


            arguments.add(k);
            arguments.add(kText);
            arguments.add(teta);
            arguments.add(tetaText);


        }
        else if (box.getSelectedItem() == "Exponential")
        {
            JLabel lambda = new JLabel(" lambda ");
            lambda.setBounds(10,10,100,30);



            arguments.add(lambda);
            arguments.add(lambdaText);

        }
        else if (box.getSelectedItem() == "Normal")
        {

            JLabel average = new JLabel(" average ");
            average.setBounds(10,10,100,30);


            JLabel varians = new JLabel(" varians ");
            varians.setBounds(10,50,100,30);

            arguments.add(average);
            arguments.add(averageText);
            arguments.add(varians);
            arguments.add(variansText);





        }
        else if (box.getSelectedItem() == "Weibull")
        {

            JLabel alpha = new JLabel(" alpha ");
            alpha.setBounds(10,10,100,30);


            JLabel beta = new JLabel(" beta ");
            beta.setBounds(10,50,100,30);


            JLabel v = new JLabel(" v ");
            v.setBounds(10,90,100,30);




            arguments.add(alpha);
            arguments.add(alphaText);
            arguments.add(beta);
            arguments.add(betaText);
            arguments.add(v);
            arguments.add(vText);
        }

        arguments.repaint();
    }



   public static void fillArray(double [] array ,  String [] strings)
   {


           array[20] =  (Integer) cpuComboBox.getSelectedItem();
           array[21] = (Integer) queueComboBox.getSelectedItem();

           strings[0] = (String) interArrivalComboBox.getSelectedItem();
           strings[1] = (String) serviceTimeComboBox.getSelectedItem();

           array[0] =  checkValue( iaText.getValue());
           array[10] = checkValue( saText.getValue());

           array[1] =  checkValue( ibText.getValue());
           array[11] =  checkValue( sbText.getValue());


           array[2] = checkValue( ikText.getValue());
           array[12] = checkValue(skText.getValue());

           array[3] = checkValue( itetaText.getValue());
           array[13] = checkValue( stetaText.getValue());

           array[4] = checkValue(ilambdaText.getValue());
           array[14] = checkValue( slambdaText.getValue());

           array[5] = checkValue( iaverageText.getValue());
           array[15] = checkValue( saverageText.getValue());

           array[6] = checkValue( ivariansText.getValue());
           array[16] =checkValue( svariansText.getValue());

           array[7] = checkValue(ialphaText.getValue());
           array[17] =checkValue( salphaText.getValue());

           array[8] = checkValue( ibetaText.getValue());
           array[18] =checkValue( sbetaText.getValue());

           array[9] = checkValue( ivText.getValue());
           array[19] =checkValue( svText.getValue());



/*
       for (int i = 0; i < 20 ; i++) {
           System.out.println(i + " " + array[i]);
       }

       System.out.println(interArrival + " " + serviceTime + " " + cpuCount + " "+ queueCount);
*/
   }

  public static double checkValue (Object v)
   {
       if (v == null)
           return 0;
       else return Double.parseDouble(v.toString());

   }


   }




















