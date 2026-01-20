
package hotel.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddEmployee extends JFrame  implements ActionListener{
    
    JTextField tfname,tfemail, tfphone,tfage, tfsalary , tfaadhar;
    JRadioButton  rbmale,rbfemale ;
    JButton submit;
    JComboBox cbjob;
    
    
    AddEmployee(){
     
        setLayout(null);
        
        JLabel Iblname = new JLabel("NAME");
        Iblname.setBounds(60,30,120,30);
        Iblname.setFont(new Font("Tahoma",Font.PLAIN, 17));
        add(Iblname);
        
        tfname = new JTextField("");
        tfname.setBounds(200,30,150,30);
        add(tfname);
        
        JLabel Iblage = new JLabel("AGE");
        Iblage.setBounds(60,80,120,30);
        Iblage.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(Iblage);
        
        tfage = new JTextField();
        tfage.setBounds(200,80,150,30);
        add(tfage);
        
        JLabel Iblgender = new JLabel("GENDER");
        Iblgender.setBounds(60,120,120,30);
        Iblgender.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(Iblgender);
        
         rbmale = new JRadioButton("Male");
        rbmale.setBounds(200,120,70,30);
        rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbmale.setBackground(Color.WHITE);
        add(rbmale);
        
         rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(280,120,170,30);
        rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbfemale.setBackground(Color.WHITE);
        add(rbfemale);
        
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        
        JLabel Ibljob = new JLabel ("JOB");
        Ibljob.setBounds(60,180,120,30);
        Ibljob.setFont(new Font("Tahoma", Font.PLAIN,17));
        add(Ibljob);
        
        String str[] = { "Front Desk Clerks", "Porters", "Housekeeping", " Kitchen Staff", "Room Service","Chefs","Waiter/Waitress","Accountant", " Manager"};
         cbjob = new JComboBox(str);
        cbjob.setBounds(200,180,150,30);
        cbjob.setBackground(Color.WHITE);
        add(cbjob);
       
        
        JLabel Iblsalary = new JLabel("SALARY");
        Iblsalary.setBounds(60,230,120,30);
        Iblsalary.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(Iblsalary);
        
       tfsalary = new JTextField();
        tfsalary.setBounds(200,230,150,30);
        add(tfsalary);
        
        JLabel Iblphone = new JLabel("PHONE");
        Iblphone.setBounds(60,280,120,30);
        Iblphone.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(Iblphone);
        
         tfphone = new JTextField();
        tfphone.setBounds(200,280,150,30);
        add(tfphone);
        
         JLabel Iblemail = new JLabel("EMAIL");
        Iblemail.setBounds(60,330,120,30);
        Iblemail.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(Iblemail);
        
         tfemail = new JTextField();
        tfemail.setBounds(200,330,150,30);
        add(tfemail);
        
        JLabel Iblaadhar = new JLabel("AADHAR");
        Iblaadhar.setBounds(60,380,120,30);
        Iblaadhar.setFont(new Font("Tahoma",Font.PLAIN,17));
        add(Iblaadhar);
        
         tfaadhar = new JTextField();
        tfaadhar.setBounds(200,380,150,30);
        add(tfaadhar);
        
        
         submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200,430,150,30);
        submit.addActionListener(this);
        add(submit);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380,60,450,370);   // image ko crop kar sakte ho
        add(image);
        
        getContentPane().setBackground(Color.WHITE);
        setBounds(350,200,850,540);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText();
        String age = tfage.getText();
        String salary = tfsalary.getText();
        String phone = tfphone.getText();
        String email = tfemail.getText();
        String aadhar = tfaadhar.getText();
        
        String gender = null;
        
        
        if (name.equals("")) {
            JOptionPane.showMessageDialog(null,"Name should not be empty");
        }
        
        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()){
            gender = "Female";
        }
        
        String job = (String) cbjob.getSelectedItem();
        
        try {
            
            Conn conn = new Conn();
            
            String query = "insert into employee values('"+name+"', '"+age+"', '"+gender+"', '"+job+"', '"+salary+"', '"+phone+"', '"+email+"', '"+aadhar+"')";
           
            conn.s.executeUpdate(query);
            
            JOptionPane.showMessageDialog(null, "Employee added successfully");
            
            setVisible(false);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
        
    
    public static void main(String[] args) {
        new AddEmployee();
    }
}
