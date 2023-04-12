import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AddCharge extends JFrame implements ActionListener {
    JButton submit, cancle;
    JComboBox<String> ChargeType;
    JComboBox<Integer> cbRoomNumber;
    JTextField sumToCharge,reasonToCharge;
    int[] arr_Charges = { 100, 500, 75, 0 };

    AddCharge() {
        setBounds(300, 100, 450, 380);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JLabel header = new JLabel("Add Charge");
        header.setFont(new Font("gisha", Font.PLAIN, 30));
        header.setBounds(20, 20, 300, 40);
        add(header);

        JLabel roomNumber_ = new JLabel("Room Number");
        roomNumber_.setBounds(20, 70, 150, 20);
        roomNumber_.setFont(new Font("gisha", Font.PLAIN, 18));
        add(roomNumber_);

        String query2 = "SELECT rooms.roomnumber FROM rooms WHERE rooms.availability = 'Occupied' ORDER BY rooms.roomnumber";
        Conn c = new Conn();
        List<Integer> ocupiedRoomsNumbers = new LinkedList<>();
        try {
            ResultSet rs = c.s.executeQuery(query2);
            while (rs.next()) {
                ocupiedRoomsNumbers.add(rs.getInt("roomnumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Integer[] x = ocupiedRoomsNumbers.toArray(new Integer[0]);
        cbRoomNumber = new JComboBox<>(x);
        cbRoomNumber.setBounds(170, 70, 100, 20);
        add(cbRoomNumber);

        JLabel ChargeType_ = new JLabel("Charge Type");
        String[] option_Charge = { "Room Service", "Spa", "Dry Cleaning", "Different" };

        ChargeType_.setFont(new Font("gisha", Font.PLAIN, 18));
        ChargeType_.setBounds(20, 100, 200, 25);
        add(ChargeType_);

        ChargeType = new JComboBox<>(option_Charge);
        ChargeType.setBounds(170, 100, 120, 25);
        add(ChargeType);

        JLabel note = new JLabel("if you selected 'different', enter the sum you would like to add");
        note.setFont(new Font("gisha", Font.PLAIN, 14));
        note.setBounds(20, 130, 400, 25);
        add(note);

        JLabel sumToCharge_ = new JLabel("Sum To Charge");
        sumToCharge_.setFont(new Font("gisha", Font.PLAIN, 18));
        sumToCharge_.setBounds(20, 160, 200, 25);
        add(sumToCharge_);

        sumToCharge = new JTextField();
        sumToCharge.setBounds(170, 160, 120, 25);
        add(sumToCharge);

        JLabel reasonToCharge_ = new JLabel("Reason For The Charge");
        reasonToCharge_.setFont(new Font("gisha", Font.PLAIN, 18));
        reasonToCharge_.setBounds(20, 200, 220, 25);
        add(reasonToCharge_);

        reasonToCharge = new JTextField();
        reasonToCharge.setBounds(230, 200, 150, 25);
        add(reasonToCharge);


        submit = new JButton("submit");
        add(submit);
        submit.setBounds(300, 290, 100, 30);
        submit.setFont(new Font("gisha", Font.PLAIN, 15));
        submit.addActionListener(this);

        cancle = new JButton("cancle");
        add(cancle);
        cancle.setBounds(20, 290, 100, 30);
        cancle.setFont(new Font("gisha", Font.PLAIN, 15));
        cancle.addActionListener(this);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCharge();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int RoomNumber_ = (int)cbRoomNumber.getSelectedItem();
        String ChargeType_ = (String) ChargeType.getSelectedItem();
        if (e.getSource() == cancle) {
            setVisible(false);
            new Reception();
        } else if (e.getSource() == submit) {
            try {
                Conn c = new Conn();
                String query = "SELECT detailsofbalance,totalbalance FROM rooms WHERE roomnumber = " + RoomNumber_;
                ResultSet rs = c.s.executeQuery(query);
                if (rs.next()) {
                    String detailsOfBalance = rs.getString("detailsofbalance");
                    int price = rs.getInt("totalbalance");
                    if (ChargeType.getSelectedItem().equals("Different")) {
                        price += Integer.parseInt(sumToCharge.getText());
                        detailsOfBalance += reasonToCharge.getText() + ": " +sumToCharge.getText()+"\n";
                    } else {
                        price += arr_Charges[ChargeType.getSelectedIndex()];
                        detailsOfBalance += ChargeType_ + ": " + arr_Charges[ChargeType.getSelectedIndex()]+"\n";
                    }
                    query = "update rooms set detailsofbalance='" + detailsOfBalance + "', totalbalance=" + price
                            + " where roomnumber = " + RoomNumber_;
                            System.out.println(query);
                    c.s.executeUpdate(query);
                }

                JOptionPane.showMessageDialog(null, "Charge Added Successfuly");
                setVisible(false);
                new Reception();

            } catch (Exception o) {
                o.printStackTrace();
            }
        }

    }
}
