package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class AddDriver extends JFrame implements ActionListener {

    JButton addR, cancel;
    JTextField tfname, tfage, tfcompany, tfbranch;
    JComboBox<String> gendercombo, drivercombo;

    AddDriver() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel heading = new JLabel("Add Driver");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(150, 10, 200, 30);
        add(heading);

        JLabel lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setBounds(60, 70, 120, 30);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 70, 150, 30);
        add(tfname);

        // Restrict name to alphabets
        tfname.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isLetter(e.getKeyChar()) && !Character.isWhitespace(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        JLabel lblage = new JLabel("Age");
        lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblage.setBounds(60, 110, 120, 30);
        add(lblage);

        tfage = new JTextField();
        tfage.setBounds(200, 110, 150, 30);
        add(tfage);

        // Restrict age to numeric
        tfage.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }
        });

        JLabel lblgender = new JLabel("Gender");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setBounds(60, 150, 120, 30);
        add(lblgender);

        String genderOptions[] = {"Male", "Female"};
        gendercombo = new JComboBox<>(genderOptions);
        gendercombo.setBounds(200, 150, 150, 30);
        gendercombo.setBackground(Color.WHITE);
        add(gendercombo);

        JLabel lblcompany = new JLabel("Car Company");
        lblcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblcompany.setBounds(60, 190, 120, 30);
        add(lblcompany);

        tfcompany = new JTextField();
        tfcompany.setBounds(200, 190, 150, 30);
        add(tfcompany);

        JLabel lblbranch = new JLabel("Branch");
        lblbranch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblbranch.setBounds(60, 230, 120, 30);
        add(lblbranch);

        tfbranch = new JTextField();
        tfbranch.setBounds(200, 230, 150, 30);
        add(tfbranch);

        JLabel lblavailable = new JLabel("Availability");
        lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblavailable.setBounds(60, 270, 120, 30);
        add(lblavailable);

        String driverOptions[] = {"Available", "Busy"};
        drivercombo = new JComboBox<>(driverOptions);
        drivercombo.setBounds(200, 270, 150, 30);
        drivercombo.setBackground(Color.WHITE);
        add(drivercombo);

        addR = new JButton("Add Driver");
        addR.setForeground(Color.WHITE);
        addR.setBackground(Color.BLACK);
        addR.setBounds(60, 330, 130, 30);
        addR.addActionListener(this);
        add(addR);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220, 330, 130, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setBounds(300, 200, 980, 470);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addR) {

            String name = tfname.getText().trim();
            String age = tfage.getText().trim();
            String gender = (String) gendercombo.getSelectedItem();
            String company = tfcompany.getText().trim();
            String branch = tfbranch.getText().trim();
            String available = (String) drivercombo.getSelectedItem();

            // Validation
            if (name.equals("") || age.equals("") || company.equals("") || branch.equals("")) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            // Age Validation
            int ageValue;
            try {
                ageValue = Integer.parseInt(age);
                if (ageValue <= 0 || ageValue > 100) {
                    JOptionPane.showMessageDialog(null, "Enter a valid age (1-100)");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Age must be numeric");
                return;
            }

            // SQL Insert
            try {
                Conn conn = new Conn();
                String query = "INSERT INTO driver(name, age, gender, company, branch, available) VALUES(?,?,?,?,?,?)";
                PreparedStatement pst = conn.c.prepareStatement(query);

                pst.setString(1, name);
                pst.setInt(2, ageValue);
                pst.setString(3, gender);
                pst.setString(4, company);
                pst.setString(5, branch);
                pst.setString(6, available);

                pst.executeUpdate();
                pst.close();

                JOptionPane.showMessageDialog(null, "Driver Added Successfully ✨");

                // Clear fields
                tfname.setText("");
                tfage.setText("");
                tfcompany.setText("");
                tfbranch.setText("");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddDriver();
    }
}
