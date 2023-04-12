import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;
public class SearchRoom extends JFrame implements ActionListener {
	JButton search,cancle ;
	JComboBox<String> roomType;
	JCheckBox available;
	JTable table;
	public SearchRoom(){
		setBounds(300,100,700,500);
		setLayout(null);
		setBackground(Color.white);
		JLabel headline=new JLabel("Search Room");
		headline.setFont(new Font("gisha", Font.PLAIN, 20));
		headline.setBounds(300,20,200,30);
		add(headline);


		JLabel roomType_ = new JLabel("Room Type");
		String[] option_room = { "Standard", "Delux", "Junior Suite", };
		roomType_.setFont(new Font("gisha", Font.PLAIN, 18));
		roomType_.setBounds(20, 70, 200, 25);
		add(roomType_);

		roomType = new JComboBox<>(option_room);
		roomType.setFont(new Font("gisha", Font.PLAIN, 16));
		roomType.setBounds(150, 70, 120, 25);
		add(roomType);

		available = new JCheckBox("Only Display Available");
		available.setFont(new Font("gisha", Font.PLAIN, 16));
		available.setBounds(450, 70, 300, 25);
		add(available);

		table=new JTable();
		table.setBounds(20,150,660,250);
		add(table);

		JLabel roomNum=new JLabel("Room Number");
		roomNum.setFont(new Font("gisha", Font.PLAIN, 12));
		roomNum.setBounds(20,120,130,30);
		add(roomNum);

		JLabel availability=new JLabel("Availability");
		availability.setFont(new Font("gisha", Font.PLAIN, 12));
		availability.setBounds(130,120,130,30);
		add(availability);

		JLabel cleaning=new JLabel("Cleaning Status");
		cleaning.setFont(new Font("gisha", Font.PLAIN, 12));
		cleaning.setBounds(240,120,200,30);
		add(cleaning);

		JLabel roomType=new JLabel("Room Type");
		roomType.setFont(new Font("gisha", Font.PLAIN, 12));
		roomType.setBounds(350,120,200,30);
		add(roomType);

		JLabel roomBalance=new JLabel("Room Balance");
		roomBalance.setFont(new Font("gisha", Font.PLAIN, 12));
		roomBalance.setBounds(460,120,200,30);
		add(roomBalance);
		
		JLabel price=new JLabel("Price Per Night");
		price.setFont(new Font("gisha", Font.PLAIN, 12));
		price.setBounds(570,120,200,30);
		add(price);

		cancle=new JButton("Cancle");
		cancle.setFont(new Font("gisha", Font.PLAIN, 15));
		cancle.setBounds(20,420,100,30);
		cancle.addActionListener(this);
		add(cancle);

		search=new JButton("search");
		search.setFont(new Font("gisha", Font.PLAIN, 15));
		search.setBounds(575,420,100,30);
		search.addActionListener(this);
		add(search);


		try {
			Conn c=new Conn();
			ResultSet rs=c.s.executeQuery("SELECT rooms.roomnumber,rooms.availability,rooms.cleaning,rooms.roomtype,rooms.totalbalance, roomTypes.price FROM roomTypes JOIN rooms ON roomTypes.roomname = rooms.roomtype ORDER BY rooms.roomnumber");
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			
		}

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancle) {
			new Reception();
			setVisible(false);
		}
		if(e.getSource()==search) {
			Conn c=new Conn();
			try {
				String query1="SELECT rooms.*, roomTypes.price FROM roomTypes JOIN rooms ON roomTypes.roomname = rooms.roomtype WHERE rooms.roomtype = '"+roomType.getSelectedItem() +"' ORDER BY rooms.roomnumber";
				String query2="SELECT rooms.*, roomTypes.price FROM roomTypes JOIN rooms ON roomTypes.roomname = rooms.roomtype WHERE rooms.availability = 'Available' AND rooms.roomtype = '"+roomType.getSelectedItem() +"' ORDER BY rooms.roomnumber";
				ResultSet rs;
				if(available.isSelected()) {
					rs = c.s.executeQuery(query2);
				}
				else {
					rs = c.s.executeQuery(query1);
				}
				table.setModel(DbUtils.resultSetToTableModel(rs));
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new SearchRoom();
	}
}
