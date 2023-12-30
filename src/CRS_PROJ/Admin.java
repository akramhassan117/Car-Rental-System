package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Objects;

public class Admin extends JFrame implements ActionListener {
    JButton Sign,Sign2;
    String formno;
    JTextField nametextfield;
    JPasswordField c_pass_textfield;
    String name;
    String name2;
    Admin() {

        setSize(1500, 700); // Set the size
        setVisible(true);
        setLocation(20, 0); // Set the location
        setLayout(null); // Use null layout to manually position components
        Dimension content_size = getContentPane().getSize();

        // Load and display an image as an icon
        ImageIcon iconn = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/sign_up.jpg"));
        Image iconn2 = iconn.getImage().getScaledInstance(content_size.width, content_size.height, Image.SCALE_DEFAULT);
        ImageIcon iconn3 = new ImageIcon(iconn2);
        JLabel k = new JLabel(iconn3);
        k.setBounds(-170, -100, 1820, 860);
        add(k);

        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/propic.jpg"));
        Image icon2 = icon.getImage().getScaledInstance(100, 100, 1);
        ImageIcon icon3 = new ImageIcon(icon2);
        JLabel k2 = new JLabel(icon3);
        k2.setBounds(210, 145, 66, 66);
        k.add(k2);
        this.formno = formno;

        JLabel title = new JLabel("SIGN IN: ");
        title.setBounds(300, 165, 150, 25);
        title.setFont(new Font("Osward", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        k.add(title);

        JLabel name = new JLabel("Username: ");
        name.setBounds(210, 245, 150, 25);
        name.setFont(new Font("Osward", Font.BOLD, 22));
        name.setForeground(Color.WHITE);
        k.add(name);

        nametextfield = new JTextField();
        nametextfield.setBounds(335, 245, 200, 25);
        nametextfield.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        k.add(nametextfield);

        JLabel pass = new JLabel("Password: ");
        pass.setBounds(210, 305, 150, 25);
        pass.setFont(new Font("Osward", Font.BOLD, 22));
        pass.setForeground(Color.WHITE);
        k.add(pass);

        c_pass_textfield = new JPasswordField();
        c_pass_textfield.setBounds(335, 305, 200, 25);
        c_pass_textfield.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        k.add(c_pass_textfield);

        Sign = new JButton("SIGN IN");
        Sign.setBounds(210, 370, 325, 25); // Adjust the position and size
        Sign.setUI(new buttons()); // Apply the custom UI
        Sign.addActionListener(this);
        k.add(Sign);

        Sign2 = new JButton("Back");
        Sign2.setBounds(210, 410, 325, 25); // Adjust the position and size
        Sign2.setUI(new buttons()); // Apply the custom UI
        Sign2.addActionListener(this);
        k.add(Sign2);

        getContentPane().setBackground(Color.WHITE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            name = nametextfield.getText();
            String pass = c_pass_textfield.getText();
            if (e.getSource() == Sign2) {
                setVisible(false);
                new Start().setVisible(true);
            }
             if (e.getSource() == Sign) {
                Conn c1 = new Conn();

                 String q = "select * from admin_table where admin = '" + name + "' and pass = '" + pass + "'";
                 ResultSet rs = c1.s.executeQuery(q);
                if (rs.next()) {
                    setVisible(false);
                    new Admin2(name).setVisible(true);
                    System.out.println(name);
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
                }
            }

        } catch (Exception ae) {
            ae.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Admin();
    }
}

