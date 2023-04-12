
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.*;
public class HotelManagmentSystem extends JFrame implements ActionListener{

    HotelManagmentSystem(){
        setBounds(200,5,1024,768);
        setLayout(null);

        ImageIcon background=new ImageIcon(ClassLoader.getSystemResource("icons/login_hotel_picture.jpg"));
        JLabel image=new JLabel(background);
        image.setBounds(0,0,1024,768);
        add(image);

        JLabel text=new JLabel("hotel managment system");
        text.setForeground(Color.PINK);
        text.setFont(new Font("gisha", Font.PLAIN,70));
        text.setBounds(20, 30, 800, 80);
        image.add(text);

        JButton next=new JButton("Next");
        next.setForeground(Color.black);
        next.setBackground(Color.white);
        next.addActionListener(this);
        next.setBounds(900, 600, 100, 30);
        next.setFont(new Font("gisha", Font.PLAIN,20));
        image.add(next);
        setVisible(true);

        while(true){
            text.setVisible(false);
            try{
                Thread.sleep(500);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            text.setVisible(true);
            try{
                Thread.sleep(500);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        setVisible(false);
        new Login();
        
    }
    public static void main(String[] args) {
        new HotelManagmentSystem();
    }

}
