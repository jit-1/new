/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package technoshinex_4_gadgetguru;


import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import javax.swing.*;
import java.awt.*;
import java.sql.*;


/**
 *
 * @author SHRIKANT
 */
public class register implements ActionListener
{
    Socket s;
    JFrame jfrm;
    JPanel panel1;
    JLabel team,pas,confr,conf1;
    JTextField tm_name;
    JButton reg_ok,cancel;
    JPasswordField pass,conf;
    static Socket clientSocket;
    static PrintStream os = null;
    static DataInputStream is = null;
    static BufferedReader inputLine = null;
    Graphics g;
    String client_name,IP_Address;




    public register(String IP)
    {
       IP_Address=IP;
        jfrm=new JFrame();
        jfrm.setContentPane(new JLabel(new ImageIcon("image//z.jpg")));

         jfrm.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        jfrm.setUndecorated(true);
        jfrm.setBounds(0, 0, screenSize.width, screenSize.height);
        jfrm.setBackground(new Color(100,100,10));

        //jfrm.setSize(32767,32767);
        //jfrm.setLocation(175,25);

        jfrm.setLayout(null);
        jfrm.setExtendedState(JFrame.MAXIMIZED_BOTH);


            JLabel jl1=new JLabel("Technosine X.4");
            jl1.setFont(new Font("arial",Font.BOLD,70));
            jl1.setForeground(Color.white);
            jl1.setBounds(420,10,550,80);
            jfrm.add(jl1);

            JLabel jl2=new JLabel("Gadget Guru");
            jl2.setFont(new Font("OCR A Extended",Font.BOLD,50));
            jl2.setForeground(Color.yellow);
            jl2.setBounds(525,60,550,80);
            jfrm.add(jl2);



            panel1 = new JPanel();
            panel1.setLayout(null);
            panel1.setBackground(new Color(100,100,0));
            panel1.setSize(500, 200);
            panel1.setLocation(430, 280);
            jfrm.add(panel1);

        JLabel reg=new JLabel("LOGIN");
        reg.setFont(new Font("Arial",Font.BOLD,20));
        reg.setBounds(200,10,120,30);
        reg.setForeground(Color.white);
        panel1.add(reg);

        team=new JLabel("Team Name:");
        team.setFont(new Font("arial",Font.BOLD,20));
        team.setForeground(Color.white);
        team.setBounds(30,40,120,40);
        panel1.add(team);

        tm_name=new JTextField();
        tm_name.setBounds(230, 50,170, 25);
        tm_name.setBackground(Color.white);
        tm_name.setForeground(Color.red);
        tm_name.setFont(new Font("arial",Font.BOLD,20));
        panel1.add(tm_name);

        pas=new JLabel("Password:");
        pas.setBounds(30, 70, 120, 40);
        pas.setFont(new Font("arial",Font.BOLD,20));
        pas.setForeground(Color.white);
        panel1.add(pas);

        pass=new JPasswordField();
        pass.setBounds(230, 80, 170, 25);
        pass.setForeground(Color.red);
        panel1.add(pass);

        cancel=new JButton("cancel");
        cancel.setBounds(130, 150, 100, 30);
        panel1.add(cancel);
        cancel.addActionListener(this);


        reg_ok=new JButton("Ok");
        reg_ok.setBounds(270, 150, 100, 30);
        panel1.add(reg_ok);
        reg_ok.addActionListener(this);


        jfrm.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==cancel)
        {
            jfrm.dispose();
            jfrm.setVisible(false);
            System.exit(0);
            //logon l=new logon();
        }
        if(e.getSource()==reg_ok)
        {

         String passwrd="";
         client_name=tm_name.getText();
         ResultSet   rs;
         Connection con;


                            try
                             {
                             Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                             String connURL="jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=DataBase\\chat.mdb;";
                             con=DriverManager.getConnection(connURL);
                             Statement  smt=con.createStatement();
                             String name=tm_name.getText();
                             int id=1;
                             rs=smt.executeQuery("select ID,TEAM_NAME,password from login where TEAM_NAME='" + name + "';");
                                    if(rs.next())
                                    {


                                        passwrd=rs.getString(3);
                                        String ps=pass.getText();

                                        if(passwrd.equals(ps))
                                        {
                                        //JOptionPane.showMessageDialog(null,passwrd);

                                            try
                                             {
                                                clientSocket = new Socket(IP_Address, 9090);
                                                PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
		                                        client_name=tm_name.getText();

                                                out.println(client_name);
                                                jfrm.setVisible(false);
                                                jfrm.dispose();
                                                //rules rl=new rules(clientSocket,client_name);
                                                GadgetGuru gg=new GadgetGuru(clientSocket,client_name);


                                              }
                                                catch (Exception ex)
                                                {
                                                JOptionPane.showMessageDialog(null,"error in register");
                                                //System.out.println(ex);
                                                }



                                        }
                                        else
                                        {
                                        JOptionPane.showMessageDialog(null,"Password incorrect");
                                        //tm_name.setText("");
                                        pass.setText("");
                                        }
                                    }
                                    else
                                    {
                                       JOptionPane.showMessageDialog(null,"User name incorrect...");
                                       tm_name.setText("");
                                        pass.setText("");

                                    }
                             }
                             catch(Exception w)
                             {
                             }


        }
    }

    
}
