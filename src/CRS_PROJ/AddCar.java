package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;

public class AddCar extends JFrame implements ActionListener {
 JButton  Back,Add;
 fields  model_textfield2, price_textfield2,nametextfield2,avg_textfield2;
 String owner;
    AddCar(String Owner) {
        this.owner=Owner;
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


        JLabel name = new JLabel("Car: ");
        name.setBounds(210, 150, 150, 25);
        name.setFont(new Font("Osward", Font.BOLD, 20));
        name.setForeground(Color.WHITE);
        k.add(name);

        JLabel model = new JLabel("Model: ");
        model.setBounds(210, 200, 150, 25);
        model.setFont(new Font("Osward", Font.BOLD, 20));
        model.setForeground(Color.WHITE);
        k.add(model);

        JLabel price = new JLabel("Price: ");
        price.setBounds(210, 250, 150, 25);
        price.setFont(new Font("Osward", Font.BOLD, 20));
        price.setForeground(Color.WHITE);
        k.add(price);


        JLabel average = new JLabel("Average: ");
        average.setBounds(210, 300, 150, 25);
        average.setFont(new Font("Osward", Font.BOLD, 20));
        average.setForeground(Color.WHITE);
        k.add(average);


        nametextfield2 = new fields(20, 10, 10);
        nametextfield2.setBounds(305, 150, 200, 25);
        k.add(nametextfield2);

        model_textfield2 = new fields(20, 10, 10);
        model_textfield2.setBounds(305, 200, 200, 25);
        k.add(model_textfield2);


        price_textfield2 = new fields(20, 10, 10);
        price_textfield2.setBounds(305, 250, 200, 25);
        k.add(price_textfield2);

        avg_textfield2 = new fields(20, 10, 10);
        avg_textfield2.setBounds(305, 300, 200, 25);
        k.add(avg_textfield2);

        Add = new JButton("Add Car");
        Add.setBounds(210, 350, 300, 25);
        Add.setUI(new buttons()); // Apply the custom UI
        Add.addActionListener(this);
        k.add(Add);

        Back = new JButton("Back");
        Back.setBounds(210, 700, 150, 25);
        Back.setUI(new buttons()); // Apply the custom UI
        Back.addActionListener(this);
        k.add(Back);


        getContentPane().setBackground(Color.WHITE);

    }

    public void actionPerformed(ActionEvent ae) {

        try {
            String name2 = nametextfield2.getText();
            String model= model_textfield2.getText();
            String price =price_textfield2.getText();
            String avg=avg_textfield2.getText();
            if (ae.getSource() == Back) {
                setVisible(false);
                new Admin2(owner).setVisible(true);
            } if (ae.getSource() == Add) {
                if (name2.equals("")||model.equals("") || price.equals("") || avg.equals("") ) {
                    JOptionPane.showMessageDialog(null, "Fill All Fields");
                } else {
                    Conn c1 = new Conn();
                    String q1 = "INSERT INTO cars (name, model, price, average, Owner) VALUES ('" + name2 + "','" + model + "','" + price + " Rs','" + avg + " Km/L','" + owner + "')";
                    c1.s.executeUpdate(q1);
                    System.out.println(owner);
                    JOptionPane.showMessageDialog(null,"Car List Updated");
                    nametextfield2.setText("");
                    model_textfield2.setText("");
                    price_textfield2.setText("");
                    avg_textfield2.setText("");

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        new AddCar("");
    }
}
