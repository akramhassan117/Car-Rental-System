package CRS_PROJ;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class MyComplaints extends JFrame {
String username,owner;
public MyComplaints(String username,String owner) {
        this.username = username;
        this.owner=owner;
        setTitle("My Complaints");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size to 800 x 600 pixels
        setSize(800, 600);

        // Use a border layout for the content pane
        setLayout(new BorderLayout());

        // Create a label for the title and set the font and alignment
        JLabel titleLabel = new JLabel("My Complaints");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the north region of the content pane
        add(titleLabel, BorderLayout.NORTH);

        // Create a table model
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setGridColor(Color.black);

        // Set column headers
        model.setColumnIdentifiers(new Object[]{"Complaint", "Status"});

        try {
            Conn c1 = new Conn();
            ResultSet resultSet = c1.s.executeQuery("SELECT * FROM comp WHERE username = '" + username + "'");

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("complaint"),
                        resultSet.getString("status")
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

        // Create a "Back" button and add an ActionListener to handle the event
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle the "Back" button click event
                setVisible(false); // Hide the current frame
                // Replace "YourPreviousFrameClass" with the actual class for your previous frame
                new Customer2(username,owner).setVisible(true);
            }
        });

        // Create a panel for the buttons and set the layout and background color
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Add the "Back" button to the panel
        buttonPanel.add(backButton);

        // Add the panel to the south region of the content pane
        add(buttonPanel, BorderLayout.SOUTH);

        // Pack the frame and center it on the screen
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        // You can test the MyComplaints class by providing a username
        new MyComplaints("testUser","");
    }
}
