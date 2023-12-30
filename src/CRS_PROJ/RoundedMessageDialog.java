//package bankmanagementsystem;
//
//import javax.swing.*;
//
//public class RoundedMessageDialog extends JDialog {
//
//    public RoundedMessageDialog(String message) {
//        super(null, "Message", true);
//        setLayout(new BorderLayout());
//        setSize(300, 100);
//
//        // Create the rounded border
//        int radius = 20;
//        Color color = new Color(100, 100, 100);
//        Border border = BorderFactory.createRoundedBorder(radius, color);
//
//        // Add the message label
//        JLabel messageLabel = new JLabel(message);
//        messageLabel.setBorder(border);
//        add(messageLabel, BorderLayout.CENTER);
//
//        // Add the OK button
//        JButton okButton = new JButton("OK");
//        okButton.addActionListener(e -> dispose());
//        add(okButton, BorderLayout.SOUTH);
//
//        setVisible(true);
//    }
//}
