package CRS_PROJ;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SleekTableCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(
            JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        Component cell = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);

        // Customize cell appearance
        cell.setBackground(isSelected ? new Color(51, 153, 255) : Color.white);
        cell.setForeground(Color.black);

        return cell;
    }
}
