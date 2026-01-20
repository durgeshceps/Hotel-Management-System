package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class UpdateCheck extends JFrame implements ActionListener {

    Choice ccustomer;
    JTextField tfroom, tfname, tfcheckin, tfpaid, tfpending;
    JButton check, update, back;

    public UpdateCheck() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Update Status");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(90, 20, 200, 30);
        text.setForeground(Color.BLUE);
        add(text);

        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 20);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(200, 80, 150, 25);
        add(ccustomer);

        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 120, 100, 20);
        add(lblroom);

        tfroom = new JTextField();
        tfroom.setBounds(200, 120, 150, 25);
        add(tfroom);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(30, 160, 100, 20);
        add(lblname);

        tfname = new JTextField();
        tfname.setBounds(200, 160, 150, 25);
        add(tfname);

        JLabel lblcheckin = new JLabel("Check-in Time");
        lblcheckin.setBounds(30, 200, 100, 20);
        add(lblcheckin);

        tfcheckin = new JTextField();
        tfcheckin.setBounds(200, 200, 150, 25);
        add(tfcheckin);

        JLabel lblpaid = new JLabel("Amount Paid");
        lblpaid.setBounds(30, 240, 100, 20);
        add(lblpaid);

        tfpaid = new JTextField();
        tfpaid.setBounds(200, 240, 150, 25);
        add(tfpaid);

        JLabel lblpending = new JLabel("Pending Amount");
        lblpending.setBounds(30, 280, 120, 20);
        add(lblpending);

        tfpending = new JTextField();
        tfpending.setBounds(200, 280, 150, 25);
        tfpending.setEditable(false);
        add(tfpending);

        check = new JButton("Check");
        check.setBounds(30, 340, 100, 30);
        check.setBackground(Color.BLUE);
        check.setForeground(Color.WHITE);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBounds(150, 340, 100, 30);
        update.setBackground(Color.RED);
        update.setForeground(Color.WHITE);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBounds(270, 340, 100, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);

        setBounds(300, 240, 980, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == check) {

            try {
                Conn c = new Conn();
                String id = ccustomer.getSelectedItem();

                ResultSet rs = c.s.executeQuery(
                        "select * from customer where number = '" + id + "'"
                );

                if (rs.next()) {
                    tfroom.setText(rs.getString("room"));
                    tfname.setText(rs.getString("name"));
                    tfcheckin.setText(rs.getString("checkintime"));
                    tfpaid.setText(rs.getString("deposite"));
                }

                ResultSet rs2 = c.s.executeQuery(
                        "select * from room where roomnumber = '" + tfroom.getText() + "'"
                );

                if (rs2.next()) {
                    int price = Integer.parseInt(rs2.getString("price"));
                    int paid = Integer.parseInt(tfpaid.getText());
                    tfpending.setText(String.valueOf(price - paid));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (ae.getSource() == update) {

            try {
                Conn c = new Conn();

                String query = "update customer set " +
                        "room = '" + tfroom.getText() + "', " +
                        "name = '" + tfname.getText() + "', " +
                        "checkintime = '" + tfcheckin.getText() + "', " +
                        "deposite = '" + tfpaid.getText() + "' " +
                        "where number = '" + ccustomer.getSelectedItem() + "'";

                c.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Data Updated Successfully");

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new UpdateCheck();
    }
}
