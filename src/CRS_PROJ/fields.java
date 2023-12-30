package CRS_PROJ;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class fields extends JTextField {

    private int arcWidth;
    private int arcHeight;

    public fields(int columns, int arcWidth, int arcHeight) {
        super(columns);
        this.arcWidth = arcWidth;
        this.arcHeight = arcHeight;
        setOpaque(true); // Make the text field transparent
        setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5)); // Add padding
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isOpaque()) {
            super.paintComponent(g);
            return;
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Shape border = new RoundRectangle2D.Double(0, 0, getWidth() - 1, getHeight() - 1, arcWidth, arcHeight);
        g2.setClip(border);

        super.paintComponent(g);

        g2.setClip(null);
        g2.setStroke(new BasicStroke(1));
        g2.setColor(getBackground());
        g2.draw(border);
    }
}

