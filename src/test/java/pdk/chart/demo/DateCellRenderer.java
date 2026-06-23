package pdk.chart.demo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.text.DateFormat;

public class DateCellRenderer extends DefaultTableCellRenderer {

    private DateFormat formatter;

    public DateCellRenderer() {
        this(DateFormat.getDateTimeInstance());
    }

    public DateCellRenderer(DateFormat formatter) {
        this.formatter = formatter;
        this.setHorizontalAlignment(0);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        this.setFont((Font) null);
        if (value != null) {
            this.setText(this.formatter.format(value));
        } else {
            this.setText("");
        }

        if (isSelected) {
            this.setBackground(table.getSelectionBackground());
        } else {
            this.setBackground((Color) null);
        }

        return this;
    }
}
