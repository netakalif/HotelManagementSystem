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

public class NewAccount extends JFrame implements ActionListener{

    JTextField userName, passField;
    JButton NewAccount, cancle;
    JCheckBox isAdminBox;
    NewAccount(){
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

        isAdminBox=new JCheckBox("Is Admin");
        isAdminBox.setBounds(40,100, 200,20);
        isAdminBox.setBackground(Color.white);
        isAdminBox.setFont(new Font("gisha", Font.PLAIN,15));
        add(isAdminBox);
        
        NewAccount=new JButton("Create Account");
        NewAccount.setBounds(50, 200, 150, 30);
        NewAccount.setFont(new Font("gisha", Font.PLAIN,15));
        NewAccount.setBackground(Color.black);
        NewAccount.setForeground(Color.white);
        NewAccount.addActionListener(this);
        add(NewAccount);

        ImageIcon people=new ImageIcon(ClassLoader.getSystemResource("icons/login_people.png"));
        Image i2=people.getImage().getScaledInstance(300, 200, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image=new JLabel(i3);
        image.setBounds(300,0,300,300);
        add(image);
        setVisible(true);
    }
    public static void main(String[] args) {
        new NewAccount();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
           String user= userName.getText();
           String pass=passField.getText();

           try{
        	   Conn c=new Conn();
        	   String query="";
        	   if(isAdminBox.isSelected()) {
        		   query = "insert into login values('" + user + "','" + pass +  "','yes')";   
        	   }
        	   else {
        		   query = "insert into login values('" + user + "','" + pass +  "','no')";
			}
			c.s.executeUpdate(query);
			JOptionPane.showMessageDialog(null,"Account Added Successfuly");
	        setVisible(false);

           } catch (Exception o){
            o.printStackTrace();
           }
    }
}
