package CRS_PROJ;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class complaints extends JFrame {
String username, owner,owner2;
    JTextField complaintTextField;
    String newComplaint;
public complaints(String username, String owner) {
        this.username = username;
        this.owner = owner;
    setTitle("Complaints");
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(1000, 800);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Complaints");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Allow only the "Complaint" column to be editable
                return column == 4;
            }
        };

        JTable table = new JTable(model);
        table.setGridColor(Color.black);

        model.setColumnIdentifiers(new Object[]{"Car", "Owner", "From", "To"});
        try {
            Conn c1 = new Conn();
            ResultSet resultSet = c1.s.executeQuery("SELECT * FROM reciept WHERE username = '" + username + "'");

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("name"),
                        resultSet.getString("Owner"),
                        resultSet.getString("Date1"),
                        resultSet.getString("Date2")
                };
                model.addRow(rowData);
            }

            resultSet.close();

        } catch (Exception e) {
            e.printStackTrace();
        }



        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(900, 700));
        add(scrollPane, BorderLayout.CENTER);

        // Add a JTextField for entering complaints
        complaintTextField = new JTextField(30);

        // Add KeyListener to detect "Enter" key press


        add(complaintTextField, BorderLayout.WEST);

        // Add a "Submit Complaint" button
        JButton submitButton = new JButton("Submit Complaint");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the complaint text from the JTextField
                String newComplaint = complaintTextField.getText();
                try {
                    Conn c1 = new Conn();
                    String q1 = "INSERT INTO comp (username, complaint, owner) VALUES ('" + username + "', '" + newComplaint + "', '" + owner2 + "')";
                    c1.s.executeUpdate(q1);
                } catch (Exception re) {
                    re.printStackTrace();
                }


                // Perform actions with the new complaint (e.g., update the database)
                System.out.println("Submitted Complaint: " + newComplaint);

                // Clear the text field after submission
                complaintTextField.setText("");

                // Move the cursor up
                complaintTextField.transferFocusBackward();
            }
        });

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Save the name of the owner of the selected row
                        owner2 = (String) table.getValueAt(selectedRow, 1);


                    }
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Customer2(username, owner).setVisible(true);
            }
        });

        buttonPanel.add(backButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new complaints("", "");
    }
}
