package CRS_PROJ;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class yourOrders extends JFrame implements ActionListener {
    String username,owner;
    JButton back; // Declare a button to go back to customer 2
    public yourOrders() {
    }

    public yourOrders(String name,String owner) {
        this.username = name;
        this.owner=owner;
        setTitle("Receipt Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size to 800 x 600 pixels
        setSize(800, 600);

        // Use a border layout for the content pane
        setLayout(new BorderLayout());

        // Create a label for the title and set the font and alignment
        JLabel titleLabel = new JLabel("Your Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the north region of the content pane
        add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel();

        JTable table = new JTable(model);
        table.setGridColor(Color.black);

        model.setColumnIdentifiers(new Object[]{"Name", "Model", "Price", "Average", "Date1", "Date2"});

        try {
            Conn c1 = new Conn();
            ResultSet resultSet = c1.s.executeQuery("SELECT * FROM reciept WHERE username ='" + username + "'");

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("name"),
                        resultSet.getString("model"),
                        resultSet.getString("price"),
                        resultSet.getString("average"),
                        resultSet.getString("Date1"),
                        resultSet.getString("Date2")
                };
                model.addRow(rowData);

            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Create a scroll pane for the table and set the preferred size
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(750, 500));

        // Add the scroll pane to the center region of the content pane
        add(scrollPane, BorderLayout.CENTER);

        // Create a panel for the button and set the layout and background color
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Create the button to go back to customer 2 and set the custom UI and action listener
        back = new JButton("Back");
        back.setUI(new buttons());
        back.addActionListener(this);

        // Add the button to the panel
        buttonPanel.add(back);

        // Add the panel to the south region of the content pane
        add(buttonPanel, BorderLayout.SOUTH);

        // Pack the frame and center it on the screen
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == back) { // If the back button is clicked
                setVisible(false); // Hide the current frame
                new Customer2(username,owner).setVisible(true);
                System.out.println(owner+"\n");// Show the customer 2 frame with the username
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new yourOrders("test",""); // Pass a test username to the constructor
    }
}
