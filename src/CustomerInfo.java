import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;

public class CustomerInfo extends JFrame implements ActionListener {
	JButton cancle ;
	JTable table;
	public CustomerInfo(){
		setBounds(300,100,700,500);
		setLayout(null);
		setBackground(Color.white);
		JLabel headline=new JLabel("Customer Info");
		headline.setFont(new Font("gisha", Font.PLAIN, 20));
		headline.setBounds(280,20,200,30);
		add(headline);

		table=new JTable();
		table.setBounds(20,150,660,250);
		add(table);

		JLabel firstName=new JLabel("First Name");
		firstName.setFont(new Font("gisha", Font.PLAIN, 12));
		firstName.setBounds(20,120,130,30);
		add(firstName);

		JLabel lastName=new JLabel("Last Name");
		lastName.setFont(new Font("gisha", Font.PLAIN, 12));
		lastName.setBounds(130,120,130,30);
		add(lastName);

		JLabel id=new JLabel("ID");
		id.setFont(new Font("gisha", Font.PLAIN, 12));
		id.setBounds(240,120,200,30);
		add(id);

		JLabel phoneNumber=new JLabel("Phone Number");
		phoneNumber.setFont(new Font("gisha", Font.PLAIN, 12));
		phoneNumber.setBounds(350,120,200,30);
		add(phoneNumber);

		JLabel roomNumber=new JLabel("Room Number");
		roomNumber.setFont(new Font("gisha", Font.PLAIN, 12));
		roomNumber.setBounds(460,120,200,30);
		add(roomNumber);

        JLabel checkInTime=new JLabel("Check In Time");
		checkInTime.setFont(new Font("gisha", Font.PLAIN, 12));
		checkInTime.setBounds(570,120,200,30);
		add(checkInTime);

		cancle=new JButton("Cancle");
		cancle.setFont(new Font("gisha", Font.PLAIN, 15));
		cancle.setBounds(20,420,100,30);
		cancle.addActionListener(this);
		add(cancle);

		try {
			Conn c=new Conn();
			ResultSet rs=c.s.executeQuery("SELECT * FROM guests");
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Something went wrong. please try again later");
		}

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancle) {
			new Reception();
			setVisible(false);
		}
		// if(e.getSource()==search) {
		// 	Conn c=new Conn();
		// 	try {
		// 		String query1="SELECT rooms.*, roomTypes.roomNumber FROM roomTypes JOIN rooms ON roomTypes.roomname = rooms.roomtype WHERE rooms.roomtype = '"+phoneNumber.getSelectedItem() +"' ORDER BY rooms.firstNameber";
		// 		String query2="SELECT rooms.*, roomTypes.roomNumber FROM roomTypes JOIN rooms ON roomTypes.roomname = rooms.roomtype WHERE rooms.lastName = 'Available' AND rooms.roomtype = '"+phoneNumber.getSelectedItem() +"' ORDER BY rooms.firstNameber";
		// 		ResultSet rs;
		// 		if(available.isSelected()) {
		// 			rs = c.s.executeQuery(query2);
		// 		}
		// 		else {
		// 			rs = c.s.executeQuery(query1);
		// 		}
		// 		table.setModel(DbUtils.resultSetToTableModel(rs));
		// 	} catch (SQLException e1) {
		// 		e1.printStackTrace();
		// 	}
		//}
	}
	public static void main(String[] args) {
		new CustomerInfo();
	}
}
