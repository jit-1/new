/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package technoshinex_4_gadgetguru;


import java.awt.event.*;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.util.TimerTask;
import java.util.Timer;

/**
 *
 * @author SHRIKANT
 */
public class GadgetGuru implements MouseListener,ActionListener
{
    
JFrame frame;
 JPanel panel1,panel6,panel7,panel3;
  JButton a1[]=new JButton[44];
  JRadioButton op1,op2,op3,op4;
  ButtonGroup bg1;
  TextArea textarea;
  JButton next,prev,lock,submit;

  JLabel time,imglabel;
  ImageIcon img;
  int m,s,minut=0;
  int Qnumber=0;
  int option=0;
  int xx=1;

  String client_Name;
  Socket client_socket=null;
  PrintWriter out=null;
  Timer tm;
    public GadgetGuru(Socket socket,String client_name)
    {
           client_Name=client_name;
           client_socket=socket;
            frame=new JFrame("Technosine X.4 - Gadget Guru");
        frame.setContentPane(new JLabel(new ImageIcon("image//y.jpg")));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setUndecorated(true);
        frame.setBounds(0, 0, screenSize.width, screenSize.height);

        //frame.setSize(32767,32767);
        frame.setLayout(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            JLabel jl1=new JLabel("Technosine X.4");
            jl1.setFont(new Font("arial",Font.BOLD,60));
            jl1.setForeground(Color.white);
            jl1.setBounds(420,10,550,80);
            frame.add(jl1);

            JLabel jl2=new JLabel("Gadget Guru");
            jl2.setFont(new Font("OCR A Extended",Font.BOLD,30));
            jl2.setForeground(Color.orange);
            jl2.setBounds(535,60,550,80);
            frame.add(jl2);



            panel1 = new JPanel();
            panel1.setLayout(null);
            //panel1.setBackground(Color.darkGray);
            panel1.setBackground(new Color(0,0,0,10));
            panel1.setSize(510, 400);
            panel1.setLocation(170, 170);
            frame.add(panel1);

            panel6 = new JPanel();
            panel6.setLayout(null);
            panel6.setBackground(new Color(0,0,0,65));
            panel6.setSize(1400, 100);
            panel6.setLocation(0, 610);

            panel7 = new JPanel();
            panel7.setLayout(null);
            panel7.setBackground(new Color(0,0,0,65));
            panel7.setSize(1250, 90);
            panel7.setLocation(50, 5);
            int x,y,y2;
            x=3 ;
            y=15;
            y2=5;


            for (int i = 0; i < 44; i++)
            {

                a1[i] = new JButton(Integer.toString(i+1));
                      if(i==20)
                      {
                       y=y+32;
                       x=3;
                       }

                       if(i<40)
                       {
                         a1[i].setBounds(x, y, 50, 30);
                         a1[i].setFont(new Font("Arial",Font.BOLD,15));
                         a1[i].addMouseListener(this);
                         a1[i].setToolTipText("Question No."+(i+1));
                         panel7.add(a1[i]);
                         x=x+52;
                         }
                       else
                       {
                          a1[i].setBounds(1070,y2,150,20);
                          a1[i].setText("");
                           y2=y2+21;
                           panel7.add(a1[i]);


                       }
             }
             a1[40].setText("Not Visited");
             a1[40].setToolTipText("not visited question ");

             a1[41].setText("Visited");
             a1[41].setBackground(Color.darkGray);
             a1[41].setForeground(Color.white);
             a1[41].setToolTipText("visited question");

             a1[42].setText("Current Position");
             a1[42].setBackground(Color.green);
             a1[42].setToolTipText("current position of question");

             a1[43].setText("Locked");
             a1[43].setBackground(Color.red);
             a1[43].setForeground(Color.white);
             a1[43].setToolTipText("current position of question");

             panel6.add(panel7);

             frame.add(panel6);





             textarea=new TextArea("",0,0,TextArea.SCROLLBARS_NONE);
             textarea.setBounds( 10,10,500,113);//setBounds(int x,int y,box width,box height)
             //textarea.setBackground(Color.darkGray);
             textarea.setBackground(new Color(0,50,50));
             textarea.setForeground(Color.white);
             textarea.setFont(new Font("Arial",Font.BOLD,18));
             textarea.setEditable(false);
             panel1.add(textarea);


            op1 = new JRadioButton();
            op1.setBounds(10, 130, 500, 30);
            op1.setFont(new Font("Arial",Font.BOLD,16));
            op1.setForeground(Color.white);
            op1.addActionListener(this);
            op1.setOpaque(true);
            op1.setBackground(new Color(0,20,20));
            panel1.add(op1);

            op2 = new JRadioButton();
            op2.setBounds(10, 164, 500, 30);
            op2.setFont(new Font("Arial",Font.BOLD,16));
            op2.addActionListener(this);
            op2.setOpaque(true);
            op2.setBackground(new Color(0,20,20));
            //op2.setBackground(Color.black);
            op2.setForeground(Color.white);
            panel1.add(op2);

            op3 = new JRadioButton();
            op3.setBounds(10, 198, 500, 30);
            op3.setFont(new Font("Arial",Font.BOLD,16));
            op3.addActionListener(this);
            op3.setOpaque(true);
            op3.setForeground(Color.white);
            op3.setBackground(new Color(0,20,20));
            //op3.setBackground(Color.black);
            panel1.add(op3);

            op4 = new JRadioButton();
            op4.setBounds(10, 232, 500, 30);
            op4.setFont(new Font("Arial",Font.BOLD,16));
            op4.addActionListener(this);
            op4.setOpaque(true);
            op4.setForeground(Color.white);
            op4.setBackground(new Color(0,20,20));
            //op4.setBackground(Color.black);
            panel1.add(op4);

            bg1 = new ButtonGroup();
            bg1.add(op1);
            bg1.add(op2);
            bg1.add(op3);
            bg1.add(op4);

            panel3=new JPanel();
            panel3.setBounds(10,300,500,40);
            panel3.setBackground(Color.darkGray);
            panel3.setBackground(new Color(0,0,0,0));
            panel1.add(panel3);

            prev=new JButton("Prev");
            prev.setBounds(10, 10, 125, 35);
            panel3.add(prev);
            prev.addActionListener(this);


            lock=new JButton("Lock");
            lock.setBounds(135, 10, 116, 35);
            panel3.add(lock);
            lock.addActionListener(this);

            next=new JButton("Next");
            next.setBounds(241, 10, 125, 35);
            panel3.add(next);
            next.addActionListener(this);

            JLabel line3=new JLabel();
            line3.setBounds(100,340,330,3);
            line3.setBackground(Color.yellow);
            line3.setOpaque(true);
            panel1.add(line3);

           submit=new JButton("Submit");
            submit.setBounds(600, 720, 100, 30);
            submit.setBackground(Color.yellow);
            frame.add(submit);
            submit.addActionListener(this);


            time=new JLabel("",JLabel.CENTER);
            time.setBounds(1060,48,300,73);
            //time.setOpaque(true);
            time.setFont(new Font("DigifaceWide",Font.BOLD,40));
            time.setBackground(Color.darkGray);
            time.setForeground(Color.GREEN);
            frame.add(time);

            JLabel contact=new JLabel("shrikantnitdgp@gmail.com");
            contact.setBounds(1130,120,300,20);
            contact.setFont(new Font("Arial",Font.BOLD,13));
            contact.setForeground(Color.white);
            frame.add(contact);

             imglabel = new JLabel("",JLabel.CENTER);
            img = new ImageIcon();

           try
           {
            out=new PrintWriter(client_socket.getOutputStream(),true);//to send data to server
            //out.println("hii server");
           }
           catch(Exception ee){}
           frame.setVisible(true);
            int start=openStartPosition();
            Qnumber=start;
             setColor(start);
             openDataBase(start);
             opentime();

            }//constructor




            int openStartPosition()
            {
                             ResultSet   rs;
                            Connection con;
                            int i=1;
                            try
                            {
                            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                            }
                            catch(ClassNotFoundException e)
                            {
                            JOptionPane.showMessageDialog(null,"error 1");
                            }

                             try
                             {
                             String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=DataBase\\answer.mdb;";
                             con=DriverManager.getConnection(connURL);
                             Statement  smt=con.createStatement();
                             rs=smt.executeQuery("select * from ANSWER;");
                             int s;
                              while(rs.next())
                              {
                                 s=rs.getInt(6);
                                 if(s==1)
                                 {
                                 break;
                                 }
                                 i++;
                              }
                             con.close();
                             rs.close();
                             smt.close();
                             }

                             catch(Exception e1){}
             return i;
            }


                         void opentime()
                        {
                             ResultSet   rs;
                            Connection con;
                            try
                            {
                            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                            }
                            catch(ClassNotFoundException e)
                            {
                            JOptionPane.showMessageDialog(null,"error 1");
                            }

                             try
                             {
                             String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=DataBase\\chat.mdb;";
                             con=DriverManager.getConnection(connURL);
                             Statement  smt=con.createStatement();
                             rs=smt.executeQuery("select minut,second from timing;");

                              if (rs.next())
                              {
                                s=Integer.parseInt(rs.getString(2));
                              }
                             con.close();
                             rs.close();
                             smt.close();

                             }

                             catch(Exception e1)
                             {//JOptionPane.showMessageDialog(null,"error in opentime function");
                             }
                             tm=new Timer();
                             tm.schedule(new timerclass(), 1,1000);

                    }


       public class timerclass extends TimerTask
      {

            public void run()
            {
                   int second=s%60;
                    minut=s/60;
                    if (second >= 10)
                    {
                          time.setText("" + minut + ":" + second);
                          sendToServer(client_Name,Qnumber,time.getText(),s);

                    }
                    else
                    {
                        time.setText("" + minut + ":" + "0"+second);
                        sendToServer(client_Name,Qnumber,time.getText(),s);

                        if (second == 0)
                        {
                            minut++;
                        }
                    }

              s++;

              if(s==3600)
              {
               frame.setVisible(false);
               frame.setEnabled(false);
               frame.dispose();


               new SubmitAnswer(client_Name);

              }

       }
       }
    void sendToServer(String client_Name,int Qnumber,String time,int s)
     {
                try
                {
                   out.println(client_Name+" "+Integer.toString(Qnumber)+" "+time);
                   //out.println(Integer.toString(Qnumber));
                   //out.println(time);
                 }
                catch(Exception e2)
                {
                }
               //update timing ...take backup for any type of error occurence during game
                try
                {
                 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                 String url = "jdbc:odbc:Driver={Microsoft Access Driver " + "(*.mdb)};DBQ=DataBase\\chat.mdb";
                 Connection con = DriverManager.getConnection(url);
                 Statement stm = con.createStatement();
                 int x=1;
                 String ss=Integer.toString(s);

                 stm.executeUpdate("update timing set second='" + ss + "' where ID=" + x);

                 stm.close();
                 con.close();
                }
                catch(Exception e3)
                {
                }



    }


    public void actionPerformed(ActionEvent event)
    {
           if(event.getSource() instanceof JButton)
           {
                    if(event.getSource()==prev)
                    {
                       Qnumber=Qnumber-1;
                       if(Qnumber<=0){Qnumber=1;}
                       openDataBase(Qnumber);

                    }
                    else if(event.getSource()==next)
                    {
                         Qnumber=Qnumber+1;
                     if(Qnumber>=40){Qnumber=40;}
                       openDataBase(Qnumber);

                    }
                    else if(event.getSource()==lock)
                    {
                     saveAnswer(Qnumber,option);
                      Qnumber=Qnumber+1;
                      openDataBase(Qnumber);

                    }
                    else if(event.getSource()==submit)
                    {
                      frame.setVisible(false);
                      frame.setEnabled(false);
                      frame.dispose();

                      new SubmitAnswer(client_Name);
                    }
           }
          else if(event.getSource() instanceof JRadioButton)
          {
                if(event.getSource()==op1)
                {
                  option=1;

                 }
                else if(event.getSource()==op2)
                {
                option=2;
                 }
                else if(event.getSource()==op3)
                {
                option=3;
                 }
                else if(event.getSource()==op4)
                {
                option=4;
                }
           }

    }




    void saveAnswer(int Qnumber,int option)
    {
       try
       {
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       String url = "jdbc:odbc:Driver={Microsoft Access Driver " + "(*.mdb)};DBQ=DataBase\\answer.mdb";
       Connection con = DriverManager.getConnection(url);
       Statement stm = con.createStatement();
       String lk="YES";
       stm.executeUpdate("update ANSWER set USER_ANS='" + option + "',LOCKED='" + lk + "' where ANS_ID=" + Qnumber);
       stm.close();
       con.close();
       }
       catch(Exception e2)
       {
       }
    }
    public void mouseClicked(MouseEvent ev)
    {

                 for(int i=0;i<40;i++)
                 {
                         if(ev.getSource()==a1[i])
                         {
                            Qnumber=Integer.parseInt(a1[i].getText());
                            openDataBase(Qnumber);


                          }
                  }



    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}

                        void openDataBase(int i)
                        {
                             ResultSet   rs;
                            Connection con;
                            //int id=Integer.parseInt(a1[i].getText());

                            try
                            {
                            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                            }
                            catch(ClassNotFoundException e)
                            {
                            JOptionPane.showMessageDialog(null,"error 1");
                            }

                             try
                             {
                             String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=DataBase\\chat.mdb;";
                             con=DriverManager.getConnection(connURL);
                             Statement  smt=con.createStatement();
                             rs=smt.executeQuery("select QES,O1,O2,O3,O4 from question where QES_ID=" + i + ";");

                              if (rs.next())
                              {
                              updateVisited(i);
                              setEnable();
                              textarea.setText(rs.getString(1));
                              op1.setText(rs.getString(2));
                              op2.setText(rs.getString(3));
                              op3.setText(rs.getString(4));
                              op4.setText(rs.getString(5));

                              setImage(i);
                              setColor(i);
                              setDisable(i);
                              updateCurrentPosition(i);

                              }
                             con.close();
                             rs.close();
                             smt.close();
                             }

                             catch(Exception e1){}


                    }


void setImage(int Q)
{

img = new ImageIcon("image\\Question\\" + Q + ".jpg");
imglabel.setBounds(690, 175, img.getIconWidth(), img.getIconHeight());
imglabel.setIcon(img);
frame.add(imglabel);
}


void setDisable(int Q)
{
 ResultSet   rs;
 Connection con;

 try
 {
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 }
 catch(ClassNotFoundException e)
 {
 JOptionPane.showMessageDialog(null,"error");
 }
 try
 {
 String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=DataBase\\answer.mdb;";
 con=DriverManager.getConnection(connURL);
 Statement  smt=con.createStatement();
 rs=smt.executeQuery("select * from answer where ANS_ID=" + (Q) + ";");
 if(rs.next())
 {
    String s=rs.getString(5);
    if(s.equals("YES"))
    {
      op1.setEnabled(false);
      op2.setEnabled(false);
      op3.setEnabled(false);
      op4.setEnabled(false);
      lock.setEnabled(false);

    }
 }

 }
 catch(Exception e){}
}

void setEnable()
{
op1.setEnabled(true);
op1.setSelected(false);
op2.setEnabled(true);
op2.setSelected(false);
op3.setEnabled(true);
op3.setSelected(false);
op4.setEnabled(true);
op4.setSelected(false);
lock.setEnabled(true);
}

void setColor(int b)
{
 ResultSet   rs;
 Connection con;
 try
 {
 Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 }
 catch(ClassNotFoundException e)
 {
 JOptionPane.showMessageDialog(null,"error");
 }
 try
 {
 String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=DataBase\\answer.mdb;";
 con=DriverManager.getConnection(connURL);
 Statement  smt=con.createStatement();
 rs=smt.executeQuery("select * from answer");
  int i=0;
  String vst,lck;
  while(rs.next())
 {

  vst=rs.getString(4);
  lck=rs.getString(5);

  if(lck.equals("YES"))
  {
         a1[i].setBackground(Color.red);
         a1[i].setForeground(Color.white);

  }

  if(vst.equals("YES")==true&&lck.equals("NO")==true)
   {
        a1[i].setBackground(Color.darkGray);
         a1[i].setForeground(Color.white);
   }

  i++;
 }
  con.close();
 }
  catch(SQLException  e)
  {
  JOptionPane.showMessageDialog(null,"error in play page");
  }


  for(int i=0;i<40;i++)
  {

        if((b-1)==i)
        {
         a1[i].setBackground(Color.green);
         a1[i].setForeground(Color.white);
        break;
        }
  }


}

void updateVisited(int Q)
{
     try
       {
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       String url = "jdbc:odbc:Driver={Microsoft Access Driver " + "(*.mdb)};DBQ=DataBase\\answer.mdb";
       Connection con = DriverManager.getConnection(url);
       Statement stm = con.createStatement();
       String lk="YES";

       stm.executeUpdate("update ANSWER set VISITED='" + lk + "' where ANS_ID=" + Q);
       stm.close();
       con.close();
       }
       catch(Exception e2)
       {
       }

}


void updateCurrentPosition(int Q)
{
     try
       {
       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       String url = "jdbc:odbc:Driver={Microsoft Access Driver " + "(*.mdb)};DBQ=DataBase\\answer.mdb";
       Connection con = DriverManager.getConnection(url);
       Statement stm = con.createStatement();
       int lk=1;
       int z=0;
       stm.executeUpdate("update ANSWER set CURRENT=" + z);
       stm.executeUpdate("update ANSWER set CURRENT=" + lk + " where ANS_ID=" + Q);
       stm.close();
       con.close();
       }
       catch(Exception e2)
       {
       }


}




}
