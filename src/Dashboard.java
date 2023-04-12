
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dashboard extends JFrame implements ActionListener{

	JMenuItem editEmployee,addRoom;
	JMenuItem reception;
	Dashboard(){
		setBounds(300,100,500,400);
		setLayout(null);

		ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
		Image i2=i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);

		image.setBounds(0,0,500,400);
		add(image);

		JLabel headline=new JLabel("Admin Managment System");
		headline.setFont(new Font("gisha", Font.PLAIN,20));
		headline.setBounds(120,30,300,30);
		image.add(headline);

		JMenuBar mb=new JMenuBar();
		mb.setBounds(0,0,1550,30);
		image.add(mb);

		JMenu hotelM= new JMenu("hotel managment");
		hotelM.setForeground(Color.red);
		mb.add(hotelM);

		JMenu admin= new JMenu("admin");
		admin.setForeground(Color.blue);
		mb.add(admin);

		reception= new JMenuItem("reception");
		reception.addActionListener(this);
		reception.addActionListener(this);
		hotelM.add(reception);

		editEmployee= new JMenuItem("edit employees");
		editEmployee.addActionListener(this);
		admin.add(editEmployee);

		addRoom= new JMenuItem("edit rooms");
		addRoom.addActionListener(this);
		admin.add(addRoom);

		setVisible(true);
	}

	public static void main(String[] args) {
		new Dashboard();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==editEmployee) //option number 1
			new AddStuff();
		else if(e.getActionCommand().equals("edit rooms")){ //option number 2
			new AddRoom();
		}
		else if (e.getSource()==reception) {
			new Reception();
			//setVisible(false);

		}

	}
}
