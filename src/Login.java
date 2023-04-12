import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{

	JTextField userName, passField;
	JButton login, cancle;
	Login(){
		getContentPane().setBackground(Color.white);
		setLayout(null);
		setBounds(500,200,650,300);

		JLabel user=new JLabel("UserName");
		user.setBounds(40,20, 100,20);
		user.setFont(new Font("gisha", Font.PLAIN,20));
		add(user);
		userName=new JTextField();
		userName.setBounds(150,20,100,20);
		add(userName);

		JLabel password=new JLabel("Password");
		password.setBounds(40,60, 100,20);
		password.setFont(new Font("gisha", Font.PLAIN,20));
		add(password);
		passField=new JTextField();
		passField.setBounds(150,60,100,20);
		add(passField);

		login=new JButton("login");
		login.setBounds(170, 150, 90, 30);
		login.setFont(new Font("gisha", Font.PLAIN,20));
		login.setBackground(Color.black);
		login.setForeground(Color.white);
		login.addActionListener(this);
		add(login);

		cancle=new JButton("cancel");
		cancle.setBounds(30, 150, 90, 30);
		cancle.setFont(new Font("gisha", Font.PLAIN,20));
		cancle.setBackground(Color.black);
		cancle.setForeground(Color.white);
		cancle.addActionListener(this);
		add(cancle);

		ImageIcon people=new ImageIcon(ClassLoader.getSystemResource("icons/login_people.png"));
		Image i2=people.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		JLabel image=new JLabel(i3);
		image.setBounds(300,0,300,300);
		add(image);
		setVisible(true);
	}
	public static void main(String[] args) {
		new Login();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() ==login){
			String user= userName.getText();
			String pass=passField.getText();

			try{
				Conn c=new Conn();
				String query="select * from login where username = '"+user+"' and password = '"+pass+"'";
				ResultSet rs=c.s.executeQuery(query);
				if(rs.next()){
					if(rs.getString("isadmin").equals("yes")) {
						new Dashboard();
					}
					else {
						new Reception();
					}
					setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null,"invaild username or password");
				}

			} catch (Exception o){
				o.printStackTrace();
			}
		}
		else if(e.getSource()==cancle){
			setVisible(false);
		}

	}
}
