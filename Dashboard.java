
package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener{
    Dashboard(){
      setBounds(0,0,1550,1000);
      
   setLayout(null);
      
      ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
     Image i2 = i1.getImage().getScaledInstance(1550,1000, Image.SCALE_DEFAULT );
     ImageIcon i3 = new ImageIcon(i2);
     JLabel image = new JLabel(i3);
     image.setBounds(0,0,1550,1000);
     add(image);
     
     
     JLabel text = new JLabel("THE TAJ GROUP WELCOME YOU");
     text.setBounds(400,80,1000,50);
     text.setFont(new Font("Tahoma", Font.PLAIN, 46));
     text.setForeground(Color.WHITE);
     image.add(text);
     
     JMenuBar mb = new JMenuBar();
     mb.setBounds(0,0,1550,30);
     image.add(mb);
     
     JMenu hotel = new JMenu("HOTEL MANAGEMENT");
     hotel.setForeground(Color.RED);
     mb.add(hotel);
     
     JMenuItem reception = new JMenuItem("RECEPTION");
     reception.addActionListener(this);
     hotel.add(reception);
     
     JMenu admin = new JMenu("ADMIN");
     admin.setForeground(Color.BLUE);
     mb.add(admin);
    
     JMenuItem addemployee = new JMenuItem("ADD EMPLOYEE");
     addemployee.addActionListener(this);
     admin.add(addemployee);
     
     JMenuItem addrooms = new JMenuItem ("ADD ROOMS");
     addrooms.addActionListener(this);
     admin.add(addrooms);
     
     JMenuItem adddriver = new JMenuItem ("ADD DRIVER");
     adddriver.addActionListener(this);
     admin.add(adddriver);
     
     
      setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
       String msg = ae.getActionCommand();
    
    if (msg.equals("ADD EMPLOYEE")) {
        new AddEmployee();
    } else if (msg.equals("ADD ROOMS")) {
        new AddRooms();
    } else if (msg.equals("ADD DRIVER")) {
        new AddDriver();
    } else if (msg.equals("RECEPTION")) {
        new Reception();
    }
    }
    
    public static void main(String[] args) {
        new Dashboard();
    }
}
