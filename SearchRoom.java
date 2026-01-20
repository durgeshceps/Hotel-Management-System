
package hotel.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;


public class SearchRoom extends JFrame implements ActionListener  {
    
    JTable table;
    JButton back , submit;
    JComboBox bedType;
    JCheckBox available;
    
    SearchRoom() {
        
        
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
      JLabel text = new JLabel("Search for Room");
      text.setFont(new Font ("Tahoma", Font.PLAIN, 20));
      text.setBounds(400,30,200,30);
      add(text);
      
      JLabel lblbed = new JLabel ("Bed Type");
      lblbed.setBounds(50,100,100,20);
      add(lblbed);
      
     bedType = new JComboBox (new String[] {"Single Bed", "Double Bed"});
     bedType.setBounds (150,100,150,25);
     bedType.setBackground(Color.WHITE);
     add(bedType);
      
     available = new JCheckBox("Only display Available");
     available.setBounds(650,100,150,25);
     available.setBackground(Color.WHITE);
     add(available);
             
        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50,160,100,20);
        add(l1);
        
         JLabel l2 = new JLabel("Availibility ");
        l2.setBounds(250,160,100,20);
        add(l2);
        
         JLabel l3 = new JLabel("Status ");
        l3.setBounds(500,160,100,20);
        add(l3);
        
         JLabel l4 = new JLabel("Price ");
        l4.setBounds(650,160,100,20);
        add(l4);
        
           JLabel l5 = new JLabel("Bed Type ");
        l5.setBounds(850,160,100,20);
        add(l5);
        
        table = new JTable();
        table.setBounds(0,200,1000,300);
        add(table);
        
        try{
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from room");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }

        
        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setBounds(300,520,120,30);
        add(submit);
        
        
        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        back.setBounds(500,520,120,30);
        add(back);
        
        setBounds(300,200,1000,600);
        setVisible(true);
    
    }
    
    public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == submit) {
        try {
            String bed = (String) bedType.getSelectedItem();

            // Base query
            String query1 = "SELECT * FROM room WHERE bed_type = '" + bed + "'";

            // Query when checkbox is selected
            String query2 = "SELECT * FROM room WHERE availability = 'Available' AND bed_type = '" + bed + "'";

            Conn conn = new Conn();
            ResultSet rs;

            if (available.isSelected()) {
                rs = conn.s.executeQuery(query2);
            } else {
                rs = conn.s.executeQuery(query1);
            }

            table.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            e.printStackTrace();
        }

    } else {
        setVisible(false);
        new Reception();
    }
}

 
    public static void main(String[] args) {
        new SearchRoom();
    }
    
}
