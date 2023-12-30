package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class user_c extends JFrame implements ActionListener {
    JRadioButton radioButton5, radioButton6, radioButton7, radioButton8;
    JComboBox<String> religiontextfield;
    JComboBox<String> Categorytextfield;
    JComboBox<String> Incometextfield;
    JComboBox<String> Educationtextfield;
    JComboBox<String> Occupationtextfield;
    JTextField CNICtextfield;
    ButtonGroup gendergroup3, gendergroup4;
    String formno;
    JButton Next;

    user_c(String formno) {

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

        JLabel user = new JLabel("USER CREATED");
        user.setFont(new Font("Osward", Font.BOLD, 30));
        user.setBounds(210, 140, 400, 30);
        user.setForeground(Color.WHITE);
        k.add(user);

        Next = new JButton("HOME");
        Next.setBounds(210, 700, 150, 25); // Adjust the position and size
        Next.setUI(new buttons());
        Next.addActionListener(this);
        k.add(Next);


        getContentPane().setBackground(Color.WHITE);

    }

    public void actionPerformed(ActionEvent ae) {

   if(ae.getSource()==Next){
       setVisible(false);
       new Start().setVisible(true);
   }
    }


    public static void main(String[] args) {
        new user_c("");
    }


}
