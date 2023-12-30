package CRS_PROJ;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class buttons extends BasicButtonUI {
    private static final int arcSize = 15; // Adjust the arc size as needed

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        AbstractButton button = (AbstractButton) c;
        button.setOpaque(false);
        button.setBorderPainted(false);
        button.setBackground(Color.DARK_GRAY); // Set button background color
        button.setForeground(Color.WHITE); // Set button text color
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        ButtonModel model = button.getModel();

        if (model.isPressed()) {
            g.setColor(Color.LIGHT_GRAY); // Change the color when the button is pressed
        } else {
            g.setColor(Color.DARK_GRAY);
        }

        int width = button.getWidth();
        int height = button.getHeight();

        // Create a rounded shape for the button
        Shape shape = new RoundRectangle2D.Double(0, 0, width, height, arcSize, arcSize);

        ((Graphics2D) g).fill(shape);

        super.paint(g, c);
    }
}
