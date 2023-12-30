package CRS_PROJ;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Admin_Orders extends JFrame implements ActionListener {
    JButton back;
    String owner;
    DefaultTableModel model;
    Conn c1;

    public Admin_Orders(String owner) {
        this.owner = owner;
        setTitle("Receipt Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("All Orders");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        model = new DefaultTableModel();
        JTable table = new JTable(model);
        table.setGridColor(Color.black);

        model.setColumnIdentifiers(new Object[]{"Username", "Name", "Model", "Price", "Average", "Date1", "Date2"});

        populateTable(); // Populate the table with data

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(950, 700));
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.WHITE);

        back = new JButton("Back");
        back.setUI(new buttons());
        back.addActionListener(this);
        buttonPanel.add(back);

        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void populateTable() {
        try {
            c1 = new Conn();
            String query = "SELECT * FROM reciept WHERE Owner = '"+owner+"'";

            ResultSet resultSet = c1.s.executeQuery(query);

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("username"),
                        resultSet.getString("name"),
                        resultSet.getString("model"),
                        resultSet.getString("price"),
                        resultSet.getString("average"),
                        resultSet.getString("Date1"),
                        resultSet.getString("Date2")
                };
                model.addRow(rowData);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception gracefully, log it, or show a user-friendly message
        }
        }


    public void actionPerformed(ActionEvent ae) {
        try {
            if (ae.getSource() == back) {
                setVisible(false);
                new Admin2(owner).setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Admin_Orders("");
    }
}
