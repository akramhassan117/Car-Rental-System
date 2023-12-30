package CRS_PROJ;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class addOrder extends JFrame {
    String username, owner,owner2;
    JTextField complaintTextField;
    String newComplaint,name,model,price,avg,model2;
    public addOrder(String username) {
        this.username = username;
        this.owner = owner;
        setTitle("New Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("NEW ORDERS");
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
        table.setDefaultRenderer(Object.class, new SleekTableCellRenderer());

        model.setColumnIdentifiers(new Object[]{"Car", "Model","Price","Average","Owner","Status"});
        try {
            Conn c1 = new Conn();
            ResultSet resultSet = c1.s.executeQuery("SELECT * FROM cars WHERE status = 'Available'");
            table.getColumnModel().getColumn(0).setPreferredWidth(150); // Car
            table.getColumnModel().getColumn(1).setPreferredWidth(150); // Model
            table.getColumnModel().getColumn(2).setPreferredWidth(100); // Price
            table.getColumnModel().getColumn(3).setPreferredWidth(100); // Average
            table.getColumnModel().getColumn(4).setPreferredWidth(150); // Owner
            table.getColumnModel().getColumn(5).setPreferredWidth(100);
            int rowHeight = 30;
            table.setRowHeight(rowHeight);

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("name"),
                        resultSet.getString("model"),
                        resultSet.getString("price"),
                        resultSet.getString("average"),
                        resultSet.getString("Owner"),
                        resultSet.getString("status")
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

        JButton submitButton = new JButton("Select Car");
        submitButton.setUI(new buttons());
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                        Conn c1 = new Conn();
                        String q = "UPDATE cars SET status = 'Not Available' WHERE name = '" + name + "'";
                        c1.s.executeUpdate(q);
                        setVisible(false);


                } catch (Exception re) {
                    re.printStackTrace();
                }
                new Choose_date(name,avg,price,model2,username,owner2);

            }
        });

        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        // Save the name of the owner of the selected row
                        owner2 = (String) table.getValueAt(selectedRow, 4);
                        name = (String) table.getValueAt(selectedRow, 0);
                        model2 = (String) table.getValueAt(selectedRow, 1);
                        price = (String) table.getValueAt(selectedRow, 2);
                        avg = (String) table.getValueAt(selectedRow, 3);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please select a Car to Order.", "No Selection", JOptionPane.WARNING_MESSAGE);
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
       backButton .setUI(new buttons());
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
        new addOrder("");
    }
}
