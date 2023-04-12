import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteStuff extends JFrame implements ActionListener {

    JTextField FirstName, LastName, id;
    JButton cancle,deleteStuff;

    DeleteStuff() {
        setLayout(null);
        setBounds(400, 100, 330, 300);
        getContentPane().setBackground(Color.white);
        

        JLabel headline = new JLabel("delete employee");
        headline.setFont(new Font("gisha", Font.PLAIN, 20));
        headline.setBounds(30, 0, 200, 50);
        add(headline);

        JLabel firstName_ = new JLabel("First Name");
        firstName_.setBounds(30, 60, 80, 20);
        add(firstName_);

        FirstName = new JTextField();
        FirstName.setBounds(120, 60, 100, 20);
        add(FirstName);

        JLabel lastName_ = new JLabel("Last Name");
        lastName_.setBounds(30, 90, 80, 20);
        add(lastName_);

        LastName = new JTextField();
        LastName.setBounds(120, 90, 100, 20);
        add(LastName);

        JLabel id_ = new JLabel("ID");
        id_.setBounds(30, 120, 80, 20);
        add(id_);

        id = new JTextField();
        id.setBounds(120, 120, 100, 20);
        add(id);

        cancle = new JButton("cancle");
        add(cancle);
        cancle.setBounds(10, 220, 90, 30);
        cancle.setFont(new Font("gisha", Font.PLAIN, 15));
        cancle.addActionListener(this);

        deleteStuff = new JButton("delete employee");
        add(deleteStuff);
        deleteStuff.setBounds(150, 220, 150, 30);
        deleteStuff.setFont(new Font("gisha", Font.PLAIN, 15));
        deleteStuff.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        new DeleteStuff();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String first_ = FirstName.getText();
        String last_ = LastName.getText();
        String id_ = id.getText();
        if (e.getSource() == cancle) {
            setVisible(false);
        } else if (e.getSource() == deleteStuff) {
            if (first_.equals("")  || last_ .equals("")|| id_.equals("") )
                JOptionPane.showMessageDialog(null, "all fields must be full");
            else {
                try {
                    Conn c = new Conn();
                    String query1="select * from employees where id = '"+id_+"' and firstname = '"+first_+"' and lastname = '"+last_+"'";
                    ResultSet rs=c.s.executeQuery(query1);
                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "no such employee");
                    }
                    else{
                    String query2 = "delete from employees where id = '"+id_+"' and firstname = '"+first_+"' and lastname = '"+last_+"'";
                    c.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "employee removed successfuly");
                    setVisible(false);
                    }

                } catch (Exception o) {
                    o.printStackTrace();
                }
            }
        }
    }
}


