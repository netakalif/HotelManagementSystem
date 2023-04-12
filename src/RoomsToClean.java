import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;

public class RoomsToClean extends JFrame implements ActionListener {
	JButton back, allRoomsAreClean;
	JTable table;
	public RoomsToClean() {
		setBounds(300,100,550,400);
		setLayout(null);
		setBackground(Color.white);

		JLabel headline=new JLabel("Rooms To Clean");
		headline.setFont(new Font("gisha", Font.PLAIN, 20));
		headline.setBounds(190,20,200,30);
		add(headline);


		table=new JTable();
		table.setBounds(20,80,500,230);
		add(table);

		JLabel roomNum=new JLabel("Room Number");
		roomNum.setFont(new Font("gisha", Font.PLAIN, 12));
		roomNum.setBounds(20,55,130,30);
		add(roomNum);

		JLabel availability=new JLabel("Availability");
		availability.setFont(new Font("gisha", Font.PLAIN, 12));
		availability.setBounds(150,55,130,30);
		add(availability);

		JLabel cleaning=new JLabel("Cleaning Status");
		cleaning.setFont(new Font("gisha", Font.PLAIN, 12));
		cleaning.setBounds(280,55,200,30);
		add(cleaning);

		JLabel roomType=new JLabel("Room Type");
		roomType.setFont(new Font("gisha", Font.PLAIN, 12));
		roomType.setBounds(410,55,200,30);
		add(roomType);

		back=new JButton("Back");
		back.setFont(new Font("gisha", Font.PLAIN, 15));
		back.setBounds(20,320,100,30);
		back.addActionListener(this);
		add(back);

		allRoomsAreClean=new JButton("Clean All Rooms");
		allRoomsAreClean.setFont(new Font("gisha", Font.PLAIN, 15));
		allRoomsAreClean.setBounds(350,320,170,30);
		allRoomsAreClean.addActionListener(this);
		add(allRoomsAreClean);
		


		try {
			Conn c=new Conn();
			ResultSet rs=c.s.executeQuery("SELECT rooms.roomnumber, rooms.availability, rooms.cleaning, rooms.roomtype FROM rooms WHERE cleaning ='Need Cleaning' ORDER BY rooms.roomnumber");
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
		}

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			new Reception();
			setVisible(false);
		}

		else if (e.getSource() == allRoomsAreClean) {
			Conn c=new Conn();
			String query="update rooms set cleaning='Clean'";
			try {
				c.s.executeUpdate(query);
			} catch (Exception j) {
				JOptionPane.showMessageDialog(null, "Something went wrong. please try again later");
			}
			JOptionPane.showMessageDialog(null, "All Rooms Are Now Clean");
			new Reception();
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new RoomsToClean();
	}
}
