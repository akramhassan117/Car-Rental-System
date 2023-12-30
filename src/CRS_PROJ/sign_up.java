package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

import com.toedter.calendar.JDateChooser;

public class sign_up extends JFrame implements ActionListener {


    JDateChooser dateChooser;
    JButton Next,Sign;
    JComboBox<String> city_textfield;
    fields nametextfield, usernametextfield2, email_textfield2, CNICtextfield,nametextfield2;
    JPasswordField e_pass_textfield, c_pass_textfield;
    sign_up() {

        // JFrame setup
        setSize(1500, 700); // Set the size
        setVisible(true);
        setLocation(20, 0); // Set the location
        setLayout(null);// Use null layout to manually position components
        Dimension content_size = getContentPane().getSize();

        // Load and display an image as an icon
        ImageIcon iconn = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/sign_up.jpg"));
        Image iconn2 = iconn.getImage().getScaledInstance(content_size.width, content_size.height, Image.SCALE_DEFAULT);
        ImageIcon iconn3 = new ImageIcon(iconn2);
        JLabel k = new JLabel(iconn3);
        k.setBounds(-170, -100, 1820, 860);
        add(k);


        JLabel name2 = new JLabel("Name: ");
        name2.setBounds(210, 150, 150, 25);
        name2.setFont(new Font("Osward", Font.BOLD, 20));
        name2.setForeground(Color.WHITE);
        k.add(name2);

        JLabel username = new JLabel("Username: ");
        username.setBounds(210, 200, 150, 25);
        username.setFont(new Font("Osward", Font.BOLD, 20));
        username.setForeground(Color.WHITE);
        k.add(username);


        // Date of Birth Label
        JLabel DOB = new JLabel("Date of Birth:");
        DOB.setBounds(210, 240, 400, 40);
        DOB.setFont(new Font("Osward", Font.BOLD, 20));
        DOB.setForeground(Color.WHITE);
        k.add(DOB);

        // DateChooser for Date of Birth
        dateChooser = new JDateChooser();
        dateChooser.setBounds(395, 249, 200, 25);
        dateChooser.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        dateChooser.setForeground(Color.WHITE);
        dateChooser.setBackground(Color.lightGray);
        k.add(dateChooser);

        JLabel email = new JLabel("Email: ");
        email.setBounds(210, 300, 150, 25);
        email.setFont(new Font("Osward", Font.BOLD, 20));
        email.setForeground(Color.WHITE);
        k.add(email);


        JLabel password = new JLabel("Password: ");
        password.setBounds(210, 360, 150, 25);
        password.setFont(new Font("Osward", Font.BOLD, 20));
        password.setForeground(Color.WHITE);
        k.add(password);


        JLabel cpassword = new JLabel("Confirm Password: ");
        cpassword.setBounds(210, 420, 200, 25);
        cpassword.setFont(new Font("Osward", Font.BOLD, 20));
        cpassword.setForeground(Color.WHITE);
        k.add(cpassword);

        JLabel city = new JLabel("City:");
        city.setBounds(210, 480, 150, 25);
        city.setFont(new Font("Osward", Font.BOLD, 20));
        city.setForeground(Color.WHITE);
        k.add(city);

        Next = new JButton("Next");
        Next.setBounds(210, 700, 150, 25); // Adjust the position and size
        Next.setUI(new buttons()); // Apply the custom UI
        Next.addActionListener(this);
        k.add(Next);

        nametextfield2 = new fields(20, 10, 10);
        nametextfield2.setBounds(395, 150, 200, 25);
        k.add(nametextfield2);

        usernametextfield2 = new fields(20, 10, 10);
        usernametextfield2.setBounds(395, 200, 200, 25);
        k.add(usernametextfield2);


        email_textfield2 = new fields(20, 10, 10);
        email_textfield2.setBounds(395, 300, 200, 25);
        k.add(email_textfield2);


        e_pass_textfield = new JPasswordField();
        e_pass_textfield.setBounds(395, 360, 200, 25);
        e_pass_textfield.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        k.add(e_pass_textfield);

        c_pass_textfield = new JPasswordField();
        c_pass_textfield.setBounds(395, 420, 200, 25);
        c_pass_textfield.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        k.add(c_pass_textfield);

        String[] City = {"Null", "Karachi", "Lahore", "Islamabad"};
        city_textfield = new JComboBox<>(City);
        city_textfield.setBounds(395, 480, 200, 25);

        k.add(city_textfield);

        Sign = new JButton("Back");
        Sign.setBounds(380, 700, 150, 25);
        Sign.setUI(new buttons()); // Apply the custom UI
        Sign.addActionListener(this);
        k.add(Sign);


        getContentPane().setBackground(Color.WHITE);

    }

    public void actionPerformed(ActionEvent ae) {

        try {



            String name2 = nametextfield2.getText();
            String dob = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
            String email = email_textfield2.getText();
            String e_pass = e_pass_textfield.getText();
            String c_pass = c_pass_textfield.getText();

            String city = (String) city_textfield.getSelectedItem();
            String username= usernametextfield2.getText();


            if (ae.getSource() == Sign) {
                setVisible(false);
                new Start().setVisible(true);
            } if (ae.getSource() == Next) {
                if (name2.equals("")||username.equals("") || dob.equals("") || email.equals("") || e_pass.equals("") || c_pass.equals("") ||  city.equals("")) {
                    JOptionPane.showMessageDialog(null, "Fill All Fields");
                } else {
                    Conn c1 = new Conn();
                    String q1 = "insert into sign_up2 values('" + name2 + "','" + username + "','" + dob + "','" + email + "','" + e_pass + "','" + c_pass + "','" + "12345" + "','" + city + "')";
                    c1.s.executeUpdate(q1);
                    setVisible(false);
                    new user_c(" ").setVisible(true);
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new Admin_signup();
    }
}
