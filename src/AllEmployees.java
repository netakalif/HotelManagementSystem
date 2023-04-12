import java.awt.event.*;
import java.sql.ResultSet;
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import java.awt.*;

public class AllEmployees extends JFrame implements ActionListener {
	JButton back, search ;
	JComboBox<String> jobType;
	JTable table;
	public AllEmployees() {
		setBounds(300,100,550,400);
		setLayout(null);
		setBackground(Color.white);

		JLabel headline=new JLabel("All Employees");
		headline.setFont(new Font("gisha", Font.PLAIN, 20));
		headline.setBounds(190,20,200,30);
		add(headline);


		table=new JTable();
		table.setBounds(20,80,500,230);
		add(table);

		JLabel firstName=new JLabel("First Name");
		firstName.setFont(new Font("gisha", Font.PLAIN, 12));
		firstName.setBounds(20,55,130,30);
		add(firstName);

		JLabel lastName=new JLabel("Last Name");
		lastName.setFont(new Font("gisha", Font.PLAIN, 12));
		lastName.setBounds(120,55,130,30);
		add(lastName);

		JLabel id=new JLabel("ID");
		id.setFont(new Font("gisha", Font.PLAIN, 12));
		id.setBounds(220,55,200,30);
		add(id);

		JLabel phoneNum=new JLabel("Phone Number");
		phoneNum.setFont(new Font("gisha", Font.PLAIN, 12));
		phoneNum.setBounds(320,55,200,30);
		add(phoneNum);
		
		JLabel job=new JLabel("Job");
		job.setFont(new Font("gisha", Font.PLAIN, 12));
		job.setBounds(420,55,200,30);
		add(job);

		back=new JButton("Back");
		back.setFont(new Font("gisha", Font.PLAIN, 15));
		back.setBounds(20,320,100,30);
		back.addActionListener(this);
		add(back);


		try {
			Conn c=new Conn();
			ResultSet rs=c.s.executeQuery("SELECT * FROM employees ORDER BY employees.job");
			table.setModel(DbUtils.resultSetToTableModel(rs));

		} catch (Exception e) {
			// TODO: handle exception
		}

		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == back) {
			new Reception();
			setVisible(false);
		}
	}
	public static void main(String[] args) {
		new AllEmployees();
	}
}
