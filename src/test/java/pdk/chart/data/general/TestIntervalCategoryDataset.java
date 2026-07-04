package pdk.chart.data.general;

import pdk.chart.api.PublicCloneable;
import pdk.chart.data.KeyedObjects2D;
import pdk.chart.data.UnknownKeyException;
import pdk.chart.data.category.IntervalCategoryDataset;
import pdk.chart.util.CloneUtils;

import java.io.Serializable;
import java.util.List;

/**
 * A test implementation of the {@link IntervalCategoryDataset} interface.
 */
public class TestIntervalCategoryDataset<R extends Comparable<R>,
        C extends Comparable<C>> extends AbstractDataset
        implements IntervalCategoryDataset<R, C>, PublicCloneable, Serializable {

    /**
     * For serialization.
     */
    private static final long serialVersionUID = -8168173757291644622L;

    /**
     * A storage structure for the data.
     */
    private KeyedObjects2D<R, C> data;

    /**
     * Creates a new (empty) dataset.
     */
    public TestIntervalCategoryDataset() {
        this.data = new KeyedObjects2D<>();
    }

    /**
     * Returns the number of rows in the table.
     *
     * @return The row count.
     * @see #getColumnCount()
     */
    @Override
    public int getRowCount() {
        return this.data.getRowCount();
    }

    /**
     * Returns the number of columns in the table.
     *
     * @return The column count.
     * @see #getRowCount()
     */
    @Override
    public int getColumnCount() {
        return this.data.getColumnCount();
    }

    /**
     * Returns a value from the table.
     *
     * @param row    the row index (zero-based).
     * @param column the column index (zero-based).
     * @return The value (possibly {@code null}).
     */
    @Override
    public Number getValue(int row, int column) {
        IntervalDataItem item = (IntervalDataItem) this.data.getObject(row,
                column);
        if (item == null) {
            return null;
        }
        return item.getValue();
    }

    /**
     * Returns the key for the specified row.
     *
     * @param row the row index (zero-based).
     * @return The row key.
     * @see #getRowIndex(Comparable)
     * @see #getRowKeys()
     * @see #getColumnKey(int)
     */
    @Override
    public R getRowKey(int row) {
        return this.data.getRowKey(row);
    }

    /**
     * Returns the row index for a given key.
     *
     * @param key the row key ({@code null} not permitted).
     * @return The row index.
     * @see #getRowKey(int)
     */
    @Override
    public int getRowIndex(R key) {
        // defer null argument check
        return this.data.getRowIndex(key);
    }

    /**
     * Returns the row keys.
     *
     * @return The keys.
     * @see #getRowKey(int)
     */
    @Override
    public List<R> getRowKeys() {
        return this.data.getRowKeys();
    }

    /**
     * Returns a column key.
     *
     * @param column the column index (zero-based).
     * @return The column key.
     * @see #getColumnIndex(Comparable)
     */
    @Override
    public C getColumnKey(int column) {
        return this.data.getColumnKey(column);
    }

    /**
     * Returns the column index for a given key.
     *
     * @param key the column key ({@code null} not permitted).
     * @return The column index.
     * @see #getColumnKey(int)
     */
    @Override
    public int getColumnIndex(C key) {
        // defer null argument check
        return this.data.getColumnIndex(key);
    }

    /**
     * Returns the column keys.
     *
     * @return The keys.
     * @see #getColumnKey(int)
     */
    @Override
    public List<C> getColumnKeys() {
        return this.data.getColumnKeys();
    }

    /**
     * Returns the value for a pair of keys.
     *
     * @param rowKey    the row key ({@code null} not permitted).
     * @param columnKey the column key ({@code null} not permitted).
     * @return The value (possibly {@code null}).
     * @throws UnknownKeyException if either key is not defined in the dataset.
     * @see #addValue(Number, Comparable, Comparable)
     */
    @Override
    public Number getValue(R rowKey, C columnKey) {
        IntervalDataItem item = (IntervalDataItem) this.data.getObject(rowKey,
                columnKey);
        if (item == null) {
            return null;
        }
        return item.getValue();
    }

    /**
     * Adds a value to the table.  Performs the same function as setValue().
     *
     * @param value     the value.
     * @param rowKey    the row key.
     * @param columnKey the column key.
     * @see #getValue(Comparable, Comparable)
     * @see #removeValue(Comparable, Comparable)
     */
    public void addItem(Number value, Number lower, Number upper,
            R rowKey, C columnKey) {
        IntervalDataItem item = new IntervalDataItem(value, lower, upper);
        this.data.addObject(item, rowKey, columnKey);
        fireDatasetChanged();
    }

    /**
     * Adds a value to the table.
     *
     * @param value     the value.
     * @param rowKey    the row key.
     * @param columnKey the column key.
     * @see #getValue(Comparable, Comparable)
     */
    public void addItem(double value, double lower, double upper,
            R rowKey, C columnKey) {
        addItem(Double.valueOf(value), Double.valueOf(lower), Double.valueOf(upper),
                rowKey, columnKey);
    }

    /**
     * Adds or updates a value in the table and sends a
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param value     the value ({@code null} permitted).
     * @param rowKey    the row key ({@code null} not permitted).
     * @param columnKey the column key ({@code null} not permitted).
     * @see #getValue(Comparable, Comparable)
     */
    public void setItem(Number value, Number lower, Number upper,
            R rowKey, C columnKey) {
        IntervalDataItem item = new IntervalDataItem(value, lower, upper);
        this.data.addObject(item, rowKey, columnKey);
        fireDatasetChanged();
    }

    /**
     * Adds or updates a value in the table and sends a
     * {@link DatasetChangeEvent} to all registered listeners.
     *
     * @param value     the value.
     * @param rowKey    the row key ({@code null} not permitted).
     * @param columnKey the column key ({@code null} not permitted).
     * @see #getValue(Comparable, Comparable)
     */
    public void setItem(double value, double lower, double upper,
            R rowKey, C columnKey) {
        setItem(Double.valueOf(value), Double.valueOf(lower), Double.valueOf(upper),
                rowKey, columnKey);
    }

    /**
     * Removes a value from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey    the row key.
     * @param columnKey the column key.
     * @see #addValue(Number, Comparable, Comparable)
     */
    public void removeItem(R rowKey, C columnKey) {
        this.data.removeObject(rowKey, columnKey);
        fireDatasetChanged();
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowIndex the row index.
     * @see #removeColumn(int)
     */
    public void removeRow(int rowIndex) {
        this.data.removeRow(rowIndex);
        fireDatasetChanged();
    }

    /**
     * Removes a row from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param rowKey the row key.
     * @see #removeColumn(Comparable)
     */
    public void removeRow(R rowKey) {
        this.data.removeRow(rowKey);
        fireDatasetChanged();
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnIndex the column index.
     * @see #removeRow(int)
     */
    public void removeColumn(int columnIndex) {
        this.data.removeColumn(columnIndex);
        fireDatasetChanged();
    }

    /**
     * Removes a column from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     *
     * @param columnKey the column key ({@code null} not permitted).
     * @throws UnknownKeyException if {@code columnKey} is not defined
     *                             in the dataset.
     * @see #removeRow(Comparable)
     */
    public void removeColumn(C columnKey) {
        this.data.removeColumn(columnKey);
        fireDatasetChanged();
    }

    /**
     * Clears all data from the dataset and sends a {@link DatasetChangeEvent}
     * to all registered listeners.
     */
    public void clear() {
        this.data.clear();
        fireDatasetChanged();
    }

    /**
     * Tests this dataset for equality with an arbitrary object.
     *
     * @param obj the object ({@code null} permitted).
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof TestIntervalCategoryDataset)) {
            return false;
        }
        TestIntervalCategoryDataset<R, C> that = (TestIntervalCategoryDataset) obj;
        if (!getRowKeys().equals(that.getRowKeys())) {
            return false;
        }
        if (!getColumnKeys().equals(that.getColumnKeys())) {
            return false;
        }
        int rowCount = getRowCount();
        int colCount = getColumnCount();
        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                Number v1 = getValue(r, c);
                Number v2 = that.getValue(r, c);
                if (v1 == null) {
                    if (v2 != null) {
                        return false;
                    }
                } else if (!v1.equals(v2)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a hash code for the dataset.
     *
     * @return A hash code.
     */
    @Override
    public int hashCode() {
        return this.data.hashCode();
    }

    /**
     * Returns a clone of the dataset.
     *
     * @return A clone.
     * @throws CloneNotSupportedException if there is a problem cloning the
     *                                    dataset.
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        TestIntervalCategoryDataset<R, C> clone = (TestIntervalCategoryDataset)
                super.clone();
        clone.data = CloneUtils.clone(this.data);
        return clone;
    }

    @Override
    public Number getStartValue(int series, int category) {
        IntervalDataItem item = (IntervalDataItem) this.data.getObject(series,
                category);
        if (item == null) {
            return null;
        }
        return item.getLowerBound();
    }

    @Override
    public Number getStartValue(R series, C category) {
        IntervalDataItem item = (IntervalDataItem) this.data.getObject(series,
                category);
        if (item == null) {
            return null;
        }
        return item.getLowerBound();
    }

    @Override
    public Number getEndValue(int series, int category) {
        IntervalDataItem item = (IntervalDataItem) this.data.getObject(series,
                category);
        if (item == null) {
            return null;
        }
        return item.getUpperBound();
    }

    @Override
    public Number getEndValue(R series, C category) {
        IntervalDataItem item = (IntervalDataItem) this.data.getObject(series,
                category);
        if (item == null) {
            return null;
        }
        return item.getUpperBound();
    }

}

