package pdk.chart.data;

import pdk.chart.data.general.DatasetUtils;
import pdk.chart.data.xy.XYZDataset;
import pdk.chart.util.Args;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * Utility methods for use with some of the data classes (but not the datasets,
 * see {@link DatasetUtils}).
 */
public abstract class DataUtils {

    private DataUtils() {}

    /**
     * Tests two arrays for equality.  To be considered equal, the arrays must
     * have exactly the same dimensions, and the values in each array must also
     * match (two values that qre both NaN or both INF are considered equal
     * in this test).
     *
     * @param a the first array ({@code null} permitted).
     * @param b the second array ({@code null} permitted).
     * @return A boolean.
     */
    public static boolean equal(double[][] a, double[][] b) {
        if (a == null) {
            return (b == null);
        }
        if (b == null) {
            return false;  // already know 'a' isn't null
        }
        if (a.length != b.length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            if (!Arrays.equals(a[i], b[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a clone of the specified array.
     *
     * @param source the source array ({@code null} not permitted).
     * @return A clone of the array.
     */
    public static double[][] clone(double[][] source) {
        Args.nullNotPermitted(source, "source");
        double[][] clone = new double[source.length][];
        for (int i = 0; i < source.length; i++) {
            if (source[i] != null) {
                double[] row = new double[source[i].length];
                System.arraycopy(source[i], 0, row, 0, source[i].length);
                clone[i] = row;
            }
        }
        return clone;
    }

    /**
     * Returns the total of the values in one column of the supplied data
     * table.
     *
     * @param data   the table of values ({@code null} not permitted).
     * @param column the column index (zero-based).
     * @return The total of the values in the specified column.
     */
    public static double calculateColumnTotal(Values2D data, int column) {
        Args.nullNotPermitted(data, "data");
        double total = 0.0;
        int rowCount = data.getRowCount();
        for (int r = 0; r < rowCount; r++) {
            Number n = data.getValue(r, column);
            if (n != null) {
                total += n.doubleValue();
            }
        }
        return total;
    }

    /**
     * Returns the total of the values in one column of the supplied data
     * table by taking only the row numbers in the array into account.
     *
     * @param data      the table of values ({@code null} not permitted).
     * @param column    the column index (zero-based).
     * @param validRows the array with valid rows (zero-based).
     * @return The total of the valid values in the specified column.
     */
    public static double calculateColumnTotal(Values2D data, int column,
            int[] validRows) {
        Args.nullNotPermitted(data, "data");
        double total = 0.0;
        int rowCount = data.getRowCount();
        for (int row : validRows) {
            if (row < rowCount) {
                Number n = data.getValue(row, column);
                if (n != null) {
                    total += n.doubleValue();
                }
            }
        }
        return total;
    }

    /**
     * Returns the total of the values in one row of the supplied data
     * table.
     *
     * @param data the table of values ({@code null} not permitted).
     * @param row  the row index (zero-based).
     * @return The total of the values in the specified row.
     */
    public static double calculateRowTotal(Values2D data, int row) {
        Args.nullNotPermitted(data, "data");
        double total = 0.0;
        int columnCount = data.getColumnCount();
        for (int c = 0; c < columnCount; c++) {
            Number n = data.getValue(row, c);
            if (n != null) {
                total += n.doubleValue();
            }
        }
        return total;
    }

    /**
     * Returns the total of the values in one row of the supplied data
     * table by taking only the column numbers in the array into account.
     *
     * @param data      the table of values ({@code null} not permitted).
     * @param row       the row index (zero-based).
     * @param validCols the array with valid cols (zero-based).
     * @return The total of the valid values in the specified row.
     */
    public static double calculateRowTotal(Values2D data, int row,
            int[] validCols) {
        Args.nullNotPermitted(data, "data");
        double total = 0.0;
        int colCount = data.getColumnCount();
        for (int col : validCols) {
            if (col < colCount) {
                Number n = data.getValue(row, col);
                if (n != null) {
                    total += n.doubleValue();
                }
            }
        }
        return total;
    }

    /**
     * Constructs an array of {@code Number} objects from an array of
     * {@code double} primitives.
     *
     * @param data the data ({@code null} not permitted).
     * @return An array of {@code double}.
     */
    public static Number[] createNumberArray(double[] data) {
        requireNonNull(data, "data");
        Number[] result = new Number[data.length];
        for (int i = 0; i < data.length; i++) {
            result[i] = data[i];
        }
        return result;
    }

    /**
     * Constructs an array of arrays of {@code Number} objects from a
     * corresponding structure containing {@code double} primitives.
     *
     * @param data the data ({@code null} not permitted).
     * @return An array of {@code double}.
     */
    public static Number[][] createNumberArray2D(double[][] data) {
        requireNonNull(data, "data");
        int l1 = data.length;
        Number[][] result = new Number[l1][];
        for (int i = 0; i < l1; i++) {
            result[i] = createNumberArray(data[i]);
        }
        return result;
    }

    /**
     * Returns a {@link KeyedValues} instance that contains the cumulative
     * percentage values for the data in another {@link KeyedValues} instance.
     * <p>
     * The percentages are values between 0.0 and 1.0 (where 1.0 = 100%).
     *
     * @param <K>  the key type.
     * @param data the data ({@code null} not permitted).
     * @return The cumulative percentages.
     */
    public static <K extends Comparable<K>> KeyedValues<K> getCumulativePercentages(KeyedValues<K> data) {
        requireNonNull(data, "data");
        DefaultKeyedValues<K> result = new DefaultKeyedValues<>();
        double total = 0.0;
        for (int i = 0; i < data.getItemCount(); i++) {
            Number v = data.getValue(i);
            if (v != null) {
                total = total + v.doubleValue();
            }
        }
        double runningTotal = 0.0;
        for (int i = 0; i < data.getItemCount(); i++) {
            Number v = data.getValue(i);
            if (v != null) {
                runningTotal = runningTotal + v.doubleValue();
            }
            result.addValue(data.getKey(i), runningTotal / total);
        }
        return result;
    }

    public static double getMinZ(XYZDataset dataset) {
        double min = Double.MAX_VALUE;
        for (int s = 0; s < dataset.getSeriesCount(); s++) {
            for (int i = 0; i < dataset.getItemCount(s); i++) {
                double zValue = dataset.getZValue(s, i);
                if (zValue < min) {
                    min = zValue;
                }
            }
        }
        return min;
    }

    public static double getMaxZ(XYZDataset dataset) {
        double max = -Double.MAX_VALUE;
        for (int s = 0; s < dataset.getSeriesCount(); s++) {
            for (int i = 0; i < dataset.getItemCount(s); i++) {
                double zValue = dataset.getZValue(s, i);
                if (zValue > max) {
                    max = zValue;
                }
            }
        }
        return max;
    }

    public static Range getZRange(XYZDataset dataset) {
        double min = Double.POSITIVE_INFINITY;
        double max = Double.NEGATIVE_INFINITY;
        for (int s = 0; s < dataset.getSeriesCount(); s++) {
            for (int i = 0; i < dataset.getItemCount(s); i++) {
                double zValue = dataset.getZValue(s, i);
                if (zValue < min) {
                    min = zValue;
                }
                if (zValue > max) {
                    max = zValue;
                }
            }
        }
        return new Range(min, max);
    }

    public static double getMin(Double[] values) {
        double min = Double.MAX_VALUE;
        for (Double value : values) {
            min = Math.min(value, min);
        }
        return min;
    }

    public static double getMax(Double[] values) {
        double max = -Double.MAX_VALUE;
        for (Double value : values) {
            max = Math.max(value, max);
        }
        return max;
    }

    public static double getRange(Double[] values) {
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        for (Double value : values) {
            max = Math.max(value, max);
            min = Math.min(value, min);
        }
        return max - min;
    }
}
