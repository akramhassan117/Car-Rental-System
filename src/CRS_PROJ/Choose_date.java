package CRS_PROJ;

import com.toedter.calendar.JDateChooser;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class Choose_date extends JFrame implements ActionListener {
    JDateChooser dateChooser, dateChooser2;
    JButton Next;
    String D1, D2;

    String Name, Avg, Price, Model, username2, owner;

    Choose_date() {
    }

    Choose_date(String name, String avg, String price, String model, String username, String owner_name) {
        this.Name = name;
        this.Avg = avg;
        this.Price = price;
        this.Model = model;
        this.username2 = username;
        this.owner = owner_name;

        setSize(400, 300); // Set the size
        setVisible(true);
        setLocation(530, 280); // Set the location
        setLayout(null);

        JLabel DOB = new JLabel("Choose Date:");
        DOB.setBounds(10, 0, 400, 40);
        DOB.setFont(new Font("Osward", Font.BOLD, 15));
        DOB.setForeground(Color.BLACK);
        add(DOB);

        JLabel DOB2 = new JLabel("From:");
        DOB2.setBounds(10, 20, 400, 40);
        DOB2.setFont(new Font("Osward", Font.BOLD, 15));
        DOB2.setForeground(Color.BLACK);
        add(DOB2);

        JLabel DOB3 = new JLabel("To:");
        DOB3.setBounds(10, 80, 400, 40);
        DOB3.setFont(new Font("Osward", Font.BOLD, 15));
        DOB3.setForeground(Color.BLACK);
        add(DOB3);

        // DateChooser for Date of Birth
        dateChooser = new JDateChooser();
        dateChooser.setBounds(10, 60, 150, 25);
        dateChooser.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        dateChooser.setForeground(Color.WHITE);
        dateChooser.setBackground(Color.lightGray);
        add(dateChooser);

        dateChooser2 = new JDateChooser();
        dateChooser2.setBounds(10, 120, 150, 25);
        dateChooser2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        dateChooser2.setForeground(Color.WHITE);
        dateChooser2.setBackground(Color.lightGray);
        add(dateChooser2);

        Next = new JButton("Next");
        Next.setBounds(10, 200, 150, 25); // Adjust the position and size
        Next.setUI(new buttons()); // Apply the custom UI
        Next.addActionListener(this);
        Next.setForeground(Color.white);
        add(Next);
    }

    public void sendEmail(String Email_Name, String Email) {
        System.out.println(Email);
        String username = "carrentals189@gmail.com";
        String password = "ihocldkxhdszglzd"; // Replace with your actual password

        String subject = "Car Booking Information";
        String message = "-------------------------------------\n" +
                "            RECEIPT\n" +
                "-------------------------------------\n" +
                " Mr. " + Email_Name + "\n" +  // Assuming username2 is a variable in your class
                Name + "\n" +
                Model + "\n" +
                Price + "\n" +
                owner + "\n" +
                "-------------------------------------\n" +
                "From: " + D1 + "\n" +
                "To: " + D2 + "\n" +
                "-------------------------------------\n" +
                "Terms and Conditions:\n" +
                "1. All sales are final.\n" +
                "2. No refunds or exchanges.\n" +
                "3. Warranty information can be obtained from the manufacturer.\n" +
                "4. Payment is due upon receipt of goods.\n" +
                "5. Thank you for your business!\n" +
                "-------------------------------------";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Email));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Changed Transport.send to Transport.send(mimeMessage, mimeMessage.getAllRecipients())
            // This ensures that multiple recipients are properly handled
            Transport.send(mimeMessage, mimeMessage.getAllRecipients());

            JOptionPane.showMessageDialog(null, "Order Confirmed \n Check Your Email\nAlso Check Your Spam Folder");
        } catch (MessagingException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error sending email: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == Next) {
                // Get the current date
                java.util.Date currentDate = new java.util.Date();

                // Get the selected dates from the date choosers
                D1 = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
                D2 = ((JTextField) dateChooser2.getDateEditor().getUiComponent()).getText();

                // Parse the selected dates into Date objects
                java.util.Date date1 = new SimpleDateFormat("MMM dd, yyyy").parse(D1);
                java.util.Date date2 = new SimpleDateFormat("MMM dd, yyyy").parse(D2);
                System.out.println("Dates are: \n");
                System.out.println(D1);
                System.out.println(D2);

                // Check if D1 is greater than the current date and less than D2
                if (date1.after(currentDate) && date1.before(date2)) {
                    Conn c1 = new Conn();
                    String q1 = "insert into reciept values( '" + username2 + "','" + Name + "','" + Model + "','" + Price + "','" + Avg + "','" + owner + "','" + D1 + "','" + D2 + "')";
                    String q2 = "SELECT name, email FROM sign_up2 WHERE username2 = '" + username2 + "'";

                    ResultSet rs = c1.s.executeQuery(q2);

                    // Check if there are any results before attempting to access them
                    if (rs.next()) {
                        // Retrieve data from the result set
                        String name = rs.getString("name");
                        String email = rs.getString("email");

                        // Close the ResultSet to release resources
                        rs.close();

                        // Assuming sendEmail is a method that sends an email
                        sendEmail(name, email);
                    }
                    c1.s.executeUpdate(q1);

                    setVisible(false);
                    new addOrder(username2).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a valid date range.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Choose_date c1 = new Choose_date();
        String username = c1.username2;
        String owner = c1.owner;
        new Choose_date("", "", "", "", username, owner);
    }
}
