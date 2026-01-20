package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.Date;
import java.awt.event.*;

public class Checkout extends JFrame implements ActionListener {

    Choice ccustomer;
    JLabel lblroomnumber, lblcheckintime, lblcheckouttime;
    JButton checkout, back;

    Checkout() {

        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        JLabel text = new JLabel("Checkout");
        text.setBounds(100, 20, 150, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lblid = new JLabel("Customer Id");
        lblid.setBounds(30, 80, 100, 30);
        add(lblid);

        ccustomer = new Choice();
        ccustomer.setBounds(150, 80, 150, 25);
        add(ccustomer);

        // Tick icon
        ImageIcon i1 = new ImageIcon(
                Checkout.class.getResource("/icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        JLabel tick = new JLabel(new ImageIcon(i2));
        tick.setBounds(300, 80, 20, 20);
        add(tick);

        JLabel lblroom = new JLabel("Room Number");
        lblroom.setBounds(30, 130, 100, 30);
        add(lblroom);

        lblroomnumber = new JLabel();
        lblroomnumber.setBounds(150, 130, 150, 30);
        add(lblroomnumber);

        JLabel lblcheckin = new JLabel("Checkin Time");
        lblcheckin.setBounds(30, 180, 100, 30);
        add(lblcheckin);

        lblcheckintime = new JLabel();
        lblcheckintime.setBounds(150, 180, 250, 30);
        add(lblcheckintime);

        JLabel lblcheckout = new JLabel("Checkout Time");
        lblcheckout.setBounds(30, 230, 100, 30);
        add(lblcheckout);

        Date date = new Date();
        lblcheckouttime = new JLabel("" + date);
        lblcheckouttime.setBounds(150, 230, 250, 30);
        add(lblcheckouttime);

        checkout = new JButton("Checkout");
        checkout.setBackground(Color.BLACK);
        checkout.setForeground(Color.WHITE);
        checkout.setBounds(30, 280, 120, 30);
        checkout.addActionListener(this);
        add(checkout);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(170, 280, 120, 30);
        back.addActionListener(this);
        add(back);

        // ================= LOAD CUSTOMER IDS =================
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");

            while (rs.next()) {
                ccustomer.add(rs.getString("number"));
            }

            // 🔥 AUTO-SELECT FIRST CUSTOMER
            if (ccustomer.getItemCount() > 0) {
                ccustomer.select(0);

                ResultSet rs2 = c.s.executeQuery(
                        "select * from customer where number = '"
                                + ccustomer.getSelectedItem() + "'");

                if (rs2.next()) {
                    lblroomnumber.setText(rs2.getString("room"));
                    lblcheckintime.setText(rs2.getString("checkintime"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // ================= UPDATE DATA ON SELECTION =================
        ccustomer.addItemListener(e -> {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(
                        "select * from customer where number = '"
                                + ccustomer.getSelectedItem() + "'");

                if (rs.next()) {
                    lblroomnumber.setText(rs.getString("room"));
                    lblcheckintime.setText(rs.getString("checkintime"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // Right-side image
        ImageIcon i4 = new ImageIcon(
                Checkout.class.getResource("/icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        JLabel image = new JLabel(new ImageIcon(i5));
        image.setBounds(350, 50, 400, 250);
        add(image);

        setBounds(300, 200, 800, 400);
        setVisible(true);
    }

    // ================= BUTTON ACTION =================
    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == checkout) {

            String customerId = ccustomer.getSelectedItem();

            if (customerId == null || customerId.equals("")) {
                JOptionPane.showMessageDialog(null, "Please select a customer");
                return;
            }

            String query1 = "delete from customer where number = '" + customerId + "'";
            String query2 = "update room set availability = 'Available' where roomnumber = '"
                    + lblroomnumber.getText() + "'";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Checkout Successful");
                setVisible(false);
                new Reception();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            setVisible(false);
            new Reception();
        }
    }

    public static void main(String[] args) {
        new Checkout();
    }
}
