import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckIn extends JFrame implements ActionListener {

	JTextField FirstName, LastName, id, phoneNumber;
	JButton submit, cancle, back;
	JComboBox<Integer> cbRoomNumber;
	java.util.Date date = new java.util.Date();

	CheckIn() {
		setLayout(null);
		setBounds(400, 100, 500, 600);
		getContentPane().setBackground(Color.white);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
		Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0, 290, 500, 300);
		add(image);

		JLabel headline = new JLabel("Check In");
		headline.setFont(new Font("gisha", Font.PLAIN, 30));
		headline.setBounds(30, 0, 600, 50);
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

		JLabel phoneNumber_ = new JLabel("Phone Number");
		phoneNumber_.setBounds(30, 150, 100, 20);
		add(phoneNumber_);

		phoneNumber = new JTextField();
		phoneNumber.setBounds(120, 150, 100, 20);
		add(phoneNumber);

		JLabel roomNumber_ = new JLabel("Room Number");
		roomNumber_.setBounds(30, 180, 100, 20);
		add(roomNumber_);

		String query2 = "SELECT rooms.roomnumber FROM rooms WHERE rooms.availability = 'Available' ORDER BY rooms.roomnumber";
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
		cbRoomNumber.setBounds(120, 180, 100, 20);
		add(cbRoomNumber);

		JLabel timeJLabel = new JLabel("Check In Time");
		timeJLabel.setBounds(30, 210, 100, 20);
		add(timeJLabel);

		JLabel dateJLabel = new JLabel("" + date);
		dateJLabel.setFont(new Font("gisha", Font.PLAIN, 13));
		dateJLabel.setBounds(130, 210, 180, 20);
		add(dateJLabel);

		submit = new JButton("Submit Check In");
		submit.setBounds(370, 240, 100, 30);
		submit.setFont(new Font("gisha", Font.PLAIN, 15));
		submit.addActionListener(this);
		add(submit);

		cancle = new JButton("Cancle");
		cancle.setBounds(130, 240, 100, 30);
		cancle.setFont(new Font("gisha", Font.PLAIN, 15));
		cancle.addActionListener(this);
		add(cancle);

		back = new JButton("Back");
		back.setBounds(20, 240, 100, 30);
		back.setFont(new Font("gisha", Font.PLAIN, 15));
		back.addActionListener(this);
		add(back);

		setVisible(true);
	}

	public static void main(String[] args) {
		new CheckIn();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String first_ = FirstName.getText();
		String last_ = LastName.getText();
		String id_ = id.getText();
		String phoneNum_ = phoneNumber.getText();
		Integer roomNum_ = (Integer) cbRoomNumber.getSelectedItem();
		if (e.getSource() == cancle) {
			setVisible(false);
			new Reception();
		} else if (e.getSource() == submit) {
			if (first_.equals("") || last_.equals("") || id_.equals("") || phoneNum_.equals(""))
				JOptionPane.showMessageDialog(null, "all fields must be full");
			else {
				try {
					Conn c = new Conn();
					String query0 = "select * from guests where id=" + id_;
					if (c.s.executeQuery(query0).next()) {
						JOptionPane.showMessageDialog(null, "there is already a guests with that id");
					} else {
						String query2 = "insert into guests values('" + first_ + "','" + last_ + "','" + id_ + "','"
								+ phoneNum_
								+ "','" + roomNum_ + "','" + date + "')";
						c.s.executeUpdate(query2);
						String query1 = "update rooms set availability='Occupied' where roomnumber = " + roomNum_;
						c.s.executeUpdate(query1);
						JOptionPane.showMessageDialog(null, "Check In Completed Successfuly");
						new Reception();
						setVisible(false);
					}

				} catch (Exception o) {
					o.printStackTrace();
				}
			}
		} else if (e.getSource() == back) {
			new SearchRoomForCheckIn();
			setVisible(false);
		}
	}
}
