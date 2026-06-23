package pdk.chart.data;

/**
 * A general purpose interface that can be used to access a table of values.
 */
public interface Values2D {

    /**
     * Returns the number of rows in the table.
     *
     * @return The row count.
     */
    int getRowCount();

    /**
     * Returns the number of columns in the table.
     *
     * @return The column count.
     */
    int getColumnCount();

    /**
     * Returns a value from the table.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The value (possibly {@code null}).
     * @throws IndexOutOfBoundsException if the {@code row}
     *                                   or {@code column} is out of bounds.
     */
    Number getValue(int row, int column);

}
