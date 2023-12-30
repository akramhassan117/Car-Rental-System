package CRS_PROJ;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Customer2 extends JFrame implements ActionListener, MouseListener {
    JLabel k, k2, k3, k4, k5;
    JButton addb, orderb, compb, Sign, compb2;
    String username, owner;

    Customer2() {
    }

    public Customer2(String username, String owner) {
        this.username = username;
        this.owner = owner;

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

        compb = new JButton("+ COMPLAINTS");
        compb.setFont(new Font("Osward", Font.BOLD, 15));
        compb.setBounds(260, 330, 150, 50);
        compb.setUI(new buttons());
        compb.addActionListener(this);
        k.add(compb);

        ImageIcon comp4 = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/complaint.jpg"));
        Image comp5 = comp4.getImage().getScaledInstance(150, 150, 1);
        ImageIcon comp6 = new ImageIcon(comp2);
        k3 = new JLabel(comp6);
        k3.setBounds(960, 155, 150, 150);
        k3.addMouseListener(this);
        k.add(k3);

        compb2 = new JButton("YOUR COMPLAINTS");
        compb2.setFont(new Font("Osward", Font.BOLD, 15));
        compb2.setBounds(960, 335, 170, 50);
        compb2.setUI(new buttons());
        compb2.addActionListener(this);
        k.add(compb2);

        // Your Orders
        ImageIcon orders = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/orders.jpg"));
        Image order2 = orders.getImage().getScaledInstance(160, 160, 1);
        ImageIcon order3 = new ImageIcon(order2);
        k4 = new JLabel(order3);
        k4.setBounds(740, 155, 150, 150);
        k4.addMouseListener(this);
        k.add(k4);

        orderb = new JButton("YOUR ORDERS");
        orderb.setFont(new Font("Osward", Font.BOLD, 15));
        orderb.setBounds(740, 335, 150, 50);
        orderb.setUI(new buttons());
        orderb.addActionListener(this);
        k.add(orderb);

        // Add Order
        ImageIcon add = new ImageIcon(ClassLoader.getSystemResource("CRS_PROJ/addorder.png"));
        Image add2 = add.getImage().getScaledInstance(150, 155, 1);
        ImageIcon add3 = new ImageIcon(add2);
        k5 = new JLabel(add3);
        k5.setBounds(500, 150, 160, 160);
        k5.addMouseListener(this);
        k.add(k5);

        addb = new JButton("+ NEW ORDER");
        addb.setFont(new Font("Osward", Font.BOLD, 15));
        addb.setBounds(500, 335, 150, 50);
        addb.setUI(new buttons());
        addb.addActionListener(this);
        k.add(addb);

        Sign = new JButton("SIGN OUT");
        Sign.setBounds(1450, 680, 180, 50);// Adjust the position and size
        Sign.setUI(new buttons()); // Apply the custom UI
        Sign.addActionListener(this);
        k.add(Sign);

        getContentPane().setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == compb) {
                setVisible(false);
                new complaints(username, owner).setVisible(true);
            }

            if (ae.getSource() == orderb) {
                setVisible(false);
                new yourOrders(username, owner).setVisible(true);
            }

            if (ae.getSource() == addb) {
                setVisible(false);
                new addOrder(username).setVisible(true);
            }
            if (ae.getSource() == Sign) {
                setVisible(false);
                new Start().setVisible(true);
            }
            if (ae.getSource() == compb2) {
                setVisible(false);
                new MyComplaints(username,owner).setVisible(true);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == k2) {
            setVisible(false);
            new complaints(username, owner).setVisible(true);
        }

        if (e.getSource() == k4) {
            setVisible(false);
            new yourOrders(username, owner).setVisible(true);
        }

        if (e.getSource() == k5) {
            setVisible(false);
            new addOrder(username).setVisible(true);
        }
        if (e.getSource() == k3) {
            setVisible(false);
            new MyComplaints(username, owner).setVisible(true);
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

    public static void main(String[] args) {
        Customer2 c1 = new Customer2();
        String username = c1.username;
        String owner = c1.owner;
        new Customer2(username, owner);
    }
}
