import java.awt.Color;
import java.awt.Font;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DeleteRoom extends JFrame implements ActionListener {

    JTextField roomToDelete;
    JButton cancle, deleteRoom;
    public DeleteRoom(){
         setLayout(null);
            setBounds(400, 100, 330, 300);
            getContentPane().setBackground(Color.white);

            JLabel headline = new JLabel("delete room");
            headline.setFont(new Font("gisha", Font.PLAIN, 20));
            headline.setBounds(30, 0, 200, 50);
            add(headline);

            JLabel roomNumberLable = new JLabel("Room Number");
            roomNumberLable.setBounds(30, 60, 120, 20);
            add(roomNumberLable);

            roomToDelete = new JTextField();
            roomToDelete.setBounds(120, 60, 100, 20);
            add(roomToDelete);

            cancle = new JButton("cancle");
        add(cancle);
        cancle.setBounds(10, 220, 90, 30);
        cancle.setFont(new Font("gisha", Font.PLAIN, 15));
        cancle.addActionListener(this);

        deleteRoom = new JButton("delete room");
        add(deleteRoom);
        deleteRoom.setBounds(150, 220, 150, 30);
        deleteRoom.setFont(new Font("gisha", Font.PLAIN, 15));
        deleteRoom.addActionListener(this);

        setVisible(true);

    }

    public static void main(String[] args) {
        new DeleteRoom();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String roomToDelete_ = roomToDelete.getText();
        if (e.getSource() == cancle) {
            setVisible(false);
        } else if (e.getSource() == deleteRoom) {
            if (roomToDelete_.equals(""))
                JOptionPane.showMessageDialog(null, "all fields must be full");
            else {
                try {
                    Conn c = new Conn();
                    String query1="select * from rooms where roomnumber = '"+roomToDelete_+"'";
                    ResultSet rs=c.s.executeQuery(query1);
                    if(!rs.next()){
                        JOptionPane.showMessageDialog(null, "no such room");
                    }
                    else{
                    String query2 = "delete from rooms where roomnumber = '"+roomToDelete_+"'";
                    c.s.executeUpdate(query2);
                    JOptionPane.showMessageDialog(null, "room number "+roomToDelete_+" removed successfuly");
                    setVisible(false);
                    }

                } catch (Exception o) {
                    o.printStackTrace();
                }
            }
        }
    }
}
