import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddStuff extends JFrame implements ActionListener {

	JTextField FirstName, LastName, id, phoneNumber, job;
	JButton submit, cancle,deleteStuff;
	JComboBox<String> cbjob;

	AddStuff() {
		setLayout(null);
		setBounds(400, 100, 500, 600);
		getContentPane().setBackground(Color.white);
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
		Image i2 = i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		JLabel image = new JLabel(i3);
		image.setBounds(0, 200, 500, 400);
		add(image);

		JLabel headline = new JLabel("new employee");
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

		JLabel job_ = new JLabel("Job");
		job_.setBounds(30, 180, 100, 20);
		add(job_);

		String[] str = { "Front Desk", "Poreter", "House Keeper", "Kitchen Stuff" };
		cbjob = new JComboBox<>(str);
		cbjob.setBounds(120, 180, 100, 20);
		add(cbjob);

		submit = new JButton("submit");
		image.add(submit);
		submit.setBounds(370, 320, 100, 30);
		submit.setFont(new Font("gisha", Font.PLAIN, 15));
		submit.addActionListener(this);

		cancle = new JButton("cancle");
		image.add(cancle);
		cancle.setBounds(20, 320, 100, 30);
		cancle.setFont(new Font("gisha", Font.PLAIN, 15));
		cancle.addActionListener(this);

		deleteStuff = new JButton("delete employee");
		add(deleteStuff);
		deleteStuff.setBounds(320, 30, 150, 30);
		deleteStuff.setFont(new Font("gisha", Font.PLAIN, 15));
		deleteStuff.addActionListener(this);

		setVisible(true);
	}

	public static void main(String[] args) {
		new AddStuff();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String first_ = FirstName.getText();
		String last_ = LastName.getText();
		String id_ = id.getText();
		String phoneNum_ = phoneNumber.getText();
		String job_ = (String) cbjob.getSelectedItem();
		if (e.getSource() == cancle) {
			setVisible(false);
		} else if (e.getSource() == submit) {
			if (first_.equals("")  || last_ .equals("")|| id_.equals("") || phoneNum_.equals(""))
				JOptionPane.showMessageDialog(null, "all fields must be full");

			else {
				try {
					Conn c = new Conn();
					String query = "insert into employees values('" + first_ + "','" + last_ + "','" + id_ + "','"
							+ phoneNum_
							+ "','" + job_ + "')";
					c.s.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "employee added successfuly");
				}
				catch (Exception o) {
					o.printStackTrace();
				}
				if(job_.equals("Front Desk")) {
					JOptionPane.showMessageDialog(null, "the employee is a front desk employee. create a new login account");
					new NewAccount();
					setVisible(false);
				}

			}

		}
		else if(e.getSource()==deleteStuff){
			new DeleteStuff();
			setVisible(false);
		}
	}
}
