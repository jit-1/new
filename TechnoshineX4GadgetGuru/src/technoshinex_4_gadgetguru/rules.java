/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package technoshinex_4_gadgetguru;


import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.awt.*;
import javax.swing.*;
import java.net.Socket;
import java.awt.event.*;
import java.io.*;
import java.util.TimerTask;
import java.util.Timer;


/**
 *
 * @author SHRIKANT
 */
public class rules implements ActionListener
{
   JPanel m;
    JLabel care;
    ImageIcon img;
    int i=30;
    Graphics g;
    JButton start,stop;
    JFrame jfrm;
    JTextArea a1;
    String client_Name;
    Socket client_socket;
    JLabel jl2;
     Thread t;
    Timer tm;
    int x=0,f=0;
    String IP;
    public rules()
    {



        jfrm=new JFrame();
        jfrm.setContentPane(new JLabel(new ImageIcon("image/z.jpg")));
        jfrm.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jfrm.setBounds(0, 0, screenSize.width, screenSize.height);

        jfrm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jfrm.setLayout(null);



         JLabel jl1=new JLabel("Technosine X.4");
            jl1.setFont(new Font("arial",Font.BOLD,70));
            jl1.setForeground(Color.white);
            jl1.setBounds(420,2,550,80);
            jfrm.add(jl1);

            jl2=new JLabel("Gadget Guru");
            jl2.setFont(new Font("OCR A Extended",Font.BOLD,50));
            jl2.setBounds(425,350,550,80);
            jl2.setForeground(Color.yellow);
            jfrm.add(jl2);

            JLabel jl3=new JLabel("Rules");
            jl3.setFont(new Font("Arial",Font.BOLD,20));
            jl3.setForeground(Color.red);
            jl3.setBounds(10,50,100,30);
            jfrm.add(jl3);

            a1=new JTextArea();
            a1.setFont(new Font("Arial",Font.BOLD,17));
            a1.setForeground(Color.white);
            a1.setBackground(new Color(0,0,0,0));
            a1.setLineWrap(true);
            a1.setWrapStyleWord(true);
            a1.setBounds(10,90,300,500);
            a1.setEditable(false);
            a1.setEditable(false);
            jfrm.add(a1);



        start=new JButton("START");
        start.setBounds(580,700,80,30);
        start.addActionListener(this);
        //start.setBackground(Color.GREEN);
        start.setForeground(Color.red);
        jfrm.add(start);

        stop=new JButton("EXIT");
        stop.setBounds(690,700,80,30);
        stop.addActionListener(this);
        //stop.setBackground(Color.GREEN);
        stop.setForeground(Color.red);
        jfrm.add(stop);

        care=new JLabel("please read instruction carefully...");
        care.setSize(300,50);
        care.setLocation(5,700);
        care.setForeground(Color.red);
        jfrm.add(care);
         t=new Thread();
        displayRule();
        tm=new Timer();
        tm.schedule(new timerclass(), 1,100);


        jfrm.setVisible(true);

        IP=JOptionPane.showInputDialog(null,"IP Address :");



    }
 void displayRule()
 {

   String fileName = "DataBase/rule.txt";
   String line = null;
   String content="";
   try {
       FileReader fileReader =  new FileReader(fileName);
       BufferedReader bufferedReader = new BufferedReader(fileReader);
       while((line = bufferedReader.readLine()) != null)
       {
          content=content+"\n"+line;

       }
       a1.setText(content);
       bufferedReader.close();
       }
    catch(Exception ex)
    {}


 }


public class timerclass extends TimerTask
{

  public void run()
  {
      jl2.setLocation(390+x,350);

      if(x%2==0)
      {
        care.setForeground(Color.red);
      }
      else
      {
        care.setForeground(Color.white);
      }

   if(x<=210 && f==0)
   {
      x=x+5;
   }

   if(x==210 )
   {
      f=1;
    }
   if(f==1)
   {
   x=x-5;
   }
   if(x==0)
   {
   f=0;
   }


  }


}


public void actionPerformed(ActionEvent e)
{

       if(e.getSource()==start)
       {
             jfrm.setVisible(false);
             jfrm.dispose();
            new register(IP);
       }
       else
       {
           if(e.getSource()==stop)
           System.exit(0);
       }

}
 
}
