package CRS_PROJ;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Change_Car_Status extends JFrame implements ActionListener {
    JButton back, markResolved;
    String car, owner,status;
    JTable table;
    DefaultTableModel model;

    Change_Car_Status(String owner) {
        this.owner = owner;
        setTitle("Receipt Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the window size to 800 x 600 pixels
        setSize(1000, 800);

        // Use a border layout for the content pane
        setLayout(new BorderLayout());

        // Create a label for the title and set the font and alignment
        JLabel titleLabel = new JLabel("Change Status");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add the label to the north region of the content pane
        add(titleLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        table = new JTable(model);
        table.setGridColor(Color.black);

        model.setColumnIdentifiers(new Object[]{"Username", "Name", "Model", "Price", "Average", "Date1", "Date2", "Status"});
        showTable();



        // Create a scroll pane for the table and set the preferred size
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(950, 700));

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

        markResolved = new JButton("Mark as Available");
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

    private void markCarAvailable() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            car = (String) model.getValueAt(selectedRow, 1);
            model.setValueAt("Available", selectedRow, 7);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a car to mark as Available.");
        }
    }
    private void showTable(){
        try {
            Conn c1 = new Conn();
            ResultSet resultSet = c1.s.executeQuery("SELECT * FROM reciept WHERE Owner = '" + owner + "'");


            while (resultSet.next() ) {
                Object[] rowData = {
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("model"),
                        resultSet.getString("price"),
                        resultSet.getString("average"),
                        resultSet.getString("Date1"),
                        resultSet.getString("Date2"),
                        getStatus(resultSet.getString("name"))

             };
                model.addRow(rowData);


            }

            resultSet.close();
            } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStatus(String carName){
        try{
            Conn c1 =new Conn();
            ResultSet resultSet2 = c1.s.executeQuery("SELECT * FROM cars WHERE name = '" + carName + "'");
            if (resultSet2.next() ) {
                status =resultSet2.getString("status");
            }
            resultSet2.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return  status;
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == back) {
                setVisible(false);
                new Admin2(owner).setVisible(true); // Show the customer 2 frame with the username
            } else if (ae.getSource() == markResolved) { // If the markResolved button is clicked
                markCarAvailable(); // Call the method to mark the car as available
                try {
                    Conn c1 = new Conn();
                    String q = "UPDATE cars SET status = 'Available' WHERE name = '" + car + "'";
                    c1.s.executeUpdate(q);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error updating car status: " + ex.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Change_Car_Status("");
    }
}
