/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package technoshinex_4_gadgetguru_server;

/**
 *
 * @author SHRIKANT
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
class server1
{


       int port=9090;
       int x=50;
       int y=70,i=1;
       JFrame frame;

       JLabel l;
       TextArea [] textarea=new TextArea[20];
        public void runServer() throws IOException
        {
        frame=new JFrame("Technosine X.4 - Gadget Guru - Client Status");
        frame.setContentPane(new JLabel(new ImageIcon("image//y.jpg")));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setUndecorated(true);
        frame.setBounds(0, 0, screenSize.width, screenSize.height);
        frame.setBackground(Color.DARK_GRAY);
        frame.setLayout(null);
        frame.setVisible(true);
        l=new JLabel("Conected Clients");
        l.setBounds(600,5,200, 50);
        l.setFont(new Font("Arial",Font.BOLD,20));
        //l.setOpaque(true);
        //l.setBackground(Color.DARK_GRAY);
        l.setForeground(Color.white);
        frame.add(l);

        int xx=50,yy=100;
        for(int j=0;j<20;j++)
        {

            textarea[j]=new TextArea("",100,980,TextArea.SCROLLBARS_VERTICAL_ONLY);
             textarea[j].setBounds( xx,yy,200,100);//setBounds(int x,int y,box width,box height)
             textarea[j].setBackground(Color.darkGray);
             textarea[j].setForeground(Color.white);
             textarea[j].setFont(new Font("Arial",Font.BOLD,18));
             textarea[j].setEditable(false);
             frame.add(textarea[j]);
             xx=xx+210;
             if(xx>1160)
             {
             xx=50;
             yy=yy+150;
             }


       }

        ServerSocket serverSocket=new ServerSocket(port);
        System.out.println("Server is ready for connection...");

                while(true)
                {
                    Socket socket=serverSocket.accept();
                    new ServerThread1(socket,frame,x,y,i,textarea).start();
                    //System.out.println("server1 class");

                    y=y+70;
                    i=i+1;


                }
        }
}

class ServerThread1 extends Thread
{
JFrame frm;
JLabel l;
TextArea textarea[];
int x,y,i;


        Socket socket;
        ServerThread1(Socket socket,JFrame frame,int x,int y,int i,TextArea[] area)
        {
            this.socket=socket;
            this.frm=frame;
            this.x=x;
            this.y=y;
            this.i=i;
            this.textarea=area;
        }

         public void run()
         {
                try
                {
                   String msg=null;
                   String name=null;
                   String Qnumber=null;
                   String time=null;



                    //PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
                    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    name=bufferedReader.readLine();
                    System.out.println("client "+name+" is now connected to server...");
                    //System.out.println("inside run");

                     //put code here for check user already exist in databse or not...if exist then only update  exicute otherwise insert into table
                    try
                    {
                       Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                       String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=database.mdb;";
                       Connection con = DriverManager.getConnection(connURL);
                       Statement stmt = con.createStatement();
                       String clientId=Integer.toString(i);
                       String t="0:0";
                       String p="0";
                       stmt.executeQuery("INSERT INTO record VALUES ('"+clientId+"','"+name+"','"+t+"','"+p+"')");
                       con.close();
                    }
                    catch(Exception e1)
                    {
                            //javax.swing.JOptionPane.showMessageDialog(null,"Insertion Error");
                    }

                while(true)
                {
                 msg=bufferedReader.readLine();
                 //System.out.println("massage  " + msg);
                 String[] word=msg.split(" ");
                 name=word[0];
                 Qnumber=word[1];
                 time=word[2];
                 updateDatabase(name,Qnumber,time);
                 displayProgress();
                 }
                 //socket.close();
                 }
                 catch(IOException e)
                 {
                 e.printStackTrace();
                 }



    }//run

    void updateDatabase(String clientname,String Qtnumber,String timing)
    {

                  try
                 {
                      Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                       String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=database.mdb;";
                       Connection con = DriverManager.getConnection(connURL);
                       Statement stmt = con.createStatement();
                       stmt.executeUpdate("update record set usertime='" + timing + "',position='" + Qtnumber + "' where user='" + clientname +"'");
                       stmt.close();
                       con.close();
                }
                catch(Exception e3)
                {
                JOptionPane.showMessageDialog(null,"error");
                }


    }

    void displayProgress()
    {
         ResultSet   rs;
         Connection con;
         int qt;
        try
        {
         Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

           String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=database.mdb;";
           con=DriverManager.getConnection(connURL);
           Statement  smt=con.createStatement();
           rs=smt.executeQuery("select * from record");
           while(rs.next())
           {
               qt=Integer.parseInt(rs.getString(1))-1;
               textarea[qt].setText(rs.getString(2)+"\ntime: "+rs.getString(3)+"\nposition: "+rs.getString(4));

          }
          con.close();
        }
  catch(Exception  e)
  {
  JOptionPane.showMessageDialog(null,"display error ");
  }
}

}


public class TechnoshineX_4_GadgetGuru_Server {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException
    {
        // TODO code application logic here
        new server1().runServer();
    }
}

