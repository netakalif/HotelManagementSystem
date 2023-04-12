import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.event.*;
import java.awt.*;

public class Reception extends JFrame implements ActionListener {
    JButton updateRoom,searchRoom,logout;
    JButton roomsToClean,checkIn,department,allEmployee, customerInfo,managerInfo,checkout,addCharges;
    Reception(){
        getContentPane().setBackground(Color.white);
        setLayout(null);
        setBounds(500,200,800,570);
        
        checkIn=new JButton("Check In");
        checkIn.setBounds(10,30,200,30);
        checkIn.setBackground(Color.black);
        checkIn.setForeground(Color.white);
        checkIn.addActionListener(this);
        add(checkIn);
        
        checkout=new JButton("Check Out");
        checkout.setBounds(10,70,200,30);
        checkout.setBackground(Color.black);
        checkout.setForeground(Color.white);
        checkout.addActionListener(this);
        add(checkout);

        roomsToClean=new JButton("Rooms To Clean");
        roomsToClean.setBounds(10,110,200,30);
        roomsToClean.setBackground(Color.black);
        roomsToClean.setForeground(Color.white);
        roomsToClean.addActionListener(this);
        add(roomsToClean);

        allEmployee=new JButton("All Employees");
        allEmployee.setBounds(10,150,200,30);
        allEmployee.setBackground(Color.black);
        allEmployee.setForeground(Color.white);
        allEmployee.addActionListener(this);
        add(allEmployee);

        customerInfo=new JButton("Customers Info");
        customerInfo.setBounds(10,190,200,30);
        customerInfo.setBackground(Color.black);
        customerInfo.setForeground(Color.white);
        customerInfo.addActionListener(this);
        add(customerInfo);

        searchRoom=new JButton("Search Room");
        searchRoom.setBounds(10,230,200,30);
        searchRoom.setBackground(Color.black);
        searchRoom.setForeground(Color.white);
        searchRoom.addActionListener(this);
        add(searchRoom);

        addCharges=new JButton("Add Charges");
        addCharges.setBounds(10,270,200,30);
        addCharges.setBackground(Color.black);
        addCharges.setForeground(Color.white);
        addCharges.addActionListener(this);
        add(addCharges);


        logout=new JButton("Logout");
        logout.setBounds(10,480,200,30);
        logout.setBackground(Color.black);
        logout.setForeground(Color.white);
        logout.addActionListener(this);
        add(logout);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        Image i2=i1.getImage().getScaledInstance(570, 570, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(220,0,570,570);
        add(image);
        
        setVisible(true);
    }
	@Override
	public void actionPerformed(ActionEvent e) {
        if(e.getSource()==checkIn) {
            new SearchRoomForCheckIn();
            setVisible(false);
        }
        else if(e.getSource()==checkout) {
            new CheckOut();
            setVisible(false);
        }
        else if(e.getSource()==roomsToClean) {
            new RoomsToClean();
            setVisible(false);
        }
        else if(e.getSource()==allEmployee) {
            new AllEmployees();
            setVisible(false);
        }
        else if(e.getSource()==customerInfo){
            new CustomerInfo();
            setVisible(false);
        }
		else if(e.getSource()==searchRoom) {
			new SearchRoom();
			setVisible(false);
		}
        else if(e.getSource()==addCharges) {
			new AddCharge();
			setVisible(false);
		}
        else if(e.getSource()==logout) {
			dispose();
            new Login();
		}
		
	}
	public static void main(String[] args) {
		new Reception();
	}
}
