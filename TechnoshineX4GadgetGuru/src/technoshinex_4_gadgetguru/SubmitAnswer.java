/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package technoshinex_4_gadgetguru;


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;

/**
 *
 * @author SHRIKANT
 */
public class SubmitAnswer implements ActionListener
{
JFrame frm;
JLabel res,tot,t,att,a,user,u;
JButton submit;
int correct=0;
int attempt=0;

int total_Q=40;
   SubmitAnswer(String name)
   {
        frm=new JFrame();
        frm.setUndecorated(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frm.setBounds(0, 0, screenSize.width, screenSize.height);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setBackground(Color.black);
        frm.setContentPane(new JLabel(new ImageIcon("image//b.jpg")));

         JLabel jl1=new JLabel("Technosine X.4");
            jl1.setFont(new Font("arial",Font.BOLD,60));
            jl1.setForeground(Color.white);
            jl1.setBounds(420,10,550,80);
            frm.add(jl1);

            JLabel jl2=new JLabel("Gadget Guru");
            jl2.setFont(new Font("OCR A Extended",Font.BOLD,30));
            jl2.setForeground(Color.orange);
            jl2.setBounds(535,60,550,80);
            frm.add(jl2);


        res=new JLabel("Result");
        res.setBounds(570,220,200,50);
        res.setFont(new Font("Budmo Jiggler",Font.BOLD,30));
        res.setForeground(Color.ORANGE);
        frm.add(res);

        user=new JLabel("User Name :");
        //user.setOpaque(true);
        user.setBounds(450,300,200,50);
        user.setFont(new Font("Century Schoolbook",Font.BOLD,30));
        user.setForeground(Color.white);
        frm.add(user);

        u=new JLabel(name);
        //u.setOpaque(true);
        u.setBounds(750,300,300,50);
        u.setFont(new Font("Century Schoolbook",Font.BOLD,30));
        u.setForeground(Color.white);
        frm.add(u);

        tot=new JLabel("Total Question :");
        //tot.setOpaque(true);
        tot.setBounds(450,350,250,50);
        tot.setFont(new Font("Century Schoolbook",Font.BOLD,30));
        tot.setForeground(Color.white);
        frm.add(tot);

        t=new JLabel();
        //t.setOpaque(true);
        t.setBounds(750,350,100,50);
        t.setFont(new Font("Centtry Schoolbook",Font.BOLD,30));
        t.setForeground(Color.white);
        frm.add(t);

        att=new JLabel("Attempt :");
        //att.setOpaque(true);
        att.setBounds(450,400,200,50);
        att.setFont(new Font("Century Schoolbook",Font.BOLD,30));
        att.setForeground(Color.white);
        frm.add(att);

        a=new JLabel();
        //a.setOpaque(true);
        a.setBounds(750,400,100,50);
        a.setFont(new Font("Centtry Schoolbook",Font.BOLD,30));
        a.setForeground(Color.white);
        frm.add(a);



            submit=new JButton("EXIT");
            submit.setBounds(600, 720, 100, 30);
            submit.setForeground(Color.red);

            frm.add(submit);
            submit.addActionListener(this);


        JLabel w=new JLabel("Thanks");
        w.setForeground(Color.green);
        w.setBounds(850,550,1000,150);
        w.setFont(new Font("Freestyle Script",Font.BOLD,150));
        frm.add(w);

        Label l=new Label();
        l.setBackground(Color.green);
        l.setBounds(200,700,1000,5);
        frm.add(l);
        getResult();
        frm.setLayout(null);
        frm.setVisible(true);
   }

 void getResult()
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
  String vst,lck;
  int ans,user_ans;
  while(rs.next())
 {

  lck=rs.getString(5);
  ans=rs.getInt(2);
  user_ans=rs.getInt(3);

  if(lck.equals("YES"))
  {
       attempt=attempt+1;
  }

  if(ans==user_ans)
  {
  correct=correct+1;
  }


 a.setText(Integer.toString(attempt));
 t.setText(Integer.toString(total_Q));

 }
  con.close();
 }
  catch(SQLException  e)
  {
  JOptionPane.showMessageDialog(null,"error in total sum");
  }

}

    public void actionPerformed(ActionEvent event)
    {
           if(event.getSource() instanceof JButton)
           {
                    if(event.getSource()==submit)
                    {
                       System.exit(0);
                    }
            }
    }
    
}
