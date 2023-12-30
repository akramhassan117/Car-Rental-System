package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Arrays;

public class Admin2 extends JFrame implements ActionListener, MouseListener {
    JLabel k, k2, k3, k4, k5;
    JButton addb, orderb, compb, Sign, Change;
    String owner;
    Admin2(){}
    Admin2(String owner){

        this.owner=owner;

        setSize(1500, 700); // Set the size
        setVisible(true);
        setLocation(20, 0); // Set the location
        setLayout(null); // Use null layout to manually position components
        Dimension content_size = getContentPane().getSize();

        // Load and display an image as an icon
        ImageIcon iconn = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/sign_up.jpg"));
        Image iconn2 = iconn.getImage().getScaledInstance(content_size.width, content_size.height, Image.SCALE_DEFAULT);
        ImageIcon iconn3 = new ImageIcon(iconn2);
        k = new JLabel(iconn3);
        k.setBounds(-170, -100, 1820, 860);
        add(k);

        // Complaints
        ImageIcon comp = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/complaint.jpg"));
        Image comp2 = comp.getImage().getScaledInstance(150, 150, 1);
        ImageIcon comp3 = new ImageIcon(comp2);
        k2 = new JLabel(comp3);
        k2.setBounds(260, 150, 150, 150);
        k2.addMouseListener(this);
        k.add(k2);

        compb = new JButton(" COMPLAINTS");
        compb.setFont(new Font("Osward", Font.BOLD, 15));
        compb.setBounds(260, 330, 150, 50);
        compb.setUI(new buttons());
        compb.addActionListener(this);
        k.add(compb);

        // Your Orders
        ImageIcon orders = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/orders.jpg"));
        Image order2 = orders.getImage().getScaledInstance(160, 160, 1);
        ImageIcon order3 = new ImageIcon(order2);
        k4 = new JLabel(order3);
        k4.setBounds(740, 155, 150, 150);
        k4.addMouseListener(this);
        k.add(k4);

        orderb = new JButton("ORDERS");
        orderb.setFont(new Font("Osward", Font.BOLD, 15));
        orderb.setBounds(740, 335, 150, 50);
        orderb.setUI(new buttons());
        orderb.addActionListener(this);
        k.add(orderb);

        Sign = new JButton("SIGN OUT");
        Sign.setBounds(1450, 680, 180, 50); // Adjust the position and size
        Sign.setUI(new buttons()); // Apply the custom UI
        Sign.addActionListener(this);
        k.add(Sign);

        ImageIcon add = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/addorder.png"));
        Image add2 = add.getImage().getScaledInstance(150, 155, 1);
        ImageIcon add3 = new ImageIcon(add2);
        k5 = new JLabel(add3);
        k5.setBounds(500, 150, 160, 160);
        k5.addMouseListener(this);
        k.add(k5);

        addb = new JButton("+ NEW CAR");
        addb.setFont(new Font("Osward", Font.BOLD, 15));
        addb.setBounds(500, 335, 150, 50);
        addb.setUI(new buttons());
        addb.addActionListener(this);
        k.add(addb);

        Change= new JButton("UPDATE STATUS");
        Change.setFont(new Font("Osward", Font.BOLD, 15));
        Change.setBounds(900, 335, 150, 50);
        Change.setUI(new buttons());
        Change.addActionListener(this);
        k.add(Change);

        getContentPane().setBackground(Color.WHITE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compb) {
            setVisible(false);
            new Admin_Complaints(owner).setVisible(true);
            System.out.println(owner);
        }
        if (e.getSource() == addb) {
            setVisible(false);
            new AddCar(owner).setVisible(true);
            System.out.println(owner);
        }

        if (e.getSource() == orderb) {
            setVisible(false);
            new Admin_Orders(owner).setVisible(true);
            System.out.println(owner);
        }
        if (e.getSource() == Sign) {
            setVisible(false);
            System.out.println(owner);
            new Start().setVisible(true);
        }if (e.getSource() == Change) {
            setVisible(false);
            System.out.println(owner);
            new Change_Car_Status(owner).setVisible(true);
        }
    }

    public static void main(String[] args) {
        Admin2 c1= new Admin2();
        String owner=c1.owner;
        new Admin2(owner);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == k2) {
            setVisible(false);
            new Admin_Complaints(owner).setVisible(true);
            System.out.println(owner);
        }

        if (e.getSource() == k4) {
            setVisible(false);
            new Admin_Orders(owner).setVisible(true);
            System.out.println(owner);
        }
        if (e.getSource() == k5) {
            setVisible(false);
            new AddCar(owner).setVisible(true);
            System.out.println(owner);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
