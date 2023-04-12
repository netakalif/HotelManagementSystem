import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AddRoom extends JFrame implements ActionListener {
    JTextField roomNumber, floor;
    JButton submit, cancle, deleteRoom;
    JComboBox<String> roomType, avalibale, clean;

    AddRoom() {
        setBounds(300, 100, 1000, 600);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 600, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 0, 600, 600);
        add(image);

        JLabel header = new JLabel("Add Room");
        header.setFont(new Font("gisha", Font.PLAIN, 40));
        header.setBounds(20, 20, 300, 40);
        add(header);

        JLabel roomNumber_ = new JLabel("Room Number");
        roomNumber_.setFont(new Font("gisha", Font.PLAIN, 18));
        roomNumber_.setBounds(20, 70, 200, 25);
        add(roomNumber_);

        roomNumber = new JTextField();
        roomNumber.setBounds(210, 70, 120, 25);
        add(roomNumber);

        JLabel avalible_ = new JLabel("Availability");
        String[] option_av = { "Available", "Occupied" };
        avalible_.setFont(new Font("gisha", Font.PLAIN, 18));
        avalible_.setBounds(20, 100, 200, 25);
        add(avalible_);

        avalibale = new JComboBox<>(option_av);
        avalibale.setBounds(210, 100, 120, 25);
        add(avalibale);

        JLabel clean_ = new JLabel("Cleaning Status");
        String[] option_clean = { "Clean", "Need Cleaning" };
        clean_.setFont(new Font("gisha", Font.PLAIN, 18));
        clean_.setBounds(20, 130, 200, 25);
        add(clean_);

        clean = new JComboBox<>(option_clean);
        clean.setBounds(210, 130, 120, 25);
        add(clean);

        JLabel roomType_ = new JLabel("Room Type");
        String[] option_room = { "Standard", "Delux", "Junior Suite", };
        roomType_.setFont(new Font("gisha", Font.PLAIN, 18));
        roomType_.setBounds(20, 160, 200, 25);
        add(roomType_);

        roomType = new JComboBox<>(option_room);
        roomType.setBounds(210, 160, 120, 25);
        add(roomType);

        submit = new JButton("submit");
        add(submit);
        submit.setBounds(270, 500, 100, 30);
        submit.setFont(new Font("gisha", Font.PLAIN, 15));
        submit.addActionListener(this);

        cancle = new JButton("cancle");
        add(cancle);
        cancle.setBounds(20, 500, 100, 30);
        cancle.setFont(new Font("gisha", Font.PLAIN, 15));
        cancle.addActionListener(this);

        deleteRoom = new JButton("delete room");
        add(deleteRoom);
        deleteRoom.setBounds(240, 30, 120, 30);
        deleteRoom.setFont(new Font("gisha", Font.PLAIN, 16));
        deleteRoom.setForeground(Color.white);
        deleteRoom.setBackground(Color.black);
        deleteRoom.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddRoom();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String roomNumber_ = roomNumber.getText();
        String available_ = (String) avalibale.getSelectedItem();
        String cleaning_ = (String) clean.getSelectedItem();
        String roomType_ = (String) roomType.getSelectedItem();
        if (e.getSource() == cancle) {
            setVisible(false);
        } else if (e.getSource() == submit) {
            if (roomNumber_.equals(""))
                JOptionPane.showMessageDialog(null, "room number must be full");
            else {
                try {
                    Conn c = new Conn();
                    String checkIfExists="select * from rooms where roomnumber="+roomNumber_;
                    ResultSet rs=c.s.executeQuery(checkIfExists);
                    if(rs.next()){
                        JOptionPane.showMessageDialog(null, "Room Number Already Exists");
                    }
                    else{
                    String query = "insert into rooms values('" + roomNumber_ + "','" + available_ + "','" + cleaning_
                            + "','"+ roomType_ + "',0,'')";
                            c.s.executeUpdate(query);
                    JOptionPane.showMessageDialog(null, "Room Added Successfuly");
                    }
                    //setVisible(false);

                } catch (Exception o) {
                    o.printStackTrace();
                }
            }
        } else if (e.getSource() == deleteRoom) {
            new DeleteRoom();
            setVisible(false);

        }
    }

}
