import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import javax.swing.*;
import net.proteanit.sql.DbUtils;

public class CheckOut extends JFrame implements ActionListener {

    JTextField FirstName, LastName, id, phoneNumber;
    JButton submit, cancle, select;
    JComboBox<Integer> cbRoomNumber;
    java.util.Date date = new java.util.Date();

    CheckOut() {
        setLayout(null);
        setBounds(400, 100, 500, 700);
        getContentPane().setBackground(Color.white);

        JLabel headline = new JLabel("Check Out");
        headline.setFont(new Font("gisha", Font.PLAIN, 30));
        headline.setBounds(30, 0, 600, 50);
        add(headline);

        JLabel roomNumber_ = new JLabel("Room Number");
        roomNumber_.setBounds(30, 60, 100, 20);
        add(roomNumber_);

        String query2 = "SELECT rooms.roomnumber FROM rooms WHERE rooms.availability = 'Occupied' ORDER BY rooms.roomnumber";
        Conn c = new Conn();
        List<Integer> availibleRoomsNumbers = new LinkedList<>();
        try {
            ResultSet rs = c.s.executeQuery(query2);
            while (rs.next()) {
                availibleRoomsNumbers.add(rs.getInt("roomnumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Integer[] x = availibleRoomsNumbers.toArray(new Integer[0]);
        cbRoomNumber = new JComboBox<>(x);
        cbRoomNumber.setBounds(120, 60, 100, 20);
        add(cbRoomNumber);

        JLabel timeJLabel = new JLabel("Check Out Time");
        timeJLabel.setBounds(30, 90, 100, 20);
        add(timeJLabel);

        JLabel dateJLabel = new JLabel("" + date);
        dateJLabel.setFont(new Font("gisha", Font.PLAIN, 13));
        dateJLabel.setBounds(130, 90, 180, 20);
        add(dateJLabel);

        select = new JButton("select");
        select.setBounds(300, 60, 80, 20);
        select.setFont(new Font("gisha", Font.PLAIN, 15));
        select.addActionListener(this);
        add(select);

        submit = new JButton("Submit Check Out");
        submit.setBounds(300, 600, 160, 30);
        submit.setFont(new Font("gisha", Font.PLAIN, 15));
        submit.addActionListener(this);
        add(submit);

        cancle = new JButton("Cancle");
        cancle.setBounds(20, 600, 100, 30);
        cancle.setFont(new Font("gisha", Font.PLAIN, 15));
        cancle.addActionListener(this);
        add(cancle);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Integer roomNum_ = (Integer) cbRoomNumber.getSelectedItem();
        if (e.getSource() == cancle) {
            setVisible(false);
            new Reception();
        } else if (e.getSource() == submit) {
            try {
                Conn c = new Conn();
                String query = "update rooms set availability='Available',cleaning='Need Cleaning',totalbalance=0,detailsofbalance='' where roomnumber = "
                        + roomNum_;
                c.s.executeUpdate(query);
                query = "delete from guests where roomnumber=" + roomNum_;
                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Check Out Completed Successfuly");
                new Reception();
                setVisible(false);

            } catch (Exception o) {
                o.printStackTrace();
            }
        } else if (e.getSource() == select) {
            JTable table = new JTable();
            table.setBounds(20, 150, 450, 50);
            add(table);
            Conn c = new Conn();
            String query = "select guests.* from guests where roomnumber = " + roomNum_;
            try {
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
                String checkinTime = null;
                rs = c.s.executeQuery(query);
                if (rs.next()) {
                    checkinTime = rs.getString("checkintime");
                }
                

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy");
                LocalDateTime dateTime = LocalDateTime.parse(checkinTime, formatter);

                LocalDate today = LocalDate.now();
                long daysDiff = ChronoUnit.DAYS.between(dateTime.toLocalDate(), today);

                query = "select roomTypes.price from rooms join roomTypes on rooms.roomType=roomTypes.roomname where rooms.roomnumber="
                        + roomNum_;
                rs = c.s.executeQuery(query);
                int pricePerNight = 0;
                if (rs.next())
                    pricePerNight = rs.getInt("price");

              
                long amountToPayForNights = daysDiff * pricePerNight;

                JLabel numberOfNights = new JLabel("Number Of Nights: " + daysDiff);
                numberOfNights.setBounds(20, 200, 200, 30);
                numberOfNights.setFont(new Font("gisha", Font.PLAIN, 15));
                add(numberOfNights);
                JLabel payment = new JLabel("Amount To Pay For Nights: " + amountToPayForNights);
                payment.setBounds(20, 230, 500, 30);
                payment.setFont(new Font("gisha", Font.PLAIN, 15));
                add(payment);

                int extraChargesInt = 0;
                String extraChargessString = "";
                rs = c.s.executeQuery("select detailsofbalance,totalbalance from rooms where roomnumber=" + roomNum_);
                if (rs.next()) {
                    extraChargesInt = rs.getInt("totalbalance");
                    extraChargessString = rs.getString("detailsofbalance");
                }

                JLabel totalPayment = new JLabel("Total Payment: " + (amountToPayForNights + extraChargesInt));
                totalPayment.setBounds(20, 290, 200, 30);
                totalPayment.setFont(new Font("gisha", Font.PLAIN, 15));
                add(totalPayment);

                JLabel extraCharges_ = new JLabel("Extra Charges: " + extraChargesInt);
                extraCharges_.setBounds(20, 260, 200, 30);
                extraCharges_.setFont(new Font("gisha", Font.PLAIN, 15));
                add(extraCharges_);

                JLabel extraChargesDetails_ = new JLabel("Details Of Charges: ");
                extraChargesDetails_.setBounds(20, 320, 200, 30);
                extraChargesDetails_.setFont(new Font("gisha", Font.BOLD, 15));
                add(extraChargesDetails_);

                JLabel temp = new JLabel();
                temp.setText("<html>" + extraChargessString.replaceAll("\n", "<br>") + "</html>");
                temp.setBounds(20, 340, 200, 300);
                temp.setFont(new Font("gisha", Font.PLAIN, 12));
                add(temp);

                repaint();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
