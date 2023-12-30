package CRS_PROJ;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Admin_Complaints extends JFrame implements ActionListener {
    JButton back, markResolved;
    String Owner,username;

    DefaultTableModel model;
    JTable table;

    public Admin_Complaints(String Owner) {
        this.Owner=Owner;

        setTitle("Complaint Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size to 800 x 600 pixels
        setSize(800, 600);

        // Use a border layout for the content pane
        setLayout(new BorderLayout());

        // Create a label for the title and set the font and alignment
        JLabel titleLabel = new JLabel("All Complaints");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the north region of the content pane
        add(titleLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setGridColor(Color.black);

        model.setColumnIdentifiers(new Object[]{"Username", "Complaint", "Status"});

        try {
            Conn c1 = new Conn();
            ResultSet resultSet = c1.s.executeQuery("SELECT * FROM comp WHERE owner = '" + Owner + "'");

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("username"),
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

        // Create a panel for the buttons and set the layout and background color
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        // Create the button to go back to admin 2 and set the custom UI and action listener
        back = new JButton("Back");
        back.setUI(new buttons());
        back.addActionListener(this);

        // Create the button to mark a complaint as resolved
        markResolved = new JButton("Mark Resolved");
        markResolved.setUI(new buttons());
        markResolved.addActionListener(this);

        // Add the buttons to the panel
        buttonPanel.add(back);
        buttonPanel.add(markResolved);

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
                new Admin2(Owner).setVisible(true); // Show the admin 2 frame
            } else if (ae.getSource() == markResolved) { // If the markResolved button is clicked
                markComplaintResolved();
                Conn c1 = new Conn();
                String q = "UPDATE comp SET status = 'resolved' WHERE username = '" + username + "'";
                c1.s.executeUpdate(q);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void markComplaintResolved() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
             username = (String) model.getValueAt(selectedRow, 0);
            model.setValueAt("resolved", selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a complaint to mark as resolved.");
        }
    }

    public static void main(String[] args) {
        new Admin_Complaints("");
    }
}