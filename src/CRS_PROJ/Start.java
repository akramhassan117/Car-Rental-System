package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Define the Start class that extends JFrame and implements ActionListener
public class Start extends JFrame implements ActionListener {
    JButton Admin, Custom, Sign,A_sign; // Buttons
    JTextField text2field; // Text field for card number
    Start() {

        setTitle("Car Rental System"); // Set the title of the frame
        setSize(1500, 700); // Set the size of the frame
        setVisible(true); // Make the frame visible
        setLocation(20, 0); // Set the location of the frame
        setLayout(null); // Use null layout to manually position components
        Dimension content_size = getContentPane().getSize();

        // Load and display an image as an icon
        ImageIcon iconn = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/front_page3.jpg"));
        Image iconn2 = iconn.getImage().getScaledInstance(content_size.width, content_size.height, Image.SCALE_DEFAULT);
        ImageIcon iconn3 = new ImageIcon(iconn2);
        JLabel k = new JLabel(iconn3);
        k.setBounds(-220, -150, 1920, 960);
        add(k);

        // Inside your Start class constructor
        Admin = new JButton("ADMIN");
        Admin.setBounds(250, 730, 150, 50); // Adjust the position and size
        Admin.setUI(new buttons()); // Apply the custom UI
        Admin.addActionListener(this);
        k.add(Admin);

        Custom = new JButton("CUSTOMER");
        Custom.setBounds(420, 730, 150, 50); // Adjust the position and size
        Custom.setUI(new buttons()); // Apply the custom UI
        Custom.addActionListener(this);
        k.add(Custom);

        Sign = new JButton("CUSTOMER  SIGN UP");
        Sign.setBounds(1500, 730, 190, 50); // Adjust the position and size
        Sign.setUI(new buttons()); // Apply the custom UI
        Sign.addActionListener(this);
        k.add(Sign);

        A_sign = new JButton("ADMIN SIGN UP");
        A_sign.setBounds(1300, 730, 190, 50); // Adjust the position and size
        A_sign.setUI(new buttons()); // Apply the custom UI
        A_sign.addActionListener(this);
        k.add(A_sign);

        getContentPane().setBackground(Color.WHITE); // Set the background color to white
    }

    // ActionListener implementation for button clicks
    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == Custom) {
                setVisible(false);
                new Customer(" ").setVisible(true);
            }if (ae.getSource() == Admin) {
                setVisible(false);
                new Admin().setVisible(true);
            } else if (ae.getSource() == Sign) {
                setVisible(false);// If SIGN UP button is clicked
                new Admin_signup().setVisible(true);
            }
            if(ae.getSource()==A_sign){
                setVisible(false);
                new Admin_signup().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new Start();
    }
}
