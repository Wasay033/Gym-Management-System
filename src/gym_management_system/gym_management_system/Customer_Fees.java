package gym_management_system;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Customer_Fees extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l9;
    JButton bt1, bt2;
    Choice ch1, ch2;
    JTextField tf1, tf2, tf3, tf4, tf6;
    JPanel p1, p2, p3;

    Customer_Fees() {
        super("Customer Fees");
        setSize(670, 300);
        setLocation(270, 250);
        setResizable(false);
        Font f1 = new Font("Lucida Fax", Font.BOLD, 25);
        Font f = new Font("MS UI Gothic", Font.BOLD, 18);
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("gym_management_system/fees_customer.png"));
        Image im = img.getImage().getScaledInstance(140, 200, Image.SCALE_DEFAULT);
        l6 = new JLabel(new ImageIcon(im));

        l1 = new JLabel("Customer Id");
        l2 = new JLabel("Name");
        l3 = new JLabel("Category");
        l7 = new JLabel("Month");
        l4 = new JLabel("Charge fees per month");
        l5 = new JLabel("Pay Fees");
        l9 = new JLabel("Trainer Name");
        l5.setFont(f1);
        l5.setHorizontalAlignment(JLabel.CENTER);

        tf1 = new JTextField();
        tf2 = new JTextField();
        tf3 = new JTextField();
        tf4 = new JTextField();
        tf6 = new JTextField();

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l7.setFont(f);
        l9.setFont(f);

        tf1.setFont(f);
        tf2.setFont(f);
        tf3.setFont(f);
        tf4.setFont(f);
        tf6.setFont(f);

        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        l3.setForeground(Color.WHITE);
        l4.setForeground(Color.WHITE);
        l7.setForeground(Color.WHITE);
        l9.setForeground(Color.WHITE);
        l5.setForeground(Color.WHITE);

        ch1 = new Choice();
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select id from add_customer";
            ResultSet rest = obj.stm.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        ch1.setFont(f);

        ch2 = new Choice();
        ch2.add("January");
        ch2.add("February");
        ch2.add("March");
        ch2.add("April");
        ch2.add("May");
        ch2.add("June");
        ch2.add("July");
        ch2.add("August");
        ch2.add("September");
        ch2.add("October");
        ch2.add("November");
        ch2.add("December");

        ch2.setFont(f);

        bt1 = new JButton("Submit");
        bt2 = new JButton("Cancel");

        bt1.setBackground(Color.BLACK);
        bt1.setBackground(Color.CYAN);
        bt2.setBackground(Color.BLACK);
        bt2.setBackground(Color.RED);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1, 10, 10));
        p1.add(l5);

        p2 = new JPanel();
        p2.setLayout(new GridLayout(7, 2, 10, 10));
        p2.add(l1);
        p2.add(ch1);
        p2.add(l2);
        p2.add(tf1);
        p2.add(l3);
        p2.add(tf2);
        p2.add(l9);
        p2.add(tf6);
        p2.add(l7);
        p2.add(ch2);
        p2.add(l4);
        p2.add(tf3);
        p2.add(bt1);
        p2.add(bt2);

        p3 = new JPanel();
        p3.setLayout(new GridLayout(1, 1, 10, 10));
        p3.add(l6);

        p1.setBackground(Color.BLACK);
        p2.setBackground(Color.BLACK);
        p3.setBackground(Color.BLACK);

        setLayout(new BorderLayout(0, 0));

        add(p1, "North");
        add(p2, "Center");
        add(p3, "West");

        ch1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg) {
                int id = Integer.parseInt(ch1.getSelectedItem());
                try {
                    ConnectionClass obj1 = new ConnectionClass();
                    String q1 = "select * from add_customer where id='" + id + "'";
                    ResultSet rest1 = obj1.stm.executeQuery(q1);
                    while (rest1.next()) {
                        tf1.setText(rest1.getString("name"));
                        tf2.setText(rest1.getString("category"));
                        tf6.setText(rest1.getString("trainer_name"));
                    }
                    String tname = tf6.getText();
                    ConnectionClass obj2 = new ConnectionClass();
                    String q2 = "select fees from fee_trainer where name='" + tname + "'";
                    ResultSet rest = obj2.stm.executeQuery(q2);
                    while (rest.next()) {
                        tf3.setText(rest.getString("fees"));
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == bt1) {
                // Validate input fields
                String feesText = tf3.getText();
                if (feesText.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter fees per month.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                float fees = 0;
                try {
                    fees = Float.parseFloat(feesText);
                    if (fees <= 0) {
                        JOptionPane.showMessageDialog(null, "Fees per month must be a positive number.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid numeric value for fees per month.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // If all fields are valid, proceed with insertion
                String id = ch1.getSelectedItem();
                String name = tf1.getText();
                String category = tf2.getText();
                String trainer_name = tf6.getText();
                String month = ch2.getSelectedItem();

                // Insert payment details into database
                ConnectionClass obj2 = new ConnectionClass();
                String q2 = "insert into pay_customer values('" + id + "','" + name + "','" + category + "','" + fees + "','" + month + "','" + trainer_name + "')";
                obj2.stm.executeUpdate(q2);
                JOptionPane.showMessageDialog(null, "Your fees have been successfully submitted.");
                setVisible(false);

                // Send email notification to the customer
                String customerEmail = getEmail(id);
                sendEmailNotification(customerEmail, name,fees);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (e.getSource() == bt2) {
            int result = JOptionPane.showConfirmDialog(this, "Are you sure you want to cancel?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    private String getEmail(String customerId) {
        String email = "";
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT email FROM add_customer WHERE id='" + customerId + "'";
            ResultSet resultSet = obj.stm.executeQuery(query);
            if (resultSet.next()) {
                email = resultSet.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    private void sendEmailNotification(String recipientEmail, String customerName, float feeAmount) {
        // Sender's email address
        String senderEmail = "ahmadghumro@gmail.com";
        // Sender's app password
        String senderPassword = "kvol smqt tubv jqps";

        // Gym name
        String gymName = "Power Gym";

        // Setup properties for the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        // Create a session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a default MimeMessage object
            MimeMessage message = new MimeMessage(session);
            // Set From: header field of the header
            message.setFrom(new InternetAddress(senderEmail));
            // Set To: header field of the header
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            // Set Subject: header field
            message.setSubject("Payment Notification");
            // Now set the actual message
            message.setText("Dear " + customerName + ",\n\nYour payment of RS " + feeAmount + " has been successfully processed.\nThank you for your payment.\n\nBest regards,\n" + gymName);

            // Send message
            Transport.send(message);
            System.out.println("Email sent successfully to " + recipientEmail);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Customer_Fees().setVisible(true);
    }
}
